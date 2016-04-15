public class regularExpressions {

    //pre: args [0,1] must contain 2 expressions, first is pattern, second is string
    public static void main(String args[]){
	String w1= args[0];
	String w2 =args[1];

	System.out.println(isRegularExpression(w1,w2));

    }

 //pre: w1 is pattern, w2 is string, i.e. w2 contains no special charecters ? or *. 
    public static boolean isRegularExpression(String w1, String w2){

	//base case: if the string is empty, return true if pattern is also empty
	if(w1.length() == 0 || w2.length()==0) return w1.equals(w2);

	else{
	    //if the first charecter is the same, good, advance recursion
	    if (w1.charAt(0)==w2.charAt(0))
		return isRegularExpression(w1.substring(1), w2.substring(1));

	    //if 1st pattern char is a ?, advance recurion with both cases
	    if (w1.charAt(0)=='?'){
		return (
			isRegularExpression(w1.substring(1), w2.substring(1)) ||
			isRegularExpression(w1.substring(1), w2)  );
	    }

	    if (w1.charAt(0) == '*'){
		return (
			isRegularExpression(w1.substring(1), w2.substring(1)) ||
			isRegularExpression(w1, w2.substring(1)) ||
			isRegularExpression(w1.substring(1), w2) );
	    }

	    return false;
	}

    }

}