import java.util.Comparator;

public class SUComparator implements Comparator<Student>{

    public int compare (Student a, Student b){
	return ( b.SUBox-a.SUBox);
    }
}