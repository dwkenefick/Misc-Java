
import java.util.Random;


/*
 * Recursion.java
 *
 * by Dan Kenefick
 * a whiole bunch of recursive methods
 *
 * Starter code for the recursion lab.
 *
 */
import structure5.*;

public class Recursion {


    /*****  1  ***************************************************/

    /*
     *post: Return number of cannoballs in pyramid with the given height.
     */
    public static int countCannonballs(int h){
	Assert.pre(h>=1, "height must be greater than or equal to 1");
	
	if ( h == 1){
	    return 1;
		}

	else{
	    return h*h + countCannonballs(h-1);
	}
    }


    /*****  2  ***************************************************/

    /*
     * Return true if str is a palindrome.
     *O-analysis : based on number of iterations
     * best: O(1) , if passed empty string or first 
     *worst: O(length/2) = O(length) if the nontrivial word is a palindrome
     *avg: O(length)
     */

    
    //pre: word contais no spaces(otherwise the terminal will take it as 2 
    //seperate arguments

    //post: returns true if the word is a palindrome


    public static boolean isPalindrome(String wrd){
	
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

    /*****  3  ***************************************************/

    /*
     * post: Return true if str is a string of matched parens,
     * brackets, and braces.
     */

    // runtime:
    //best: O(1)
    //worst: nontrivial text is ballanced, O(length/2) = O(length)
    //avg: O(length)

    //pre: text contains only [({})] charecters
    
    public static boolean isBalanced(String text){
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


    /*****  4  ***************************************************/

    /*
     * Print all substrings of str.  (Order does not matter.)
     */
    //Pre: just about always works
    //post: returns all permutations (substrings) of the given string

    //runtime (of helper method)
    //best: passed trivial string, 0(1)
    //worst: goes through all permutations, O(2^length)
    //avg: O(2^length)


    public static void substrings(String str){
	SubstringHelper(str, "");
    }


    public static void SubstringHelper( String str, String soFar){
	if (str.equals("")){
	    System.out.print(soFar+", ");
	}

	else{
	    
	    SubstringHelper(str.substring(1), soFar+str.charAt(0));
	    SubstringHelper(str.substring(1), soFar);
		}
    }

    /*****  5  ***************************************************/

    /*
     * Print number in binary
     */
    //pre: int i>= 0
    //post: returns the binary representation of the digit

    /*Runtime
      best: O(1), passed 0 or 1.
      worst: O(log i) 
      avg: O(log i)
    */


    public static String Binary (int i){
	if( i==0||i==1){
	    return ""+i;
	}

	else{
	    return ""+Binary(i/2)+i%2;
	}
    }


    /*****  6  ***************************************************/

    /*
     *Post: Return whether a subset of the numbers in nums add up to sum,
     * and print them out.
     */

    /*Analysis:
      best: trivial case, 1 number = target, O(1)
      worst: no sum is found, goes through whole tree, O(2^n) where n is 
      number of elements
      average: goes through a certain percentage of tree, O(2^n)
     */
      
    //Pre: nums is non null array of ints, contains all non null values, targetsum is non null
    //note: dont need to call a precondition for non null values, whenever we 
    //create an array of ints it is initialy filled in with 0

    

    public static boolean printSubSetSum(int nums[], 
					 int targetSum) {
	Assert.pre(nums != null, "list of numbers must be prepared");

	return printSubsetSumHelper(nums, targetSum, 0);
    }

    //pre: same as printSubsetSum, with the addition of bounded index

    private static boolean printSubsetSumHelper(int set[], int target,
					       int index){

	Assert.pre( index >= 0 && index <set.length+1, "Error: index must be greater than equal to zero and less than the number of elements");

	//other conditions are checked before initial call in printSubsetSum


	if (set.length == index) {
	    return (target == 0);

	} else {
	    if( printSubsetSumHelper(set, target - set[index],
				     index + 1)){
		
		System.out.print(set[index]+" ");
		return true;
		
		
	    } else return  printSubsetSumHelper(set, target, index + 1);
	}
    }


    //********************************************************************


    /*
     * Return the number of different ways elements in nums can be
     * added together to equal sum.
     best: O(1)
     worst: O(2^n)
     avg: O(2^n)
     */



        //Pre: nums is non null array of ints, contains all non null values, targetsum is non null
    public static int countSubSetSumSolutions(int nums[], 
					      int targetSum) {

	Assert.pre(nums != null, "list of numbers must be prepared");

	return countSubsetSumSolutionsHelper(nums, targetSum,0);

    }




    //pre: same as countSubsetSum, with the addition of bounded index
    //post: recusivly determines the number of solutions
    public static int countSubsetSumSolutionsHelper(int set[], int target, 
					      int index){

	Assert.pre( index >= 0 && index <set.length+1, "Error: index must be greater than equal to zero and less than the number of elements");

	//other conditions are checked before initial call in countSubsetSum
	

                
	if (set.length == index) {
	    if (target == 0){
		return 1;
	    }else return 0;
	    
        } else {
            return
		countSubsetSumSolutionsHelper(set, target - set[index], index + 1) +
                countSubsetSumSolutionsHelper(set, target, index + 1);
	}
    }

    //********************************************************************



    /*
     * prints all different ways elements in nums can be
     * added together to equal sum.
     best: O(2^n)
     worst: O(2^n)
     avg: O(2^n)
     n is number of elements
     */



    //Pre: nums is non null array of ints, contains all non null values, targetsum is non null

    

    public static void printAllSubsetSums(int set[], int target){
	
	Assert.pre(set != null, "list of numbers must be prepared");

	printAllSubsetSumHelper(set, target, 0, "");

    }

    //pre: same as printSubsetSum, with the addition of bounded index. Also, to make sense, series must consist of elements in set. 
    //post: recusivly prints all solutions 
    public static void printAllSubsetSumHelper(int set[], int target, int index,
					   String series ){

	Assert.pre( index >= 0 && index <set.length+1, "index must be greater than equal to zero and less than the number of elements");

        if (set.length == index) {
            if (target == 0 && index!= 0){
		 System.out.print("["+series+"\b]");
	    }

        } else {
	    printAllSubsetSumHelper(set, target - set[index], index + 1, 
			      series+set[index]+",");

	    printAllSubsetSumHelper(set, target, index + 1, series);
        }
    }




    /*****  7  ***************************************************/
    
    public static void listCompletions(String digitSequence, 
				       Lexicon lex) {
    
    }


    /**************************************************************/
    
    /*
     * Add testing code to main to demonstrate that each of your 
     * recursive methods works properly.
     */
    public static void main(String args[]) {

	Random r = new Random();
	String word = "";
	String word1 = "";
	int array[] = new int[5];
	for (int i = 0; i<5; i++){
	    int n = r.nextInt(9);
	    word = word + n;
	    word1 = n+ word1;
	    array[i] =n;
	    
	    
	}

	word1 = word +word1;     
	
	System.out.println("Words are: "+word+" "+word1);
	System.out.println();


	// test code for problem 1
	int a = r.nextInt(9)+1;
	int b = r.nextInt(9)+1;

	System.out.println(" ints are a: "+a+" b: "+b);
	System.out.println();

	System.out.println("Cannonball test using a and b");
    
	System.out.println(countCannonballs(a));
	System.out.println(countCannonballs(b));

	//test 2 

	System.out.println();
	System.out.println("Palindrome test using word and word 1. 2 should always be true");
	System.out.println(isPalindrome(word));
	System.out.println(isPalindrome(word1));

	//test 3
	System.out.println();
	System.out.println("Balanced test using 1 balanced and one unbalanced");
	System.out.println(isBalanced("{}[]()[[]]{[]}([[]()])[{}([{}])]"));
	System.out.println(isBalanced("{{{}}}{}{{})"));

	//test4
	System.out.println();
	System.out.println("substrings test using word");
	substrings(word);
	System.out.println();

	//test 5
	System.out.println();
	System.out.println("binary test using a, b and word as as an int");
	System.out.println(Binary(a));
	System.out.println(Binary(b));
	System.out.println(Binary(Integer.parseInt(word)));
	System.out.println();

	//test 6
       	System.out.println("Substring tests using word as an array and a as target: "+word+" , "+a);
	
	System.out.println("Print subset sum:");
	printSubSetSum(array, a);

	System.out.println("count subset sum:");
	System.out.println(countSubSetSumSolutions(array, a));

	System.out.println("print all subset sum:");
	printAllSubsetSums(array,a);

	System.out.println();

	int[] ar = new int [3];
	    ar[0] = 0;
	    ar[1]= -2;
	    ar[2] = 2;

	    printAllSubsetSums(ar, 0);

    }
	

	
}
