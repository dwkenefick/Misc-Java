//Parentheses, by Dan Kenefick
//determines if a string is parentheticaly balanced

// runtime: O(n)



public class Parentheses{

    public Parentheses(String txt){
	System.out.println(isBalanced(txt));
    }

    public static void main(String args[]){
	String txt = args[0];

	new Parentheses (txt);
    }

    //pre: text contains only [({})] charecters
    //post: returns true if all parenteses are ballanced
    
    public boolean isBalanced(String text){
	if (text.equals( "")){
	    return true;
	}

	else{
	    int index = text.indexOf("{}");

	    if (index == -1) index = text.indexOf("()");

	    if (index == -1) index = text.indexOf("[]");

	    if (index == -1) return false;

	    else{

		text = text.substring(0,index)+text.substring(index+2);

		return isBalanced(text);
	    }
	}
    }
}


	    