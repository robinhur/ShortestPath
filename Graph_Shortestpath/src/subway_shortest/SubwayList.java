package subway_shortest;

import java.util.*;

public class SubwayList {
	final static int White = 0;
	final static int Gray = 1;
	final static int Black  = 2;
	
	final int n;
	final Set<Integer>[] neighbors;
	int src;
	final int previous[];
	final int color[];
	
	public SubwayList(int numStations) {
		n = numStations;
		neighbors = new TreeSet[n+1];
		for (int i = 1; i <= n; i++)
			neighbors[i] = new TreeSet<Integer>();
		
		previous = new int[n+1];
		color = new int[n+1];
		src = 0;
	}
	
	public void addLine(int[] stations) {
		for (int i = 1; i< stations.length; i++) {
			neighbors[stations[i-1]].add(stations[i]);
			neighbors[stations[i]].add(stations[i-1]);
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
		
		for (int v : neighbors[u]) {
			if (color[v] == White) {
				previous[v] = u;
				dfsVisit(v);
			}
		}
		
		color[u] = Black;
	}
	
	public ArrayList<Integer> path (int d) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		if (src != 0 && src != d) {
			while (d!=0) {
				path.add(0,d);
				d = previous[d];
			}
		}
		
		return path;
	}
}