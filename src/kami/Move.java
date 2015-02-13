package kami;

public class Move {
    private Pos pos;
    
    private char color;
    
    public Move(Pos pos, char color) {
        this.pos = pos;
        this.color = color;
    }
    

    
    public Pos getPos() {
        return pos;
    }


    
    public void setPos(Pos pos) {
        this.pos = pos;
    }


    public char getColor() {
        return color;
    }

    
    public void setColor(char color) {
        this.color = color;
    }
    
    public String toString() {
    	return color + pos.toString();
    }
}
