import java.util.Comparator;
import structure5.*;

public class countComparator implements Comparator<Association <String,Integer> >{
    


    public int compare(Association<String,Integer> a,
		       Association<String,Integer> b){
	
	return b.getValue()- a.getValue();

    }

}
