package maze;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class MazeApplet_Findpath extends JApplet {
	int size = 10, offset = 50;
	int width = 500, height = 500;
	boolean init = false;

	final static int White = 0;
	final static int Gray = 1;
	final static int Black = 2;

	int color[][];
	LinkedList<Point> neighbors[][];
	boolean hasEastWall[][];
	boolean hasSouthWall[][];

	boolean go_find = false;
	int path[][];
	
	int num = 0;	
		
	void clearWall(int fromR, int fromC, int toR, int toC) {
		if (fromC == toC) {
			hasSouthWall[Math.min(fromR, toR)][fromC] = false;
		} else {
			hasEastWall[fromR][Math.min(fromC, toC)] = false;
		}
	}


	public MazeApplet_Findpath() {

		hasEastWall = new boolean[height/size][width/size];
		hasSouthWall= new boolean[height/size][width/size];
		color = new int[height/size][width/size];
		path = new int[height/size][width/size];
		neighbors = new LinkedList[height/size][width/size];

		for (int r = 0; r <height/size; r++) {
			for (int c = 0; c < width/size; c++) {
				path[r][c] = -1;
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

		if (FIND_PATH(height/size -1, width/size/2)==10) {
			go_find = true;
			JOptionPane.showMessageDialog(null, "path num = " + num, "е╫╩Ж ╟А╟З", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private int FIND_PATH(int r, int c) {
		
		path[r][c] = 0;

		if ((r==0)&&(c==width/size/2)) return 10;
		
		int right = 0;
		
		if ((right==0)&&check_wall(r,c,0)) right = FIND_PATH(r, c + 1); //©Л
		if ((right==0)&&check_wall(r,c,1)) right = FIND_PATH(r, c - 1); //аб
		if ((right==0)&&check_wall(r,c,2)) right = FIND_PATH(r + 1, c); //го
		if ((right==0)&&check_wall(r,c,3)) right = FIND_PATH(r - 1, c); //╩С		

		if (right==10){
			num++;
			path[r][c] =  num;
		}
		return right;
		
	}
	
	public boolean check_wall(int r, int c, int mode) {
		
		boolean can = false;
		
		int result = 0;
		
		if (r==0) result += 0;
		else if (r == height/size - 1) result += 20;
		else result += 10;
		
		if (c==0) result += 0;
		else if (c == height/size - 1) result += 2;
		else result += 1;
		
		switch (mode) {
		
		case 0://©Л
			if (result%10!=2) {
				if ((path[r][c+1]==-1)&&(hasEastWall[r][c] == false)) can = true;
			}
			break;
		case 1://аб
			if (result%10!=0) {
				if ((path[r][c-1]==-1)&&(hasEastWall[r][c-1] == false)) can = true;
			}
			break;
		case 2://го
			if (result/10!=2) {
				if ((path[r+1][c]==-1)&&(hasSouthWall[r][c] == false)) can = true;
			}
			break;
		case 3://╩С
			if (result/10!=0) {
				if ((path[r-1][c]==-1)&&(hasSouthWall[r-1][c] == false)) can = true;	
			}
			break;
		}

		return can;
	}
	
	void dfsVisit(int r, int c) {	
		
		color[r][c] = Gray;
		
		while (!neighbors[r][c].isEmpty()) {
			Point cell = neighbors[r][c].remove();

			if (color[cell.x][cell.y] == White) {
				clearWall(r,c, cell.x,cell.y);
				dfsVisit(cell.x, cell.y);
			}
		}
		color[r][c] = Black;
	}

	public void paint(Graphics g) {
		
		if (!init) {
			this.setSize(600,600);
			init = !init;
		}

		if (go_find) {
			for (int i = 1; i <= num; i++) {

				for (int r = 0; r < height/size; r += 1) {
					for (int c = 0; c < width/size; c += 1) {
						if (path[r][c] == i) {
							g.setColor(Color.getHSBColor((float)(i)/num, 1, 1));
							g.fillRect (offset+c*size, offset + r*size, size, size);
							break;
						}
					}
				}
				
			}	

			g.fillRect (offset+((width/size)/2)*size, offset, size, size);
			g.setColor(Color.BLACK);
		}
		
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

	public void pause() {
		String z;
		Scanner sc = new Scanner(System.in);
		
		z = sc.nextLine();	
	}
}
