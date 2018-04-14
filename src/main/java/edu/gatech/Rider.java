package edu.gatech;

/**
 * Created by xan on 4/9/2018.
 */
public class Rider {

    //route ID (UI side: dropdown)
    private Integer targetBusRoute;

    // stop ID (UI side: dropdown)
    private Integer destination;

    public Rider() { }


    public Rider(int inputRoute) {
        this.targetBusRoute= inputRoute;
        this.destination = -1;
    }

    public Rider(int inputRoute, int inputDestination) {
        this.targetBusRoute= inputRoute;
        this.destination = inputDestination;
    }

    public void setTargetBusRoute(int inputRoute) { this.targetBusRoute = inputRoute; }

    public void setDestination(int inputDestination) { this.destination = inputDestination; }


    public Integer getTargetBusRoute() { return this.targetBusRoute; }

    public Integer getDestination() { return this.destination; }

    /*
    public void displayEvent() {
        // System.out.println();
        System.out.println("# event rank: " + Integer.toString(timeRank) + " type: " + eventType + " ID: " + Integer.toString(eventID));
    }
    */

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Rider rider = (Rider) object;
            if (this.targetBusRoute == rider.getTargetBusRoute() && this.destination == rider.getDestination()) {
                result = true;
            }
        }
        return result;
    }
}
