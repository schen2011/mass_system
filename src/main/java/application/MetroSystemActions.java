package application;

import java.util.List;

public interface MetroSystemActions {

	// start/end the system, load the data
	public void start();
	
	//
	public List<String> getRoute(int startStopID, int destinationStopID) throws Exception;
	
}
