public class longestCommonSubsequence {

    //pre: args[0,1] are 2 words
    //post: returns the longest common subsequence of 2 words
    public static void main (String args[]){
	String word1 = args[0];
	String word2 = args[1];

	System.out.println(longestCommonSubsequence(word1, word2));

    }

    //pre: w1, w2 are non null 
    //post: returns the longest common subsequence of 2 words
    public static String longestCommonSubsequence (String w1, String w2){
	    return lcsHelper(w1,w2, "");
    }

    //pre/post: same as above. 
    public static String lcsHelper (String w1, String w2, String sofar){
	if (w1.equals("")||w2.equals("")){
	    return sofar;
	}

	else{
	    //checks if the first two charecters are equal
	    char a =w1.charAt(0);
	    char b =w2.charAt(0);

	    if(a==b){
		sofar = sofar+a;
		
		//progressess the recursion
		return lcsHelper(w1.substring(1),w2.substring(1), sofar);
	    }

	    //finds more subsequences
	    else{
		return longerString( 
		       lcsHelper(w1.substring(1),w2,sofar),
		       lcsHelper(w1, w2.substring(1),sofar));
	    }

	}
    }


    //post: returns the longer of 2 strings, or the first one if they are equal
    private static String longerString(String w1, String w2){

	int a = w1.length()-w2.length();

	if (a < 0) return w2;
	else return w1;

    }
}
	 
	


	

	    
	