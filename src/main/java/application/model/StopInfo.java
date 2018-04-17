package application.model;

/**
 * This class contains the details of Stops
 * @author mythili
 *
 */
public class StopInfo {

	private int stopId;
	private String name;
	private int xAxis;
	private int yAxis;
	private int typeId;

	/**
	 * @return the stopId
	 */
	public int getStopId() {
		return stopId;
	}

	/**
	 * @param stopId
	 *            the stopId to set
	 */
	public void setStopId(int stopId) {
		this.stopId = stopId;
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
	 * @return the xAxis
	 */
	public int getxAxis() {
		return xAxis;
	}

	/**
	 * @param xAxis
	 *            the xAxis to set
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
	 * @param yAxis
	 *            the yAxis to set
	 */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StopInfo [stopId=");
		builder.append(stopId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", xAxis=");
		builder.append(xAxis);
		builder.append(", yAxis=");
		builder.append(yAxis);
		builder.append(", typeId=");
		builder.append(typeId);
		builder.append("]");
		return builder.toString();
	}

}
