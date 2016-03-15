package hw6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.sun.j3d.loaders.IncorrectFormatException;

public class JimAlgorithm<V, E> implements CoffeeSolver<V, E> {

	public static void main(String[] args) {
		// Create Jim's poor poor intern who has to run all these menial
		// errands, with a graph type of Integer, Integer
		// The first Integer is the location ID of the ingredient (vertex)
		// The second is just a binary edge representation (0 if no edge, 1 if
		// edge)
		JimAlgorithm<Integer, Integer> intern = new JimAlgorithm<Integer, Integer>();
		JimAlgorithm<Coordinate, Road> internsCar = new JimAlgorithm<Coordinate, Road>();

		// Parse the first file of ingredients list
		// Format is:
		// INGREDIENTS: <numIngredients>
		// 0, <vertexLocationID>
		// 1, <vertexLocationID>
		// ...n, <vertexLocationID>
		FileReader ingredientsFile = getFile(args[0]);
		FileReader amesFile = getFile(args[1]);
		MyGraph<Integer, Integer> ingredientList = BuildIngredients(ingredientsFile);
		MyGraph<Coordinate, Road> ames = BuildAmes(amesFile);
		MyWeighing weigh = new MyWeighing();

		List<Integer> order = intern.sortVertices(ingredientList);
		ArrayList<Integer> orderIds = new ArrayList<Integer>();
		for (Integer id : order) {
			orderIds.add(ingredientList.getData(id));
		}
		System.out.println("Order of ingredients (0 is starting, 1 is A, 2 is B and so on...):");
		System.out.println(order);
		System.out.println("The same order, except using location ID's:");
		System.out.println(orderIds);
		System.out.println("The shortest path through ames to get all of these ingredients and return them to Jim (by location ID):");
		System.out.println(internsCar.shortestPath(ames, orderIds, weigh));

	}

	@Override
	public List<Integer> sortVertices(Graph<V, E> graph) {
		Set<Integer> vertices = graph.getVertices();
		Set<Integer> edges = graph.getEdges();
		Stack<Integer> noIncoming = new Stack<Integer>();
		List<Integer> sorted = new ArrayList<Integer>();
		int incoming[] = new int[vertices.size()];

		// For each edge O(|E|)...
		for (int j = 0; j < edges.size(); j++) {
			// ...Increment the incoming for that edges to vertex
			incoming[graph.getTarget(j)]++;
		}

		// For each vertex O(|V|)...
		for (int j = 0; j < vertices.size(); j++) {
			// ... If there are no incoming edges
			if (incoming[j] == 0) {
				// Add it to the stack
				noIncoming.push(j);
				// Since this adds every node with no incoming edges, this will
				// account for disconnected graph components, while still being
				// O(|V| + |E|)
			}
		}

		int current, next;
		Iterator<Integer> iter;
		// While we're not sorted... O(|V|)
		while (!noIncoming.isEmpty()) {
			// Get the current vertex with no incoming edges from the stack
			current = noIncoming.pop();
			// Point the iterator at the beginning of this set
			iter = graph.getEdgesOf(current).iterator();
			// Though this is a nested loop, this only hits every edge once, so
			// in total it's O(|E|), not O(|E|*|V|)
			while (iter.hasNext()) {
				next = graph.getTarget(iter.next());
				// For each outbound edge of this vertex, decrement the incoming
				// edge count for that edge's target vertex
				if (--incoming[next] == 0) {
					// If this vertex now has no incoming edges, add it to the
					// stack
					noIncoming.push(next);
				}
			}
			sorted.add(current);
		}
		return sorted;
	}

	@Override
	public List<Integer> shortestPath(Graph<V, E> graph,
			List<Integer> locations, Weighing<E> weigh) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		MyDijkstra<V, E> dijkstra = new MyDijkstra<V, E>();
		dijkstra.setGraph(graph);
		dijkstra.setWeighing(weigh);

