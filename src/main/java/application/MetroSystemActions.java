package application;

import java.util.List;

import application.model.MovingHistory;

public interface MetroSystemActions {
	public List<MovingHistory> start() throws Exception;
	
	public List<String> getRoute(int startStopID, int destinationStopID) throws Exception;
}
