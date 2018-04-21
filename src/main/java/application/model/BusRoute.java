package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusRoute {
    private Integer ID;
    private Integer routeNumber;
    private String routeName;
    private HashMap<Integer, Integer> stopsOnRoute;
    private HashMap<Integer, List<Double>> roadsOnRoute;

    public BusRoute() {
        this.ID = -1;
    }

    public BusRoute(int uniqueValue) {
        this.ID = uniqueValue;
        this.routeNumber = -1;
        this.routeName = "";
        this.stopsOnRoute = new HashMap<Integer, Integer>();
        this.roadsOnRoute = new HashMap<Integer, List<Double>>();
    }

    public BusRoute(int uniqueValue, int inputNumber, String inputName) {
        this.ID = uniqueValue;
        this.routeNumber = inputNumber;
        this.routeName = inputName;
        this.stopsOnRoute = new HashMap<Integer, Integer>();
        this.roadsOnRoute = new HashMap<Integer, List<Double>>();

   }

    public void setNumber(int inputNumber) { this.routeNumber = inputNumber; }

    public void setName(String inputName) { this.routeName = inputName; }

    public void addNewStop(int stopID) { this.stopsOnRoute.put(stopsOnRoute.size(), stopID); }
    public void addNewRoad(double length, double speed, double trafficstatus) {
		List<Double> list = new ArrayList<Double>();
		list.add(length);
		list.add(speed);
		list.add(trafficstatus);
		this.roadsOnRoute.put(roadsOnRoute.size(), list);
	}

    public Integer getID() { return this.ID; }

    public Integer getNumber() { return this.routeNumber; }

    public String getName() { return this.routeName; }

    public void displayEvent() {
        System.out.println(" bus route: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("provide next stop on route along with the distance");
    }

    public Integer getNextLocation(int routeLocation) {
        int routeSize = this.stopsOnRoute.size();
        if (routeSize > 0) { return (routeLocation + 1) % routeSize; }
        return -1;
    }

    public Integer getStopID(int routeLocation) { return this.stopsOnRoute.get(routeLocation); }
    public Integer getStopRank(int stopID) { 
    	for (int key : stopsOnRoute.keySet()) {
    		if (stopsOnRoute.get(key) == stopID) {
    			return key;
    		}
    	}
    	
    	return -1;}

    public boolean hasStop(int stopID) { return stopsOnRoute.containsValue(stopID); }

    public List<Double> calculateRoute(int stStopRank, int dstStopRank) {
	    double distance = 0.0;
	    double stopcount = 0;
	    double time = 0.0;
	    
    	int current = stStopRank;
	    if (stStopRank>dstStopRank) {
	    	current =  dstStopRank;
	    	dstStopRank = stStopRank;
	    	stStopRank = current;
	    }
	    while (current != dstStopRank) {
	    	distance += roadsOnRoute.get(current).get(0);
	    	// trafficstatus is a fraction that reduce full speed limit to  0 ~ 0.99 times it
	    	time += roadsOnRoute.get(current).get(0)/(roadsOnRoute.get(current).get(1)* roadsOnRoute.get(current).get(2));
	    	stopcount++;
	    	current++;
	    }
	    
	    List<Double> stats = new ArrayList<Double>();
		stats.add(distance);
		stats.add(time);
		stats.add(stopcount);
	    return stats;
    }
    
    
    

    public Integer getLength() { return this.stopsOnRoute.size(); }

    public void displayInternalStatus() {
        System.out.print("> route - ID: " + Integer.toString(ID));
        System.out.print(" number: " + Integer.toString(routeNumber) + " name: " + routeName);
        System.out.print(" stops: [ ");
        for (int i = 0; i < stopsOnRoute.size(); i++) {
            System.out.print(Integer.toString(i) + ":" + Integer.toString(stopsOnRoute.get(i)) + " ");
            
        }
        System.out.println("] road lengths [ " + roadsOnRoute.size());
        for (int i = 0; i < roadsOnRoute.size(); i++) {
            System.out.print(Integer.toString(i) + ":" + Double.toString(roadsOnRoute.get(i).get(0)) + " ");
            
        }
      
        
        System.out.println("]");
    }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            BusRoute me = (BusRoute) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

	public HashMap<Integer, List<Double>> getStopRoad() {
		return roadsOnRoute;
	}

	public void setStopRoad(HashMap<Integer, List<Double>> stopRoad) {
		this.roadsOnRoute = stopRoad;
	}

}
