/* 
WordGen, By Dan Kenefick

Generates a random output based on given input and level of analysis. first argument is the level of analysis and the second is the output length, while the
 3rd indicates wether you are doing a word based analysis (it should be
 working, Although I ad a little trouble getting it to read from certain text 
files. All forms of analysis work if you just coppy and paste text into 
the terminal)



self check questions:

3.2 the difrence between the add(v) and add(i,v) methods of a vector is that
 the first appends an object to the end of a vector, extending it as 
nessicary, whereas the second inserts an obect at the given index, shifting
 the rest over (and also extending as nessicary).

3.3 add(i,v) adds the element to the vector; all of the previous values in the
 vector are retained, they are just shifted over after the index. set(i,v)
 replaces the element at i with the provided object, and no other objects
 are moved or changed.

3.4 remove (v) will search the vector for a given object that ".equals" the
 provided object, and shift the other elements of the vector over which
 essential deletes it. This method will remove at most one coppy of the
 object provided. the remove (i) method will remove the element at the 
indext i regardless of what it is, by shifting the other elements over. 

3.6 The advantages of a vector instead of an array in Hangman reflect the
 advantages of vectors versus over themselves. Since a vector is 
extendable, a paticular game of hangman need not have a maximum number of words.
words could be added at run time for more versitile game play. additionaly,
 the remove(), get(), and isempty() methods facilitate the mechanics of the
 game. instead of explicitly searching through the array to remove or
 retrieve a word, we have pre-packaged methods that will do this for us.
 similarly, the vector's isEmpty() method saves us the trouple of writing our own method (also, it is consiterably faster since it does not have to search through the whole array, it just checks if the number of elements in the array is 0)
*/



import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class WordGen{

    //the desired length of output
    private static int outputlength = 200;

    //the level of analysis, default is 2
    private static int level = 2;

    //table for data
    private static Table table;

    //the input text we're dealing with
    private static String text;

    //length of the input
    private static int length;
    
    //the input scanner
    private static Scanner input;

    //a random number generator
    private static Random r = new Random();

    //a buffer for more efficient string concatanation
    private static StringBuffer textBuffer;

    //a boolean that keeps track of wether we are doing a word based analysis
    private static boolean doingwordanalysis = false;

    public static void main(String args[]){


	//gets info from args

	if (args.length >= 1){
	    level = Integer.parseInt(args[0]);
	    if(args.length>=2){
		outputlength = Integer.parseInt(args[1]);
	    
		if(args.length == 3){
		    if(Integer.parseInt(args[2]) == 1 ){
			doingwordanalysis = true;
			level = 2;
		    }
		}
	    }
	}

	//collects input
	input = new Scanner(System.in);

	textBuffer = new StringBuffer();
	while (input.hasNextLine()) {
	    String line = input.nextLine(); 
	    textBuffer.append(line); 
	    textBuffer.append("\n");
	}
	
	String text = textBuffer.toString();

	//textbuffers return lenghts faster than strings
	length = textBuffer.length();		

	//creates the table for the data

	if(!doingwordanalysis){

	    table = new Table(level);

	    for (int x = 0; x< length-level; x++){
		//"digests" text to go faster
		String chunk = text.substring(0, level+1);

		table.add(chunk);

		text = text.substring(1);
	    }
	}
	    
	//collects data accoriding to wordanalysis 
    else{

        table = new Table();
        
        //prepares text
        text = text.trim();

	//loops through the text for pairs of words to send to the table
        for (int index = text.indexOf(" ",text.indexOf(" ")+1);
            index>0;
            index = text.indexOf(" ",text.indexOf(" ")+1))
            
            {

            
        String words = text.substring(0, index);
        
        table.addWord(words);
        
        text = text.substring(text.indexOf(" ")+1);
        

        }
    }

	//creates output 
	String output = "";
	String next = "";

	for(int x=0;x<level;x++){
	    next = table.getRandomSuffix();
	    output = output + next;

	}

	if(!doingwordanalysis){
	    next = output;
	}

	for(int x = 0; x<outputlength; x++){

	    String word =  table.nextString(next);

	    output = output + word;

	    next = next + word;

	    if (!doingwordanalysis){
		next = next.substring(1);

	    } else{
		next = word;
	    }
	}
	

	//prints output
	System.out.println("-----------------------");
	System.out.println("-----------------------");
	System.out.println("-----------------------");
	System.out.println(output);
    }
}
	
