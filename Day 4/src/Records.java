package day4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Records {

	public Map<Date, Day> days;
	private List<Integer> guardIds;
	
	public Records() {
		days = new HashMap<>();
		guardIds = new ArrayList<>();
	}
	
	public List<String> sortLines(List<String> lines) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		Map<Date, String> unsortedLines = new HashMap<>();
		
		for (String line : lines) {
			String timestamp = line.split("]")[0].replace("[", "");
			Date date = dateFormat.parse(timestamp);
			
			unsortedLines.put(date, line);
		}
		
		Map<Date, String> sortedMap = new TreeMap<>(unsortedLines);
		
		List<String> sortedLines = new ArrayList<>();
		
		for (Map.Entry<Date, String> entry : sortedMap.entrySet()) {
            sortedLines.add(entry.getValue());
        }
		
		return sortedLines;
	}
	
	public Integer getSleepiestGuard() {
		
		Integer guardId = null;
		int mostMinutesSlept = 0;
		
		TreeMap<Integer, Integer> sleepyGuards = new TreeMap<>();
		
		for(Day day : days.values()) {
			Integer id = day.getGuardId();
			Integer minutesSlept = null;
			
			if (sleepyGuards.containsKey(id)) {
				minutesSlept = sleepyGuards.get(id);
				minutesSlept += day.getMinutesSlept();
			} else {
				minutesSlept = day.getMinutesSlept();
			}
			sleepyGuards.put(id, minutesSlept);
		}
		
		for (Map.Entry<Integer, Integer> entry : sleepyGuards.entrySet()) {
            
			Integer id = entry.getKey();
			Integer minutesSlept = entry.getValue();
			
			if (minutesSlept > mostMinutesSlept) {
				guardId = id;
				mostMinutesSlept = minutesSlept;
			}
			
        }
		
		return guardId;
	}
	
	public Guard sleepiestMinuteForGuard(int guardId) {
		
		int[] minutes = new int[60];
		
		for(Day day : days.values()) {
			if (day.getGuardId() == guardId) {
				for(int i=0; i< 60; i++) {
					
					if ("#".equals(day.getMinutes()[i].getStatus())) {
						minutes[i] += 1;
					}
				}
			}
		}
		
		int sleepiestMinute = 0;
		int max = 0;
		
		for(int i=0; i< 60; i++) {
			if (minutes[i] > max) {
				sleepiestMinute = i;
				max = minutes[i];
			}
		}
		
		Guard g = new Guard(guardId);
		g.mostSleptMinute = sleepiestMinute;
		g.timesSleptThatMinute = max;
		
		return g;
	}
	
	public Guard sleepiestMinuteForAnyGuard(List<Integer> guardIds) {
		
		List<Guard> guards = new ArrayList<>();
		
		for(Integer id : guardIds) {
			Guard g = sleepiestMinuteForGuard(id);
			guards.add(g);
		}
		
		Guard sleepiestGuard = guards.get(0);
		
		for(Guard g : guards) {
			if (g.timesSleptThatMinute > sleepiestGuard.timesSleptThatMinute) {
				sleepiestGuard = g;
			}
		}
		
		return sleepiestGuard;
	}
	
	public void addRecord(String line) throws ParseException {
		
		// e.g. "[1518-11-01 00:00] Guard #10 begins shift"
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String timestamp = line.split("]")[0].replace("[", "");
		Date date = dateFormat.parse(timestamp);
		
		String[] bits = line.split(" ");
		
		String dateString = bits[0].replace("[", "");
		String time = bits[1].replace("]", "");
		int month = Integer.valueOf(dateString.split("-")[1]);
		int dayNumber = Integer.valueOf(dateString.split("-")[2]);
		
		if (date.getHours() != 0) {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, 1);
	        date = cal.getTime();
		}
		
		date.setHours(0);
		date.setMinutes(0);
		
		Day day = null;
		if (days.containsKey(date)) {
			day = days.get(date);
		} else {
			day = new Day(month, dayNumber);
		}
		
		if ("guard".equals(bits[2].toLowerCase())) {
			int guardId = Integer.valueOf(bits[3].replace("#", ""));
			day.guardBeginsShift(guardId);
			
			if (!guardIds.contains(guardId)) {
				guardIds.add(guardId);
			}
		} else if ("falls".equals(bits[2].toLowerCase())) {
			day.guardFallsAsleep(time);
		} else if ("wakes".equals(bits[2].toLowerCase())) {
			day.guardWakesUp(time);
		}
		
		days.put(date, day);
		
	}
	
	public Map<Date, Day> getDays() {
		return new TreeMap<Date, Day>(this.days);
	}

	public void addRecords(List<String> input) throws ParseException {
		
		List<String> sortedInput = sortLines(input);
		
		for(String line : sortedInput) {
			addRecord(line);
		}
		
	}

	public List<Integer> getGuardIds() {
		return guardIds;
	}

	public void setGuardIds(List<Integer> guardIds) {
		this.guardIds = guardIds;
	}
}
