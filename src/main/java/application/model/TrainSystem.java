package application.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TrainSystem implements MetroSystem {
    private HashMap<Integer, TrainStop> stops;
    private HashMap<Integer, TrainRoute> routes;
    private HashMap<Integer, Train> trains;
    private HashMap<Integer, Road> roads;

    public TrainSystem() {
        stops = new HashMap<Integer, TrainStop>();
        routes = new HashMap<Integer, TrainRoute>();
        trains = new HashMap<Integer, Train>();
        roads = new HashMap<Integer, Road>();
    }
    
    public TrainStop getStop(int stopID) {
        if (stops.containsKey(stopID)) { return stops.get(stopID); }
        return null;
    }

    public TrainRoute getRoute(int routeID) {
        if (routes.containsKey(routeID)) { return routes.get(routeID); }
        return null;
    }

    public Train getTrain(int trainID) {
        if (trains.containsKey(trainID)) { return trains.get(trainID); }
        return null;
    }

    public int makeStop(int uniqueID, String inputName, int inputRiders, double inputXCoord, double inputYCoord) {
        stops.put(uniqueID, new TrainStop(uniqueID, inputName, inputRiders, inputXCoord, inputYCoord));
        return uniqueID;
    }
    
    public int makeRoad(Integer uniqueID, String roadName, 
			double roadLength, double averageSpeed, int trafficIndicator) {
    	roads.put(uniqueID, new Road(uniqueID, roadName, roadLength, averageSpeed, trafficIndicator));
    	return uniqueID;
    }
    
    public void appendRoadToRoute(int routeID, double length, double speed, double trafficstatus) { routes.get(routeID).addNewRoad(length, speed, trafficstatus); }

    public int makeRoute(int uniqueID, int inputNumber, String inputName) {
        routes.put(uniqueID, new TrainRoute(uniqueID, inputNumber, inputName));
        return uniqueID;
    }

    public int makeTrain(int uniqueID, int inputRoute, int inputLocation, int inputLocation2, int inputPassengers, int inputCapacity, int inputSpeed, String direction) {
        trains.put(uniqueID, new Train(uniqueID, inputRoute, 
        		inputLocation, inputLocation2, inputPassengers, inputCapacity, inputSpeed, direction));
        return uniqueID;
    }

    public void appendStopToRoute(int routeID, int nextStopID) { routes.get(routeID).addNewStop(nextStopID); }

    public HashMap<Integer, TrainStop> getStops() { return stops; }

    public HashMap<Integer, TrainRoute> getRoutes() { return routes; }

    public HashMap<Integer, Train> getTrains() { return trains; }
    
    public HashMap<Integer, Road> getRoads() {
    	return roads;
    }
    
    public MovingHistory moveTrain(Train train) {
    	TrainStop currentStop = stops.get(train.getCurrentLocation());
    	if (train.getRiderList().isEmpty()) {
    		train.setRiderList(new ArrayList<>());
    	}
    	int currentRider = train.getRiderList().size();
    	if (currentStop.getWaitingQueue().isEmpty()) {
    		currentStop.setWaitingQueue(new HashMap<>());
    	}
    	if (!currentStop.getWaitingQueue().containsKey(train.getRouteID())) {
    		currentStop.getWaitingQueue().put(train.getRouteID(), new LinkedList<>());
    	}
    	int riderWaiting = currentStop.getWaitingQueue().get(train.getRouteID()).size();
    	currentStop.exchangeRiders(train);
    	int afterExchange = train.getRiderList().size();
    	//load rider first
    	int nextStop = train.getNextLocation();
    	train.setCurrentLocation(train.getNextLocation());
    	return saveMovingHistory(currentStop.getID(), currentRider, riderWaiting, afterExchange, nextStop);
    }
    
    private MovingHistory saveMovingHistory(int currentStop, int currentRider, int riderWaiting, int afterExchange,
			int nextStop) {
		MovingHistory moveHistory = new MovingHistory();
		moveHistory.setCurrentStop(stops.get(currentStop).getName());
		moveHistory.setCurrentRider(currentRider);
		moveHistory.setPeopleWaiting(riderWaiting);
		moveHistory.setNewRider(afterExchange - currentRider);
		moveHistory.setNextStop(stops.get(nextStop).getName());
		return moveHistory;
	}    
    
    public void displayModel() {
    	ArrayList<MiniPair> trainNodes, stopNodes;
    	MiniPairComparator compareEngine = new MiniPairComparator();
    	
    	int[] colorScale = new int[] {9, 29, 69, 89, 101};
    	String[] colorName = new String[] {"#000077", "#0000FF", "#000000", "#770000", "#FF0000"};
    	Integer colorSelector, colorCount, colorTotal;
    	try{
            // create new file access path
            String path="./train.dot";
            File file = new File(path);

            // create the file if it doesn't exist
            if (!file.exists()) { file.createNewFile();}

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("digraph G\n");
            bw.write("{\n");
    	
            trainNodes = new ArrayList<MiniPair>();
            for (Train b: trains.values()) { trainNodes.add(new MiniPair(b.getID(), b.getPassengers())); }
            Collections.sort(trainNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = trainNodes.size();
            for (MiniPair c: trainNodes) {
            	if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
            	bw.write("  train" + c.getID() + " [ label=\"train#" + c.getID() + " | " + c.getValue() + " riding\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            
            stopNodes = new ArrayList<MiniPair>();
            for (TrainStop s: stops.values()) { stopNodes.add(new MiniPair(s.getID(), s.getWaiting())); }
            Collections.sort(stopNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = stopNodes.size();    	
            for (MiniPair t: stopNodes) {
            	if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
            	bw.write("  stop" + t.getID() + " [ label=\"stop#" + t.getID() + " | " + t.getValue() + " waiting\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();

            for (Train m: trains.values()) {
            	//System.out.println(m.getRouteID());
            	//System.out.println(routes.get(m.getRouteID()));
            	//Integer prevStop = routes.get(m.getRouteID()).getStopID(m.getNextLocation());
            	//Integer nextStop = routes.get(m.getRouteID()).getStopID(m.getLocation());
            	//bw.write("  stop" + Integer.toString(prevStop) + " -> train" + Integer.toString(m.getID()) + " [ label=\" dep\" ];\n");
            	//bw.write("  train" + Integer.toString(m.getID()) + " -> stop" + Integer.toString(nextStop) + " [ label=\" arr\" ];\n");
            }

            bw.write("}\n");
            bw.close();

    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
	public void setStops(HashMap<Integer, TrainStop> stops) {
		this.stops = stops;
	}
	public void setRoutes(HashMap<Integer, TrainRoute> routes) {
		this.routes = routes;
	}
	public void setTrains(HashMap<Integer, Train> trains) {
		this.trains = trains;
	}
	public void setRoads(HashMap<Integer, Road> roads) {
		this.roads = roads;
	}
}
