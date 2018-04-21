package application.model;

public class RouteStopInfo {

	private int routeId;
	private int stopId;
	double length; 
	double speed;
	double trafficstatus;
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getTrafficstatus() {
		return trafficstatus;
	}

	public void setTrafficstatus(double trafficstatus) {
		this.trafficstatus = trafficstatus;
	}

	public RouteStopInfo(int routeId, int stopId, double length, double speed, double trafficstatus) {
		this.routeId = routeId;
		this.stopId = stopId;
		this.length = length;
		this.speed = speed;
		this.trafficstatus = trafficstatus;
	}
	/**
	 * @return the id
	 */
	
	/**
	 * @param id the id to set
	 */
	
	/**
	 * @return the routeId
	 */
	public int getRouteId() {
		return routeId;
	}
	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	/**
	 * @return the stopId
	 */
	public int getStopId() {
		return stopId;
	}
	/**
	 * @param stopId the stopId to set
	 */
	public void setStopId(int stopId) {
		this.stopId = stopId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RouteStopInfo routeId= ");
		builder.append(routeId);
		builder.append(", stopId=");
		builder.append(stopId);
		builder.append("]");
		return builder.toString();
	}
}
