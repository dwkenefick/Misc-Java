import java.util.Scanner;
import java.lang.Runtime;

public class CoinGame1{


    public static void main (String args[]){
    //post: excecutes coinstrip game
   
	//initializes game
	CoinStrip game;
    
	//prints instructions
	System.out.println("welcome to the coin game! The goal of this 2 or more person game is to move all of the coins to the left. coins can move any number of spaces to the left, but cannot jump over other coins. The last person to move wins!");
    
	//asks user if they want a specified board
	System.out.println("would you like to specify your own board? y/n ");

	//scanner for input
	Scanner input = new Scanner (System.in);

	//if the user wants a specialized board, this prompts them for the param        eters
	if (input.next().equals("y")){
	    System.out.println("how many spaces? Must be at least 3");
	    int sp = input.nextInt();

	    System.out.println("how many coins? Must be at least 2");
	    int co = input.nextInt();

	    game = new CoinStrip(sp,co);

	//otherwise, constructs a random board    
	}else {game = new CoinStrip();}


    

    
    int coin;
    int spacesleft;

    //asks for moves until the game is finished

    while (!game.isFinishedBoard()){
    System.out.println ("Pick a coin to move");
    coin = input.nextInt();
    System.out.println("Move how many spaces left?");
    spacesleft = input.nextInt();
   
    game.move(coin, spacesleft);
    }
    
    //prints a victory message. 
    System.out.println("congratulations! you won.");
    }
}
	    