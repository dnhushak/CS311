package hw6;

public class Edge<E>{
	private int srcID;
	private int targetID;
	private E attr;

	Edge(int srcID, int targetID, E attr) {
		this.srcID = srcID;
		this.targetID = targetID;
		this.attr = attr;
	}

	public int getSource() {
		return srcID;
	}

	public int getTarget() {
		return targetID;
	}

	public E getAttr() {
		return attr;
	}
	
	@Override
	public String toString(){
		return "-" + attr.toString() + "->";
	}
}
