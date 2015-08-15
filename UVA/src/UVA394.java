import java.util.*;


public class UVA394 {
	
	public static HashMap<String, List<Integer>> arrays = new HashMap<String, List<Integer>>();
	public static HashMap<String, int[]> bounds = new HashMap<String, int[]>();
	public static int[] C = new int[11];
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		while (io.hasMoreTokens()) {
			int noArrays = io.getInt();
			int noAddresses = io.getInt();
						
			for (int i=0; i < noArrays; i++) {
				String name = io.getWord();
				List<Integer> val = new ArrayList<Integer>();
				int baseAdd = io.getInt(); val.add(baseAdd);
				int eleSize = io.getInt(); val.add(eleSize);
				int dim = io.getInt(); val.add(dim);
				int[] indices = new int[20];
				for (int j=0; j < dim; j++) {
					int lower = io.getInt();
					indices[j*2] = lower;
					int upper = io.getInt();
					indices[j*2+1] = upper;
				}
				arrays.put(name, val);
				bounds.put(name, indices);
			}
			
			for (int i=0; i < noAddresses; i++) {
				String name = io.getWord();
				io.print(name + "[");
				List<Integer> constants = arrays.get(name);
				int[] boundVals = bounds.get(name);
				int[] indexVals = new int[10];
				
				int baseAdd = constants.get(0);
				int eleSize = constants.get(1);
				int dim = constants.get(2);
				
				C[dim] = eleSize;
				
				// get index values
				for (int j=0; j < dim; j++) {
					indexVals[j] = io.getInt();
					if (j == dim-1)
						io.print(indexVals[j] + "] = ");
					else
						io.print(indexVals[j] + ", ");
				}
				
				// simple case handle
				if (dim == 1) {
					C[0] = baseAdd - C[1] * boundVals[0];
					io.println(C[0] + C[1] * indexVals[0]);
					continue;
				}
				
				for(int j=dim-1; j >=1; j--) {
					C[j] = C[j+1] * (boundVals[j*2+1] - boundVals[j*2] + 1);
				}
				
				int runningSum=0;
				for(int j=0; j <dim; j++) {
					runningSum = runningSum + (boundVals[j*2]* C[j+1]);
				}
				
				C[0] = baseAdd - runningSum;
				
				int newRunningSum = C[0];
				for (int j=1; j<=dim; j++) {
					newRunningSum = newRunningSum + C[j] * indexVals[j-1];
				}
				
				io.println(newRunningSum);	
			}
		}
		
		io.close();
	}
	
	
}
