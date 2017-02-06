package com.elevatorsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Elevator {
	private int shaftNumber;
	private int numberOfFloors;
	private int maxRiders;
	private int maxWeight;
	private Timer timer;
	
	private List<Rider> currentRiders;
	private int currentFloor;

	public Elevator(int shaftNumber, 
					int numberOfFloors, 
					int maxWeight, 
					int maxRiders) {
		setShaftNumber(shaftNumber);
		setNumberOfFloors(numberOfFloors);
		this.maxRiders = maxRiders;
		this.maxWeight = maxWeight;
		timer = new Timer();
		
		currentRiders = new ArrayList<Rider>();
		currentFloor = 1;
	}
	
	@ Override
	public String toString() {
		String newline = System.getProperty("line.separator");
		
		return "Elevator #"+ shaftNumber + newline +
				"# of Floors = " + numberOfFloors + newline +
				"Max Weight = " + maxWeight + newline +
				"Current Weight: " + getRiderTotalWeight() + newline + 
				"Max Riders = " + maxRiders + newline +
				"Current # of Riders = " + getNumberOfRiders() + newline; 
	} 
	
	public int getShaftNumber() {
		return shaftNumber;
	}

	public void setShaftNumber(int shaftNumber) {
		if (shaftNumber > -1) {
			this.shaftNumber = shaftNumber;
		}
	}

	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	public int getNumberOfFloors() {
		return numberOfFloors;
	}
	public void setNumberOfFloors(int numberOFloors) {
		if (numberOFloors > -1) {
			this.numberOfFloors = numberOFloors;
		}
	}

	public int getMaxRiders() {
		return maxRiders;
	}

	public int getMaxWeight() {
		return maxWeight;
	}
	
	public int getNumberOfRiders() {
		return currentRiders.size();
	}
	
	public void printRiders() {
		System.out.println("Current Riders:");
		for (Rider rider : currentRiders) {
			System.out.println(rider);
		}
	}
	
	public int getRiderTotalWeight() {
		int riderTotalWeight = 0;
		
		for (Rider rider : currentRiders) {
			riderTotalWeight += rider.getWeightInPounds();
		}
		
		return riderTotalWeight;
	}
	
	public boolean isRoomOnElevator() {
		return getNumberOfRiders() < maxRiders &&
				getRiderTotalWeight() < maxWeight;
	}
	
	public boolean riderCanFit(Rider rider) {
		return getRiderTotalWeight() + rider.getWeightInPounds() <= maxWeight;
	}
	
	public void addRider(Rider rider) {
		if (getRiderTotalWeight() + rider.getWeightInPounds() < maxWeight) {
			currentRiders.add(rider);
		}
	}
	
	public void sortRidersByDestinationFloor() {
		Collections.sort(currentRiders);
	}
	
	public Timer fullElevatorDeliverRiders() {
		Iterator<Rider> iterator = currentRiders.iterator();
		Timer returnTimer = new Timer();

		while (iterator.hasNext()) {
			Rider rider = iterator.next();
			System.out.println("Elevator " + shaftNumber + " now heading for floor " + rider.getDestinationFloor() + "...");
			goUp(rider.getDestinationFloor());
			iterator.remove();
			System.out.println(timer);
		}
		
		System.out.println("All riders delivered. Returned to ground floor...");
		goDown(1);
		System.out.println(timer);
		returnTimer.addMinutesFromTimer(timer);
		timer.reset();
		
		return returnTimer;
	}

	public boolean goUp(int desiredFloor) {
		if (desiredFloor > currentFloor && 
			desiredFloor <= numberOfFloors) {
			addDeliveryTime(desiredFloor);
			currentFloor = desiredFloor;
			return true;
		} else {
			return false;
		}
	}

	public boolean goDown(int desiredFloor) {
		if (desiredFloor < currentFloor && 
			desiredFloor > 0) {
			addDeliveryTime(desiredFloor);
			currentFloor = desiredFloor;
			return true;
		}

		return false;
	}
	
	public void addDeliveryTime(int desiredFloor) {
		double travelTime = (double) (Math.abs(desiredFloor - currentFloor)) / 10;
		timer.addMinutes((int) travelTime);
	}
}
