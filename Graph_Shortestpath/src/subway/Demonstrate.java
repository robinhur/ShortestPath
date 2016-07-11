package subway;

public class Demonstrate {

	public static void main(String[] args) {
		SubwayMatrix sm = new SubwayMatrix(10);
	    sm.addLine(new int[]{1, 4, 2, 8, 7, 6});
	    sm.addLine(new int[]{3, 5, 4, 2, 8, 9, 10});
	    sm.addLine(new int[]{3, 10});
	    
	    sm.dfsSearch(4);
	    
	    for (int i = 1; i <= 10; i++) {
	      System.out.println("4-" + i + " : " + sm.path(i));
	    }
	}

}
