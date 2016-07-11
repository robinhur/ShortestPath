package maze;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MazeApplet extends JApplet {
	int size = 10, offset = 50;
	int width = 500, height = 500;
	
	final static int White = 0;
	final static int Gray = 1;
	final static int Black = 2;
	
	int color[][];
	LinkedList<Point> neighbors[][];
	boolean hasEastWall[][];
	boolean hasSouthWall[][];

	  void clearWall(int fromR, int fromC, int toR, int toC) {
	    if (fromC == toC) {
	      hasSouthWall[Math.min(fromR, toR)][fromC] = false;
	    } else {
	      hasEastWall[fromR][Math.min(fromC, toC)] = false;
	    }
	  }
	  

	public MazeApplet() {
		hasEastWall = new boolean[height/size][width/size];
		hasSouthWall= new boolean[height/size][width/size];
		color = new int[height/size][width/size];
		neighbors = new LinkedList[height/size][width/size];
		
		for (int r = 0; r <height/size; r++) {
			for (int c = 0; c < width/size; c++) {
				hasEastWall[r][c] = true;
				hasSouthWall[r][c] = true;
				neighbors[r][c] = new LinkedList<Point>();

				if (r != 0) {neighbors[r][c].add(new Point(r-1,c));}
				if (r != height/size - 1) {neighbors[r][c].add(new Point(r+1,c));}
				if (c != 0) {neighbors[r][c].add(new Point(r,c-1));}
				if (c != height/size - 1) {neighbors[r][c].add(new Point(r,c+1));}
				
				Collections.shuffle(neighbors[r][c]);
			}
		}
		
		dfsVisit(0,width/size/2);
		hasSouthWall[height/size -1][width/size/2] = false;
	}

	  void dfsVisit(int r, int c) {
	    color[r][c] = Gray;
	    while (!neighbors[r][c].isEmpty()) {
	      Point cell = neighbors[r][c].remove();
	      if (color[cell.x][cell.y] == White) {
	        //clearWall(r,c, cell.x,cell.y);
	        dfsVisit(cell.x, cell.y);
	      }
	    }
	    color[r][c] = Black;
	  }
	  
	  public void paint(Graphics g) {
	    g.drawLine(offset, offset, offset, offset+(height/size)*size);
	    g.drawLine(offset, offset, offset + (width/size/2)*size, offset);
	    g.drawLine(offset + size*(1+(width/size)/2), offset, offset+(width/size)*size, offset);
	    
	    for (int r = 0; r < height/size; r += 1) {
	      for (int c = 0; c < width/size; c += 1) {
	        if (hasSouthWall[r][c]) { 
	          g.drawLine (offset+c*size, offset + (r+1)*size, offset+(c+1)*size, offset + (r+1)*size);
	        }
	        if (hasEastWall[r][c]) {
	          g.drawLine (offset+(c+1)*size, offset + r*size, offset+(c+1)*size, offset + (r+1)*size);
	        }
	      }
	    }
	  }
}
