package com.elevatorsim;

public class Rider implements Comparable<Rider>{
	public int destinationFloor;
	public int weightInPounds;
	
	
	public Rider(int destinationFloor, int weightInPounds) {
		this.destinationFloor = destinationFloor;
		this.weightInPounds = weightInPounds;
	}
	
	@ Override
	public String toString() {
		return "Rider: Destination Floor = " + destinationFloor + ", Weight: " + weightInPounds + "lbs.";
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}


	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}


	public int getWeightInPounds() {
		return weightInPounds;
	}
	
	public int compareTo(Rider rider) {
		return (this.destinationFloor < rider.getDestinationFloor() ? -1 : 
            (this.destinationFloor == rider.getDestinationFloor() ? 0 : 1));
	}
	
	

}
