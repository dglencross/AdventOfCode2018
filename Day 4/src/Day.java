package day4;

import java.util.Date;

public class Day {

	private Minute[] minutes;
	private int dayNumber;
	private int month;
	private Integer guardId;
	
	public Day(int month, int dayNumber) {
		this.dayNumber = dayNumber;
		this.month = month;
		
		minutes = new Minute[60];
		
		for(int i=0; i<60; i++) {
			minutes[i] = new Minute();
		}
	}
	
	public Integer getGuardId() {
		return guardId;
	}
	public void setGuardId(Integer guardId) {
		this.guardId = guardId;
	}

	// e.g. 00:00
	public void guardBeginsShift(int guardId) {
		setGuardId(guardId);
	}
	
	public void guardWakesUp(String time) {
		String[] bits = time.split(":");
		int hour = Integer.valueOf(bits[0]);
		int minute = Integer.valueOf(bits[1]);
		
		if (hour != 0) {
			hour = 0;
			minute = 0;
		}
		
		boolean stillWriting = true;
		int count = minute;
		
		while(stillWriting) {
			
			if (count > 59) {
				stillWriting = false;
				continue;
			}
			
			Minute m = minutes[count];
			
			if ("#".equals(m.getStatus())) {
				m.setStatus(".");
				count += 1;
			} else {
				stillWriting = false;
			}
		}
	}
	
	public void guardFallsAsleep(String time) {
		String[] bits = time.split(":");
		int hour = Integer.valueOf(bits[0]);
		int minute = Integer.valueOf(bits[1]);
		
		if (hour != 0) {
			hour = 0;
			minute = 0;
		}
		
		boolean stillWriting = true;
		int count = minute;
		
		while(stillWriting) {
			
			if (count > 59) {
				stillWriting = false;
				continue;
			}
			
			Minute m = minutes[count];
			
			if (".".equals(m.getStatus())) {
				m.setStatus("#");
				count += 1;
			} else {
				stillWriting = false;
			}
		}
	}
	
	public String getHour() {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<60; i++) {
			builder.append(minutes[i].getStatus());
		}
		return builder.toString();
	}

	public void printDay() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(month).append("-").append(formattedDayNumber());
		builder.append(" ").append("#").append(guardId).append(" ");
		builder.append(getHour());
		
		System.out.println(builder);
	}
	
	public int getMinutesSlept() {
		int count = 0;
		
		for(Minute m : minutes) {
			if ("#".equals(m.getStatus())) {
				count += 1;
			}
		}
		
		return count;
	}
	
	private String formattedDayNumber() {
		if (dayNumber > 9) {
			return String.valueOf(dayNumber);
		}
		return "0" + String.valueOf(dayNumber);
	}

	public Minute[] getMinutes() {
		return minutes;
	}
	
}
