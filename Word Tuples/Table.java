//Table, by Dan Kenefick. A utility method for wordgen that keeps track of
// frequencylists of charecter prefixes


import structure5.*;
import java.util.Random;

public class Table {
    
    //the level of analysis, i.e. the number of seed charecters
    private int level;

    //the data
    private Vector<Association> data = new Vector(1000);

    //an asociation for loops
    private Association<String,Integer> count;

    //an asociation for the second loop
    private Association<String,FrequencyList> entry;

    //a placeholder for the key of an asociation
    private String key;

    //a placeholder for a frequency list
    private FrequencyList list;

    //a random number genrator
    Random random = new Random();

    // keeps track of wether or not we are doing a word based analysis
    private boolean wordanalysis = false;
    

    public Table (int seed){
	level = seed;
    }

    //a blank constructor for word based analyiss
    public Table(){
	 wordanalysis = true;
    }

    //pre: input contains a space and 2 word strings
    //post: updates table with data 
    public void addWord(String input){
	Assert.pre(input.indexOf(" ")>0,
	"supllied bad string of words to table");

	//breaks up the 2 words
	String in = input.substring(0,input.indexOf(" "));
	String tobecounted = input.substring(input.indexOf(" "));

	Association<String,FrequencyList> prefix;
	String key;

	int i;

	for (i = 0; i<data.size(); i++){

	    prefix = data.get(i);
	    key = prefix.getKey();

	    if (in.equals(key)){

		//match: updates count in frequency table
	        list = prefix.getValue();
		
		list.add(tobecounted);

		break;
	    }
	}

	//no match, create new association

	if(i==data.size()){
	    data.add(new Association(in,new FrequencyList(tobecounted)));
	}
    }


    
    public void add(String input) {
	
	Assert.pre(input.length() == level +1, "input of wrong length");

	//post: updates table to include input, and the frequency count of the frequency list

	//breaks input up into key - charecter pair
	String in = input.substring(0,level);
	String tobecounted = input.substring(level);

	Association<String,FrequencyList> prefix;
	String key;

	int i;

	for (i = 0; i<data.size(); i++){

	    prefix = data.get(i);
	    key = prefix.getKey();

	    if (in.equals(key)){

		//match: updates count in frequency table
	        list = prefix.getValue();

		list.add(tobecounted);

		break;
	    }
	}
    

	//no match, create new association

	if(i==data.size()){
	    data.add(new Association(in,new FrequencyList(tobecounted)));
	}
    }


    public String nextString(String prefix){
	//pre: data is non null
	Assert.pre(data!=null, "data has not been prepared");
	//post: returns the next random string, or a random charecter if no
	//such prefix is acounted for in the table, or if 

	String word = "";

	//returns a random suffix if blank
	if (prefix == "" || prefix == null){
	    word = getRandomSuffix();
	    return word;
	}

	
	int i;
	for (i =0; i<data.size(); i++){

	    entry = data.get(i);
	    key =  entry.getKey();

	    if (key.equals(prefix)){
	
	        list =entry.getValue();

		word = list.getRandomCharecter();
		break;
	    }
	}

	//no match, need a random charecter. should not happen in word analysis. 
	if (i == data.size()){

	    word = getRandomSuffix();
	}

	return word;
    }
	
    //pre:data is non-null
    public String getRandomSuffix(){
	int r = random.nextInt(data.size());
	entry = data.get(r);
	list = entry.getValue();
	String word = list.getRandomCharecter();

	return word;
    }

}
	    
