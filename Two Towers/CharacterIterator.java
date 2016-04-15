import structure5.*;

public class CharacterIterator extends AbstractIterator<Character>{

    //where we are in the string
    protected int count;

    //the length of the data string
    protected int length; 

    //The string itself
    protected String str;

    public CharacterIterator (String str){

	//updates variables
	this.count = 0;
	this.str = str;
	this.length = str.length();
    }


    //pre: String has been initialized
    //post: returns the character at the current position and advances count
    public Character next(){
	
	if(hasNext()){

	    Character ch = get();
	    count++;
	    return ch;
	}
	//if at end of iteration, return null
	return null;

    }

    //pre: data has been initalized
    //post: checks if there is another element in the data
    public boolean hasNext(){
	return count < length;
    }

    //post: count is reset to zero
    public void reset(){
	count = 0;
    }

    //pre: there is a 'next' element, i.e. we are not at the end of the string.
    //post: retuns the character at the position of string indicated by count
    public Character get() {
	return str.charAt(count);
    }

}