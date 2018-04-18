package application.model;

import java.util.HashMap;

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

	private HashMap<Integer, Integer> stopsOnRoute;

	/**
	 * @return the routeId
	 */
	
	public RouteInfo() {
        this.stopsOnRoute = new HashMap<Integer, Integer>();
    }
	
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
	
	public void setStopsOnRoute(){
		
		this.stopsOnRoute.clear();
	}
	
	public HashMap<Integer, Integer> getStopsOnRoute(){
		
		return this.stopsOnRoute;
	}

	public void addNewStop(int stopID) { this.stopsOnRoute.put(stopsOnRoute.size(), stopID); }

	public boolean hasStop(int stopID) { return stopsOnRoute.containsValue(stopID); }

	public Integer getCurrentLocation(int stopId) {

		int currentLocation=-1;
		
		for (int i = 0; i < this.stopsOnRoute.size(); i++) {

			if(this.stopsOnRoute.get(i)==stopId){
				currentLocation = i;
				i=this.stopsOnRoute.size();
			}
		}
		return currentLocation;
	}

	public Integer getNextLocation(int routeLocation) {
		int routeSize = this.stopsOnRoute.size();
		if (routeSize > 0) { return (routeLocation + 1) % routeSize; }
		return -1;
	}

	public Integer getStopID(int routeLocation)
	{ return this.stopsOnRoute.get(routeLocation); }


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
