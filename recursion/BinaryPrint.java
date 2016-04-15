public class BinaryPrint{
    
    public static void main (String args[]){
	int i = Integer.parseInt(args[0]);

	new BinaryPrint(i);
    }

    public BinaryPrint(int num){
	System.out.println( Binary(num) );
    }

    public String Binary (int i){
	if( i==0||i==1){
	    return ""+i;
	}

	else{
	    return ""+Binary(i/2)+i%2;
	}
    }
}

			 