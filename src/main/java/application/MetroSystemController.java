package application;

import application.model.RouteInfo;
import application.model.RouteStopInfo;
import application.model.StopRoadInfo;
import application.model.Road;
import application.repository.MetroDataRepository;
import edu.gatech.BusRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.service.MetroService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MetroSystemController implements MetroSystemActions {

	/*
	@Autowired
	UserRepository userRepository;
	*/

	@Autowired
	MetroService metroService;

	@Autowired
	MetroDataRepository metroDataRepository;

	@RequestMapping(path = "/metrosystem", method = RequestMethod.GET)
	public String metrosystem(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "metrosystem";
	}

	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	@ResponseBody
	public String admin() {
		try {
			return "admin";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(path = "/client", method = RequestMethod.GET)
	@ResponseBody
	public String client() {

		try {
			return "client";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@RequestMapping(path = "/fetchAdminData", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<UserData> getAdminUsers() {
		return userRepository.findAll();
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


	//TEST
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
	public List<String> getRoute(int startStopID, int destinationStopID) {


		// get data from DB
		List<RouteInfo> routeInfos = metroDataRepository.getRouteData();
		List<RouteStopInfo> routeStopInfos = metroDataRepository.getRouteStopData();
		List<Road> busRouteRoads = metroDataRepository.getRoadData();
		List<StopRoadInfo> stopRoadInfos = metroDataRepository.getStopRoadData();

		//may not need
		List<Integer> busRouteChoice= new ArrayList<Integer>();
		List<Integer> trainRouteChoice= new ArrayList<Integer>();

		//output
		List<String> pathOptions = new ArrayList<String>();

		//loop list of routes
		for (int i = 0; i < routeInfos.size(); i++) {

			RouteInfo routeInfo = routeInfos.get(i);

			// loop list of route-stops
			for(int j = 0; i < routeStopInfos.size(); j++){

				RouteStopInfo routeStopInfo = routeStopInfos.get(j);

				if (routeStopInfo.getRouteId()== routeInfo.getRouteId()){

					routeInfo.addNewStop(routeStopInfo.getStopId());
				}

			}

		}

		//loop again list of routes
		for (int i = 0; i < routeInfos.size(); i++){

			RouteInfo routeInfo = routeInfos.get(i);

			// identify the route that has both start ID and destination ID
			if (routeInfo.hasStop(startStopID) && routeInfo.hasStop(destinationStopID)){

				//bus
				if (routeInfo.getTypeId() ==0 ){

					busRouteChoice.add(routeInfo.getRouteId());

					// list of roads between startStopId and destinationStopId
					List<Integer> roads= new ArrayList<Integer>();

					int currentStop = startStopID;
					int currentLocation = routeInfo.getCurrentLocation(currentStop);
					int nextLocation = routeInfo.getNextLocation(currentLocation);
					int nextStopID = routeInfo.getStopID(nextLocation);

					//iterate the stopRoadInfo to get the road list
					do {

						for (int j = 0; j < stopRoadInfos.size(); j++){

							StopRoadInfo stopRoadInfo = stopRoadInfos.get(j);

							if ((stopRoadInfo.getStopIdStart() == currentLocation) && (stopRoadInfo.getStopIdEnd()==nextStopID)){}
							{

								roads.add(stopRoadInfo.getRoadId());

							}
						}

						currentStop = nextStopID;
						currentLocation = routeInfo.getCurrentLocation(currentStop);
						nextLocation = routeInfo.getNextLocation(currentLocation);
						nextStopID = routeInfo.getStopID(nextLocation);

					}while(nextStopID!= destinationStopID);

					//loop list of roads to get the length, and travel time

					Double travelTime =0.0;
					Double travelLength  = 0.0;

					for (int k = 0; k < roads.size(); k++){

						for (int z = 0; z < busRouteRoads.size(); z++){

							Road road = busRouteRoads.get(z);

							if (roads.get(k) == road.getRoadId()){

								travelLength = travelLength + road.getRoadLength();
								travelTime = travelTime  + (road.getRoadLength()/road.getAverageSpeed());
								z = busRouteRoads.size();
							}

						}
					}

					pathOptions.add("Option: take Route: " + routeInfo.getRouteId() + "; total length: " + travelLength + "; total travel time: " + travelTime);

				}

				else
					trainRouteChoice.add(routeInfo.getRouteId());
			}
		}

		return pathOptions;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
