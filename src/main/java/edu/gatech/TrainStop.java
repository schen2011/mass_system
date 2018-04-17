package edu.gatech;

import java.util.Random;
import java.util.HashMap;

public class TrainStop {
    private Integer ID;
    private String stopName;
    private Double xCoord;
    private Double yCoord;
    private Random randGenerator;
    private HashMap<Integer, int[]> rateCatchingTrain;
    private HashMap<Integer, int[]> rateLeavingTrain;
    private Integer waiting;

    public TrainStop() {
        this.ID = -1;
    }

    public TrainStop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.xCoord = 0.0;
        this.yCoord = 0.0;
        randGenerator = new Random();
        rateCatchingTrain = new HashMap<Integer, int[]>();
        rateLeavingTrain = new HashMap<Integer, int[]>();
        this.waiting = 0;
    }

    public TrainStop(int uniqueValue, String inputName, int inputRiders, double inputXCoord, double inputYCoord) {
        this.ID = uniqueValue;
        this.stopName = inputName;
        this.xCoord = inputXCoord;
        this.yCoord = inputYCoord;
        randGenerator = new Random();
        rateCatchingTrain = new HashMap<Integer, int[]>();
        rateLeavingTrain = new HashMap<Integer, int[]>();
        this.waiting = inputRiders;
    }

    public void setName(String inputName) { this.stopName = inputName; }

    public void setRiders(int inputRiders) { this.waiting = inputRiders; }

    public void setXCoord(double inputXCoord) { this.xCoord = inputXCoord; }

    public void setYCoord(double inputYCoord) { this.yCoord = inputYCoord; }

    public Integer getID() { return this.ID; }

    public String getName() { return this.stopName; }

    public Integer getWaiting() { return this.waiting; }

    public Double getXCoord() { return this.xCoord; }

    public Double getYCoord() { return this.yCoord; }

    public void displayEvent() {
        System.out.println(" Train stop: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("get new people - exchange with Train when it passes by");
    }

    public Double findDistance(TrainStop destination) {
        // coordinates are measure in abstract units and conversion factor translates to statute miles
        final double distanceConversion = 70.0;
        return distanceConversion * Math.sqrt(Math.pow((this.xCoord - destination.getXCoord()), 2) + Math.pow((this.yCoord - destination.getYCoord()), 2));
    }

    public Integer exchangeRiders(int rank, int initialPassengerCount, int capacity) {
        int hourOfTheDay = (rank / 60) % 24;
        int ableToBoard;
        int[] leavingTrainRates, catchingTrainRates;
        int[] filler = new int[]{0, 1, 1};

        // calculate expected number riders leaving the Train
        if (rateLeavingTrain.containsKey(hourOfTheDay)) { leavingTrainRates = rateLeavingTrain.get(hourOfTheDay); }
        else { leavingTrainRates = filler; }
        int leavingTrain = randomBiasedValue(leavingTrainRates[0], leavingTrainRates[1], leavingTrainRates[2]);

        // update the number of riders actually leaving the Train versus the current number of passengers
        int updatedPassengerCount = Math.max(0, initialPassengerCount - leavingTrain);

        // calculate expected number riders leaving the Train
        if (rateCatchingTrain.containsKey(hourOfTheDay)) { catchingTrainRates = rateCatchingTrain.get(hourOfTheDay); }
        else { catchingTrainRates = filler; }
        int catchingTrain = randomBiasedValue(catchingTrainRates[0], catchingTrainRates[1], catchingTrainRates[2]);

        // determine how many of the currently waiting and new passengers will fit on the Train
        int tryingToBoard = waiting + catchingTrain;
        int availableSeats = capacity - updatedPassengerCount;

        // update the number of passengers left waiting for the next Train
        if (tryingToBoard > availableSeats) {
            ableToBoard = availableSeats;
            waiting = tryingToBoard - availableSeats;
        } else {
            ableToBoard = tryingToBoard;
            waiting = 0;
        }

        // update the number of riders actually catching the Train and return the difference from the original riders
        int finalPassengerCount = updatedPassengerCount + ableToBoard;
        return finalPassengerCount - initialPassengerCount;
    }

    public void addNewRiders(int moreRiders) { waiting = waiting + moreRiders; }

    public void displayInternalStatus() {
        System.out.print("> stop - ID: " + Integer.toString(ID));
        System.out.print(" name: " + stopName + " waiting: " + Integer.toString(waiting));
        System.out.println(" xCoord: " + Double.toString(xCoord) + " yCoord: " + Double.toString(yCoord));
    }

    public void addArrivalInfo(int timeSlot, int minOn, int avgOn, int maxOn, int minOff, int avgOff, int maxOff) {
        rateCatchingTrain.put(timeSlot, new int[]{minOn, avgOn, maxOn});
        rateLeavingTrain.put(timeSlot, new int[]{minOff, avgOff, maxOff});
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
            TrainStop me = (TrainStop) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}

