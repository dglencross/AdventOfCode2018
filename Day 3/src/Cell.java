import java.util.HashSet;
import java.util.Set;

public class Cell {

	private Set<Integer> ids;
	
	public Cell() {
		ids = new HashSet<Integer>();
	}
	
	public void AddId(int id) {
		ids.add(id);
	}
	
	public int CountIds() {
		return ids.size();
	}
	
	public Set<Integer> GetIds() {
		return ids;
	}
	
	public String GetPrintableId() {
		if (ids.isEmpty()) {
			return "#";
		}
		
		if (CountIds() == 1) {
			return String.valueOf(ids.iterator().next());
		}
		
		return "X";
	}
	
}
