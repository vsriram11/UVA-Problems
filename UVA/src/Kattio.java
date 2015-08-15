import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}
	
	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() { 
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}
	
	// use only if reading line by line
	public String getLine() {
		return nextLine();
	}

	public boolean isNewLine() {
		return enteredNextLine;
	}
	
	public boolean isLastWord() {
		return enteredLastWord;
	}

	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;
	private boolean enteredNextLine;
	private boolean enteredLastWord = false;

	private String peekToken() {
		if (token == null) {
			if (st == null) {
				enteredNextLine = false;
			} else if (!st.hasMoreTokens()) {
				enteredNextLine = true;
			} else {
				enteredNextLine = false;
			}
			
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null) return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
				
				if (!st.hasMoreTokens()) {
					enteredLastWord = true;
				} else {
					enteredLastWord = false;
				}
			} catch (IOException e) { }
		}
		
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
	
	private String nextLine() {
		try {
			line = r.readLine();
		} catch (IOException e) { }
		return line;
	}
}