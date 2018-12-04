package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import day4.Day;
import day4.Records;

public class RecordsTests {

	private Records records;
	
	@Before
	public void setup() {
		records = new Records();
	}
	
	@Test
	public void check_we_can_add_a_record() throws ParseException {
		String line = "[1518-11-01 00:00] Guard #10 begins shift";
		
		records.addRecord(line);
		
		Map<Date, Day> days = records.getDays();
		Assert.assertEquals(1, days.size());
	}
	
	@Test
	public void test_sample_input() throws FileNotFoundException, IOException, ParseException {
		List<String> input = LoadInput("sample_input");
		
		this.records.addRecords(input);
		
		Map<Date, Day> days = records.getDays();
		
		for(Day day : days.values()) {
			day.printDay();
		}
	}
	
	@Test
	public void test_guard_who_sleeps_most_minutes() throws FileNotFoundException, IOException, ParseException {
		List<String> input = LoadInput("sample_input");
		
		this.records.addRecords(input);
		
		Assert.assertEquals(10, (int)this.records.getSleepiestGuard());
	}
	
	@Test
	public void test_get_sleepiest_minute_for_guard() throws FileNotFoundException, IOException, ParseException {
		List<String> input = LoadInput("sample_input");
		
		this.records.addRecords(input);
		
		int guardId = (int)this.records.getSleepiestGuard();
		Assert.assertEquals(10, guardId);
		
		int sleepiestMinute = this.records.sleepiestMinuteForGuard(guardId).mostSleptMinute;
		Assert.assertEquals(24, sleepiestMinute);
	}
	
	@Test
	public void puzzle_one() throws FileNotFoundException, IOException, ParseException {
		List<String> input = LoadInput("input");
		
		this.records.addRecords(input);
		
		int guardId = (int)this.records.getSleepiestGuard();
		
		int sleepiestMinute = this.records.sleepiestMinuteForGuard(guardId).mostSleptMinute;
		
		Assert.assertEquals(115167, sleepiestMinute * guardId);
	}
	
	@Test
	public void test_sample_puzzle_two() throws FileNotFoundException, IOException, ParseException  {
		List<String> input = LoadInput("sample_input");
		
		this.records.addRecords(input);
		
		Guard guard = this.records.sleepiestMinuteForAnyGuard(this.records.getGuardIds());
		
		Assert.assertEquals(4455, guard.mostSleptMinute * guard.id);
	}
	
	@Test
	public void puzzle_two() throws FileNotFoundException, IOException, ParseException  {
		List<String> input = LoadInput("input");
		
		this.records.addRecords(input);
		
		Guard guard = this.records.sleepiestMinuteForAnyGuard(this.records.getGuardIds());
		
		Assert.assertEquals(32070, guard.mostSleptMinute * guard.id);
	}
	
	private List<String> LoadInput(String fileName) throws FileNotFoundException, IOException {
		
		String file = "/Users/dave/eclipse-workspace/AdventOfCode2018/test/day4/" + fileName;
		List<String> output = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	output.add(line);
		    }
		}
		
		return output;
	}
}
