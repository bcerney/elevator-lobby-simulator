package com.elevatorsim;

public class Main {

	public static void main (String[] args) {
		
		ElevatorLobby lobby = new ElevatorLobby(200, 150, 1600, 10);
		
		System.out.println(lobby);
		lobby.printElevators();
		
		lobby.generateRiders(15000);
		lobby.printRiders();
		
		
		lobby.fillElevators();
		lobby.printElevators();
		
		lobby.deliverRidersAllElevators();
		
		System.out.println(lobby);
	}
	
}
