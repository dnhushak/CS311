package hw6;

public class MyWeighing implements Weighing<Road> {

	@Override
	public double weight(Road edge) {
		return edge.getDistance();
	}

}
