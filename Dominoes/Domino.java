public class Domino { 
    protected int side1, side2;

    protected boolean isUsed = false;

    public Domino(int side1, int side2) {
	this.side1 = side1; this.side2 = side2;
    } 

    public int firstSide() { return side1; } 

    public int secondSide() { return side2; }

    public void use(){
	isUsed = true;
    }

    public void unUse(){
	isUsed = false;
    }
    
    public boolean isUsed(){
	return isUsed;
    }


}
