package kami;

import java.util.HashSet;
import java.util.Set;

public class Pos {
    private int row;
    private int col;
    private int index;
    
    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
        this.index = row * Var.cols + col;
    }
    
    public Pos(int index) {
    	this.col = index % Var.cols;
    	this.row = index / Var.cols;
    	this.index = index;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public Set<Pos> getNeighbours() {
    	Set<Pos> set = new HashSet<>();
    	// up
    	if (row > 0) {
    		set.add(new Pos(row - 1, col));
    	}
    	// down
    	if (row < Var.rows - 1) {
    		set.add(new Pos(row + 1, col));
    	}
    	// left
    	if (col > 0) {
    		set.add(new Pos(row, col - 1));
    	}
    	// right
    	if (col < Var.cols - 1) {
    		set.add(new Pos(row, col + 1));
    	}
    	return set;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof Pos) {
    		Pos aPos = (Pos) o;
    		return aPos.getIndex() == this.getIndex();
    	}
    	return false;
    }
    
    public int hashCode() {
    	return this.getIndex();
    }
    
    public String toString() {
    	return String.format("@(%d, %d) ", row, col);
    }
}
