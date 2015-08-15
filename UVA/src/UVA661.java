public class UVA661 {
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		int seq = 1;
		while (io.hasMoreTokens()) {
			int n = io.getInt();
			int m = io.getInt();
			int c = io.getInt();
			
			// don't process this case
			if (n==0 && m==0 && c==0) {
				continue;
			} else {
				io.println("Sequence " + seq++);
			}
			
			int[] c_i = new int[n];
			int[] states = new int[n];
			
			// fill in capacities
			for (int i=0; i<n; i++) {
				c_i[i] = io.getInt();
			}
			
			int totalCapacityUsed = 0;
			int maxCapacity = totalCapacityUsed;
			boolean fuseBlown = false;
			
			for (int i=0; i<m; i++) {
				int dev = io.getInt();
				int capacity = c_i[dev-1];
				
				if (states[dev-1] == 0) {
					states[dev-1] = 1;
					totalCapacityUsed += capacity;
				} else {
					states[dev-1] = 0;
					totalCapacityUsed -= capacity;
				}
				
				if (totalCapacityUsed > maxCapacity) {
					maxCapacity = totalCapacityUsed;
				}
				
				if (totalCapacityUsed > c) {
					if (!fuseBlown) {
						io.println("Fuse was blown.");
						io.println();
					}
					fuseBlown = true;
					continue;
				}
			}
			
			if (fuseBlown) {
				continue;
			} else {
				io.println("Fuse was not blown.");
				io.println("Maximal power consumption was " + maxCapacity + " amperes.");
				io.println();
			}
		}
		io.close();
	}
}
