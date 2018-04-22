package application;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MetroSystemActions {
	public List<String> getRoute(int startStopID, 
			int destinationStopID) throws Exception;
}
