
import structure5.*;
import java.util.Iterator;

/**
 * A simple table of definitions for a Context Free Grammar.
 * This particular implementation is very expensive, and we'll see
 * improved implementations in the latter part of the semester.
 * @author duane a. bailey, modified by Stephen Freund
 */
public class DefinitionTable
{
    /**
     * A list of string-definition associations; representation of a
     * "definition table".
     */
    private List<Association<String,Definition>> table;     

    /**
     * Constructs an empty symbol table.
     */
    public DefinitionTable() {
	table = new DoublyLinkedList<Association<String,Definition>>();
    }

    /**
     * Checks for an entry associated with a particular string.
     * @param nonTerminal a string potentially associated with a value.
     * @return true if (and only if) the symbol has an associated value
     * in this table.
     */
    public boolean contains(String nonTerminal) {
	Association<String,Definition> a = 
	    new Association<String,Definition>(nonTerminal,null);
	return table.contains(a);
    }

    /**
     * Adds a string-value association to the table.
     * Assumes that the nonTerminal is not null.  If string has an associated
     * value already, it is removed before adding the new association.
     * @param nonTerminal a non-null string
     * @param value a Definition associated with a string.
     */
    public void add(String nonTerminal, Definition value) {
	Association<String,Definition> a = 
	    new Association<String,Definition>(nonTerminal,value);
	if (table.contains(a)) table.remove(a);
	table.addLast(a);
    }

    /**
     * Gets a value associated with a string, from the table.
     * @param nonTerminal the string whose value is sought
     * @return the Definition associated with the string, or null, if none.
     */
    public Definition get(String nonTerminal) {
	Association<String,Definition> a = 
	    new Association<String,Definition>(nonTerminal,null);
	if (table.contains(a)) {
	    a = table.remove(a);
	    table.addLast(a);
	    return a.getValue();
	} else {
	    return null;
	}
    }


    /**
     * Returns a string representation of the table.
     * @return a string representation of the table.
     */
    public String toString() {
	String result = "";
	for (int i = 0; i < table.size(); i++) {
	    Association<String,Definition> a = table.get(i);
	    result = result + a.getKey() +
		" {" + "\n" + a.getValue() + "}\n\n";
	}
	return result;
    }

    /**
     * An example method that makes use of a definition table.
     */
    public static void main(String args[]) {
	DefinitionTable table = new DefinitionTable();
	Definition def = new Definition();
	// sometime later:
	table.add("<start>", def);
	// sometime even later:
	if (table.contains("<start>")) {
	    def = table.get("<start>");
	    System.out.println(def);
	}
    }
}
