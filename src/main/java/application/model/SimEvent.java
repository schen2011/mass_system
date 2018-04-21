package application.model;

public class SimEvent {
    private Integer timeRank;
    private String eventType;
    private Integer eventID;

    public SimEvent() { this.timeRank = 0; }

    public SimEvent(int inputRank, String inputType, int inputID) {
        this.timeRank= inputRank;
        this.eventType = inputType;
        this.eventID = inputID;
    }
    
    private Integer vehicleID;
    
    public SimEvent(int eventID, int rank, int vehicleID) {
    	this.eventID = eventID;
    	this.timeRank = rank;
    	this.vehicleID = vehicleID;
    }

    public void setRank(int inputRank) { this.timeRank = inputRank; }

    public void setType(String inputType) { this.eventType = inputType; }

    public void setID(int inputID) { this.eventID = inputID; }

    public Integer getRank() { return this.timeRank; }

    public String getType() { return this.eventType; }

    public Integer getID() { return this.eventID; }

    public void displayEvent() {
        // System.out.println();
        System.out.println("# event rank: " + Integer.toString(timeRank) + " type: " + eventType + " ID: " + Integer.toString(eventID));
    }

    public Integer getTimeRank() {
		return timeRank;
	}

	public void setTimeRank(Integer timeRank) {
		this.timeRank = timeRank;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public Integer getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(Integer vehicleID) {
		this.vehicleID = vehicleID;
	}

	//Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            SimEvent me = (SimEvent) object;
            if (this.timeRank == me.getRank() && this.eventType == me.getType() && this.eventID == me.getID() ) {
                result = true;
            }
        }
        return result;
    }

}
