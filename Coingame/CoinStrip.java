import java.util.Random;
import java.util.Scanner;


/* CoinStrip, by Dan Kenefick. A program that contains the tools to construct and play the coin game. 
 */


public class CoinStrip {

   //The maximum number of coins and spaces, respectivly
   private final static int MAXCOINS = 6;
   private final static int MAXSPACES = 20;
   
    //the minimum number of coins and spaces 
   private final static int MINCOINS = 2;
   private final static int MINSPACES = 3;

   //the number of coins and spaces, respectivly
   private int coins;
   private int spaces;

   //the board state
   private int[] data;

   // Boolean keeping track of valid board
    private boolean ValidBoard = false;

   //a random number generator for various methods
   private Random r = new Random();


    public CoinStrip() {
    //post: initializes data to a random board. 


	while (!ValidBoard){
	    ConstructBoard();
	    ValidBoard = IsLegalBoard();
	}
    
	SetUpBoard();
	System.out.println(toString());

    }
    




    public CoinStrip (int numspaces, int numcoins){
	//post: creates a random board with given parameters

	//clears terminal

	spaces = numspaces;
	coins = numcoins;

	if (IsLegalBoard()){
	     SetUpBoard();
	     System.out.println(toString());
	}
	else{
	    System.out.println("Sorry, invalid board");
		}
    }






    private void ConstructBoard(){
    //post: constructs a new board
    
    //creates the data board within parameters
    spaces = r.nextInt(MAXSPACES-MINSPACES+1)+MINSPACES;


    //establishes the number of coins
    coins = r.nextInt(MAXCOINS-MINCOINS+1)+MINCOINS;
    }
    




    private boolean IsLegalBoard(){
    //post: returns true if board is legal
    return coins < spaces-1;
    }



    private void SetUpBoard(){
    //pre: coins & spaces are non null
    //post: creates a random board configuration 

    data = new int[spaces];  
    boolean boardready = false;
    
    //initializes board for shuffling
    for (int c = 1; c <= coins; c++){
        data[c-1] = c;
     }
    
    
    //shuffles board, swapping random pairs
    while (!boardready){
        for (int x = 0; x < spaces; x++){
        int rand = r.nextInt(spaces);
        int temp = data[rand];
        data [rand] = data[x];
        data[x] = temp;
        boardready = !isFinishedBoard();
        }
    }

    }





    public boolean isFinishedBoard(){
    //pre: board is non null
    //post: returns true if the board is finished
    
    for(int x = 0; x < coins; x++){
        if( data[x] == 0)
        return false;
    }
    return true;
    }




    public String toString(){
    //pre: data is non null 
    //post: prints a textual rep. of the game board

    String output = "";
    for (int x = 0; x < spaces; x++){
        if (data[x] == 0){
        output = output + "[ ]"; 
         } else {
        output = output + "("+data[x]+")";
        }
    }

    return output;
    }




    private boolean isLegalMove( int coin, int spacesleft){
    // pre: game board is in valid configuration
    //post: returns true iff move is valid

    //makes sure the number of coins is valid
    if (coin > coins) return false;


    //gets the index of the coin
    int i;
    for (i = 0; i < spaces; i++){
        if (data[i] == coin){
        break;
        }
    }

    //checks if desired move is in bounds of the aray
    if (i - spacesleft < 0) return false;

    //checks for obsticles in way of move
    for (int s = i-1; s >= i - spacesleft; s--){
        if ( data[s] !=0) return false;
    }

    return true;
    }

    


    //create a move class?
    public void move ( int coin, int spacesleft ){
    //pre: move is legal
    //post: changes data to post move configuration and prints out new game         board


	if  ( isLegalMove(coin,spacesleft)){

	    //gets the index of the coin
	    int i;
	    for (i = 0; i < spaces; i++){
		if (data[i] == coin){
		    break;
		}
	    }
        
	    //moves te coin to a new space
	    data[i-spacesleft] = coin;
	    data[i] = 0;

	    //prints the new board
	    System.out.println(toString());
	}
	else{
	    System.out.println("Sorry, Invalid Move.");
	    System.out.println(toString());
	}
    }
     
        
        

}

