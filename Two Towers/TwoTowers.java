//Two Towers, by Dan Kenefick. A program to check for the best sollution to the block problem 
//


import java.util.Iterator;
import structure5.*;


public class TwoTowers{



    public static void main (String[] args){
	int num = Integer.parseInt(args[0]);

	//data containing blocks
	 Vector <Double> blocks = new Vector <Double>();

	 //puts all of the square roots into the vector
	 for (double l = 1.0; l <= num; l++){
	     double temp = Math.sqrt(l);

		 blocks.add(new Double(temp));
	 }

	 //a subset iterator for the blocks
	 SubsetIterator<Double> it = new SubsetIterator(blocks);

	 //the total height of the blocks
	 double h = sumVector(blocks);

	 //our best guess so far
	 double bestsofar = 0;

	 //best vector so far
	 Vector<Double> bestvector = new Vector<Double>() ;


	 //checks each of the possibilites
	 while (it.hasNext()){

	     Vector temp = it.next();

	     //gets the vector, so we can print the actual solution
	     double guess = sumVector(temp);

	     if(guess > bestsofar && guess < h/2){
		 //updates the values
		 bestsofar = guess;
		 bestvector = temp;

	     }
	 }

	     
	 //squares each of the elements of the vector, so we know which blocksmake up the solution
	 squareVector(bestvector);

	 //prints the closest we can get
	 System.out.println("the closest we can get is: "+(h - 2*bestsofar));

	 //prints out the block config
	 System.out.println("the first block configuration is "+bestvector );
	 


	     }


    

    //Post: sums all of the elements in a vector of doubles. a utility method. 
    public static  double sumVector(Vector<Double> nums){
	double total = 0;

	Iterator<Double> ita = nums.iterator();

	while (ita.hasNext()){
	    total = total + ita.next();
	}

	return total;
    }

    
    //Post: squares each of the elements in the vector
    public static void squareVector(Vector<Double> nums){

	int size = nums.size();
	 for (int i = 0; i< size; i++){

	     double m = nums.get(i);

	     nums.set(i,(double)Math.round( m*m ));
	 }
 
	
    }


}