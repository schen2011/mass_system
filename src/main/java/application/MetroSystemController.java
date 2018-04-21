package application;

import application.model.RouteStopInfo;
import application.model.StopRoadInfo;
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

@Controller
public class MetroSystemController implements MetroSystemActions {
	
	@Autowired
	MetroService metroService;

	@Autowired
	MetroDataRepository metroDataRepository;

	@RequestMapping(path = "/metrosystem", method = RequestMethod.GET)
	public String metrosystem(@RequestParam(name = "name", required = false, 
			defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "metrosystem";
	}

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
	
	@RequestMapping(path = "/getRoutes", method = RequestMethod.GET)
	@ResponseBody
	public String getAllRoutes() {
		try {
			return "option: route 1; total distance: 10 miles; travel time: 30 minutes";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
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
		System.out.println("Take below routes from stop " + stID + " to stop " + dstID);
		for (BusRoute route : martaModel.getRoutes().values()) {
			double busID = -1;
			double arrivalTime = Double.MAX_VALUE;
			if (route.hasStop(stID) && route.hasStop(dstID)) {
				System.out.println("Route: " + route.getID());
				int stStopRank = route.getStopRank(stID);
				int dstStopRank = route.getStopRank(dstID);
				List<Double> busPath = route.calculateRoute(stStopRank, dstStopRank);
				for (Bus bus : buses.values()) {
					System.out.println("Bus direction"+bus.getDirection());
					bus.displayInternalStatus();
					if (bus.getRoute() == route.getID()) {
						int currStopRank = route.getStopRank(bus.getCurrentLocation());
						// inbound - departing
						if (bus.getDirection() == "INBOUND") {
							// in your direction
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
							// opposition to your direction
							else {
								double time = route.calculateRoute(currStopRank, route.getLength() - 2).get(1)
										+ route.calculateRoute(stStopRank, route.getLength() - 2).get(1);
								if (time < arrivalTime) {
									arrivalTime = time;
									busID = bus.getID();
								}
							}
						}
						// outbound - returning
						else {
							// in your direction
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
							// opposition to your direction
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
			// "Option: take Bus Route: " + routeInfo.getRouteId() + "; total length: " +
			// travelLength + "miles; total travel time: " + travelTime + "hours;"
			pathOptions.add("Option: take Bus Route: " + routeID + "; total distance: " + busPaths.get(routeID).get(0)
					+ "miles; total travel time: " + busPaths.get(routeID).get(1) + " hours; total stops : "
					+ busPaths.get(routeID).get(2) + " next Bus No: " + busPaths.get(routeID).get(4) + "; Arriving in : "
					+ busPaths.get(routeID).get(3) + " hours;");
		}

		HashMap<Integer, Train> trains = trainModel.getTrains();
		HashMap<Integer, List<Double>> trainPaths = new HashMap<Integer, List<Double>>();

		System.out.println("Take below routes from stop " + stID + " to stop " + dstID);
		for (TrainRoute route : trainModel.getRoutes().values()) {
			double trainID = -1;
			double arrivalTime = Double.MAX_VALUE;
			if (route.hasStop(stID) && route.hasStop(dstID)) {
				System.out.println("Route: " + route.getID());
				int stStopRank = route.getStopRank(stID);
				int dstStopRank = route.getStopRank(dstID);
				List<Double> trainPath = route.calculateRoute(stStopRank, dstStopRank);
				for (Train train : trains.values()) {
					System.out.println("Train direction"+train.getDirection());
					train.displayInternalStatus();
					if (train.getRoute() == route.getID()) {
						int currStopRank = route.getStopRank(train.getCurrentLocation());
						// inbound - departing
						if (train.getDirection() == "INBOUND") {
							// in your direction
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
							// opposition to your direction
							else {
								double time = route.calculateRoute(currStopRank, route.getLength() - 2).get(1)
										+ route.calculateRoute(stStopRank, route.getLength() - 2).get(1);
								if (time < arrivalTime) {
									arrivalTime = time;
									trainID = train.getID();
								}
							}
						}
						// outbound - returning
						else {
							// in your direction
							if (dstStopRank < stStopRank) {
								if (currStopRank >= dstStopRank) {
									double time = route.calculateRoute(dstStopRank, stStopRank).get(1);
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
							// opposition to your direction
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
			// "Option: take Train Route: " + routeInfo.getRouteId() + "; total length: " +
			// travelLength + "miles; total travel time: " + travelTime + "hours;"
			pathOptions.add("Option: take Train Route: " + routeID + "; total distance: "
					+ trainPaths.get(routeID).get(0) + " miles; total travel time: " + trainPaths.get(routeID).get(1)
					+ " hours; total stops : " + trainPaths.get(routeID).get(2) + " hours; next Train No: "
					+ trainPaths.get(routeID).get(3) + "; Arriving in : " + trainPaths.get(routeID).get(4) + " hours;");
		}

		return pathOptions;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
