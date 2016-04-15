//a callframe class to help with the iterative / stack based version of quicksort

public class callFrame{
    int pivot; //location of pivot
    int low;  //lowindex
    int high;  //high index
    int PC;   //the next statement to be done

    public callFrame(int low, int high){
	this.low = low;
	this.high = high;
	this.PC = 1;
    }
}
	