		// Add the first location to the final path
		path.add(locations.get(0));
		// For each location after that (except the final one)
		for (int i = 0; i < locations.size() - 1; i++) {
			// Start at whichever location we are at
			dijkstra.setStart(locations.get(i));
			// Compute the shortest path to every node
			dijkstra.computeShortestPath();
			// Get the path to the end node (which is the next in line of the
			// locations
			path.addAll(dijkstra.getPath(locations.get(i + 1)));
		}
		return path;
	}

	@Override
	public Collection<List<Integer>> generateValidSortS(Graph<V, E> graph) {
		ArrayList<List<Integer>> sorts = new ArrayList<List<Integer>>();
		return sorts;
	}

	public static MyGraph<Integer, Integer> BuildIngredients(
			FileReader inputFile) {
		MyGraph<Integer, Integer> ingredientList = new MyGraph<Integer, Integer>();
		Scanner sc = new Scanner(inputFile);
		String line;
		String[] sArr;
		int numIngredients = 0;
		int numDependencies = 0;
		line = sc.nextLine();
		sArr = line.split("\\s+");
		if (sArr[0].equals("VERTICES:")) {
			try {
				numIngredients = Integer.parseInt(sArr[1]);
			} catch (IndexOutOfBoundsException e) {
				throw new IncorrectFormatException(
						"Bad input file format. First line must be 'VERTICES: <numOfIngredients>' exactly");
			}
		}
		for (int i = 0; i < numIngredients; i++) {
			try {
				line = sc.nextLine(); // read the next line into a String object
			} catch (NoSuchElementException e) {
				throw new IncorrectFormatException(
						"Bad input file format. Not enough vertices listed");
			}
			sArr = line.split(","); // split the string by commas
			// Add the vertex to the ingredient list, using its location ID
			ingredientList.addVertex(Integer.parseInt(sArr[1]));
		}
		line = sc.nextLine();
		sArr = line.split("\\s+");
		if (sArr[0].equals("EDGES:")) {
			try {
				numDependencies = Integer.parseInt(sArr[1]);
			} catch (IndexOutOfBoundsException e) {
				throw new IncorrectFormatException(
						"Bad input file format. First line of edge list must be 'EDGES: <numOfIngredients>' exactly");
			}
		}
		for (int i = 0; i < numDependencies; i++) {
			try {
				line = sc.nextLine(); // read the next line into a String object
			} catch (NoSuchElementException e) {
				throw new IncorrectFormatException(
						"Bad input file format. Not enough edges listed");
			}
			sArr = line.split(","); // split the string by commas
			// Add the vertex to the ingredient list, using its location ID
			ingredientList.addEdge(Integer.parseInt(sArr[0]),
					Integer.parseInt(sArr[1]), 1);
		}
		return ingredientList;
	}

	public static MyGraph<Coordinate, Road> BuildAmes(FileReader inputFile) {
		MyGraph<Coordinate, Road> ames = new MyGraph<Coordinate, Road>();
		Scanner sc = new Scanner(inputFile);
		String line;
		String[] sArr;
		int numIntersections = 0;
		int numRoads = 0;
		line = sc.nextLine();
		sArr = line.split("\\s+");
		if (sArr[0].equals("VERTICES:")) {
			try {
				numIntersections = Integer.parseInt(sArr[1]);
			} catch (IndexOutOfBoundsException e) {
				throw new IncorrectFormatException(
						"Bad input file format. First line must be 'VERTICES: <numOfIngredients>' exactly");
			}
		}
		for (int i = 0; i < numIntersections; i++) {
			try {
				line = sc.nextLine(); // read the next line into a String object
			} catch (NoSuchElementException e) {
				throw new IncorrectFormatException(
						"Bad input file format. Not enough vertices listed");
			}
			sArr = line.split(","); // split the string by commas
			Coordinate coords = new Coordinate(Double.parseDouble(sArr[1]),
					Double.parseDouble(sArr[2]));
			// Add the vertex to the ingredient list, using its location ID
			ames.addVertex(coords);
		}
		line = sc.nextLine();
		sArr = line.split("\\s+");
		if (sArr[0].equals("EDGES:")) {
			try {
				numRoads = Integer.parseInt(sArr[1]);
			} catch (IndexOutOfBoundsException e) {
				throw new IncorrectFormatException(
						"Bad input file format. First line of edge list must be 'EDGES: <numOfIngredients>' exactly");
			}
		}
		for (int i = 0; i < numRoads; i++) {
			try {
				line = sc.nextLine(); // read the next line into a String object
			} catch (NoSuchElementException e) {
				throw new IncorrectFormatException(
						"Bad input file format. Not enough edges listed");
			}
			sArr = line.split(","); // split the string by commas
			// Add the vertex to the ingredient list, using its location ID
			Road newRoad;
			if (sArr.length == 3) {
				// No road name
				newRoad = new Road(Double.parseDouble(sArr[2]));
			} else if (sArr.length == 4) {
				// No road name
				newRoad = new Road(Double.parseDouble(sArr[2]), sArr[3]);
			} else {
				throw new IncorrectFormatException(
						"Bad input file format. Road has too few or too many arguments");
			}
			// Add the road in both directions in the graph
			ames.addEdge(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]),
					newRoad);
			ames.addEdge(Integer.parseInt(sArr[1]), Integer.parseInt(sArr[0]),
					newRoad);
		}
		return ames;
	}

	public static FileReader getFile(String filePath) {
		FileReader inputFile = null;
		try {
			// Get input file from args
			inputFile = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// If file not found, print the directory that the user should place
			// the file in, then terminate
			String current;
			try {
				current = new java.io.File(".").getCanonicalPath();
				System.out.println("Place file in this directory:" + current);
			} catch (IOException e1) {
			}
			System.out.println("Terminating the program.");
			System.exit(-1);
		}
		return inputFile;
	}

}
