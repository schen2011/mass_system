package application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.Bus;
import application.model.BusRoute;
import application.model.BusStop;
import application.model.BusSystem;
import application.model.MovingHistory;
import application.model.Rider;
import application.model.Road;
import application.model.RouteStopInfo;
import application.model.SimDriver;
import application.model.SimEvent;
import application.model.TrafficIndicator;
import application.model.Train;
import application.model.TrainRoute;
import application.model.TrainStop;
import application.model.TrainSystem;
import application.repository.MetroDataRepository;
import application.service.MetroService;


@Service
public class MetroServiceImpl implements MetroService {

	@Autowired
	MetroDataRepository metroDataRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void getTransitData(BusSystem martaModel, TrainSystem trainModel) throws Exception {
		
		//create the vehicle
		System.out.print(" extracting and adding the buses: ");
		List<Bus> BusList = metroDataRepository.getBusData();
		List<Train> TrainList = metroDataRepository.getTrainData();

		if (BusList != null && !BusList.isEmpty()) {
			for (Bus bus : BusList) {
				// TODO Add the appropriate method to set the info to martamodel for both Bus
				// and Train type
				martaModel.makeBus(bus.getID(), bus.getRouteID(), bus.getCurrentLocation(), bus.getNextLocation(), bus.getPassengers(), bus.getCapacity(), bus.getSpeed(), bus.getDirection());
			}
			System.out.println(BusList.size() + " bus stops added");
		}
		
		if (TrainList != null && !TrainList.isEmpty()) {
			for (Train train : TrainList) {
				// TODO Add the appropriate method to set the info to martamodel for both Train
				// and Train type
				trainModel.displayModel();
				trainModel.makeTrain(train.getID(), train.getRoute(), train.getCurrentLocation(), train.getCurrentLocation(), train.getPassengers(), train.getCapacity(), train.getSpeed(), train.getDirection());
			}
			System.out.println(TrainList.size() + " train stops added");
		}

		// create the Stops
		System.out.print(" extracting and adding the bus stops: ");
		List<BusStop> BusStopList = metroDataRepository.getBusStopData();
		List<TrainStop> TrainStopList = metroDataRepository.getTrainStopData();

		if (BusStopList != null && !BusStopList.isEmpty()) {
			for (BusStop stop : BusStopList) {
				// TODO Add the appropriate method to set the info to martamodel for both Bus
				// and Train type
				martaModel.makeStop(stop.getID(), stop.getName(), stop.getWaiting(), stop.getXCoord(),stop.getYCoord());
				// stopInfo.getxAxis(), stopInfo.getyAxis());
			}
			System.out.println(BusStopList.size() + " bus stops added");
		}
		
		if (TrainStopList != null && !TrainStopList.isEmpty()) {
			for (TrainStop stop : TrainStopList) {
				// TODO Add the appropriate method to set the info to martamodel for both Train
				// and Train type
				trainModel.makeStop(stop.getID(), stop.getName(), stop.getWaiting(), stop.getXCoord(),stop.getYCoord());
				// stopInfo.getxAxis(), stopInfo.getyAxis());
			}
			System.out.println(TrainStopList.size() + " Train stops added");
		}
		// create the routes
		System.out.print(" extracting and adding the bus routes: ");
		List<BusRoute> busRouteList = metroDataRepository.getBusRouteData();
		if (busRouteList != null && !busRouteList.isEmpty()) {
			for (BusRoute route : busRouteList) {
				// TODO Add the appropriate method to set the info to martamodel for both Bus
				// and Train type
				martaModel.makeRoute(route.getID(),route.getNumber(),route.getName());
			}
			System.out.println(busRouteList.size() + " bus Routes added");
		}
		
		System.out.print(" extracting and adding the train routes: ");
		List<TrainRoute> trainRouteList = metroDataRepository.getTrainRouteData();
		if (trainRouteList != null && !trainRouteList.isEmpty()) {
			for (TrainRoute route : trainRouteList) {
				// TODO Add the appropriate method to set the info to martamodel for both Train
				// and Train type
				trainModel.makeRoute(route.getID(),route.getNumber(),route.getName());
				// routeInfo.getRouteName());
			}
			System.out.println(trainRouteList.size() + " Train Routes added");
		}

		// create the RouteStop
		System.out.print(" extracting and adding the busRouteStop: ");
		List<RouteStopInfo> busRouteStopInfos = metroDataRepository.getBusRouteStopData();
		if (busRouteStopInfos != null && !busRouteStopInfos.isEmpty()) {
			for (RouteStopInfo routeStopInfo : busRouteStopInfos) {
				System.out.println(routeStopInfo.toString());	
				martaModel.appendStopToRoute(routeStopInfo.getRouteId(), routeStopInfo.getStopId());
				martaModel.appendRoadToRoute(routeStopInfo.getRouteId(), routeStopInfo.getLength(),routeStopInfo.getSpeed(), routeStopInfo.getTrafficstatus());

			}
			System.out.println(busRouteStopInfos.size() + " Bus stops appended to Route");
		}
		
		System.out.print(" extracting and adding the TrainRouteStop: ");
		List<RouteStopInfo> TrainRouteStopInfos = metroDataRepository.getTrainRouteStopData();
		if (TrainRouteStopInfos != null && !TrainRouteStopInfos.isEmpty()) {
			for (RouteStopInfo routeStopInfo : TrainRouteStopInfos) {
				System.out.println(routeStopInfo.toString());	
				trainModel.appendStopToRoute(routeStopInfo.getRouteId(), routeStopInfo.getStopId());
				trainModel.appendRoadToRoute(routeStopInfo.getRouteId(), routeStopInfo.getLength(),routeStopInfo.getSpeed(), routeStopInfo.getTrafficstatus());

			}
			System.out.println(TrainRouteStopInfos.size() + " Train stops appended to Route");
		}
		
		// create the Rider
		System.out.print(" extracting and adding the Rider: ");
		List<Rider> riderInfos = metroDataRepository.getRiderInfo();
		if (riderInfos != null && !riderInfos.isEmpty()) {
			// TODO Add the appropriate method to set the info to martamodel
			System.out.println(riderInfos.size() + " Riders added");
		}
	
	}

