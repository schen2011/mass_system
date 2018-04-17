package application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.RiderInfo;
import application.model.Road;
import application.model.RouteInfo;
import application.model.RouteStopInfo;
import application.model.StopInfo;
import application.model.StopRoadInfo;
import application.repository.MetroDataRepository;
import application.service.MetroService;
import edu.gatech.BusSystem;

/**
 * This class contains the implementation to of tranist system
 * 
 * @author mythili
 *
 */
@Service
public class MetroServiceImpl implements MetroService {

	@Autowired
	MetroDataRepository metroDataRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void getTransitData() throws Exception {
		BusSystem martaModel = new BusSystem();
		HashMap<Integer, ArrayList<Integer>> routeLists = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> targetList;

		// create the Stops
		System.out.print(" extracting and adding the stops: ");
		List<StopInfo> stopInfos = metroDataRepository.getStopData();
		if (stopInfos != null && !stopInfos.isEmpty()) {
			for (StopInfo stopInfo : stopInfos) {
				// TODO Add the appropriate method to set the info to martamodel for both Bus
				// and Train type
				// martaModel.makeStop(stopInfo.getStopId(), stopInfo.getName(),
				// stopInfo.getxAxis(), stopInfo.getyAxis());
			}
			System.out.println(stopInfos.size() + " stops added");
		}

		// create the routes
		System.out.print(" extracting and adding the routes: ");
		List<RouteInfo> routeInfos = metroDataRepository.getRouteData();
		if (routeInfos != null && !routeInfos.isEmpty()) {
			for (RouteInfo routeInfo : routeInfos) {
				// TODO Add the appropriate method to set the info to martamodel for both Bus
				// and Train type
				// martaModel.makeRoute(routeInfo.getRouteId(), routeInfo.getNumber(),
				// routeInfo.getRouteName());
			}
			System.out.println(routeInfos.size() + " Routes added");
		}

		// create the RouteStop
		System.out.print(" extracting and adding the RouteStop: ");
		List<RouteStopInfo> routeStopInfos = metroDataRepository.getRouteStopData();
		if (routeStopInfos != null && !routeStopInfos.isEmpty()) {
			for (RouteStopInfo routeStopInfo : routeStopInfos) {
				targetList = routeLists.get(routeStopInfo.getRouteId());
				if (targetList != null && !targetList.contains(routeStopInfo.getStopId())) {
					martaModel.appendStopToRoute(routeStopInfo.getRouteId(), routeStopInfo.getStopId(), 0);
					targetList.add(routeStopInfo.getStopId());
				}
			}
			System.out.println(routeStopInfos.size() + " RouteStop added");
		}

		// create the StopRoad
		System.out.print(" extracting and adding the StopRoad: ");
		List<StopRoadInfo> stopRoadInfos = metroDataRepository.getStopRoadData();
		if (stopRoadInfos != null && !stopRoadInfos.isEmpty()) {
			// TODO Add the appropriate method to set the info to martamodel
			System.out.println(stopRoadInfos.size() + " StopRoad added");
		}

		// create the Rider
		System.out.print(" extracting and adding the Rider: ");
		List<RiderInfo> riderInfos = metroDataRepository.getRiderInfo();
		if (riderInfos != null && !riderInfos.isEmpty()) {
			// TODO Add the appropriate method to set the info to martamodel
			System.out.println(riderInfos.size() + " Riders added");
		}

		// create the Rider
		System.out.print(" extracting and adding the Road Information: ");
		List<Road> roadInfos = metroDataRepository.getRoadData();
		if (roadInfos != null && !roadInfos.isEmpty()) {
			for (Road roadInfo : roadInfos) {
				martaModel.makeRoad(roadInfo);
			}
			System.out.println(roadInfos.size() + " Roads added");
		}

	}

}
