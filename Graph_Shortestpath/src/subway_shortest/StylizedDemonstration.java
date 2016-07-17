package subway_shortest;

public class StylizedDemonstration {

	private static final int Max = 20;
	private static SubwayMatrix mat;
	private static SubwayList list;
	private static int numVertices;
	private static int m = 1000000;

	public static void main(String args[]){
		System.out.println("n\tMatrix\t\tList");
		for (int k = 2; k<65; k*=2) {
			float totalMatrix = 0, totalList = 0;
			for (int numTrials = 1; numTrials <= Max; numTrials++) {
				generate(k);
				
				System.gc();
				long now = System.nanoTime();
				mat.dfsSearch(1);
				totalMatrix += (System.nanoTime()-now);
				
				System.gc();
				now = System.nanoTime();
				list.dfsSearch(1);
				totalList += (System.nanoTime()-now);
			}
			System.out.println(numVertices + "\t" + totalMatrix/Max/m  + "\t" + totalList/Max/m);
		}
	}
	
	public static void generate(int k) {
		int n = k * k;
		numVertices = n + 2;
		mat = new SubwayMatrix(n+2);
		list = new SubwayList(n+2);
		int[] pairs;
		
		for (int i = 2; i<= k+1; i++) {
			pairs = new int[]{1,i};
			list.addLine(pairs);
			mat.addLine(pairs);
		}
		for (int i = n - k + 2; i <= n+1; i++) {
			pairs = new int[]{n+2,i};
			list.addLine(pairs);
			mat.addLine(pairs);
		}
		for (int i = 0; i < k-1; i++) {
			for (int j = 0; j < k-1; j++) {
				int u = 2 + i*k + j;
				for (int m = 0; m < k; m++) {
					pairs = new int[]{u, 2+(i+1)*k+m};
					list.addLine(pairs);
					mat.addLine(pairs);
				}
			}
		}
	}
	
}
