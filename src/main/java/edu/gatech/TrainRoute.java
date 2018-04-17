package edu.gatech;

import java.util.HashMap;

public class TrainRoute {
    private Integer ID;
    private Integer routeNumber;
    private String routeName;
    private HashMap<Integer, Integer> stopsOnRoute;

    public TrainRoute() {
        this.ID = -1;
    }

    public TrainRoute(int uniqueValue) {
        this.ID = uniqueValue;
        this.routeNumber = -1;
        this.routeName = "";
        this.stopsOnRoute = new HashMap<Integer, Integer>();
    }

    public TrainRoute(int uniqueValue, int inputNumber, String inputName) {
        this.ID = uniqueValue;
        this.routeNumber = inputNumber;
        this.routeName = inputName;
        this.stopsOnRoute = new HashMap<Integer, Integer>();
   }

    public void setNumber(int inputNumber) { this.routeNumber = inputNumber; }

    public void setName(String inputName) { this.routeName = inputName; }

    public void addNewStop(int stopID) { this.stopsOnRoute.put(stopsOnRoute.size(), stopID); }

    public Integer getID() { return this.ID; }

    public Integer getNumber() { return this.routeNumber; }

    public String getName() { return this.routeName; }
    public boolean hasStop(int stopID) { return stopsOnRoute.containsValue(stopID); }

    public void displayEvent() {
        System.out.println(" train route: " + Integer.toString(this.ID));
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

    public Integer getLength() { return this.stopsOnRoute.size(); }

    public void displayInternalStatus() {
        System.out.print("> route - ID: " + Integer.toString(ID));
        System.out.print(" number: " + Integer.toString(routeNumber) + " name: " + routeName);
        System.out.print(" stops: [ ");
        for (int i = 0; i < stopsOnRoute.size(); i++) {
            System.out.print(Integer.toString(i) + ":" + Integer.toString(stopsOnRoute.get(i)) + " ");
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
            TrainRoute me = (TrainRoute) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}
