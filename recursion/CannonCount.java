//cannoncount, by Dan Kenefick
//returns the number of spheres stacked to heigh h
//essentialy the sum of squares
// runtime: O(h) 

public class CannonCount {
    int height;

    public CannonCount (int h){

	height = h;
	System.out.println (count(height));

    }

    public static void main (String args[]){

	Integer I = Integer.parseInt(args[0]);
	int i = I.intValue();

	new CannonCount(i);
    }

    public int count(int h){
	if ( h == 1){
	    return 1;
		}

	else{
	    return h*h + count(h-1);
	}
    }
}