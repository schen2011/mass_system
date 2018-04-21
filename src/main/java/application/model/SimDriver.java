package application.model;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.*;
import java.util.Properties;

public class SimDriver {
    private SimQueue simEngine;
    private BusSystem busModel;
    private TrainSystem trainModel;

    public SimDriver() {
        simEngine = new SimQueue();
        busModel = new BusSystem();
        trainModel = new TrainSystem();
    }

    public SimQueue getSimEngine() {
		return simEngine;
	}

	public BusSystem getMartaModel() {
		return busModel;
	}

	public TrainSystem getTrainModel() {
		return trainModel;
	}

	public void runInterpreter() {
        final String DELIMITER = ",";
        Scanner takeCommand = new Scanner(System.in);
        String[] tokens;

        do {
            System.out.print("# main: ");
            String userCommandLine = takeCommand.nextLine();
            tokens = userCommandLine.split(DELIMITER);

            switch (tokens[0]) {
                case "add_event":
                    simEngine.addNewEvent(Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]));
                    System.out.print(" new event - rank: " + Integer.parseInt(tokens[1]));
                    System.out.println(" type: " + tokens[2] + " ID: " + Integer.parseInt(tokens[3]) + " created");
                    break;
                case "add_bus_stop":
                    int stopID = busModel.makeStop(Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]));
                    System.out.println(" new stop: " + Integer.toString(stopID) + " created");
                    break;
                case "add_train_stop":
                    stopID = trainModel.makeStop(Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]));
                    System.out.println(" new stop: " + Integer.toString(stopID) + " created");
                    break;
                case "add_bus_route":
                    int routeID = busModel.makeRoute(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
                    System.out.println(" new route: " + Integer.toString(routeID) + " created");
                    break;
                case "add_train_route":
                    routeID = trainModel.makeRoute(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
                    System.out.println(" new route: " + Integer.toString(routeID) + " created");
                    break;
                case "add_bus":
//                    int busID = busModel.makeBus(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 
//                    		Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
//                    System.out.println(" new bus: " + Integer.toString(busID) + " created");
//                    break;
                case "add_train":
//                    int trainID = trainModel.makeTrain(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]),tokens[7]);
//                    System.out.println(" new train: " + Integer.toString(trainID) + " created");
//                    break;
                case "extend_bus_route":
                	busModel.appendStopToRoute(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                	busModel.appendRoadToRoute(Integer.parseInt(tokens[1]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]));
                    
                    System.out.println(" stop: " + Integer.parseInt(tokens[2]) + " appended to route " + Integer.parseInt(tokens[1]));
                    break;
                case "extend_train_route":
                    trainModel.appendStopToRoute(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    System.out.println(" stop: " + Integer.parseInt(tokens[2]) + " appended to route " + Integer.parseInt(tokens[1]));
                    break;
                case "step_once":
                    simEngine.triggerNextEvent(busModel);
                    System.out.println(" queue activated for 1 event");
                    break;
                case "step_multi":
                    System.out.println(" queue activated for " + Integer.parseInt(tokens[1]) + " event(s)");
                    for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                    	// display the number of events completed for a given frequency
                    	if (tokens.length >= 3) {
                    		if (i % Integer.parseInt(tokens[2]) == 0) { System.out.println("> " + Integer.toString(i) + " events completed"); }
                    	}
                    	// execute the next event
                    	simEngine.triggerNextEvent(busModel);
                    	
                    	// pause after each event for a given number of seconds
                    	if (tokens.length >= 4) {
                    		try { Thread.sleep(Integer.parseInt(tokens[3]) * 1000); }
                    			catch (InterruptedException e) { e.printStackTrace(); }
                    	}
                    	// regenerate the model display (Graphviz dot file) for a given frequency
                    	if (tokens.length >= 5) {
                    		if (i % Integer.parseInt(tokens[4]) == 0) { busModel.displayModel();}
                    	}
                    }
                    break;
                case "system_report":
                    System.out.println(" bus system report - stops, buses and routes:");
                    for (BusStop singleStop: busModel.getStops().values()) { singleStop.displayInternalStatus(); }
                    for (Bus singleBus: busModel.getBuses().values()) { singleBus.displayInternalStatus(); }
                    for (BusRoute singleRoute: busModel.getRoutes().values()) { singleRoute.displayInternalStatus(); }
                    System.out.println(" train system report - stops, buses and routes:");
                    for (TrainStop singleStop: trainModel.getStops().values()) { singleStop.displayInternalStatus(); }
                    for (Train singleTrain: trainModel.getTrains().values()) { singleTrain.displayInternalStatus(); }
                    for (TrainRoute singleRoute: trainModel.getRoutes().values()) { singleRoute.displayInternalStatus(); }
                    break;
                case "display_model":
                	busModel.displayModel();
                    trainModel.displayModel();
                    break;
                case "search_routes":
	                int stID = Integer.parseInt(tokens[1]);
	                int dstID = Integer.parseInt(tokens[2]);
	                List<Integer> busRouteChoice= new ArrayList<Integer>(); 
	                List<Integer> trainRouteChoice= new ArrayList<Integer>(); 
	            	System.out.println("Take below routes from stop "+tokens[1]+" to stop "+tokens[2] );
	                    for ( BusRoute route: busModel.getRoutes().values()){
	                        if (route.hasStop(stID) && route.hasStop(dstID)){
	                            busRouteChoice.add(route.getID());
	                        }
	                    } 
	                    for (int i: busRouteChoice) {
	                    	System.out.println("Take Bus Route " + i );
	                    }
	                    for ( TrainRoute route: trainModel.getRoutes().values()){
	                        if (route.hasStop(stID) && route.hasStop(dstID)){
	                            trainRouteChoice.add(route.getID());
	                        }
	                    } 
	                    for (int i: trainRouteChoice) {
	                    	System.out.println("Take Train Route " + i );
	                    }
	                    break;
                case "quit":
                    System.out.println(" stop the command loop");
                    break;
                default:
                    System.out.println(" command not recognized");
                    break;
            }

        } while (!tokens[0].equals("quit"));

        takeCommand.close();
    }

	public BusSystem getBusModel() {
		return busModel;
	}

	public void setBusModel(BusSystem busModel) {
		this.busModel = busModel;
	}

	public void setSimEngine(SimQueue simEngine) {
		this.simEngine = simEngine;
	}

	public void setTrainModel(TrainSystem trainModel) {
		this.trainModel = trainModel;
	}

}
