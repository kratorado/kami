package kami;

import java.util.HashSet;

public class Block extends HashSet<Pos> {
	
	private char color;
	
	public Block(char color) {
		super();
		this.color = color;
	}
	
	public String toString() {
		return "\n" + color + " " + super.toString();
	}
	
	public char getColor() {
		return color;
	}
	
	public Pos getOne() {
		for (Pos p : this) {
			return p;
		}
		return null;
	}
}
