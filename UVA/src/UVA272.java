public class UVA272 {
	
	public static boolean useLeft = true;
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		while (io.hasMoreTokens()) {
			String d = io.getWord();
			
			// detects that this word is the first word on a new line
			if (io.isNewLine())
				io.println();
			
			d = replaceNthOccurence(d);
			
			if (io.isLastWord()) {
				io.print(d);
			} else {
				io.print(d + " ");
			}
		}
		io.println();
		io.close();
	}
	
	public static String replaceNthOccurence(String d) {
		char[] dch = d.toCharArray();
		
		// determine number of quotes
		int noQuotes=0;
		for (int i=0; i < dch.length; i++) {
			if (dch[i] == '"') {
				noQuotes++;
			}
		}
		
		// allocate new char array
		char[] new_dch = new char[dch.length + noQuotes];
		int j=0;
		
		for (int i=0; i < dch.length; i++) {
			if (dch[i] == '"') {
				if (useLeft) {
					new_dch[j] = '`';
					new_dch[j+1] = '`';
				} else {
					new_dch[j] = '\'';
					new_dch[j+1] = '\'';
				}
				useLeft = !useLeft;
				j += 2;
			} else {
				new_dch[j] = dch[i]; 
				j++;
			}
		}
		
		return new String(new_dch);
	}
}