	@Override
	public void loadStop(BusSystem busSystem, TrainSystem trainModel) throws Exception {
		// TODO Auto-generated method stub
		metroDataRepository.loadStop(busSystem, trainModel);
	}

	@Override
	public void getTransitData() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadRoute(BusSystem busSystem, TrainSystem trainSystem) throws Exception {
		// TODO Auto-generated method stub
		metroDataRepository.loadRoute(busSystem, trainSystem);
	}

	@Override
	public void loadVehicle(BusSystem busSystem, TrainSystem trainSystem) {
		metroDataRepository.loadVehicle(busSystem, trainSystem);
	}

	@Override
	public void loadRoad(BusSystem busSystem, TrainSystem trainSystem) {
		// TODO Auto-generated method stub
		metroDataRepository.loadRoad(busSystem, trainSystem);
	}

	@Override
	public void loadRider(BusSystem busSystem, TrainSystem trainSystem) {
		// TODO Auto-generated method stub
		metroDataRepository.assignRiderToStops(busSystem, trainSystem);
	}

	@Override
	public void loadEvents(SimDriver simDriver) {
		// TODO Auto-generated method stub
		metroDataRepository.loadEvents(simDriver);
	}

	@Override
	public List<Integer> getStops(int routeId) {
		// TODO Auto-generated method stub
		return metroDataRepository.getStops(routeId);
	}

	@Override
	public List<String> listAllTest() {
		return Arrays.asList("1", "2", "3");
	}

	@Override
	public List<Integer> getRoads(Integer routeId, Integer currentLocation, Integer nextLocation) {
		// TODO Auto-generated method stub
		return metroDataRepository.getRoads(routeId, currentLocation, nextLocation);
	}

	@Override
	public List<MovingHistory> start() throws Exception {
		SimDriver sys = new SimDriver();
		BusSystem busSystem = sys.getBusModel();
		TrainSystem trainSystem = sys.getTrainModel();
		
		// load stop
		loadStop(busSystem, trainSystem);
		// load route
		loadRoute(busSystem, trainSystem);
		// load vehicle
		loadVehicle(busSystem, trainSystem);
		// load road
		loadRoad(busSystem, trainSystem);
		// load rider
		loadRider(busSystem, trainSystem);
		// load events
		loadEvents(sys);
		
		PriorityQueue<SimEvent> events = sys.getSimEngine().getEventQueue();
		List<MovingHistory> histories = new ArrayList<>();
		while (!events.isEmpty()) {
			histories.add(triggerEvent(events.poll(), sys));
		}
		return histories;
	}
	
