package application.model;

/**
 * This class contains the details of Routes
 * @author mythili
 *
 */
public class RouteInfo {

	private int routeId;
	private String routeName;
	private int number;
	private int xAxis;
	private int yAxis;
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
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}
	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the xAxis
	 */
	public int getxAxis() {
		return xAxis;
	}
	/**
	 * @param xAxis the xAxis to set
	 */
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	/**
	 * @return the yAxis
	 */
	public int getyAxis() {
		return yAxis;
	}
	/**
	 * @param yAxis the yAxis to set
	 */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RouteInfo [routeId=");
		builder.append(routeId);
		builder.append(", routeName=");
		builder.append(routeName);
		builder.append(", number=");
		builder.append(number);
		builder.append(", xAxis=");
		builder.append(xAxis);
		builder.append(", yAxis=");
		builder.append(yAxis);
		builder.append("]");
		return builder.toString();
	}
}
