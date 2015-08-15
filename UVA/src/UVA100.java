public class UVA100 {	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		while (io.hasMoreTokens()) {
			int d = io.getInt();
			int j = io.getInt();
			int md = d;
			int mj = j;
			if (d > j) {
				md = j;
				mj = d;
			}
			int max=1;
			
			for (int x=md; x<=mj; x++) {
				int big = x;
				int i=1;
				
				while (big > 1) {
					if (big % 2 == 0)
						big = big/2;
					else
						big = big * 3 + 1;
					i++;
				}
				if (i > max)
					max = i;
			}
			io.println(String.format("%d %d %d", d, j, max));
		}
		io.close();
	}
}
