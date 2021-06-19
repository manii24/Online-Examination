This Web Application provides facility to conduct online examination worldwide.
It saves time as it allows number of students to give the exam at a time and displays the results as the test gets over, so no need to wait for the result. It is automatically generated by the server.
Administrator has a privilege to create, modify and delete the test papers and its particular questions.
User can register, login and give the test with his specific id, and can see the results as well
Project Goals and Objectives
The goals and objectives of the Online Examination System are as follows:
To  provide a bug free online examination system to the students.
To maintain the results or student data online which will be easy to maintain.
To  provide an easy interface for online examination and facility to get intent result.
Finding a reliable system which can user by multiple user in a same time.
Maintain the record of student so it will be easy to access any time 24*4.
Facility of administer to maintain the data of exams as well as students. 
Promote health by preventing future health problems.
This Web Application provides facility to conduct online examination worldwide.
It saves time as it allows number of students to give the exam at a time and displays the results as the test gets over, so no need to wait for the result. It is automatically generated by the server.

Feasibility:
This project will be developed on computer, so  first check whether the technology is  technically available or not. Now a day’s computer interaction with any job becomes common for any kind of job or work. 
And because of increasing usage of Computer, Computer is also available with a variety of hardware. Vendors can fulfill any type of hardware requirement. The whole project is developed by some special tools or by using languages and databases, which are  also available in a variety. 
Preliminary investigation of a system examines the feasibility of a system that is useful to an organization. It is the first phase of system development. 
The main objective of this phase is to identify the current deficiencies in the user’s environment and to determine which existing problem are going to be solve in proposed system and also which new function needs to be added in proposed system. 
An important outcome of such preliminary investigation is to determine whether the system that will meet all needed requirements. 
Thus, three tests are carried out on the system namely operation, technical and economical. 
Any project is beneficial if and only satisfies the organization requirement. For any new system setup, it only meets to be communicated and work the other supporting system. 
The new system meets all existing operations since it provides right information at a right time to the right user. A Leigh man can easily operate with the system. 
Technical Feasibility examines whether the technology needed is available and if it is available then it feasible to carry out all project activities. 
The technical needs of a system include: 

The facility to produce outputs in a given time. 
Ability to process large number of transaction at a particular speed. 
Giving response to users under certain conditions. 


The technology needed for our system is mainly: 

Latest version of browsers. 
Any operating system. 
	
These technologies are available which helps to carry out the system efficiently. 

Economical feasibility of a system examines whether the finance is available for implementing the new system and whether the money spent is recoverable the satisfaction. 

The cost involves is in designing and developing a good investment for the organization. 

Thus, hardware requirements used for proposed system are very standard. Moreover, by making use of proposed system to carry out the work speedily will increase and also saves the valuable time of an organization. 

In the proposed system the finance is highly required for the installation of the software’s which can also be recovered by implementing a better system.

DATABASE CONNECTIVITY CODE:

package MyPack;
import JAVA.sql.Connection;
import JAVA.sql.DriverManager;
import JAVA.sql.SQLException;
import JAVA.util.logging.Level;
import JAVA.util.logging.Logger;

/**
 *
 * @author Oops
 */
public class MyDb {
    Connection con;
    public Connection getCon(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineexamination","root","root");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyDb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyDb.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex);
        }
    return con;
    }
   
   User Model

package in.com.online.exam.model;

import JAVA.sql.Connection;
import JAVA.sql.PreparedStatement;
import JAVA.sql.ResultSet;
import JAVA.util.HashMap;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DatabaseException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.util.EmailBuilder;
import in.com.online.exam.util.EmailMessage;
import in.com.online.exam.util.EmailUtility;
import in.com.online.exam.util.JDBCDataSource;

public class UserModel {
	
