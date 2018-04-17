package application.model;

/**
 * This class contains the details of Stops and Roads
 * 
 * @author mythili
 *
 */
public class StopRoadInfo {

	private int id;
	private int stopIdStart;
	private int stopIdEnd;
	private int roadId;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the roadId
	 */
	public int getRoadId() {
		return roadId;
	}

	/**
	 * @return the stopIdEnd
	 */
	public int getStopIdEnd() {
		return stopIdEnd;
	}

	/**
	 * @param stopIdEnd
	 *            the stopIdEnd to set
	 */
	public void setStopIdEnd(int stopIdEnd) {
		this.stopIdEnd = stopIdEnd;
	}

	/**
	 * @return the stopIdStart
	 */
	public int getStopIdStart() {
		return stopIdStart;
	}

	/**
	 * @param stopIdStart
	 *            the stopIdStart to set
	 */
	public void setStopIdStart(int stopIdStart) {
		this.stopIdStart = stopIdStart;
	}

	/**
	 * @param roadId
	 *            the roadId to set
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
		builder.append(", stopIdStart=");
		builder.append(stopIdStart);
		builder.append(", stopIdEnd=");
		builder.append(stopIdEnd);
		builder.append(", roadId=");
		builder.append(roadId);
		builder.append("]");
		return builder.toString();
	}
}
