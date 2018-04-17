package application.model;

/**
 * This class contains the details of Routes
 * @author mythili
 *
 */
public class RouteInfo {

	private int routeId;
	private String routeName;
	private String number;
	private int typeId;
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
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}	
	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
		builder.append(", typeId=");
		builder.append(typeId);
		builder.append("]");
		return builder.toString();
	}
	
}
