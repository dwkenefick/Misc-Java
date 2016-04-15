import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.lang.Math;
import structure5.*;

/*combine test: a program to record the information for combine test, to find the optimal 
 switching point. 
 first argument: min switching point to test
 second: max switching point
 third: 2^(third-1) is the size of the array
 forth: numbere of trials
 */


public class combineTest1{

    public static void main(String args[]){

	Random r = new Random();

	//number of values to test
	int min = Integer.parseInt(args[0]);
	int max = Integer.parseInt(args[1]);
	int powers = Integer.parseInt(args[2]);
	int trials = Integer.parseInt(args[3]);


	try{
	    //creates the file and output stream to write
	    File file = new File("/Users/danielkenefick/Desktop/Extraprograms/combineData.txt");
	    FileOutputStream fos = new FileOutputStream (file);
	    PrintStream output = new PrintStream(fos);

	    output.print("size\t");

	    for(int x = min; x<=max; x++){
		output.print(""+x+"\t");
	    }
	    
	    output.print("\b\n");

	    


		//the size of the arrays for this pass
		int pwr = (int)Math.pow(2,powers);

		//generates random array
		short[] array1 = new short[pwr];
		
		for(int y = 0; y<pwr; y++){
		    int l =  r.nextInt(32000);

		    array1[y] = (short)l;
		}

		String currentline = ""+pwr+"\t";

		//does the sorting tests test
		int x;
		for(x = min ; x<=max; x++){

		    double avg = 0;
		    for(int y = 0; y< trials; y++){
			short[] curarray = (short[])array1.clone();

			long startTime = System.currentTimeMillis();

			combineRecursive(curarray, 0, pwr-1, x);

			long endTime = System.currentTimeMillis();
			
			avg = avg + (endTime - startTime);
			

		    }

		    avg = avg / trials;

		    currentline = currentline+ avg +"\t";
		}
			
		output.println(currentline+"\b");
	    


	} catch (Exception e){ System.out.println("fail");}
    }



    //*************************Combined sort********************************************
    
    //a combined method that swithes from quick to select once the array becomes small enough
    private static void combineRecursive(short data[],
					 int left,int right, int swtch)
    // pre: left <= right
    // post: data[left..right] in ascending order
    {
        int pivot;   
        if (left >= right) return;
		if( right - left <= swtch){
			selectionSort(data,left,right);
		} else{
		
		    pivot = partition(data,left,right);    /* 1 - place pivot */
		    combineRecursive(data,left,pivot-1,swtch); /* 2 - sort small */
		    combineRecursive(data,pivot+1,right,swtch);/* 3 - sort large */
		}
    }


	//an iterative version of selection sort, to avoid stack overflow memory problems
	//post: returns integers of a in order
    public static void selectionSort(short a[], int start, int finish) {

	
	
	for (int x = finish; x>= start+1; x--){
	    int max = x;

	    for(int y = start; y<x; y++){
		if(a[y]>a[max]) max = y;
	    }
	    
	    swap(a, max, x);
	}

    }

    private static int partition(short data[], int left, int right)
    // pre: left <= right
    // post: data[left] placed in the correct (returned) location
    {
        while (true)
        {
            // move right "pointer" toward left
            while (left < right && data[left]<data[right])
                right--;
            if (left < right) swap(data,left++,right);
            else return left;
            // move left pointer toward right
            while (left < right && data[left]<data[right])
                left++;
            if (left < right) swap(data,left,right--);
            else return right;
        }
    }


    public static void swap(short data[], int i, int j)
    // pre: 0 <= i,j < data.length
    // post: data[i] and data[j] are exchanged
    {
        short temp;
        temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

	