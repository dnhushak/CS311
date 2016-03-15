package hw6;

public class Road {
	// Data class for edges
	private String name;
	private double dist;

	Road(double dist) {
		name = "";
		this.dist = dist;
	}

	Road(double dist, String name) {
		this.dist = dist;
		this.name = name;
	}

	@Override
	public String toString() {
		if (name.equals("")) {
			return ((Double) dist).toString();
		} else {
			return name + " " + dist;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public double getDistance(){
		return dist;
	}
}
