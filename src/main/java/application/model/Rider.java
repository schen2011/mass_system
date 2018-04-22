package application.model;

import org.springframework.stereotype.Component;

@Component
public class Rider {
	private int id;
	private String name;
	private int currentStopId;
	private int destinationStopId;
	private int routeId;

	
	public Rider() {
		
	}
	
	public Rider(int riderId, String riderName, int stopId, int destinationID, int routeId) {
		this.id = riderId;
		this.name = riderName;
		this.currentStopId = stopId;
		this.destinationStopId = destinationID;
		this.routeId = routeId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	/**
	 * @param id
	 * the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the currentStopId
	 */
	public int getCurrentStopId() {
		return currentStopId;
	}

	/**
	 * @param currentStopId
	 *            the currentStopId to set
	 */
	public void setCurrentStopId(int currentStopId) {
		this.currentStopId = currentStopId;
	}

	/**
	 * @return the destinationStopId
	 */
	public int getDestinationStopId() {
		return destinationStopId;
	}

	/**
	 * @param destinationStopId
	 *            the destinationStopId to set
	 */
	public void setDestinationStopId(int destinationStopId) {
		this.destinationStopId = destinationStopId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RiderInfo [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", currentStopId=");
		builder.append(currentStopId);
		builder.append(", destinationStopId=");
		builder.append(destinationStopId);
		builder.append("]");
		return builder.toString();
	}

}
