package hw6;

import java.util.Set;

/**
 * Implementation of the supplied Graph<V,E> interface. Every method completes
 * in O(1) time. Memory isn't that bad either, as it uses 4 hashmaps in total (2
 * for each two-way hashmap), plus 2 hashSets per vertex each containing a
 * reference to an edge ID, so it's still O(|V| * |E|) space.
 * 
 * Note that toString is not O(1) time, but is generally used for debug and not
 * time crucial traversal
 * 
 * @author dnhushak
 * 
 * @param <V>
 *            Vertex data type
 * @param <E>
 *            Edge data type
 */
public class MyGraph<V, E> implements Graph<V, E> {
	// A map of vertices to their indices (I'm using a two way hashmap for O(1)
	// lookup in either direction of index <-> value)
	private TwoWayHashMap<Vertex<V>, Integer> vertexMap;
	private TwoWayHashMap<Edge<E>, Integer> edgeMap;

	MyGraph() {
		vertexMap = new TwoWayHashMap<Vertex<V>, Integer>();
		edgeMap = new TwoWayHashMap<Edge<E>, Integer>();
	}

	@Override
	public int addVertex(V v) {
		// Create a new vertex
		Vertex<V> newVertex = new Vertex<V>(v);

		// Add it to the map with index equal to the number of vertices
		vertexMap.put(newVertex, vertexMap.size());

		// Return the vertex's ID
		return vertexMap.getObjByKey(newVertex);
	}

	@Override
	public int addEdge(int srcID, int targetID, E attr)
			throws IllegalArgumentException {
		// Create a new edge
		Edge<E> newEdge = new Edge<E>(srcID, targetID, attr);

		// Add it to the map with index equal to the number of edges
		edgeMap.put(newEdge, edgeMap.size());

		// Add it to the list of outbound edges in the source vertex
		vertexMap.getKeyByObj(srcID).addOutboundEdge(
				edgeMap.getObjByKey(newEdge));

		// Add it to the list of inbound edges in the destination vertex
		vertexMap.getKeyByObj(targetID).addInboundEdge(
				edgeMap.getObjByKey(newEdge));

		// Return the edge's ID
		return edgeMap.getObjByKey(newEdge);
	}

	@Override
	public Set<Integer> getVertices() {
		// Get all vertices from the vertex map
		return vertexMap.values();
	}

	@Override
	public Set<Integer> getEdges() {
		// Get all edges from the edge map
		return edgeMap.values();
	}

	@Override
	public E getAttribute(int id) throws IllegalArgumentException {
		// Get the key (type Edge<E>) from the id, then get said edge's
		// attribute
		return edgeMap.getKeyByObj(id).getAttr();
	}

	@Override
	public V getData(int id) throws IllegalArgumentException {
		// Get the key (type Vertex<V>) from the id, then get said vertex's
		// value
		return vertexMap.getKeyByObj(id).getValue();
	}

	@Override
	public int getSource(int id) throws IllegalArgumentException {
		// Get the key (type Edge<E>) from the id, then get said edge's source
		return edgeMap.getKeyByObj(id).getSource();
	}

	@Override
	public int getTarget(int id) throws IllegalArgumentException {
		// Get the key (type Edge<E>) from the id, then get said edge's target
		return edgeMap.getKeyByObj(id).getTarget();
	}

	@Override
	public Set<Integer> getEdgesOf(int id) throws IllegalArgumentException {
		// Get the key (type Vertex<V>) from the id, and get it's outbound edge
		// set
		return vertexMap.getKeyByObj(id).getOutboundEdges();
	}

	public Set<Integer> getEdgesTo(int id) throws IllegalArgumentException {
		// Get the key (type Vertex<V>) from the id, and get it's inbound edge
		// set
		return vertexMap.getKeyByObj(id).getInboundEdges();
	}

	@Override
	public String toString() {
		String out = "";
		int edgeCounter = 0;
		Edge<E> currentEdge;
		Vertex<V> fromVertex;
		Vertex<V> toVertex;

		for (int i = 0; i < edgeMap.size(); i++) {
			currentEdge = edgeMap.getKeyByObj(i);
			fromVertex = vertexMap.getKeyByObj(currentEdge.getSource());
			toVertex = vertexMap.getKeyByObj(currentEdge.getTarget());
			out += fromVertex.toString() + currentEdge.toString() + toVertex.toString() + "  ";
			edgeCounter++;
			if (edgeCounter == 4) {
				out += "\r";
				edgeCounter = 0;
			}
		}
		return out;
	}

}
