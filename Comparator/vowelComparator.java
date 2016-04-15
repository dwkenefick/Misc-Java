import java.util.Comparator;

public class vowelComparator implements Comparator<Student>{

    public int compare (Student a , Student b){
	String n1 = a.FirstName.toLowerCase();
	String n2 = b.FirstName.toLowerCase();

	int v1 = 0;

	for (int x = 0; x< n1.length(); x++){
	    if(
	       n1.charAt(x) == 'a'||
	       n1.charAt(x) == 'e'||
	       n1.charAt(x) == 'i'||
	       n1.charAt(x) == 'o'||
	       n1.charAt(x) == 'u')

		v1 = v1 +1;

	}

	int v2 = 0;
	       
	for (int x = 0; x< n2.length(); x++){
	    if(
	       n2.charAt(x) == 'a'||
	       n2.charAt(x) == 'e'||
	       n2.charAt(x) == 'i'||
	       n2.charAt(x) == 'o'||
	       n2.charAt(x) == 'u')

		v2 = v2 +1;

	}

	return (v2-v1);

    }
}