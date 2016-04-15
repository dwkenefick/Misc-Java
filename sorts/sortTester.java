import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.lang.Math;
import structure5.*;


/*
 sortTester: a class to record the information for sorting tests. the algorithms used here:
 selectionsort, quicksort recursive, quicksort iteratie (using stacks) and a combined quick / select sort.
 
 first argument: 2^(first argument-1) is the maximum array size to test
 second argument: the number of trials for each test
 */
 


public class sortTester {

	static PrintStream output;
	
    public static void main(String args[]){

	//# of powers of 2
	int powers = Integer.parseInt(args[0]);

	//trials for each size
	int trials = Integer.parseInt(args[1]);

	Random r = new Random();


	try{
		//creates the file and output stream to write
	    File file = new File("/Users/danielkenefick/Desktop/Extraprograms/data.txt");
	    FileOutputStream fos = new FileOutputStream (file);
	    PrintStream output = new PrintStream(fos);
	    output.println("number.of.elements\tselection.sort.time\titerative.quicksort.time\trecursive.quicksort.time\tcombined.sort.time");
	
		



	for(int n =0; n< powers; n++){

	    int pwr = (int)Math.pow(2,n);

		int[] array1 = new int[pwr];
		int[] array2 = new int[pwr];
		int[] array3 = new int[pwr];
		int[] array4 = new int[pwr];

	    //generates random array
	    for(int y = 0; y<array1.length; y++){
			int l =  r.nextInt();

			array1[y] = l;
			array2[y] = l; 
			array3[y] = l;
			array4[y] = l;
		    
	    }
		
		//declares the averages of the three sorting techniques.
	    double selavg = 0; 
	    double quickiavg = 0;
	    double quickravg = 0;
		double combineavg = 0;

	    for (int z = 0;z<trials; z++){

		//conducts selection test
		long startTime = System.currentTimeMillis();

		selectionSort(array1,0,array1.length-1);
		
		long endTime = System.currentTimeMillis();
		selavg = selavg + (endTime - startTime);

		//conducts quickiterative test

		startTime = System.currentTimeMillis();

		qsIterative(array2, array2.length);

		
		endTime = System.currentTimeMillis();
		quickiavg = quickiavg + (endTime - startTime);		

		//quick reccursive test

		startTime = System.currentTimeMillis();

		quickSort(array3);
	      
		endTime = System.currentTimeMillis();
		quickravg = quickravg + (endTime - startTime);
		 
		 
			
		// combined method test
			
		startTime = System.currentTimeMillis();
			
		combineRecursive(array4, 0,array4.length - 1);
			
		endTime = System.currentTimeMillis();
		combineavg = combineavg + (endTime - startTime);


	    }
	    
	    //updates average
	    selavg = selavg / trials;
	    quickiavg = quickiavg / trials;
	    quickravg = quickravg / trials;
		combineavg = combineavg / trials;

	    
	    output.println(""+Math.pow(2,n)+"\t"+selavg+"\t"+quickiavg+"\t"+quickravg+"\t"+combineavg);
		
	    

	} 


	} catch (Exception e){
	    System.out.println("unable to create file...or something");
	}
    }


//*********************************Selection sort******************************************
    
	
	//an iterative version of selection sort, to avoid stack overflow memory problems
	//post: returns integers of a in order
    public static void selectionSort(int a[], int start, int finish) {

	int max = finish;
	
		for (int x = finish; x>= start+1; x--){
			
			
			for(int y = start; y<x; y++){
				if(a[y]>a[max]) max = y;
			}
			
			swap(a, max, x);
		}
		

    }
 


    //***********************the quicksort method************************


	
	//a recursive version of quicksort. I quickly ran into a stack overflow error, so 
	//I could only use this one for small array sizes. Adapted form Jeannie's code
	
	//post: returns a in order
    public static void quickSort(int[] data) {

        quickSortRecursive(data, 0, data.length-1);
    }

	
	
    private static int partition(int data[], int left, int right)
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
        
	
	
	
    private static void quickSortRecursive(int data[],
                                           int left,int right)
    // pre: left <= right
    // post: data[left..right] in ascending order
    {
        int pivot;   
        if (left >= right) return;
        pivot = partition(data,left,right);    /* 1 - place pivot */
        quickSortRecursive(data,left,pivot-1); /* 2 - sort small */
        quickSortRecursive(data,pivot+1,right);/* 3 - sort large */
    }


	
	
    public static void swap(int data[], int i, int j)
    // pre: 0 <= i,j < data.length
    // post: data[i] and data[j] are exchanged
    {
        int temp;
        temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

	
//******************************Iterative Quicksort**************************************	
    
	//qsiterative:  I implemented a quicksort algorithm
	//using a stack to avoid stack overflow issues in the normal recursive quicksort. It runs
	//consiterably slower than the recursive version, archtyping the time/space tradeoff. 
	
	//pre: int 0<n <= data.length
	//post: returns elements 0 thru n-1 in order
    public static void qsIterative(int data[], int n){

	Stack<callFrame> calls = new StackList<callFrame>();

	calls.push(new callFrame(0,n-1));

	while(!calls.isEmpty()){
	    callFrame curr = calls.get();

	    if(curr.PC == 1){
		if(curr.low >= curr.high){
		    calls.pop();
		    continue;
		}

		curr.pivot = partition(data,curr.low,curr.high);
		curr.PC++;

		calls.push(new callFrame(curr.low,curr.pivot-1));
	    } else if (curr.PC == 2){
		curr.PC++;
		calls.push(new callFrame(curr.pivot+1, curr.high));
	    } else {
		calls.pop();
		continue;
	    }
	}
	}
	
	//*************************Combined sort********************************************
	
	//a combined method that swithes from quick to select once the array becomes small enough (12 in this example)
	private static void combineRecursive(int data[],
                                           int left,int right)
    // pre: left <= right
    // post: data[left..right] in ascending order
    {
        int pivot;   
        if (left >= right) return;
		if( right - left <= 12){
			selectionSort(data,left,right);
		} else{
		
        pivot = partition(data,left,right);    /* 1 - place pivot */
        combineRecursive(data,left,pivot-1); /* 2 - sort small */
        combineRecursive(data,pivot+1,right);/* 3 - sort large */
		}
    }
	
	
	
}




