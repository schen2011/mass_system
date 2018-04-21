package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Bus {
	private Integer ID;
	private Integer route;
	private Integer currentLocation;
	private Integer nextLocation;
	private Integer passengers;
	private Integer capacity;
	private Integer speed; 
	private List<Rider> riderList;
	private String direction;

	public Bus() {
		this.ID = -1;
	}

	public Bus(int uniqueValue) {
		this.ID = uniqueValue;
		this.route = -1;
		this.currentLocation = -1;
		this.nextLocation = -1;
		this.passengers = -1;
		this.capacity = -1;
		this.speed = -1;
	}

	public Bus(int uniqueValue, int inputRoute, int inputLocation, int inputLocation2,
			int inputPassengers, int inputCapacity,
			int inputSpeed, String direction) {
		this.ID = uniqueValue;
		this.route = inputRoute;
		this.currentLocation = inputLocation;
		this.nextLocation = inputLocation2;
		this.passengers = inputPassengers;
		this.capacity = inputCapacity;
		this.speed = inputSpeed;
		this.riderList = new ArrayList<>();
		this.direction = direction;
	}
	
	public Integer getRoute() {
		return route;
	}

	public void setRoute(Integer route) {
		this.route = route;
	}

	public Integer getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(Integer nextLocation) {
		this.nextLocation = nextLocation;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<Rider> getRiderList() {
		return riderList;
	}

	public void setRiderList(List<Rider> riderList) {
		this.riderList = riderList;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public void setPassengers(int inputPassengers) {
		this.passengers = inputPassengers;
	}

	public void setCapacity(int inputCapacity) {
		this.capacity = inputCapacity;
	}

	public void setSpeed(int inputSpeed) {
		this.speed = inputSpeed;
	}

	public Integer getID() {
		return this.ID;
	}

	public Integer getRouteID() {
		return this.route;
	}
	
	public Integer getPassengers() {
		return this.passengers;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public Integer getSpeed() {
		return this.speed;
	}

	public void displayEvent() {
		System.out.println(" bus: " + Integer.toString(this.ID));
	}

	public void displayInternalStatus() {
		System.out.print("> bus - ID: " + Integer.toString(ID) + " route: " + Integer.toString(route));
		System.out
				.print(" current location: " + Integer.toString(currentLocation));
		System.out.print(" passengers: " + Integer.toString(passengers) + " capacity: " + Integer.toString(capacity));
		System.out.println(" speed: " + Integer.toString(speed));
	}

	public void takeTurn() {
		System.out.println("drop off passengers - pickup passengers to capacity - move to next stop");
	}

	public void adjustPassengers(int differential) {
		passengers = passengers + differential;
	}

	// Override the equals method to compare the object
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Bus me = (Bus) object;
			if (this.ID == me.getID()) {
				result = true;
			}
		}
		return result;
	}

	public Integer getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Integer currentLocation) {
		this.currentLocation = currentLocation;
	}

}
