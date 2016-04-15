//frequencylist, by Dan Kenefick. keeps track of the frequencys of a
// given prefix and all sufixes

import structure5.*;
import java.util.Random;

public class FrequencyList{

    //the maximum size of a prepared data array
    private final static int MAX_SIZE = 100;

    //a number for the total letter
    private int total = 0;

    //a vector to keep track of the data
    private Vector<Association> data = new Vector(MAX_SIZE);

    //static number generator for all frequency lists
    private static Random r = new Random();

    //boolean to check if data has been prepared
    private boolean dataprepared = false;

    //once all the data has been colected, data will be aranged in an aray to ease random number generating
    private Vector table;
    
    //input-frequency asc for loops (used in multimple methods)
    private Association<String,Integer> count;

    //the key of the association (used in multiple methods
    private String key;


    //can initialize with an input
    public FrequencyList (String input){
	add(input);
    }


    public void add(String input){
	Assert.pre(input !="", "null input");
	//post: adds the input to data if not already there and updates
	//its count

	int i;
	//tallies the input
	for(i = 0; i<data.size(); i++){
	    //gets the ascociation
	    count = data.get(i);

	    //gets the key of the association
	    key =  count.getKey();

	    if(input.equals(key)){
		//match: increment the count
		Integer f = count.getValue();
		count.setValue(new Integer(f.intValue() + 1));
		break;
	    }
	}

	//no match: adds new Association
	if (i == data.size()){
	    data.add(new Association(input, new Integer(1)));
	}

	//updated total    
	total = total + 1;
    }

    public String getRandomCharecter(){
	//Pre: data must be aquired first, and the first time through data
	//must be prepared to table
	//post: returns a frequency dependant random word/charecter

	if (!dataprepared){
	    prepareData();
	}
	
	Assert.pre(table!=null,"data must be prepared");

	return (String)table.get(r.nextInt(total));

	
    }

    //would it be easier to just store values in a Vector like this? this would be more efficient for smaller text sizes. 

    //if total > 100.... estimate ratios out of 100 and then construct a table. this will reduce the maximum size of an array if the analysis level is small or text file is big. remeber to set total = 100 


    public void prepareData(){
	Assert.pre( data!=null, "data must be aquired first");
	//post: creates a vector of items with a maximum size, nullifies the original data. This prevents us from having to loop through the table each time we need a random charecter (only once to prepare the table). this saves us more time if certain seeds are used very often.

	//alternate post: changes frequencies in data vector to the sum of all the frequencies so far. would have to change getrandomchar method


	    //if the array is small, we can write out all of the Strings
	    
	table = new Vector(MAX_SIZE);

	if (total < MAX_SIZE){
		
	
		//steps through data, creating a new vector
		for(int i = 0; i<data.size(); i++){

		    count = data.get(i);

		    Integer fr = count.getValue();
		    int f = fr.intValue();

		    if(total<MAX_SIZE){
			f = (f*MAX_SIZE)/total;
		    }
			
		    //ensures all are represented (biased towards small values)
		    //if (f == 0){ f == 1;}


		    String word = (String)count.getKey();
	    
		    for (int x = 0; x< f; x++){
			table.add( word);
		    }

		}
	    }

	//because of division with ints, the total might not add up 100.
	//so we redefine the total at the end of this process
	total = table.size();

	    //we've now prepared the data
	    dataprepared = true;

	    //removes data ascociations to clear memory
	    data = null;
	}
    
    public String toString(){
	String output = "";

	if (dataprepared){
	    output = table.toString();
	}

	else{
	    for (int i = 0; i < total; i++){
		count = data.get(i);
		output = output + count.getKey();
		output = output + count.getValue();
	    }
	    
	}
	
	return output;
    }

}