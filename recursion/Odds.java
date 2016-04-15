public class Odds {
    public static void main (String args[]){

	String output = "";

	for (int x=1; x<20;x++){
	    if(x%2 == 1){
		output = output + x+" ";
	    }
	}

	System.out.println (output);
    }
}