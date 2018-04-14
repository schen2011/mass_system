package application;

public interface MetroSystemActions {

	// start/end the system, load the data
	public void start();
	
	//
	public String getRoute(int startStopID, int destinationStopID);
	
}
