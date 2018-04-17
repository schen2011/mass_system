package application.model;

import edu.gatech.TrafficIndicator;

/**
 * Road class
 * 
 * @author mythili
 *
 */
public class Road {
	private Integer roadId;
	private String roadName;
	private double roadLength;
	private double averageSpeed;
	private String trafficIndicator = TrafficIndicator.TRAFFIC_LIGHT.toString();
	
	public Road() {
		
	}

	public Road(Integer roadId, String roadName, double roadLength, double averageSpeed, int trafficIndicator) {
		super();
		this.roadId = roadId;
		this.roadName = roadName;
		this.roadLength = roadLength;
		this.averageSpeed = averageSpeed;
		this.setTrafficIndicator(trafficIndicator);
	}

	/**
	 * @return the roadId
	 */
	public Integer getRoadId() {
		return roadId;
	}

	/**
	 * @param roadId
	 *            the roadId to set
	 */
	public void setRoadId(Integer roadId) {
		this.roadId = roadId;
	}

	/**
	 * @return the roadName
	 */
	public String getRoadName() {
		return roadName;
	}

	/**
	 * @param roadName
	 *            the roadName to set
	 */
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	/**
	 * @return the averageSpeed
	 */
	public double getAverageSpeed() {
		return averageSpeed;
	}

	/**
	 * @param averageSpeed
	 *            the averageSpeed to set
	 */
	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	/**
	 * @return the trafficIndicator
	 */
	public String getTrafficIndicator() {
		return trafficIndicator;
	}

	/**
	 * @param trafficIndicator
	 *            the trafficIndicator to set
	 */
	public void setTrafficIndicator(int trafficIndicator) {
		if (trafficIndicator == 0) {
			this.trafficIndicator = TrafficIndicator.TRAFFIC_LIGHT.toString();
		} else if (trafficIndicator == 1) {
			this.trafficIndicator = TrafficIndicator.TRAFFIC_MEDIUM.toString();
		} else if (trafficIndicator == 2) {
			this.trafficIndicator = TrafficIndicator.TRAFFIC_PEAK.toString();
		}
	}

	/**
	 * @return the roadLength
	 */
	public double getRoadLength() {
		return roadLength;
	}

	/**
	 * @param roadLength
	 *            the roadLength to set
	 */
	public void setRoadLength(double roadLength) {
		this.roadLength = roadLength;
	}

	/**
	 * This method calculates the average speed of the road based on the traffic
	 * indicator , if it is Peak, Medium or Light traffic
	 * 
	 * @param averageSpeed
	 * @param trafficIndicator
	 * @return Double
	 */
	public Double calculateAvgSpeed(Double averageSpeed, String trafficIndicator) {
		if (trafficIndicator != null && trafficIndicator.equalsIgnoreCase(TrafficIndicator.TRAFFIC_PEAK.toString())) {
			return (averageSpeed - 20.0);
		} else if (trafficIndicator != null
				&& trafficIndicator.equalsIgnoreCase(TrafficIndicator.TRAFFIC_MEDIUM.toString())) {
			return (averageSpeed - 10.0);
		} else if (trafficIndicator != null
				&& trafficIndicator.equalsIgnoreCase(TrafficIndicator.TRAFFIC_LIGHT.toString())) {
			return averageSpeed;
		}
		return averageSpeed;
	}
}