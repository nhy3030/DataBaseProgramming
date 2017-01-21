package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 사용자 관리에서 데이터베이스와의 작업을 전담하는 클래스.
 * Userinfo 테이블에 사용자를 추가, 수정, 삭제, 검색등의 작업을 한다. 
 */
public class UserinfoDAO {
	private DataSource ds;
	
	// 생성자
	public UserinfoDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	// 새로운 사용자 생성
	public int create(Userinfo userinfo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO USERINFO VALUES ");
			insertQuery.append("(?, ?, ?, ?)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, userinfo.getUser_id());
			pstmt.setString(2, userinfo.getUser_pwd());
			pstmt.setString(3, userinfo.getUser_name());
			pstmt.setString(4, userinfo.getPhone());

			int result = pstmt.executeUpdate();
			return result;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}

	// 사용자 정보 수정
	public int update(Userinfo userinfo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE USERINFO SET ");
			updateQuery.append("user_pwd=?, user_name=?, phone=? ");
			updateQuery.append("WHERE user_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, userinfo.getUser_pwd());
			pstmt.setString(2, userinfo.getUser_name());
			pstmt.setString(3, userinfo.getPhone());
			pstmt.setString(4, userinfo.getUser_id());
			
			int result = pstmt.executeUpdate();
			return result;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}

	// 사용자 삭제
	public int remove(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM USERINFO ");
			removeQuery.append("WHERE user_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, user_id);
			
			int result = pstmt.executeUpdate();			
			return result;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}

	// 사용자 정보를 반환
	public Userinfo findUser(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("user_pwd, user_name, phone ");
			findQuery.append("FROM USERINFO ");
			findQuery.append("WHERE user_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			Userinfo userinfo = null; // 사용자 정보를 Userinfo 도메인 클래스에 저장하여 반환
			if ( rs.next() ){
				userinfo = new Userinfo();
				userinfo.setUser_id(user_id);
				userinfo.setUser_pwd(rs.getString("user_pwd"));
				userinfo.setUser_name(rs.getString("user_name"));
				userinfo.setPhone(rs.getString("phone"));
			}
			return userinfo;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	// 사용자 리스트 (현재 페이지 + 페이지당 카운트 수 --이용--> 해당부분의 사용자만 list에 저장)
	public List<Userinfo> findUserList(int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT user_id, user_pwd, user_name, phone ");
			findQuery.append("FROM USERINFO ORDER BY user_id");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Userinfo> userinfoList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				userinfoList = new ArrayList<Userinfo>();				
				do {
					Userinfo userinfo = new Userinfo();
					userinfo.setUser_id(rs.getString("user_id"));
					userinfo.setUser_pwd(rs.getString("user_pwd"));
					userinfo.setUser_name(rs.getString("user_name"));
					userinfo.setPhone(rs.getString("phone"));	
					userinfoList.add(userinfo);					
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return userinfoList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	// 아이디 존재 여부 판별
	public boolean existedUser(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM USERINFO ");
			existedQuery.append("WHERE user_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			if ( rs.next() ){
				count = rs.getInt(1);
			}
			if ( count == 1 ) {
				return true;
			} else {
				return false;
			}
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}		
	}
}
