package application;

import application.repository.MetroDataRepository;
import application.service.MetroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

@Controller
public class MetroSystemController implements MetroSystemActions {
	@Autowired
	MetroService metroService;

	@Autowired
	MetroDataRepository metroDataRepository;
	
	private List<MovingHistory> movingHistories;
	
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public String admin() {
		try {
			return "admin";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(path = "/client", method = RequestMethod.GET)
	public String client() {
		try {
			return "client";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(path = "/getTransitData", method = RequestMethod.GET)
	@ResponseBody
	public String getTransitData() {
		try {
			metroService.getTransitData();
			return "Retrieved All Transit Data";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception occured when retrieving Transit Data";
		}
	}
	
//	@RequestMapping(path = "/getRoutes", method = RequestMethod.GET)
//	@ResponseBody
//	public String getAllRoutes() {
//		try {
//			return "option: route 1; total distance: 10 miles; travel time: 30 minutes";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Error";
//		}
//	}
	
	@RequestMapping(path = "/start") 
	public String startApplication(Model model) throws Exception {
		if (movingHistories == null) {
			movingHistories = metroService.start();
		}
    	model.addAttribute("entries", movingHistories);
		return "eventhistory";
	}
	
	@RequestMapping(path = "/getNextStop", method = RequestMethod.GET) 
	public void getNextStop()
			throws Exception {
		List<Integer> lst = getStops(1);
		System.out.println(lst);
		return;
	}
	
	@RequestMapping(path = "/getAllRoutes", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public List<String> getRoute(int startStopID, int destinationStopID) throws Exception {
		int stID = startStopID;
		int dstID = destinationStopID;
		BusSystem martaModel = new BusSystem();
		TrainSystem trainModel = new TrainSystem();
		martaModel.displayModel();
		trainModel.displayModel();
		metroService.getTransitData(martaModel, trainModel);
		List<String> pathOptions = new ArrayList<String>();
		HashMap<Integer, Bus> buses = martaModel.getBuses();
		HashMap<Integer, List<Double>> busPaths = new HashMap<Integer, List<Double>>();
		for (BusRoute route : martaModel.getRoutes().values()) {
			double busID = -1;
			double arrivalTime = Double.MAX_VALUE;
			if (route.hasStop(stID) && route.hasStop(dstID)) {
				int stStopRank = route.getStopRank(stID);
				int dstStopRank = route.getStopRank(dstID);
				List<Double> busPath = route.calculateRoute(stStopRank, dstStopRank);
				for (Bus bus : buses.values()) {
					bus.displayInternalStatus();
					if (bus.getRouteID() == route.getID()) {
						int currStopRank = route.getStopRank(bus.getCurrentLocation());
						if (bus.getDirection().equals("INBOUND")) {
							if (dstStopRank >= stStopRank) {
								if (currStopRank <= stStopRank) {
									double time = route.calculateRoute(currStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										busID = bus.getID();
									}
								} else {
									double time = 2 * route.calculateRoute(0, route.getLength() - 2).get(1)
											- route.calculateRoute(currStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										busID = bus.getID();
									}
								}
							}
							else {
								double time = route.calculateRoute(currStopRank, route.getLength() - 2).get(1)
										+ route.calculateRoute(stStopRank, route.getLength() - 2).get(1);
								if (time < arrivalTime) {
									arrivalTime = time;
									busID = bus.getID();
								}
							}
						}
						else {
							if (dstStopRank < stStopRank) {
								if (currStopRank >= dstStopRank) {
									double time = route.calculateRoute(dstStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										busID = bus.getID();
									}
								} else {
									double time = 2 * route.calculateRoute(0, route.getLength() - 2).get(1)
											- route.calculateRoute(currStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										busID = bus.getID();
									}
								}
							}
							else {
								double time = route.calculateRoute(0, currStopRank).get(1)
										+ route.calculateRoute(0, stStopRank).get(1);
								if (time < arrivalTime) {
									arrivalTime = time;
									busID = bus.getID();
								}
							}

						}

					}
				}
				busPath.add(arrivalTime);
				busPath.add(busID);
				busPaths.put(route.getID(), busPath);
			}
			route.displayInternalStatus();
		}

		for (Integer routeID : busPaths.keySet()) {
			pathOptions.add("Option: take Bus Route: " + routeID 
					+ "; total distance: " + busPaths.get(routeID).get(0)
					+ "miles; total travel time: " + busPaths.get(routeID).get(1) 
					+ " hours; total stops : "
					+ busPaths.get(routeID).get(2) + " next Bus No: " 
					+ busPaths.get(routeID).get(4) + "; Arriving in : "
					+ busPaths.get(routeID).get(3) + " hours;");
		}
		
		HashMap<Integer, Train> trains = trainModel.getTrains();
		HashMap<Integer, List<Double>> trainPaths = new HashMap<Integer, List<Double>>();
		for (TrainRoute route : trainModel.getRoutes().values()) {
			double trainID = -1;
			double arrivalTime = Double.MAX_VALUE;
			if (route.hasStop(stID) && route.hasStop(dstID)) {
				int stStopRank = route.getStopRank(stID);
				int dstStopRank = route.getStopRank(dstID);
				List<Double> trainPath = route.calculateRoute(stStopRank, dstStopRank);
				for (Train train : trains.values()) {
					System.out.println("Train direction"+train.getDirection());
					train.displayInternalStatus();
					if (train.getRoute() == route.getID()) {
						int currStopRank = route.getStopRank(train.getCurrentLocation());
						if (train.getDirection().equals("INBOUND")) {
							if (dstStopRank >= stStopRank) {
								if (currStopRank <= stStopRank) {
									double time = route.calculateRoute(currStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										trainID = train.getID();
									}
								} else {
									double time = 2 * route.calculateRoute(0, route.getLength() - 2).get(1)
											- route.calculateRoute(currStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										trainID = train.getID();
									}
								}
							}
							else {
								double time = route.calculateRoute(currStopRank, route.getLength() - 2).get(1)
										+ route.calculateRoute(stStopRank, route.getLength() - 2).get(1);
								if (time < arrivalTime) {
									arrivalTime = time;
									trainID = train.getID();
								}
							}
						}
						else {
							if (dstStopRank < stStopRank) {
								if (currStopRank >= dstStopRank) {
									double time = route.calculateRoute(dstStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										trainID = train.getID();
									}
								} else {
									double time = 
											2 * route.calculateRoute(0, route.getLength() - 2).get(1)
											- route.calculateRoute(currStopRank, stStopRank).get(1);
									if (time < arrivalTime) {
										arrivalTime = time;
										trainID = train.getID();
									}
								}
							}
							else {
								double time = route.calculateRoute(0, currStopRank).get(1)
										+ route.calculateRoute(0, stStopRank).get(1);
								if (time < arrivalTime) {
									arrivalTime = time;
									trainID = train.getID();
								}
							}

						}

					}
				}
				trainPath.add(arrivalTime);
				trainPath.add(trainID);
				trainPaths.put(route.getID(), trainPath);
			}
			route.displayInternalStatus();
		}
		
		for (Integer routeID : trainPaths.keySet()) {
			pathOptions.add("Option: take Train Route: " + routeID + "; total distance: "
					+ trainPaths.get(routeID).get(0) + " miles; total travel time: " 
					+ trainPaths.get(routeID).get(1)
					+ " hours; total stops : " + trainPaths.get(routeID).get(2) 
					+ " hours; next Train No: "
					+ trainPaths.get(routeID).get(3) + "; Arriving in : " 
					+ trainPaths.get(routeID).get(4) + " hours;");
		}
		return pathOptions;
	}

	@Override
	public List<MovingHistory> start() throws Exception {
		SimDriver sys = new SimDriver();
		BusSystem busSystem = sys.getBusModel();
		TrainSystem trainSystem = sys.getTrainModel();
		// load stop
		metroService.loadStop(busSystem, trainSystem);
		// load route
		metroService.loadRoute(busSystem, trainSystem);
		// load vehicle
		metroService.loadVehicle(busSystem, trainSystem);
		// load road
		metroService.loadRoad(busSystem, trainSystem);
		// load rider
		metroService.loadRider(busSystem, trainSystem);
		// load events
		metroService.loadEvents(sys);
		
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
						* covnertTraffic(road.getTrafficIndicator());  
				totalLength += road.getRoadLength();
			}
			
			operationRst = busSystem.moveBus(busSystem.getBus(vehicleID));
			
			operationRst.setHowLongWillTake(totalTime);
			operationRst.setHowLong(totalLength);
			
			List<Integer> lst = getStops(bus.getRouteID()); 
			int firstStop = 0, lastStop = lst.size() - 1;
			int getIndex = getIndex(lst, bus.getCurrentLocation());
			if (bus.getDirection().toUpperCase().equals("INBOUND")) {
				if (getIndex == lastStop) {
					bus.setDirection("OUTBOUND");
					bus.setNextLocation(lst.get(getIndex - 1));
				} else {
					bus.setNextLocation(lst.get(getIndex + 1));
				}
			} else {
				if (getIndex == firstStop) {
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
				Road road = busSystem.getRoads().get(roadId);
				totalTime += road.getRoadLength() / road.getAverageSpeed() 
						* covnertTraffic(road.getTrafficIndicator());  
				totalLength += road.getRoadLength();
			}
			
			operationRst = trainSystem.moveTrain(trainSystem.getTrain(vehicleID));
			operationRst.setHowLongWillTake(totalTime);
			operationRst.setHowLong(totalLength);
			
			List<Integer> lst = getStops(train.getRouteID()); 
			int firstStop = 0, lastStop = lst.size() - 1;
			int getIndex = getIndex(lst, train.getCurrentLocation());
			if (train.getDirection().toUpperCase().equals("INBOUND")) {
				if (getIndex == lastStop) {
					train.setDirection("OUTBOUND");
					train.setNextLocation(lst.get(getIndex - 1));
				} else {
					train.setNextLocation(lst.get(getIndex + 1));
				}
			} else {
				if (getIndex == firstStop) {
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
			return 0.8; //80% of the normal speed
		} else if (trafficIndicator.equals(TrafficIndicator.TRAFFIC_MEDIUM)) {
			return 0.6;
		} else if (trafficIndicator.equals(TrafficIndicator.TRAFFIC_PEAK)) {
			return 0.3;
		} else {
			return 1.0;
		}
	}

	private List<Integer> getRoads(Integer routeId, 
			Integer currentLocation, Integer nextLocation) {
		return metroService.getRoads(routeId, currentLocation, nextLocation);
	}

	private int getIndex(List<Integer> lst, Integer currentLocation) {
		for (int i = 0; i < lst.size(); i++) {
			if (currentLocation == lst.get(i)) {
				return i;
			}
		}
		return -1;
	}

	public List<Integer> getStops(int routeId) {
		return metroService.getStops(routeId);
	}
	
}

