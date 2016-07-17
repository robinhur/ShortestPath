package subway_shortest;

import java.util.*;

public class Compare {
	public static void main(String args[]) {
		SubwayMatrix sm = new SubwayMatrix(10);
		sm.addLine(new int[]{1,4,2,8,7,6});
		sm.addLine(new int[]{3,5,4,2,8,9,10});
		sm.addLine(new int[]{3,10});
		
		sm.dfsSearch(4);
		List dfsPath[] = new List[11];
		for (int i = 1; i<=10;i++) {
			dfsPath[i] = sm.path(i);
		}
		
		sm.bfsSearch(4);
		List bfsPath[] = new List[11];
		for (int i = 1; i<=10;i++) {
			bfsPath[i] = sm.path(i);
		}
		
		for (int i = 1;i <= 10;i++){
			if (bfsPath[i].size() < dfsPath[i].size()) {
				System.out.println("4-"+i+" : BFS " + bfsPath[i]);
			} else {
				System.out.println("4-"+i+" : DFS " + dfsPath[i]);
			}
		}
		
	}
}
