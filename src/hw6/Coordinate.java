package hw6;

// Data type for vertices
public class Coordinate {
	private double x, y;

	Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("%.2f,%.2f", x, y);
	}
}
