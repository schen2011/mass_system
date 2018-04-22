package application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import application.model.BusSystem;
import application.model.MetroSystem;
import application.model.MovingHistory;
import application.model.SimDriver;
import application.model.TrainSystem;

@Service
public interface MetroService {
	
	public List<MovingHistory> start() throws Exception;
	
	public void getTransitData() throws Exception;

	public void getTransitData(BusSystem martaModel, TrainSystem trainModel) throws Exception;

	public void loadStop(BusSystem busSystem, TrainSystem trainSystem) throws Exception;

	public void loadRoute(BusSystem busSystem, TrainSystem trainSystem) throws Exception;

	public void loadVehicle(BusSystem busSystem, TrainSystem trainSystem);

	public void loadRoad(BusSystem busSystem, TrainSystem trainSystem);

	public void loadRider(BusSystem busSystem, TrainSystem trainSystem);

	public void loadEvents(SimDriver simDriver);

	public List<Integer> getStops(int routeId);
	
	public List<String> listAllTest();

	public List<Integer> getRoads(Integer routeId, Integer currentLocation, Integer nextLocation);
}