	private MovingHistory triggerEvent(SimEvent event, SimDriver simDriver) {
		BusSystem busSystem = simDriver.getBusModel();
		TrainSystem trainSystem = simDriver.getTrainModel();
		int vehicleID = event.getVehicleID();
		MovingHistory operationRst;
		int nextStop;
		if (busSystem.getBuses().containsKey(vehicleID)) {
			//trigger bus
			Bus bus = busSystem.getBuses().get(vehicleID);
			// how long will take to get there.
			List<Integer> roadIdList = getRoads(bus.getRouteID(), 
					bus.getCurrentLocation(), bus.getNextLocation());
			double totalLength = 0;
			double totalTime = 0;
			for (Integer roadId : roadIdList) {
				Road road = busSystem.getRoads().get(roadId);
				totalTime += road.getRoadLength() / road.getAverageSpeed() 
						/ covnertTraffic(road.getTrafficIndicator()) * 60;  
				totalLength += road.getRoadLength();
			}
			operationRst = busSystem.moveBus(busSystem.getBus(vehicleID));
			operationRst.setBus(true);
			operationRst.setVehicleNumber("Bus - " + String.valueOf(vehicleID));
			operationRst.setHowLongWillTake(totalTime);
			operationRst.setHowLong(totalLength);
			
			// GetNextStop -> 
			// stop ID
			List<Integer> lst = getStops(bus.getRouteID()); 
			// use the routeid, get all stopid, with sequence, if inbound, small to larger, else larger to small
			int firstStop = 0, lastStop = lst.size() - 1;
			int getIndex = getIndex(lst, bus.getCurrentLocation());
			if (bus.getDirection().toUpperCase().equals("INBOUND")) {
				if (getIndex == lastStop) {
					//change to outbound;
					bus.setDirection("OUTBOUND");
					bus.setNextLocation(lst.get(getIndex - 1));
				} else {
					bus.setNextLocation(lst.get(getIndex + 1));
				}
			} else {
				if (getIndex == firstStop) {
					//change to inbound;
					bus.setDirection("INBOUND");
					bus.setNextLocation(lst.get(getIndex + 1));
				} else {
					bus.setNextLocation(lst.get(getIndex - 1));
				}
			}
			nextStop = bus.getNextLocation();
			
		} else {
			//trigger train
			Train train = trainSystem.getTrains().get(vehicleID);
			// how long will take to get there.
			List<Integer> roadIdList = getRoads(train.getRouteID(), 
					train.getCurrentLocation(), train.getNextLocation());
			double totalLength = 0;
			double totalTime = 0;
			for (Integer roadId : roadIdList) {
				Road road = trainSystem.getRoads().get(roadId);
				totalTime += road.getRoadLength() / road.getAverageSpeed() 
						/ covnertTraffic(road.getTrafficIndicator()) * 60;  
				totalLength += road.getRoadLength();
			}
			
			System.out.println("what is the vehicleID =" + vehicleID);
			
			operationRst = trainSystem.moveTrain(trainSystem.getTrain(vehicleID));
			operationRst.setVehicleNumber("Train - " + String.valueOf(vehicleID));
			operationRst.setHowLongWillTake(totalTime);
			operationRst.setHowLong(totalLength);
			
			List<Integer> lst = getStops(train.getRouteID()); 
			int firstStop = 0, lastStop = lst.size() - 1;
			int getIndex = getIndex(lst, train.getCurrentLocation());
			if (train.getDirection().toUpperCase().equals("INBOUND")) {
				if (getIndex == lastStop) {
					//change to outbound;
					train.setDirection("OUTBOUND");
					train.setNextLocation(lst.get(getIndex - 1));
				} else {
					train.setNextLocation(lst.get(getIndex + 1));
				}
			} else {
				if (getIndex == firstStop) {
					//change to inbound;
					train.setDirection("INBOUND");
					train.setNextLocation(lst.get(getIndex + 1));
				} else {
					train.setNextLocation(lst.get(getIndex - 1));
				}
			}
			nextStop = train.getNextLocation();
		}
		return operationRst;
	}
	
	private double covnertTraffic(String trafficIndicator) {
		if (trafficIndicator.equals(TrafficIndicator.TRAFFIC_LIGHT)) {
			return 0.8;
		} else if (trafficIndicator.equals(TrafficIndicator.TRAFFIC_MEDIUM)) {
			return 0.6;
		} else if (trafficIndicator.equals(TrafficIndicator.TRAFFIC_PEAK)) {
			return 0.3;
		} else {
			return 1.0;
		}
	}
	
	private int getIndex(List<Integer> lst, Integer currentLocation) {
		for (int i = 0; i < lst.size(); i++) {
			if (currentLocation == lst.get(i)) {
				return i;
			}
		}
		return -1;
	}

}
