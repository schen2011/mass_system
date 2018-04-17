package application.model;

/**
 * This class contains the details of Routes and Stop
 * @author mythili
 *
 */
public class RouteStopInfo {

	private int id;
	private int routeId;
	private int stopId;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
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
		builder.append("RouteStopInfo [id=");
		builder.append(id);
		builder.append(", routeId=");
		builder.append(routeId);
		builder.append(", stopId=");
		builder.append(stopId);
		builder.append("]");
		return builder.toString();
	}
}
