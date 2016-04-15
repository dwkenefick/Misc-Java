//the initial list should be of asociations: strings lists. that way we can keep track of the sites we've visited so far.
import structure5.*;

import java.util.Iterator;

/*Webcrawler: a program that finds the maximum distance between two pages on a given network
 */


public class WebCrawler2 {

    protected String startpage;

    protected String endpage;

    //    The datastructure holding the sites we are testing
    protected Stack<Vector<String>> sites;

    //the longest distance we've encountered while testing
    protected int highestdistancesofar = 0;

    //the section of web we are concerned with
    protected String websection;


    //the chain of sites we've visited, to be returned once completed
    protected Vector<String> sitechain = new Vector<String>();



    //a test method 
    public static void main (String[] args){
		
		if(args.length >0){
		
			//updates the websection we are concerned with
			String section = args[0];
		
			//the beginning site
			String start = args[1];
			
			//the end site
			String end = args[2];
			
			new WebCrawler2(start, end, section);
		}
		
		else{
		
		
			new WebCrawler2("http://www.cs.williams.edu/~jeannie/cs136/index.html","http://www.cs.williams.edu/~jeannie/cs136/unix/emacs.html", "http://www.cs.williams.edu/~jeannie/cs136/");
		}
	}

    //Pre: start and finish are valid web pages
    //post: retunrs the maximium number of links between start in finish within a web
    public WebCrawler2(String start, String end, String section){

	//initializes variables
	startpage = start;

	endpage = end;
	
	//updates the section we are concerned with
	websection = section;

	//assers the start and end pages are diffrent. 
	Assert.pre(!startpage.equals(endpage),"Start and end pages must be diffrent");

	//the stack contining the sites to visit
	sites = new StackList();

	//puts the startpage on the stack.

	Vector<String> firstvector = new Vector<String>();

	firstvector.add(startpage);

	sites.push(firstvector);

	findDistance();

	if (highestdistancesofar != 0){
	    System.out.println("the higest distance is "+highestdistancesofar+", and the sites are:");
	    System.out.println(sitechain);

	}

	else System.out.println("No chain found");

    }




    public void findDistance(){

	if(sites.isEmpty()){
	    return;
	}

	else{
	    
	    Vector<String> current = sites.pop();

	    String site = current.getLast();

	    if(site.equals(endpage)){
		int count = current.size();

		if (count > highestdistancesofar){
		    
		    highestdistancesofar = count;
		    
		    sitechain = current;

		    return;

		}

		
	    }

	    else{

		HTML page = new HTML (site);
		String URL = "";

		while (page.hasNext()){
		    URL = page.nextURL();

		    //checks if its a valid URL
		    if ((!current.contains(URL))&&URL.startsWith(websection)){
			//clones the current stack
			Vector<String> nextstack = coppy(current);

			nextstack.addLast(URL);

			sites.push(nextstack);

			findDistance();
		    }
		}
	    }

	    findDistance();
	}
    }


    //pre: 
    //post: coppies a stacklist. utility method

    protected Vector coppy(Vector original){
	Vector duplicate = new Vector<String>(); 

	Iterator it = original.iterator();

	while(it.hasNext()){
	    duplicate.add(it.next());
	}

	return duplicate;

    }


}