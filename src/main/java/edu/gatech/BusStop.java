package edu.gatech;

import java.util.Iterator;
import java.util.Random;
import java.util.HashMap;

public class BusStop {
    private Integer ID;
    private String stopName;
    private Double xCoord;
    private Double yCoord;
    private Random randGenerator;
    private HashMap<Integer, int[]> rateCatchingBus;
    private HashMap<Integer, int[]> rateLeavingBus;

    //in the bus stop, for each route, there is an array of waiting riders
    private HashMap<Integer, Rider[]> waiting;

    public BusStop() {
        this.ID = -1;
    }

    public BusStop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.xCoord = 0.0;
        this.yCoord = 0.0;
        randGenerator = new Random();
        rateCatchingBus = new HashMap<Integer, int[]>();
        rateLeavingBus = new HashMap<Integer, int[]>();
        waiting = new HashMap<Integer, Rider[]>();
    }

    // no need to provide # of waiting riders when adding new stop
    public BusStop(int uniqueValue, String inputName, double inputXCoord, double inputYCoord) {
        this.ID = uniqueValue;
        this.stopName = inputName;
        this.xCoord = inputXCoord;
        this.yCoord = inputYCoord;
        randGenerator = new Random();
        rateCatchingBus = new HashMap<Integer, int[]>();
        rateLeavingBus = new HashMap<Integer, int[]>();
        waiting = new HashMap<Integer, Rider[]>();
   }

    public void setName(String inputName) { this.stopName = inputName; }

    // need to provider route ID to set waiting riders for the specific route
    public void setRiders(int routeID, int inputRiders) {

        Rider[] riders = new Rider[inputRiders];
        for( int i=0; i<inputRiders-1; i++ )
            riders[i] = new Rider(routeID);

        this.waiting.put(routeID, riders);

    }

    public void setXCoord(double inputXCoord) { this.xCoord = inputXCoord; }

    public void setYCoord(double inputYCoord) { this.yCoord = inputYCoord; }

    public Integer getID() { return this.ID; }

    public String getName() { return this.stopName; }

    // if route ID is not provided, return the sum of waiting riders of all routes
    public Integer getWaiting()
    {
        int totalWaitingRiders = 0;
        Iterator it = waiting.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            totalWaitingRiders = totalWaitingRiders + ((Integer)pair.getValue()).intValue();
            it.remove(); // avoids a ConcurrentModificationException
        }

        return totalWaitingRiders;
    }

    // if route ID is provided, return # of waiting riders for the specific route
    public Integer getWaiting(int routeID)
    {
        //check if there is a waiting array for route ID
        boolean isWaiting = false;

        Iterator it = waiting.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            if(routeID == ((Integer)pair.getKey()).intValue())
            {
                isWaiting = true;
                break;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        if(isWaiting){

            return this.waiting.get(routeID).length;
        }
        else{return 0;}

    }


    public Double getXCoord() { return this.xCoord; }

    public Double getYCoord() { return this.yCoord; }

    public void displayEvent() {
        System.out.println(" bus stop: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("get new people - exchange with bus when it passes by");
    }

    //to-do
    public Double findDistance(BusStop destination) {
        // coordinates are measure in abstract units and conversion factor translates to statute miles
        final double distanceConversion = 70.0;
        return distanceConversion * Math.sqrt(Math.pow((this.xCoord - destination.getXCoord()), 2) + Math.pow((this.yCoord - destination.getYCoord()), 2));
    }

    //append a stop to a route, and also specify the # of riders waiting for the route
    public void addNewRoute(int routeID, int inputRiders)

    {
        Rider[] riders = new Rider[inputRiders];
        for( int i=0; i<inputRiders-1; i++ )
            riders[i] = new Rider(routeID);

        this.waiting.put(routeID, riders);
    }

    // need to provider route ID to add more riders for the specific route
    public void addNewRiders(int routeID, int moreRiders) {

        int currentRidersSize = this.waiting.get(routeID).length;
        int updatedRidersSize = currentRidersSize + moreRiders;

        Rider[] currentRiders = this.waiting.get(routeID);
        Rider[] updatedRiders = new Rider[updatedRidersSize];

        for( int i=0; i<currentRidersSize-1; i++ )
            updatedRiders[i] = currentRiders[i];

        for( int i=(currentRidersSize); i<updatedRidersSize-1; i++ )
            updatedRiders[i] = new Rider(routeID);

        this.waiting.remove(routeID);
        this.waiting.put(routeID, updatedRiders);

    }

    // need to know the route ID to exchange the riders from the bus stop
    public Integer exchangeRiders(int rank, int initialPassengerCount, int capacity, int routeID) {
        int hourOfTheDay = (rank / 60) % 24;
        int ableToBoard;
        int[] leavingBusRates, catchingBusRates;
        int[] filler = new int[]{0, 1, 1};

        // calculate expected number riders leaving the bus
        if (rateLeavingBus.containsKey(hourOfTheDay)) { leavingBusRates = rateLeavingBus.get(hourOfTheDay); }
        else { leavingBusRates = filler; }
        int leavingBus = randomBiasedValue(leavingBusRates[0], leavingBusRates[1], leavingBusRates[2]);

        // update the number of riders actually leaving the bus versus the current number of passengers
        int updatedPassengerCount = Math.max(0, initialPassengerCount - leavingBus);

        // calculate expected number riders catching the bus
        if (rateCatchingBus.containsKey(hourOfTheDay)) { catchingBusRates = rateCatchingBus.get(hourOfTheDay); }
        else { catchingBusRates = filler; }
        int catchingBus = randomBiasedValue(catchingBusRates[0], catchingBusRates[1], catchingBusRates[2]);

        // determine how many of the currently waiting and new passengers will fit on the bus
        int tryingToBoard = this.getWaiting(routeID) + catchingBus;
        int availableSeats = capacity - updatedPassengerCount;

        // update the number of passengers left waiting for the next bus
        if (tryingToBoard > availableSeats) {

            ableToBoard = availableSeats;

            int waitingToBoard = tryingToBoard - availableSeats;

            Rider[] currentRiders = this.waiting.get(routeID);
            Rider[] updatedRiders = new Rider[waitingToBoard];

            for( int i=ableToBoard; i<waitingToBoard-1; i++ )
                updatedRiders[i] = currentRiders[i];

            this.waiting.remove(routeID);
            this.waiting.put(routeID, updatedRiders);

        } else {
            ableToBoard = tryingToBoard;
            //no waiting array for next bus
            this.waiting.remove(routeID);
        }

        // update the number of riders actually catching the bus and return the difference from the original riders
        int finalPassengerCount = updatedPassengerCount + ableToBoard;
        return finalPassengerCount - initialPassengerCount;
    }


    public void displayInternalStatus() {

        System.out.print("> stop - ID: " + Integer.toString(ID));

        System.out.print(" name: " + stopName);

        //loop HashMap waiting
        Iterator it = waiting.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            int routeID = ((Integer)pair.getKey()).intValue();
            System.out.print(" route: " + routeID + " waiting: " + this.getWaiting(routeID));
            it.remove(); // avoids a ConcurrentModificationException
        }

        System.out.println(" xCoord: " + Double.toString(xCoord) + " yCoord: " + Double.toString(yCoord));
    }

    public void addArrivalInfo(int timeSlot, int minOn, int avgOn, int maxOn, int minOff, int avgOff, int maxOff) {
        rateCatchingBus.put(timeSlot, new int[]{minOn, avgOn, maxOn});
        rateLeavingBus.put(timeSlot, new int[]{minOff, avgOff, maxOff});
    }

    private int randomBiasedValue(int lower, int middle, int upper) {
        int lowerRange = randGenerator.nextInt(middle - lower + 1) + lower;
        int upperRange = randGenerator.nextInt(upper - middle + 1) + middle;
        return (lowerRange + upperRange) /2;
    }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            BusStop me = (BusStop) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}
