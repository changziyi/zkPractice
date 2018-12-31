package car.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class CarDAO implements CarDAO_Interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=car";
	String userid = "sa";
	String passwd = "sa123456";

	private static final String INSERT_CAR = "INSERT INTO cartable " + "(cname,ccolor)" + "VALUES (?,?)";
	private static final String GET_ALL_CAR = "SELECT * FROM cartable ";
	private static final String GET_BY_ID = "SELECT * FROM cartable where cnumber = ?";

	private static final String UPDATE_CAR = "UPDATE cartable " + "SET cname=?, ccolor=?" + ") WHERE　cnumber=? ";

	@Override
	public void insert(CarVO carvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_CAR);

			pstmt.setString(1, carvo.getCname());
			pstmt.setString(2, carvo.getCcolor());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CarVO carvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_CAR);

			pstmt.setString(1, carvo.getCname());
			pstmt.setString(2, carvo.getCcolor());
			pstmt.setInt(3, carvo.getCnumber());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public List<CarVO> retrieveAllCar() {
		List<CarVO> orderVOlist = new ArrayList<CarVO>();
		CarVO carvo = new CarVO();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_CAR);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				carvo.setCnumber(rs.getInt("cnumber"));
				carvo.setCname(rs.getString("cname"));
				carvo.setCcolor(rs.getString("ccolor"));
				orderVOlist.add(carvo);
			} // end while

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			} // end if
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				} // end try catch
			} // end if
		} // end finally
		return orderVOlist;
	}
	
	@Override
	public CarVO retrieveCarById(Integer cnumber) {
		CarVO carvo = new CarVO();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, cnumber);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				carvo.setCnumber(rs.getInt("cnumber"));
				carvo.setCname(rs.getString("cname"));
				carvo.setCcolor(rs.getString("ccolor"));
			} // end while

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			} // end if
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				} // end try catch
			} // end if
		} // end finally
		return carvo;
	}
	
	public static void main(String[] args) {
		CarDAO cardao = new CarDAO();
		CarVO carvo = new CarVO();
		//insert
		cardao.insert(carvo);
		//getall
		cardao.retrieveAllCar();
		//update
		cardao.update(carvo);

	}
	 public static void main(String args){
		 CarDAO_Interface dao = new CarDAO();
		 dao.retrieveAllCar();
	 }
}
