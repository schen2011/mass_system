package application.model;

import java.util.Random;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class TrainStop implements Stop {
    private Integer ID;
    private String stopName;
    private Double xCoord;
    private Double yCoord;
    private Random randGenerator;
    private HashMap<Integer, int[]> rateCatchingTrain;
    private HashMap<Integer, int[]> rateLeavingTrain;
    private Integer waiting;
    private HashMap<Integer, Queue<Rider>> waitingQueue;

    public TrainStop() {}

    public TrainStop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.xCoord = 0.0;
        this.yCoord = 0.0;
        randGenerator = new Random();
        rateCatchingTrain = new HashMap<Integer, int[]>();
        rateLeavingTrain = new HashMap<Integer, int[]>();
        this.waiting = 0;
        this.waitingQueue = new HashMap<>();
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
        this.waitingQueue = new HashMap<>();
   }
    
    public void setID(int ID) { this.ID = ID; }
    
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

    public void exchangeRiders(Train train) {
    	Iterator<Rider> riders = train.getRiderList().iterator();
    	while (riders.hasNext()) {
    		Rider rider = riders.next();
    		if (rider != null && rider.getDestinationStopId() == this.ID) {
    			riders.remove();
    		}
    	}
    	int spaceLeft = train.getCapacity() - train.getRiderList().size();
    	Queue<Rider> waitingRider = waitingQueue.get(train.getRouteID());
    	while (spaceLeft > 0 && !waitingRider.isEmpty()) {
    		train.getRiderList().add(waitingRider.poll());
    		spaceLeft--;
    	}
    }
    
    public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public Double getxCoord() {
		return xCoord;
	}

	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}

	public Double getyCoord() {
		return yCoord;
	}

	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}

	public Random getRandGenerator() {
		return randGenerator;
	}

	public void setRandGenerator(Random randGenerator) {
		this.randGenerator = randGenerator;
	}

	public HashMap<Integer, int[]> getRateCatchingTrain() {
		return rateCatchingTrain;
	}

	public void setRateCatchingTrain(HashMap<Integer, int[]> rateCatchingTrain) {
		this.rateCatchingTrain = rateCatchingTrain;
	}

	public HashMap<Integer, int[]> getRateLeavingTrain() {
		return rateLeavingTrain;
	}

	public void setRateLeavingTrain(HashMap<Integer, int[]> rateLeavingTrain) {
		this.rateLeavingTrain = rateLeavingTrain;
	}

	public HashMap<Integer, Queue<Rider>> getWaitingQueue() {
		return waitingQueue;
	}

	public void setWaitingQueue(HashMap<Integer, Queue<Rider>> waitingQueue) {
		this.waitingQueue = waitingQueue;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setWaiting(Integer waiting) {
		this.waiting = waiting;
	}

	public void addNewRiders(int moreRiders) { waiting = waiting + moreRiders; }
    
    public void addNewRiders(Rider rider) {
    	int routeId = rider.getRouteId();
    	this.waitingQueue.putIfAbsent(routeId, new LinkedList<>());
    	this.waitingQueue.get(rider.getRouteId()).add(rider);
    }
    
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
