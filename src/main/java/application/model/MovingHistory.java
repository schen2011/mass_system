package application.model;

public class MovingHistory {
	String currentStop;
	int currentRider;
	int peopleWaiting;
	int newRider;
	String nextStop;
	double howLongWillTake;
	double howLong;
	String vehicleNumber;
	boolean isBus;
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public boolean isBus() {
		return isBus;
	}

	public void setBus(boolean isBus) {
		this.isBus = isBus;
	}

	public double getHowLong() {
		return howLong;
	}

	public void setHowLong(double howLong) {
		this.howLong = howLong;
	}

	public String getCurrentStop() {
		return currentStop;
	}

	public void setCurrentStop(String currentStop) {
		this.currentStop = currentStop;
	}

	public int getCurrentRider() {
		return currentRider;
	}

	public void setCurrentRider(int currentRider) {
		this.currentRider = currentRider;
	}

	public int getPeopleWaiting() {
		return peopleWaiting;
	}

	public void setPeopleWaiting(int peopleWaiting) {
		this.peopleWaiting = peopleWaiting;
	}

	public int getNewRider() {
		return newRider;
	}

	public void setNewRider(int newRider) {
		this.newRider = newRider;
	}

	public String getNextStop() {
		return nextStop;
	}

	public void setNextStop(String nextStop) {
		this.nextStop = nextStop;
	}

	public double getHowLongWillTake() {
		return howLongWillTake;
	}

	public void setHowLongWillTake(double howLongWillTake) {
		this.howLongWillTake = howLongWillTake;
	}

	public MovingHistory() {
		
	}
	
	
}
