import java.util.HashSet;
import java.util.Set;

public class Fabric {

	private Cell[][] Grid;
	private Set<Integer> recordedIds;
	
	public Fabric(int size) {
		Grid = new Cell[size][size];
		recordedIds = new HashSet<Integer>();
		
		for (int x= 0; x < Grid.length; x++) {
			for (int y = 0; y < Grid[0].length; y++) {
				Grid[x][y] = new Cell();
			}
		}
	}
	
	public void AddGrid(int id, int distanceFromLeft, int distanceFromTop, int width, int height) {
		
		if (!recordedIds.contains(id)) {
			recordedIds.add(id);
		}
		
		for (int x= distanceFromLeft; x < distanceFromLeft + width; x++) {
			for (int y = distanceFromTop; y < distanceFromTop + height; y++) {
				Grid[x][y].AddId(id);
			}
		}
		
	}
	
	// e.g. #1 @ 1,3: 4x4
	public void InputLine(String line) {
		String[] bits = line.split(" ");
		
		int id = Integer.valueOf(bits[0].replace("#", ""));
		
		int distanceFromLeft = Integer.valueOf(bits[2].split(",")[0]);
		int distanceFromTop = Integer.valueOf(bits[2].split(",")[1].replace(":", ""));
		
		int width = Integer.valueOf(bits[3].split("x")[0]);
		int height = Integer.valueOf(bits[3].split("x")[1]);
		
		AddGrid(id, distanceFromLeft, distanceFromTop, width, height);
	}
	
	public int CountOverlappedCells() {
		int count = 0;
		
		for (int x= 0; x < Grid.length; x++) {
			for (int y = 0; y < Grid[0].length; y++) {
				if (Grid[x][y].CountIds() > 1) {
					count += 1;
				}
			}
		}
		
		return count;
	}
	
	public int FindIntactClaim() {
		// assuming, as per the puzzle, there is only 1 intact claim
		
		Set<Integer> intactIds = new HashSet<Integer>();
		intactIds.addAll(recordedIds);
		
		for (int x= 0; x < Grid.length; x++) {
			for (int y = 0; y < Grid[0].length; y++) {
				
				Set<Integer> ids = Grid[x][y].GetIds();
				
				if (ids.size() > 1) {
					intactIds.removeAll(ids);
				}
			}
		}
		
		return intactIds.iterator().next();
	}
	
	public void PrintGrid() {
		for (int x= 0; x < Grid.length; x++) {
			for (int y = 0; y < Grid[0].length; y++) {
				System.out.print(Grid[y][x].GetPrintableId());
			}
			System.out.println();
		}
	}
	
	
}
