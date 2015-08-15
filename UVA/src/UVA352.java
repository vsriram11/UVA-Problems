import java.util.*;

public class UVA352 {
	
	public static List<List<Integer>> ograph;
	public static int[] ovisited;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int blockNo = 1;
		while (io.hasMoreTokens()) {
			int row = io.getInt();
			
			int[][] grid = new int[row][row];
			
			if (!((row == 0) || (row == 1))) {
				// fill in grid
				for (int i=0; i < row; i++) {
					String s = io.getWord();
					char[] sch = s.toCharArray();
					for (int j=0; j < row; j++) {
						int x = (int)(sch[j]) - 48;
						grid[i][j] = x;
					}
				}
			
				// pass grid to core function
				int ans = computeAnswer(grid);
			
				io.println("Image Number " + 
			            blockNo + " contains " +
					    ans + " war eagles.");
				blockNo++;
			} else {
				if (row == 0) {
					io.println("Image Number " + 
				            blockNo + " contains 0 war eagles.");
				} else {
					int bit = io.getInt();
					if (bit == 1) {
						io.println("Image Number " + 
					            blockNo + " contains 1 war eagles.");
					} else {
						io.println("Image Number " + 
					            blockNo + " contains 0 war eagles.");
					}
				}
				blockNo++;
			}
		}
		io.flush();
		io.close();
	}
	
	public static int computeAnswer(int[][] grid) {
		// structure to store graph
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		
		// -1 sentinels for dummy nodes
		for(int i=0; i < (grid.length * grid.length); i++) {
			List<Integer> dummy = new ArrayList<Integer>();
			dummy.add(-1);
			graph.add(dummy);
		}
		
		// build adjacency list
		for (int i=0; i < grid.length; i++) {
			for (int j=0; j < grid[0].length; j++) {
				int index = j + (grid.length * i);
				if (grid[i][j] == 1) {
					List<Integer> lst = graph.get(index);
					lst.remove(0);
					lst.add(-10);
					
					int gridSignal = figureGridPos(i, j, grid.length);
					
					// store list of positions to search
					List<int[]> a = new ArrayList<int[]>();
					
					// interpret grid signal
					if (gridSignal == 1) {
						a.add(new int[] {i, j+1});
						a.add(new int[] {i+1, j});
						a.add(new int[] {i+1, j+1});
					} else if (gridSignal == 2) {
						a.add(new int[] {i, j-1});
						a.add(new int[] {i+1, j});
						a.add(new int[] {i+1, j-1});
					} else if (gridSignal == 3) {
						a.add(new int[] {i, j+1});
						a.add(new int[] {i, j-1});
						a.add(new int[] {i+1, j+1});
						a.add(new int[] {i+1, j-1});
						a.add(new int[] {i+1, j});
					} else if (gridSignal == 4) {
						a.add(new int[] {i, j+1});
						a.add(new int[] {i-1, j});
						a.add(new int[] {i-1, j+1});
					} else if (gridSignal == 5) {
						a.add(new int[] {i, j-1});
						a.add(new int[] {i-1, j});
						a.add(new int[] {i-1, j-1});
					} else if (gridSignal == 6) {
						a.add(new int[] {i, j+1});
						a.add(new int[] {i, j-1});
						a.add(new int[] {i-1, j-1});
						a.add(new int[] {i-1, j+1});
						a.add(new int[] {i-1, j});
					} else if (gridSignal == 7) {
						a.add(new int[] {i+1, j});
						a.add(new int[] {i-1, j});
						a.add(new int[] {i+1, j+1});
						a.add(new int[] {i, j+1});
						a.add(new int[] {i-1, j+1});
					} else if (gridSignal == 8) {
						a.add(new int[] {i+1, j});
						a.add(new int[] {i-1, j});
						a.add(new int[] {i+1, j-1});
						a.add(new int[] {i, j-1});
						a.add(new int[] {i-1, j-1});
					} else if (gridSignal == 9) {
						a.add(new int[] {i+1, j});
						a.add(new int[] {i-1, j});
						a.add(new int[] {i, j+1});
						a.add(new int[] {i, j-1});
						a.add(new int[] {i+1, j+1});
						a.add(new int[] {i-1, j+1});
						a.add(new int[] {i+1, j-1});
						a.add(new int[] {i-1, j-1});
					}
					
					// add edges for adjacent 1 grids
					for (int[] x : a) {
						int y = x[0]; int z = x[1];
						if (grid[y][z] == 1)
							lst.add(z + (grid.length * y));
					}
				}
			}
		}
		
		ograph = graph;
		ovisited = new int[(grid.length * grid.length)];
		
		return dfs();
	}
	
	public static void search(int vtx) {
		ovisited[vtx] = 1;
		
		List<Integer> lst = ograph.get(vtx);
		for (Integer i: lst) {
			if (i > 0 && ovisited[i] == 0)
				search(i);
		}
	}
	
	public static int dfs() {
		int noRestarts = 0;
		int i = 0;
		for (List<Integer> lst : ograph) {
			if (lst.get(0) != -1 && ovisited[i] == 0) {
				noRestarts++;
				search(i);
			}
			i++;
		}
		return noRestarts;
	}
	
	public static int figureGridPos(int row, int col, int dim) {
		if (row == 0) {
			// TL
			if (col == 0)
				return 1;
			// TR
			else if (col == dim - 1)
				return 2;
			// T
			else
				return 3;
		} else if (row == dim - 1) {
			// BL
			if (col==0)
				return 4;
			// BR
			else if (col == dim - 1)
				return 5;
			// B
			else
				return 6;
		} else if (col == 0) {
			// L
			return 7;
		} else if (col == dim - 1) {
			// R
			return 8;
		} else {
			// M
			return 9;
		}
	}
	
	public static void print2DArray(int[][] grid) {
		System.out.println();
		for (int i=0; i < grid.length; i++) {
			for (int j=0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
}
