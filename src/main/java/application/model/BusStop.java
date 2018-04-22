package application.model;

import java.util.Random;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class BusStop implements Stop {
    private Integer ID;
    private String stopName;
    private Double xCoord;
    private Double yCoord;
   
    private Random randGenerator;
    private HashMap<Integer, int[]> rateCatchingBus;
    private HashMap<Integer, int[]> rateLeavingBus;
    private Integer waiting;
    private HashMap<Integer, Queue<Rider>> waitingQueue;

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
        this.waiting = 0;
    }

    public BusStop(int uniqueValue, String inputName, int inputRiders, double inputXCoord, double inputYCoord) {
        this.ID = uniqueValue;
        this.stopName = inputName;
        this.xCoord = inputXCoord;
        this.yCoord = inputYCoord;
        randGenerator = new Random();
        rateCatchingBus = new HashMap<Integer, int[]>();
        rateLeavingBus = new HashMap<Integer, int[]>();
        this.waiting = inputRiders;
        this.waitingQueue = new HashMap<>();
    }
 
    public void displayEvent() {
        System.out.println(" bus stop: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("get new people - exchange with bus when it passes by");
    }

    public Double findDistance(BusStop destination) {
        // coordinates are measure in abstract units and conversion factor translates to statute miles
        final double distanceConversion = 70.0;
        return distanceConversion * Math.sqrt(Math.pow((this.xCoord - destination.getXCoord()), 2) + Math.pow((this.yCoord - destination.getYCoord()), 2));
    }

    public void exchangeRiders(Bus bus) {
    	Iterator<Rider> riders = bus.getRiderList().iterator();
    	while (riders.hasNext()) {
    		if (riders.next().getDestinationStopId() == this.ID) {
    			riders.remove();
    		}
    	}
    	int spaceLeft = bus.getCapacity() - bus.getRiderList().size();
    	Queue<Rider> waitingRider = waitingQueue.get(bus.getRouteID());
    	while (spaceLeft > 0 && !waitingRider.isEmpty()) {
    		bus.getRiderList().add(waitingRider.poll());
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

	public HashMap<Integer, int[]> getRateCatchingBus() {
		return rateCatchingBus;
	}

	public void setRateCatchingBus(HashMap<Integer, int[]> rateCatchingBus) {
		this.rateCatchingBus = rateCatchingBus;
	}

	public HashMap<Integer, int[]> getRateLeavingBus() {
		return rateLeavingBus;
	}

	public void setRateLeavingBus(HashMap<Integer, int[]> rateLeavingBus) {
		this.rateLeavingBus = rateLeavingBus;
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

	public void addNewRiders(Rider rider) {
    	int routeID = rider.getRouteId();
    	this.waitingQueue.putIfAbsent(routeID, new LinkedList<>());
    	this.waitingQueue.get(rider.getRouteId()).add(rider);
    }
    
    public void addNewRiders(int moreRiders) { waiting = waiting + moreRiders; }

    public void displayInternalStatus() {
        System.out.print("> stop - ID: " + Integer.toString(ID));
        System.out.print(" name: " + stopName + " waiting: " + Integer.toString(waiting));
        System.out.println(" xCoord: " + Double.toString(xCoord) + " yCoord: " + Double.toString(yCoord));
    }

    public void addArrivalInfo(int timeSlot, int minOn, int avgOn, int maxOn, int minOff, int avgOff, int maxOff) {
        rateCatchingBus.put(timeSlot, new int[]{minOn, avgOn, maxOn});
        rateLeavingBus.put(timeSlot, new int[]{minOff, avgOff, maxOff});
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
