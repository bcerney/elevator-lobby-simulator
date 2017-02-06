package com.elevatorsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ElevatorLobby {
	private int numberOfElevators;
	private int numberOfFloors;
	private int maxWeightPerElevator;
	private int maxRidersPerElevator;
	
	private List<Elevator> elevators;
	private Queue<Rider> riderQueue;
	private Timer lobbyTimer;
	
	public ElevatorLobby(int numberOfElevators, int numberOfFloors, 
							int maxWeightPerElevator, int maxRidersPerElevator) {
		this.numberOfElevators = numberOfElevators;
		this.numberOfFloors = numberOfFloors;
		this.maxWeightPerElevator = maxWeightPerElevator;
		this.maxRidersPerElevator = maxRidersPerElevator;
		
		elevators = new ArrayList<Elevator>();
		addElevators(numberOfElevators, numberOfFloors, maxWeightPerElevator, maxRidersPerElevator);
		lobbyTimer = new Timer();
	}
	
	@ Override
	public String toString() {
		String newline = System.getProperty("line.separator");
		
		return "Elevator Lobby:" + newline + newline +
				"# of Elevators = " + numberOfElevators + newline +
				"# of Floors = " + numberOfFloors + newline +
				"Elevator Max Weight: " + maxWeightPerElevator + " lbs." + newline +
				"Elevator Max Riders: " + maxRidersPerElevator + newline +
				lobbyTimer.toString() + newline;
	}
	
	public List<Elevator> getElevators() {
		return elevators;
	}
	
	public void printElevators() {
		for(Elevator elevator : elevators) {
			System.out.println(elevator);
			elevator.printRiders();
			System.out.println();
		}
	}
	
	public Elevator getElevatorByShaftNumber(int shaftNumber) {
		return elevators.get(shaftNumber);
	}

	public Queue<Rider> getRiderQueue() {
		return riderQueue;
	}

	public int getNumberOfElevators() {
		return numberOfElevators;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public int getMaxWeightPerElevator() {
		return maxWeightPerElevator;
	}

	public int getMaxRidersPerElevator() {
		return maxRidersPerElevator;
	}

	public void addElevators(int numberOfElevators, 
								int numberOfFloors, 
								int maxWeightPerElevator, 
								int maxRidersPerElevator) {
		for (int i = 0; i < numberOfElevators; i++) {
			elevators.add(new Elevator(i, numberOfFloors, maxWeightPerElevator, maxRidersPerElevator));
		}
	}
	
	public void generateRiders(int numberOfRiders) {
		riderQueue = new ArrayBlockingQueue<Rider>(numberOfRiders);
		
		
		for (int i = 0; i < numberOfRiders; i++) {
			int destinationFloor = (int) (Math.random() * numberOfFloors) + 1;
			int weightInPounds = (int) (Math.random() * 300) + 1;
			
			riderQueue.add(new Rider(destinationFloor, weightInPounds));
		}
	}
	
	public void printRiders() {
		for (Rider rider : riderQueue) {
			System.out.println(rider);
		}
	}
	
	public void addRidersToElevator(int shaftNumber) {
		Elevator elevator = elevators.get(shaftNumber);

		while (isRidersInQueue() && elevator.isRoomOnElevator()) {
			if (elevator.riderCanFit(riderQueue.peek())) {
				elevator.addRider(riderQueue.poll());
			} else break;
		}
	}

	public void fillElevators() {
		for (Elevator elevator : elevators) {
			while (isRidersInQueue() && elevator.isRoomOnElevator()) {
				if (elevator.riderCanFit(riderQueue.peek())) {
				elevator.addRider(riderQueue.poll());
				} else break;
			}
			elevator.sortRidersByDestinationFloor();
		}
	}
	
	public void deliverRidersAllElevators() {
		Timer slowestElevator = new Timer();
		
		for (Elevator elevator : elevators) {
			Timer totalDeliveryTime = elevator.fullElevatorDeliverRiders();
			
			if (totalDeliveryTime.getTotalMinutes() > slowestElevator.getTotalMinutes()) {
				slowestElevator = totalDeliveryTime;
				System.out.println("Elevator " + elevator.getShaftNumber() + " has returned.");
			}
		}
		
		lobbyTimer.addMinutesFromTimer(slowestElevator);
		System.out.println(lobbyTimer);
		
		if (isRidersInQueue()) {
			fillElevators();
			deliverRidersAllElevators();
		}
	}
	
	public boolean isRidersInQueue() {
		return !riderQueue.isEmpty();
	}
	
	

}
