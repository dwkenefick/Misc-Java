public class chainExists{

    public static void main(String args[]){

	Domino[] dominos = new Domino[5];

	dominos[0] = new Domino(6,2);
	dominos[1] = new Domino(4,3);
	dominos[2] = new Domino(2,5);
	dominos[3] = new Domino(5,6);
	dominos[4] = new Domino(1,5);

	int a = Integer.parseInt(args[0]);
	int b = Integer.parseInt(args[1]);

	System.out.println(chainExists(dominos, a,b));

    }




    public static boolean chainExists(Domino dominos[], int start, int finish){

	for (int x =0; x<dominos.length;x++){

	    //only procede if domino is unused
	    if(!dominos[x].isUsed()){

		
		if(dominos[x].firstSide() == start){

		    //if 1st, 2nd side of domino match start, finish we have found a chain
		    if (dominos[x].secondSide() == finish) return true;

		    //if not, we can still use it in a chain
		    else{
			
			//we "use" the domino
			dominos[x].use();

			//we check if a chain exisets, using the dominos second value as the start
			if (chainExists(dominos, dominos[x].secondSide(), finish)) return true;

			//if there was no possible chain, we must reset the domino
			else dominos[x].unUse();
		    }

		}
		//same as above, using flipped domino
		if(dominos[x].secondSide() == start){

		    if (dominos[x].firstSide() == finish) return true;

		    else{
			dominos[x].use();

			if (chainExists(dominos, dominos[x].firstSide(), finish)) return true;

			else dominos[x].unUse();
		    }

		}
	    }
	   
	}
	//if we walk through the  entire set of dominos, then there was no chain
	return false;
    }
	    
	


    }




	

	