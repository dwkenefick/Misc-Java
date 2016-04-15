import java.util.Iterator;
import structure5.*;

//subset iterator, by Dan Kenefick
//returns each of the subsets of a given vector of objects as a vector

public class SubsetIterator<E> extends AbstractIterator<Vector<E>>{

    //the set we are finding subsets of
    protected Vector<E> set;

    //the total number of subsets
    long n;

    //the subset we are on
    long position; 
    
    //the number of elements in the set
    int num;

    //a testing method
    public static void main(String[] args){
	
	Vector <Integer> ints = new Vector<Integer>();

	for (int i = 0; i< 8; i++){
	    ints.add(i);
	}

	SubsetIterator <Integer> it = new SubsetIterator<Integer> (ints);

	int count = 0;

	while (it.hasNext()){
	    System.out.println(it.next());
	    count++;
	}

	System.out.println(count);

    }

  
    //post: constructs and initializes variables
    public SubsetIterator(Vector<E> data) {
	set = data;
	num = set.size();

	Assert.pre(num<65, "Data cannot be larger than 64 items");

	n = (long)Math.pow(2.0, (long)num);

	position = 0;

    }

    //pre: data has been initialized,  0<= position < n
    //post:gets the subset indicated by the value position, and advances it
    public Vector<E> next(){

	    Vector<E> subset = get();
	    position++;
	    return subset;
	
    }



    //post: returns true if there are more subsets to be iterated
    public boolean hasNext(){
	return position < n;
    }
	
    //pre:  0<= position < n
    //post: returns the subset indicated by position as a vector of objects
    public Vector<E> get(){
	Vector<E> subset = new Vector<E>();


	for (int index = 0; index < num; index++){ 

	    if (( position & ( 1L << index)) > 0 ){
		subset.add(set.get(index));
	    }
	}

	return subset;

    }

    public void reset(){
	position = 0;
    }



	


}
