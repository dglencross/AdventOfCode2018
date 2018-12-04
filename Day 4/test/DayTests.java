package day4;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import day4.Day;

public class DayTests {

	private Day day;
	
	@Before
	public void setup() {
		this.day = new Day(1,1);
	}
	
	@Test
	public void check_sleep_is_written_properly() {
		
		this.day.guardFallsAsleep("00:05");
		this.day.guardWakesUp("00:25");
		this.day.guardFallsAsleep("00:30");
		this.day.guardWakesUp("00:55");
		
		String expected = ".....####################.....#########################.....";
		Assert.assertEquals(expected, this.day.getHour());
		
	}
	
}
