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
import application.model.RiderInfo;
import application.model.Road;
import application.model.RouteStopInfo;
import application.model.StopRoadInfo;
import application.model.Train;
import application.model.TrainRoute;
import application.model.TrainStop;

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
						rs.getInt("currentstop"),rs.getInt("passengers"),rs.getInt("capacity"),
						rs.getInt("speed"),rs.getString("direction") );
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
				Train train = new Train(rs.getInt("id"), rs.getInt("routeID"),rs.getInt("currentstop"),rs.getInt("passengers"),rs.getInt("capacity"),rs.getInt("speed"),rs.getString("direction") );
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
				TrainStop trainStop = new TrainStop(rs.getInt("id"), rs.getString("name"),rs.getInt("riders"), rs.getInt("x"),rs.getInt("y"));
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
			conn.close();
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

	

	public List<RiderInfo> getRiderInfo() {
		Statement stmt = null;
		List<RiderInfo> riderInfos = new ArrayList<RiderInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, name , CURR_STOP_ID, DEST_STOP_ID FROM Rider";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RiderInfo riderInfo = new RiderInfo();
				// Retrieve by column name
				riderInfo.setId(rs.getInt("id"));
				riderInfo.setName(rs.getString("name"));
				riderInfo.setCurrentStopId(rs.getInt("CURR_STOP_ID"));
				riderInfo.setDestinationStopId(rs.getInt("DEST_STOP_ID"));
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

	
}
