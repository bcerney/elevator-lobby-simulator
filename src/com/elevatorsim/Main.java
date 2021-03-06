package com.elevatorsim;

public class Main {

	public static void main (String[] args) {
		Stopwatch stopwatch = new Stopwatch();
		ElevatorLobby lobby = new ElevatorLobby(20, 100, 1600, 10);
		
		System.out.println(lobby);
		
		lobby.generateRiders(150000);
		lobby.printRiders();
		
		
		lobby.fillElevators();
		lobby.printElevators();
		
		lobby.deliverRidersAllElevators();
		
		System.out.println(lobby);
		
		System.out.println("Total runtime: " + stopwatch.elapsedTime() + " seconds");
	}
	
}
