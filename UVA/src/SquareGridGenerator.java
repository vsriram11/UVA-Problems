import java.util.Random;


public class SquareGridGenerator {

	public static void main(String[] args) {
		for (int i=0; i < Integer.parseInt(args[0]); i++) {
			Random rd = new Random();
			int dim = rd.nextInt(26);
		
			generateGrid(dim);
		}
	}
	
	public static void generateGrid(int d) {
		System.out.println(d);
		for (int i=0; i<d; i++) {
			boolean notLastEle = (i != d-1);
			for (int j=0; j<d; j++) {
				Random rd = new Random();
				int bin = rd.nextInt(2);
				if (notLastEle)
					System.out.print(bin);
				else {
					if (j != d-1)
						System.out.print(bin);
					else
						System.out.println(bin);
				}
			}
			if (i != d-1)
				System.out.println();
		}
	}

}
