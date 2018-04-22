package application.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import application.model.Bus;
import application.model.BusRoute;
import application.model.BusStop;
import application.model.BusSystem;
import application.model.Rider;
import application.model.RouteStopInfo;
import application.model.SimDriver;
import application.model.Train;
import application.model.TrainRoute;
import application.model.TrainStop;
import application.model.TrainSystem;

@Repository
public class MetroDataRepository {

	private static final String JDBC_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:file:~/metrosystem";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	public List<Bus> getBusData() {
		Statement stmt = null;
		List<Bus> busList = new ArrayList<Bus>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, routeID, currentstop, passengers, capacity, speed, direction from bus";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Bus bus = new Bus(rs.getInt("id"), rs.getInt("routeID"), 
						rs.getInt("currentstop"),rs.getInt("currentstop"),rs.getInt("passengers"),rs.getInt("capacity"),
						rs.getInt("speed"), rs.getString("direction"));
				busList.add(bus);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return busList;
	}
	
	public List<Train> getTrainData() {
		Statement stmt = null;
		List<Train> trainList = new ArrayList<Train>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, routeID, currentstop, passengers, capacity, speed, direction from train";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Train train = new Train(rs.getInt("id"), rs.getInt("routeID"),rs.getInt("currentstop"),rs.getInt("currentstop"),
						rs.getInt("passengers"),rs.getInt("capacity"),rs.getInt("speed"),rs.getString("direction") );
				trainList.add(train);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return trainList;
	}
	
	public List<BusStop> getBusStopData() {
		Statement stmt = null;
		List<BusStop> busStopList = new ArrayList<BusStop>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, name, riders, x, y from busstop";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BusStop busStop = new BusStop(rs.getInt("id"), rs.getString("name"),rs.getInt("riders"),rs.getInt("x"),rs.getInt("y"));
				busStopList.add(busStop);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return busStopList;

	}
	
	public List<TrainStop> getTrainStopData() {
		Statement stmt = null;
		List<TrainStop> trainStopList = new ArrayList<TrainStop>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, name, riders, x, y from trainstop";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TrainStop trainStop = new TrainStop(rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("riders"), 
						rs.getInt("x"),
						rs.getInt("y"));
				trainStopList.add(trainStop);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return trainStopList;
	}
	
	public List<BusRoute> getBusRouteData() {
		Statement stmt = null;
		List<BusRoute> routeList = new ArrayList<BusRoute>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, no, name FROM busRoute";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BusRoute route = new BusRoute(rs.getInt("id"), rs.getInt("no"), rs.getString("name"));
				routeList.add(route);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return routeList;
	}
	
	public List<TrainRoute> getTrainRouteData() {
		Statement stmt = null;
		List<TrainRoute> routeList = new ArrayList<TrainRoute>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, no, name FROM TrainRoute";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TrainRoute route = new TrainRoute(rs.getInt("id"), rs.getInt("no"), rs.getString("name"));
				routeList.add(route);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return routeList;
	}
	
	public List<RouteStopInfo> getBusRouteStopData() {
		Statement stmt = null;
		List<RouteStopInfo> busRouteStopInfos = new ArrayList<RouteStopInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT name, length, speed, trafficstatus, routeid FROM BUSSTOP";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RouteStopInfo routeStopInfo = new RouteStopInfo(rs.getInt("routeid"), rs.getInt("name"), rs.getInt("length"), rs.getInt("speed"), rs.getInt("trafficstatus"));
				busRouteStopInfos.add(routeStopInfo);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return busRouteStopInfos;
	}


	public List<RouteStopInfo> getTrainRouteStopData() {
		Statement stmt = null;
		List<RouteStopInfo> trainRouteStopInfos = new ArrayList<RouteStopInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "select name, length, speed, trafficstatus, routeid from trainstop";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RouteStopInfo routeStopInfo = new RouteStopInfo(rs.getInt("routeid"), rs.getInt("name"), rs.getInt("length"), rs.getInt("speed"), rs.getInt("trafficstatus"));
				trainRouteStopInfos.add(routeStopInfo);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return trainRouteStopInfos;
	}
	
	private Connection createConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected database successfully...");
			return conn;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	private void closeConnections(Connection conn, Statement stmt) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} 
	}

	public List<Rider> getRiderInfo() {
		Statement stmt = null;
		List<Rider> riderInfos = new ArrayList<Rider>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT RiderID, RiderNAME , CurrentStopID, DestinationStopID FROM Rider";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Rider riderInfo = new Rider();
				// Retrieve by column name
				riderInfo.setId(rs.getInt("RiderID"));
				riderInfo.setName(rs.getString("RiderNAME"));
				riderInfo.setCurrentStopId(rs.getInt("CurrentStopID"));
				riderInfo.setDestinationStopId(rs.getInt("DestinationStopID"));
				riderInfos.add(riderInfo);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return riderInfos;
	}
	
	// load 
	public void loadStop(BusSystem busSystem, TrainSystem trainSystem) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT stopid, stopname, riders, x, y, type FROM Stop";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("type") == 1)
					busSystem.makeStop(rs.getInt("stopid"), rs.getString("stopname"), rs.getInt("riders"), rs.getDouble("x"), rs.getDouble("y"));
				else {
					trainSystem.makeStop(rs.getInt("stopid"), rs.getString("stopname"), rs.getInt("riders"), rs.getDouble("x"), rs.getDouble("y"));
				}
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
	}

	public void loadRoute(BusSystem busSystem, TrainSystem trainSystem) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT RouteID, RouteNumber, RouteName, type FROM Route";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("type") == 1)
					busSystem.makeRoute(rs.getInt("RouteID"), 
							rs.getInt("RouteNumber"), rs.getString("RouteName"));
				else {
					trainSystem.makeRoute(rs.getInt("RouteID"),
							rs.getInt("RouteNumber"), rs.getString("RouteName"));
				}
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
	}

	public void loadVehicle(BusSystem busSystem, TrainSystem trainSystem) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT VehicleID, routeID, currentstop, nextStop, passengers, "
					+ "capacity, speed, direction, type from VEHICLE";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("type") == 1)
					busSystem.makeBus(rs.getInt("VehicleID"), rs.getInt("routeID"), 
							rs.getInt("currentstop"),
							rs.getInt("nextStop"),
							rs.getInt("passengers"),rs.getInt("capacity"),
							rs.getInt("speed"), rs.getString("direction"));
				else {
					trainSystem.makeTrain(rs.getInt("VehicleID"), rs.getInt("routeID"), 
							rs.getInt("currentstop"),rs.getInt("nextStop"), 
							rs.getInt("passengers"),rs.getInt("capacity"),
							rs.getInt("speed"), rs.getString("direction"));
				}
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
	}

	public void loadRoad(BusSystem busSystem, TrainSystem trainSystem) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, "
					+ "TRAFFICSTATUS, type from ROAD";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("type") == 1)
					busSystem.makeRoad(rs.getInt("ROADID"), rs.getString("ROADNAME"),
							rs.getFloat("ROADLENGTH"), rs.getInt("SPEEDLIMIT"),
							rs.getInt("TRAFFICSTATUS"));
				else {
					trainSystem.makeRoad(rs.getInt("ROADID"), rs.getString("ROADNAME"),
							rs.getFloat("ROADLENGTH"), rs.getInt("SPEEDLIMIT"),
							rs.getInt("TRAFFICSTATUS"));
				}
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
	}

	public void assignRiderToStops(BusSystem busSystem, TrainSystem trainSystem) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT RiderID, RiderNAME, CurrentStopID, DestinationStopID, "
					+ "RouteID from Rider";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int routeId = rs.getInt("RouteID");
				int stopId = rs.getInt("CurrentStopID");
				int riderId = rs.getInt("RiderID");
				String riderName = rs.getString("RiderNAME");
				int destinationID = rs.getInt("DestinationStopID");
				if (busSystem.getRoutes().containsKey(routeId)) {
					busSystem.getStop(stopId).
						addNewRiders(new Rider(riderId, riderName, stopId, destinationID, routeId));
				} else {
					trainSystem.getStop(stopId).
						addNewRiders(new Rider(riderId, riderName, stopId, destinationID, routeId));
				}
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
	}

	public void loadEvents(SimDriver simDriver) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, rank, VEHICLEID from EVENT";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int eventID = rs.getInt("id");
				int rank = rs.getInt("rank");
				int VEHICLEID = rs.getInt("VEHICLEID");
				simDriver.getSimEngine().addNewEvent(eventID, rank, VEHICLEID);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
	}

	public List<Integer> getStops(int routeId) {
		Statement stmt = null;
		Connection conn = null;
		List<Integer> stops = new ArrayList<>();
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT stopid from routeStop where routeID = #ROUTEID# order by sequence";
			sql = sql.replace("#ROUTEID#", String.valueOf(routeId));
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				stops.add(rs.getInt("stopid"));
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return stops;
	}

	public List<Integer> getRoads(Integer routeId, Integer currentLocation, Integer nextLocation) {
		Statement stmt = null;
		Connection conn = null;
		List<Integer> roads = new ArrayList<>();
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
			}
			stmt = conn.createStatement();
			String sql = "SELECT roadid from STOPROAD where "
					+ "routeID = #ROUTEID# and #STOPIDFROM# and #STOPIDTO#";
			sql = sql.replace("#ROUTEID#", String.valueOf(routeId));
			sql = sql.replace("#STOPIDFROM#", String.valueOf(currentLocation));
			sql = sql.replace("#STOPIDTO#", String.valueOf(nextLocation));
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				roads.add(rs.getInt("roadid"));
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);
		}
		return roads;
	}

	
}
