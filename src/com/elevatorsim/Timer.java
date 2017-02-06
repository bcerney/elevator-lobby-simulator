package com.elevatorsim;

public class Timer {
	private int totalMinutes;
	
	public Timer() {
		totalMinutes = 0;
	}
	
	@Override
	public String toString() {
		if (totalMinutes > 59) {
			return "Delivery Time in Hours: " + totalMinutesToHours();
		}
		return "Total Minutes: " + totalMinutes;
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	public int totalMinutesToHours() {
		double minutesToHours = (double) totalMinutes / 60;
		return (int) minutesToHours;
	}
	
	public void addMinutes(int minutes) {
		totalMinutes += minutes;
	}
	
	public void addMinutesFromTimer(Timer timer) {
		totalMinutes += timer.getTotalMinutes();
	}
	
	public void reset() {
		totalMinutes = 0;
	}

}
