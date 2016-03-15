package collatz;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (int i = 1; i < 7000; i++) {
			CollatzCalculator calc = new CollatzCalculator(5);
			System.out.println(calc.CollatzChain(i).toString());
		}
	}

}
