
//returns the sum of the digits of a number

public class DigitSum{
    
    int num;

    public DigitSum(int numb){
	num = numb;

	System.out.println(sum(num));
    }

    public static void main (String args[]){
	
	new DigitSum( Integer.parseInt(args[0]) );

    }

    public int sum(int x){
	if (x%10 == x){
	    return x;
	}

	else{
	    return x%10 +sum( (x-x%10)/10 );
	}
    }
}

	