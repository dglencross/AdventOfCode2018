import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FabricTests {

	private Fabric fabric;
	
	private List<String> LoadInput() throws FileNotFoundException, IOException {
		
		String file = "/Users/dave/eclipse-workspace/AdventOfCode2018/test/input3.txt";
		List<String> output = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	output.add(line);
		    }
		}
		
		return output;
	}
	
	@Test
	public void grid_should_save_inputs() {
		
		this.fabric = new Fabric(12);
		this.fabric.InputLine("#1 @ 1,3: 4x4");
		this.fabric.InputLine("#2 @ 3,1: 4x4");
		this.fabric.InputLine("#3 @ 5,5: 2x2");
		
		this.fabric.PrintGrid();
		
		Assert.assertEquals(4, this.fabric.CountOverlappedCells());
	}
	
	@Test
	public void should_find_intact_claim() {
		this.fabric = new Fabric(12);
		this.fabric.InputLine("#1 @ 1,3: 4x4");
		this.fabric.InputLine("#2 @ 3,1: 4x4");
		this.fabric.InputLine("#3 @ 5,5: 2x2");
		
		this.fabric.PrintGrid();
		
		Assert.assertEquals(3, this.fabric.FindIntactClaim());
	}
	
	@Test
	public void puzzle_one() throws FileNotFoundException, IOException {
		this.fabric = new Fabric(1000);
		for(String line : LoadInput()) {
			this.fabric.InputLine(line);
		}
		
		Assert.assertEquals(4, this.fabric.CountOverlappedCells());
	}
	
	@Test
	public void puzzle_two() throws FileNotFoundException, IOException {
		this.fabric = new Fabric(1000);
		for(String line : LoadInput()) {
			this.fabric.InputLine(line);
		}
		
		Assert.assertEquals(656, this.fabric.FindIntactClaim());
	}
	
}
