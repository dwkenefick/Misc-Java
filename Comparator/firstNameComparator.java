import java.util.Comparator;

public class firstNameComparator implements Comparator<Student>{

    public int compare (Student a, Student b){
	return a.FirstName.compareTo(b.FirstName);
    }

}