
import structure5.*;
import java.util.Random;
import java.util.Scanner;

/**
 * A Random Sentence Generator
 * 
 * This class currently parses a grammar file from standard input.
 * Your job is to extend it to print random sentences from the
 * grammar.
 */
public class RandomSentenceGenerator {

    /** The grammar for the generator */
    static protected DefinitionTable grammar;

    /**
     * Create a new grammar from the given file.
     */
    public RandomSentenceGenerator(Scanner in) {
	grammar = new DefinitionTable();
	readGrammar(in);
    }


    /**
     * post: reads one production and returns it <p> 
     * 
     * Student: Change this to parse and create a production properly.
     */
    protected Vector<String> readOneProduction(Scanner in) {
		
		Vector<String> prodDef = new Vector<String>();
		
	while (true) {
		
		String token = in.next();
		
	    if (token.equals(";")) break;
	    Assert.condition(in.hasNext(), 
			     "End of File occured while parsing production");
		
		
		
		prodDef.add(token);
		
		
	}
	return prodDef;
    }

    /*
	 *pre: Standard in is reading a valid grammar file (.g)
     * post: reads the name and productions for one non-terminal and adds them to grammar.
     */
    protected void readOneNonTerminalDef(Scanner in) {

	String name = in.next();  // the non-terminal name
		

	Assert.condition(in.hasNext(), 
			 "End of File occured while parsing non-terminal " 
			 + name);
	Assert.condition(in.next().equals("{"), 
			 "Expected { for " + name);
		
		//
		Definition defs = new Definition();
		
		
	while (true) {
	    Assert.condition(in.hasNext(), 
			     "Are you missing a } at end of " + name + "}");
		

	    // if we see a } as the next token, break out of the loop,
	    // because we will be done reading productions.
	    if (in.hasNext("}")) break;

	    //adds the productions to the apropriate definition
		defs.add(readOneProduction(in));
		
		
		
	}

	// read the "}" at the end of the definition.
	Assert.condition(in.next().equals("}"), "Error reading } for " + name);
		
		//adds the <non-terminal , Definition> association to gramar
		grammar.add(name,defs);
		
    }


    /**
     * Read a grammar from a file. <p>
     */
    protected void readGrammar(Scanner in) {
	while (in.hasNext()) {
	    readOneNonTerminalDef(in);
	}
    }

    /**
     * Print out the grammar.  Useful for debugging.
     */
    public String toString() {
	return grammar.toString();
    }


	//pre: word is a single word
	//post: recursively expands  if it is a non-terminal, otherwise prints word to standard output.
	
	public static void expandWord(String word){
		
		if (!word.startsWith("<")){
			System.out.print(word+" ");
		} else{
		
			Vector<String> a = grammar.get(word).getRandom();
			int n = a.size();
		
			for (int x = 0; x<n; x++) {
				expandWord(a.get(x));
			}
		}
	}
			
    
	//pre: the grammar file's first non-terminal is <start>
	//post: constructs three random outputs from grammar
	
    public static void main(String args[]) {
	RandomSentenceGenerator rsg = 
	    new RandomSentenceGenerator(new Scanner(System.in));

	// Just print out the grammar for now.
	// Student: change this after you are able to construct
	// the grammar properly.
		System.out.println();
		System.out.println();
		
		expandWord("<start>");
		
		System.out.println();
		System.out.println();
		
		expandWord("<start>");
		
		System.out.println();
		System.out.println();
		
		expandWord("<start>");
		
		System.out.println();
		System.out.println();
    }
}
