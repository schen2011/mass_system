package application.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import application.model.RiderInfo;
import application.model.Road;
import application.model.RouteInfo;
import application.model.RouteStopInfo;
import application.model.StopInfo;
import application.model.StopRoadInfo;

/**
 * This class is the repository layer to retrieve information from the H2
 * Database
 * 
 * @author mythi
 *
 */
@Repository
public class MetroDataRepository {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:mem:testdb";

	// Database credentials
	static final String USER = "sa";
	static final String PASSWORD = "";

	public List<StopInfo> getStopData() {
		Statement stmt = null;
		List<StopInfo> stopInfos = new ArrayList<StopInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, name, x, y, typeId FROM Stop";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 4: Extract data from result set

			while (rs.next()) {
				StopInfo stopInfo = new StopInfo();
				// Retrieve by column name
				stopInfo.setStopId(rs.getInt("id"));
				stopInfo.setName(rs.getString("name"));
				stopInfo.setxAxis(rs.getInt("x"));
				stopInfo.setyAxis(rs.getInt("y"));
				stopInfo.setTypeId(rs.getInt("typeId"));
				stopInfos.add(stopInfo);
			}
			// STEP 5: Clean-up environment
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			closeConnections(conn, stmt);

		}
		return stopInfos;

	}

	public List<RouteInfo> getRouteData() {
		Statement stmt = null;
		List<RouteInfo> routeInfos = new ArrayList<RouteInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, name,no,typeId FROM Route";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 4: Extract data from result set

			while (rs.next()) {
				RouteInfo routeInfo = new RouteInfo();
				// Retrieve by column name
				routeInfo.setRouteId(rs.getInt("id"));
				routeInfo.setRouteName(rs.getString("name"));
				routeInfo.setNumber(rs.getString("no"));
				routeInfo.setTypeId(rs.getInt("typeId"));
				routeInfos.add(routeInfo);
			}
			// STEP 5: Clean-up environment
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);

		}
		return routeInfos;
	}

	public List<RouteStopInfo> getRouteStopData() {
		Statement stmt = null;
		List<RouteStopInfo> routeStopInfos = new ArrayList<RouteStopInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, routeId, stopId FROM RouteStop";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 4: Extract data from result set

			while (rs.next()) {
				RouteStopInfo routeStopInfo = new RouteStopInfo();
				// Retrieve by column name
				routeStopInfo.setId(rs.getInt("id"));
				routeStopInfo.setRouteId(rs.getInt("routeId"));
				routeStopInfo.setStopId(rs.getInt("stopId"));
				routeStopInfos.add(routeStopInfo);
			}
			// STEP 5: Clean-up environment
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);

		}
		return routeStopInfos;
	}

	private Connection createConnection() {
		Connection conn = null;
		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected database successfully...");
			return conn;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			// Handle errors for Class.forName
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
		} // nothing we can do

	}

	public List<StopRoadInfo> getStopRoadData() {
		Statement stmt = null;
		List<StopRoadInfo> stopRoadInfos = new ArrayList<StopRoadInfo>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, STOPID_S,STOPID_E, roadId FROM StopRoad";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 4: Extract data from result set

			while (rs.next()) {
				StopRoadInfo stopRoadInfo = new StopRoadInfo();
				// Retrieve by column name
				stopRoadInfo.setId(rs.getInt("id"));
				stopRoadInfo.setRoadId(rs.getInt("roadId"));
				stopRoadInfo.setStopIdStart(rs.getInt("STOPID_S"));
				stopRoadInfo.setStopIdEnd(rs.getInt("STOPID_E"));
				stopRoadInfos.add(stopRoadInfo);
			}
			// STEP 5: Clean-up environment
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);

		}
		return stopRoadInfos;
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

			// STEP 4: Extract data from result set

			while (rs.next()) {
				RiderInfo riderInfo = new RiderInfo();
				// Retrieve by column name
				riderInfo.setId(rs.getInt("id"));
				riderInfo.setName(rs.getString("name"));
				riderInfo.setCurrentStopId(rs.getInt("CURR_STOP_ID"));
				riderInfo.setDestinationStopId(rs.getInt("DEST_STOP_ID"));
				riderInfos.add(riderInfo);
			}
			// STEP 5: Clean-up environment
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);

		}
		return riderInfos;
	}

	public List<Road> getRoadData() {
		Statement stmt = null;
		List<Road> roadInfos = new ArrayList<Road>();
		Connection conn = null;
		try {
			conn = createConnection();
			if (conn == null) {
				System.out.println("Connection Object to H2 DB is null.. ");
				return null;
			}
			stmt = conn.createStatement();
			String sql = "SELECT id, name , length, speed, TRAFFICSTATUS FROM Road";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 4: Extract data from result set

			while (rs.next()) {
				Road roadInfo = new Road();
				// Retrieve by column name
				roadInfo.setRoadId(rs.getInt("id"));
				roadInfo.setRoadName(rs.getString("name"));
				roadInfo.setRoadLength(rs.getDouble("length"));
				roadInfo.setAverageSpeed(rs.getDouble("speed"));
				roadInfo.setTrafficIndicator(rs.getInt("TRAFFICSTATUS"));
				roadInfos.add(roadInfo);
			}
			// STEP 5: Clean-up environment
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnections(conn, stmt);

		}
		return roadInfos;
	}
}
