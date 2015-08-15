public class UVA912 {
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		while (io.hasMoreTokens()) {
			int len = io.getInt();
			String[] seq1 = new String[len];
			String[] seq2 = new String[len];
			
			for (int i=0; i < len; i++) {
				String x = io.getWord();
				seq1[i] = x;
			}
			
			for (int i=0; i < len; i++) {
				String x = io.getWord();
				seq2[i] = x;
			}
		}
		io.close();
	}
}
