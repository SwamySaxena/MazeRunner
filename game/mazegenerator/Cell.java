package game.mazegenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Cell extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 20;

	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;
	
	private int row = -1;
	private int col = -1;
	private int type = MazeGenerator.TYPE_MAZE;

	private boolean[] wall = {true, true, true, true};
	private boolean[] path = {false, false, false, false};
	
	private boolean current = false;
	private boolean end = false;
	

    public Cell(int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}

    public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

    public boolean isWall(int index) {
		return wall[index];
	}

	public void setCurrent(boolean current) {
		this.current = current;
		repaint();
	}

	public void setEnd(boolean end) {
		this.end = end;
		repaint();
	}

	public void addPath(int side) {
		path[side] = true;
		repaint();
	}


    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
	    g.fillRect(0, 0, SIZE, SIZE);
        g.setColor(Color.BLACK);
        
        // draw the walls

		if (type==MazeGenerator.TYPE_MAZE) {
			if (isWall(TOP)) {
				g.drawLine(0, 0, SIZE, 0);
			}
			if (isWall(LEFT)) {
				g.drawLine(0, 0, 0, SIZE);
			}
		}
		else {
			if (!isWall(TOP)) {
				g.drawLine(0, 0, SIZE, 0);
			}
			if (!isWall(LEFT)) {
				g.drawLine(0, 0, 0, SIZE);
			}
		}
    
        // draw the path
		g.setColor(Color.GREEN);
		if (path[TOP]) {
			g.drawLine(SIZE/2, 0, SIZE/2, SIZE/2);
		}
		if (path[BOTTOM]) {
			g.drawLine(SIZE/2, SIZE, SIZE/2, SIZE/2);
		}
		if (path[LEFT]) {
			g.drawLine(0, SIZE/2, SIZE/2, SIZE/2);
		}
		if (path[RIGHT]) {
			g.drawLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
		}
        // draw the balls
		if (current) {
			g.setColor(Color.GREEN);
			g.fillOval(3,  3, SIZE-6, SIZE-6);
		}
		else if (end) {
			g.setColor(Color.RED);
			g.fillOval(3,  3, SIZE-6, SIZE-6);
		}	

    }

    public Dimension getPreferredSize() {
		Dimension size = new Dimension(SIZE, SIZE);
		return size;
	}

	public boolean hasAllWalls() {
		boolean allWalls = wall[0] && wall[1] && wall[2] && wall[3];
		return allWalls;
	}

	public void removeWall(int w) {
		wall[w] = false;
		repaint();
	}

	public void openTo(Cell neighbor) {
		if (row<neighbor.getRow()) {
			removeWall(BOTTOM);
			neighbor.removeWall(TOP);
		}
		else if (row>neighbor.getRow()) {
			removeWall(TOP);
			neighbor.removeWall(BOTTOM);
		}
		else if (col<neighbor.getCol()) {
			removeWall(RIGHT);
			neighbor.removeWall(LEFT);
		}
		else if (col>neighbor.getCol()) {
			removeWall(LEFT);
			neighbor.removeWall(RIGHT);
		}
	}
    
}
