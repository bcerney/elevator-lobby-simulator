package com.elevatorsim;

public class Timer {
	private int totalMinutes;
	
	public Timer() {
		totalMinutes = 0;
	}
	
	@Override
	public String toString() {
		return "Total Minutes: " + totalMinutes;
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
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
