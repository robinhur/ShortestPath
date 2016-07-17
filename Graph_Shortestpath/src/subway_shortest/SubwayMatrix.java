package subway_shortest;

import java.util.*;

public class SubwayMatrix {

	private static final int White = 0;
	private static final int Gray = 1;
	private static final int Black = 2;
	
	private int n;
	private boolean[][] matrix;
	private int[] previous;
	private int[] color;
	private int src;

	public SubwayMatrix(int numStations) {
		n = numStations;
		matrix = new boolean[n+1][n+1];
		previous = new int[n+1];
		color = new int[n+1];
		src = 0;
	}
	
	public void addLine(int[] stations) {
		for (int i = 1; i< stations.length; i++) {
			matrix[stations[i-1]][stations[i]] = true;
			matrix[stations[i]][stations[i-1]] = true;
		}
	}
	
	public void dfsSearch(int s) {
		for (int v = 1; v <= n; v++) {
			color[v] = White;
			previous[v] = 0;
		}
		
		dfsVisit(s);
		src = s;
	}
	
	void dfsVisit(int u) {
		color[u] = Gray;
		
		for (int v = 1; v <= n; v++) {
			if (matrix[u][v] && color[v] == White) {
				previous[v] = u;
				dfsVisit(v);
			}
		}
		
		color[u] = Black;
	}
	
	public List<Integer> path (int d) {
		LinkedList<Integer> path = new LinkedList<Integer>();
		if (src != 0 && src != d) {
			while (d!=0) {
				path.add(0, d);
				d = previous[d];
			}
		}
		
		return path;
	}
	
	public void bfsSearch(int s) {
		for (int v = 1; v <= n; v++) {
			color[v] = White;
			previous[v] = 0;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		color[s] = Gray;
		q.add(s);
		
		while(!q.isEmpty()) {
			int u = q.remove();
			
			for (int v = 1; v <= n; v++) {
				if (matrix[u][v] && color[v] == White) {
					previous[v] = u;
					color[v] = Gray;
					q.add(v);
				}
			}
			
			color[u] = Black;
		}
		
		src = s;
	}
	
}
