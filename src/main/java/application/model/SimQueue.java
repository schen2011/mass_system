package application.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SimQueue {
    private PriorityQueue<SimEvent> eventQueue;
    public PriorityQueue<SimEvent> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(PriorityQueue<SimEvent> eventQueue) {
		this.eventQueue = eventQueue;
	}

	public Comparator<SimEvent> getSimComparator() {
		return simComparator;
	}

	public void setSimComparator(Comparator<SimEvent> simComparator) {
		this.simComparator = simComparator;
	}

	public static Integer getPassengerfrequency() {
		return passengerFrequency;
	}

	private Comparator<SimEvent> simComparator;
    final static Integer passengerFrequency = 3;

    public SimQueue() {
        simComparator = new SimEventComparator();
        eventQueue = new PriorityQueue<SimEvent>(100, simComparator);
    }

    public void triggerNextEvent(MetroSystem metroSystemModel) {
        if (eventQueue.size() > 0) {
            SimEvent activeEvent = eventQueue.poll();
            activeEvent.displayEvent();
            switch (activeEvent.getType()) {
                case "move_bus":
                    // identify the bus that will move
                	BusSystem busModel = (BusSystem) metroSystemModel;
                    Bus activeBus = busModel.getBus(activeEvent.getID());
                    System.out.println(" the bus being observed is: " + Integer.toString(activeBus.getID()));

                    // identify the current stop
                    BusRoute activeRoute = busModel.getRoute(activeBus.getRouteID());
                    System.out.println(" the bus is driving on route: " + Integer.toString(activeRoute.getID()));

                    int activeLocation = activeBus.getCurrentLocation();
                    int activeStopID = activeRoute.getStopID(activeLocation);
                    BusStop activeStop = busModel.getStop(activeStopID);
                    System.out.println(" the bus is currently at stop: " + Integer.toString(activeStop.getID()) + " - " + activeStop.getName());

                    // drop off and pickup new passengers at current stop
                    int currentPassengers = activeBus.getPassengers();
                    int passengerDifferential = 0; //activeStop.exchangeRiders(activeEvent.getRank(), currentPassengers, activeBus.getCapacity());
                    
                    System.out.println(" passengers pre-stop: " + Integer.toString(currentPassengers) + " post-stop: " + (currentPassengers + passengerDifferential));
                    activeBus.adjustPassengers(passengerDifferential);

                    // determine next stop
                    int nextLocation = activeRoute.getNextLocation(activeLocation);
                    int nextStopID = activeRoute.getStopID(nextLocation);
                    BusStop nextStop = busModel.getStop(nextStopID);
                    System.out.println(" the bus is heading to stop: " + Integer.toString(nextStopID) + " - " + nextStop.getName() + "\n");

                    // find distance to stop to determine next event time
                    Double travelDistance = activeStop.findDistance(nextStop);
                    // conversion is used to translate time calculation from hours to minutes
                    int travelTime = 1 + (travelDistance.intValue() * 60 / activeBus.getSpeed());
                    //activeBus.setCurrentLocation(nextLocation);
                    
                    // generate next event for this bus
                    eventQueue.add(new SimEvent(activeEvent.getRank() + travelTime, "move_bus", activeEvent.getID()));
                    break;
                default:
                    System.out.println(" event not recognized");
                    break;
            }
        } else {
            System.out.println(" event queue empty");
        }
    }

    public void addNewEvent(Integer eventRank, String eventType, Integer eventID) {
        eventQueue.add(new SimEvent(eventRank, eventType, eventID));
    }
    
    public void addNewEvent(Integer eventId, Integer rank, Integer vehicleId) {
        eventQueue.add(new SimEvent(eventId, rank, vehicleId));
    }
}
