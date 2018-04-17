package application.model;

/**
 * This class contains the details of Stops and Roads
 * @author mythili
 *
 */
public class StopRoadInfo {
	
	private int id;
	private int stopId;
	private int roadId;
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
	/**
	 * @return the roadId
	 */
	public int getRoadId() {
		return roadId;
	}
	/**
	 * @param roadId the roadId to set
	 */
	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StopRoadInfo [id=");
		builder.append(id);
		builder.append(", stopId=");
		builder.append(stopId);
		builder.append(", roadId=");
		builder.append(roadId);
		builder.append("]");
		return builder.toString();
	}

}
