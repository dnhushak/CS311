package hw6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MyDijkstra<V, E> implements Dijkstra<V, E> {
	private Graph<V, E> graph;
	private HashMap<Integer, Integer> predecessors;
	double distance[];
	private Weighing<E> weighing;
	private int start;

	public MyDijkstra() {
		predecessors = new HashMap<Integer, Integer>();
	}

	@Override
	public void setGraph(Graph<V, E> graph) {
		this.graph = graph;

	}

	@Override
	public void setStart(int startId) throws IllegalArgumentException,
			IllegalStateException {
		start = startId;
		try {
			graph.getData(start);
		} catch (NullPointerException e) {
			throw new IllegalStateException("Graph has not been set");
		}
		predecessors.put(start, null);
	}

	@Override
	public void setWeighing(Weighing<E> weighing) {
		this.weighing = weighing;
	}

	@Override
	public void computeShortestPath() throws IllegalStateException {
		// Vertices not yet visited
		HashSet<Integer> outside = new HashSet<Integer>();
		for(Integer v : graph.getVertices()){
			outside.add(v);
		}
		// The distances from the start node to each node
		distance = new double[outside.size()];
		double shortest;
		int closest;

		// Set the distance to everything to roughly infinity
		for (int k : outside) {
			distance[k] = Double.MAX_VALUE;
		}
		// And the distance to the start to 0
		distance[start] = 0;

		while (!outside.isEmpty()) {
			// Find the next closest node
			shortest = Double.MAX_VALUE;
			closest = -1;
			// For each vertex in distance...
			for (Integer v : outside) {
				if (distance[v] < shortest) {
					// If it has a lower distance than what we've found, set our
					// shortest and closest accordingly
					shortest = distance[v];
					closest = v;
				}
			}
			if (closest == -1) {
				// List of unvisited is not empty, but have all infinite distances
				// This means we have a disconnected graph. Stop.
				throw new IllegalStateException("Graph is not connected");
			}

			// Remove this node from the set
			outside.remove(closest);
			
			for (int edge : graph.getEdgesOf(closest)) {
				// Calculate the distance through each edge from start through
				// the current node
				double distanceThruCurrent = weighing.weight(graph
						.getAttribute(edge)) + distance[closest];
				// If this distance is less than a distance we have already found, 
				if (distanceThruCurrent < distance[graph.getTarget(edge)]) {
					// Then set this distance as the new distance to the node
					distance[graph.getTarget(edge)] = distanceThruCurrent;
					// And set the current node as this new node's predecessor
					predecessors.put(graph.getTarget(edge), closest);
				}
			}
		}
	}

	@Override
	public List<Integer> getPath(int endId) throws IllegalArgumentException,
			IllegalStateException {
		LinkedList<Integer> path = new LinkedList<Integer>();

		// Start with the ending ID
		int current = endId;
		// While we haven't gone back to the start...
		do {
			// ... Add the current id to the beginning of the path list
			path.addFirst(current);
			// And set current to its predecessor
			current = predecessors.get(current);
		} while (current != start);
		return path;
	}

	@Override
	public double getCost(int endId) throws IllegalArgumentException,
			IllegalStateException {
		return distance[endId];
	}

}
