import structure5.*;
import java.util.Random;

/*
 * A class to store one non-terminal and its productions.
 * You should not need to modify this class.
 */
public class Definition {
    // Each production in this vector is a Vector of Strings.
	
    protected Vector<Vector<String>> productions;
	
	protected static Random r = new Random();

    /**
     * Create a new definition for a non-terminal
     */
    public Definition() {
	productions = new Vector<Vector<String>>();
    }

    /**
     * pre:  prod is a non-null vector of Strings  <p>
     * post: prod is added as a production for the definition.
     */
    public void add(Vector<String> prod) {
	productions.add(prod);
    }

    /**
     * post: return the number of productions 
     */
    public int size() {
	return productions.size();
    }

    /**
     * pre:  0 <= index < size()
     * post: return the production at given index
     */
    public Vector<String> get(int index) {
	return productions.get(index);
    }

    /**
     * Print out the productions for this definition.
     */
    public String toString() {
	String s = "";
	for (int i = 0; i < productions.size(); i++) {
	    s += "      ";
	    Vector<String> prodVec = productions.get(i);
	    for (int j = 0; j < prodVec.size(); j++) {
		s += prodVec.get(j) + " ";
	    }
	    s += "\n";
	}
	return s;
    }
	
	//pre: this has been initialized and has at least one production asociated
	//with it
	//post: returns a random production as a vector of String words. 
	//I know this is technicaly bad data astraction, since we shouldnt reveal the implimentation 
	//by returning a vector, but this greatly simplifies the problem. I could rewrite 
	// the interface so everything is consitant, or rewrite the method to use a diffrent data 
	//structure, always returning a vector of Strings. 
	public Vector<String> getRandom(){
		
		Assert.pre(size()>0, "data must be initialized");
		
		int n = r.nextInt(size());
		
		return get(n);
	}
		
		
	
}
