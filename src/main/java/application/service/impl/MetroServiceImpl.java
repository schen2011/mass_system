package application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.Bus;
import application.model.BusRoute;
import application.model.BusStop;
import application.model.BusSystem;
import application.model.RiderInfo;
import application.model.Road;
import application.model.RouteStopInfo;
import application.model.SimQueue;
import application.model.StopRoadInfo;
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
				martaModel.makeBus(bus.getID(), bus.getRoute(), bus.getCurrentLocation(), bus.getPassengers(), bus.getCapacity(), bus.getSpeed(), bus.getDirection());
			}
			System.out.println(BusList.size() + " bus stops added");
		}
		
		if (TrainList != null && !TrainList.isEmpty()) {
			for (Train train : TrainList) {
				// TODO Add the appropriate method to set the info to martamodel for both Train
				// and Train type
				trainModel.displayModel();
				trainModel.makeTrain(train.getID(), train.getRoute(), train.getCurrentLocation(), train.getPassengers(), train.getCapacity(), train.getSpeed(), train.getDirection());
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
		List<RiderInfo> riderInfos = metroDataRepository.getRiderInfo();
		if (riderInfos != null && !riderInfos.isEmpty()) {
			// TODO Add the appropriate method to set the info to martamodel
			System.out.println(riderInfos.size() + " Riders added");
		}
	
	}

	@Override
	public void getTransitData() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
