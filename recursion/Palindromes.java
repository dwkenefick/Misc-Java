//palindromes, by Dan Kenefick
//checks to see if given word is a palindrome

//runtime: O(word.length/2) = O(n)


public class Palindromes{

    public Palindromes(String word){
	System.out.println(isPalindrome(word));
    }

    public static void main (String args[]){
	String wrd = (String)args[0] ;

	new Palindromes(wrd);
    }

    //pre: word contais no spaces(otherwise the terminal will take it as 2 
    //seperate arguments

    //post: returns true if the word is a palindrome


    public boolean isPalindrome(String wrd){
	
	int length = wrd.length();

	if(length<=1){
	    return true;
	}

	else{
	    char a = wrd.charAt(0);
	    char b = wrd.charAt(length-1);

	    if (a == b){
		String wrd1 = wrd.substring(1,length);
		wrd1 = wrd1.substring(0,length - 2);
		
		return isPalindrome(wrd1);

	    }

	    else{
		return false;
	    }
	}
    }
}