	private static Logger log = Logger.getLogger(UserModel.class);
	
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM EX_USER");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EX_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFName());
			pstmt.setString(3, bean.getLName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setDate(7, new JAVA.sql.Date(bean.getDob().getTime()));
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9,bean.getAddress());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setString(14,bean.getRoleName());
			pstmt.setLong(15, bean.getRole_Id());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}
	
	public UserBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM EX_USER WHERE LOGIN=?");
		UserBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFName(rs.getString(2));
				bean.setLName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setAddress(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				bean.setRoleName(rs.getString(14));
				bean.setRole_Id(rs.getLong(15));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public UserBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM EX_USER WHERE ID=?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFName(rs.getString(2));
				bean.setLName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setAddress(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				bean.setRoleName(rs.getString(14));
				bean.setRole_Id(rs.getLong(15));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public UserBean authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM EX_USER WHERE LOGIN = ? AND PASSWORD = ?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFName(rs.getString(2));
				bean.setLName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setAddress(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				bean.setRoleName(rs.getString(14));
				bean.setRole_Id(rs.getLong(15));
				System.out.println("Usermodel here");
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return bean;
	}

	public long registerUser(UserBean bean)
			throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		try {
			EmailUtility.sendMail(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pk;
	}
	
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
	log.debug("Model update Started");
	Connection conn = null;

	UserBean beanExist = findByLogin(bean.getLogin());
	// Check if updated LoginId already exist
	if (beanExist != null && !(beanExist.getId() == bean.getId())) {
		throw new DuplicateRecordException("LoginId is already exist");
	}

	try {
		conn = JDBCDataSource.getConnection();
		conn.setAutoCommit(false); // Begin transaction
		PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE ST_USER SET FNAME=?,LNAME=?,LOGIN=?,PASSWORD=?,MOBILE_NO=?,DOB=?,ADDEESS=?,GENDER=?,"
				+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,ROLE_NAME=?,ROLE_ID=? WHERE ID=?");
		pstmt.setString(1, bean.getFName());
		pstmt.setString(2, bean.getLName());
		pstmt.setString(3, bean.getLogin());
		pstmt.setString(4, bean.getPassword());
		pstmt.setString(5, bean.getMobileNo());
		pstmt.setDate(6, new JAVA.sql.Date(bean.getDob().getTime()) );
		pstmt.setString(7, bean.getAddress());
		pstmt.setString(8, bean.getGender());
		pstmt.setString(9, bean.getCreatedBy());
		pstmt.setString(10, bean.getModifiedBy());
		pstmt.setTimestamp(11, bean.getCreatedDatetime());
		pstmt.setTimestamp(12, bean.getModifiedDatetime());
		pstmt.setLong(13, bean.getId());
		pstmt.setString(14,bean.getRoleName());
		pstmt.setLong(15,bean.getRole_Id());
		pstmt.executeUpdate();
		conn.commit(); // End transaction
		pstmt.close();
	} catch (Exception e) {
		e.printStackTrace();
		log.error("Database Exception..", e);
		try {
			conn.rollback();
		} catch (Exception ex) {
			throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
		}
		throw new ApplicationException("Exception in updating User ");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}
	log.debug("Model update End");
}
}

ResultModel==============================================


package in.com.online.exam.model;

import JAVA.sql.Connection;
import JAVA.sql.PreparedStatement;
import JAVA.sql.ResultSet;
import JAVA.util.ArrayList;
import JAVA.util.List;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DatabaseException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.util.JDBCDataSource;

public class ResultModel {
	
private static Logger log = Logger.getLogger(UserModel.class);
	
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM EX_RESULT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
public long add(ResultBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EX_RESULT VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getExaminationName());
			pstmt.setDate(3, new JAVA.sql.Date(bean.getExaminationDate().getTime()));
			pstmt.setString(4, bean.getResult());
			pstmt.setLong(5,bean.getUser_id());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

public List list() throws ApplicationException {
	return list(0, 0);
}

/**
 * Get List of User with pagination
 * 
 * @return list : List of users
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * @throws DatabaseException
 */

public List list(int pageNo, int pageSize) throws ApplicationException {
	log.debug("Model list Started");
	ArrayList list = new ArrayList();
	StringBuffer sql = new StringBuffer("select * from EX_RESULT");
	// if page size is greater than zero then apply pagination
	if (pageSize > 0) {
		// Calculate start record index
		pageNo = (pageNo - 1) * pageSize;
		sql.append(" limit " + pageNo + "," + pageSize);
	}

	
	System.out.println("sql in list user :"+sql);
	Connection conn = null;

	try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ResultBean bean = new ResultBean();
			bean.setId(rs.getLong(1));
			bean.setExaminationName(rs.getString(2));
			bean.setExaminationDate(rs.getDate(3));
			bean.setResult(rs.getString(4));
			bean.setUser_id(rs.getLong(5));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDatetime(rs.getTimestamp(9));
			bean.setModifiedDatetime(rs.getTimestamp(10));

			list.add(bean);
		}
		rs.close();
	} catch (Exception e) {
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in getting list of users");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}

	log.debug("Model list End");
	return list;

}

public List search(ResultBean bean) throws ApplicationException {
	return search(bean, 0, 0);
}

/**
 * Search User with pagination
 * 
 * @return list : List of Users
 * @param bean
 *            : Search Parameters
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * 
 * @throws DatabaseException
 */

public List search(ResultBean bean, int pageNo, int pageSize) throws ApplicationException {
	log.debug("Model search Started");
	StringBuffer sql = new StringBuffer("SELECT * FROM EX_RESULT WHERE 1=1");

	if (bean != null) {
		if (bean.getId() > 0) {
			sql.append(" AND id ="+ bean.getId());
		}
		if (bean.getUser_id() > 0) {
			sql.append(" AND  USER_ID ="+ bean.getUser_id());
		}
	

	}

	// if page size is greater than zero then apply pagination
	if (pageSize > 0) {
		// Calculate start record index
		pageNo = (pageNo - 1) * pageSize;

		sql.append(" Limit " + pageNo + ", " + pageSize);
		// sql.append(" limit " + pageNo + "," + pageSize);
	}

	System.out.println("user model search  :"+sql);
	ArrayList list = new ArrayList();
	Connection conn = null;
	try {
		conn = JDBCDataSource.getConnection();
		System.out.println("---------------------hihi-------------bjv");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new ResultBean();
			bean.setId(rs.getLong(1));
			bean.setExaminationName(rs.getString(2));
			bean.setExaminationDate(rs.getDate(3));
			bean.setResult(rs.getString(4));
			bean.setUser_id(rs.getLong(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));

			list.add(bean);
		}
		rs.close();
	} catch (Exception e) {
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in search user");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}

	log.debug("Model search End");
	return list;
}


	

	

    
 
