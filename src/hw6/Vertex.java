package hw6;

import java.util.HashSet;
import java.util.Set;

public class Vertex<V> {
	private HashSet<Integer> outboundEdges;
	private HashSet<Integer> inboundEdges;
	private V value;

	Vertex(V value) {
		this.value = value;
		outboundEdges = new HashSet<Integer>();
		inboundEdges = new HashSet<Integer>();
	}

	public V getValue() {
		return value;
	}

	public Set<Integer> getOutboundEdges() {
		return outboundEdges;
	}

	public Set<Integer> getInboundEdges() {
		return inboundEdges;
	}

	public void addOutboundEdge(int edgeID) {
		outboundEdges.add(edgeID);
	}

	public void addInboundEdge(int edgeID) {
		inboundEdges.add(edgeID);
	}
	
	@Override
	public String toString(){
		return "[" + value.toString() + "]";
	}

}
