package edu.gatech;
/**
 * Road class
 * 
 * @author mythili
 *
 */
public class Road {
    private Integer roadId;
    private String roadName;
    private Double averageSpeed;
    private int hourOfDay;
    private String trafficIndicator = TrafficIndicator.TRAFFIC_LIGHT.toString();
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
    public Double getAverageSpeed() {
        return averageSpeed;
    }
    /**
     * @param averageSpeed
     *            the averageSpeed to set
     */
    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
    /**
     * @return the hourOfDay
     */
    public int getHourOfDay() {
        return hourOfDay;
    }
    /**
     * @param hourOfDay
     *            the hourOfDay to set
     */
    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
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
    public void setTrafficIndicator(String trafficIndicator) {
        this.trafficIndicator = trafficIndicator;
    }
    /**
     * This method returns the trafficIndicator based on the hour of the day
     * 
     * @param hourOfDay
     * @return String
     */
    public String calculateTrafficFactor(int hourOfDay) {
        if (isBetween(hourOfDay, 7, 10) && isBetween(hourOfDay, 16, 19)) {
            this.setTrafficIndicator(TrafficIndicator.TRAFFIC_PEAK.toString());
        } else if (isBetween(hourOfDay, 11, 15)) {
            this.setTrafficIndicator(TrafficIndicator.TRAFFIC_MEDIUM.toString());
        } else {
            this.setTrafficIndicator(TrafficIndicator.TRAFFIC_LIGHT.toString());
        }
        return this.trafficIndicator;
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
    /**
     * This method checks if the value is in between lower and upper bound value
     * provided
     * 
     * @param x
     * @param lower
     * @param upper
     * @return
     */
    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}