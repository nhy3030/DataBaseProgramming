package model.mnginfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MnginfoDAO {
	private DataSource ds;
	
	// 생성자
	public MnginfoDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	// 새로운 사용자 생성
		public int create(Mnginfo userinfo) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				StringBuffer insertQuery = new StringBuffer();
				insertQuery.append("INSERT INTO MANAGER VALUES ");
				insertQuery.append("(?, ?, ?)");		
			
				con = ds.getConnection();
				pstmt = con.prepareStatement(insertQuery.toString());
				pstmt.setString(1, userinfo.getManager_id());
				pstmt.setString(2, userinfo.getManager_pwd());
				pstmt.setString(3, userinfo.getManager_name());

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
		public int update(Mnginfo userinfo) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				StringBuffer updateQuery = new StringBuffer();
				updateQuery.append("UPDATE MANAGER SET ");
				updateQuery.append("manager_pwd=?, manager_name=? ");
				updateQuery.append("WHERE manager_id=?");		
			
				con = ds.getConnection();
				pstmt = con.prepareStatement(updateQuery.toString());
				pstmt.setString(1, userinfo.getManager_pwd());
				pstmt.setString(2, userinfo.getManager_name());
				pstmt.setString(3, userinfo.getManager_id());
				
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
				removeQuery.append("DELETE FROM MANAGER ");
				removeQuery.append("WHERE manager_id=?");		
			
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
	public Mnginfo findUser(String mng_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("manager_pwd, manager_name ");
			findQuery.append("FROM MANAGER ");
			findQuery.append("WHERE manager_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, mng_id);
			
			rs = pstmt.executeQuery();
			
			Mnginfo mnginfo = null; // 사용자 정보를 Mnginfo 도메인 클래스에 저장하여 반환
			if ( rs.next() ){
				mnginfo = new Mnginfo();
				mnginfo.setManager_id(mng_id);
				mnginfo.setManager_pwd(rs.getString("manager_pwd"));
				mnginfo.setManager_name(rs.getString("manager_name"));
			}
			return mnginfo;
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
		public List<Mnginfo> findUserList(int currentPage, int countPerPage) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuffer findQuery = new StringBuffer();
				findQuery.append("SELECT manager_id, manager_pwd, manager_name ");
				findQuery.append("FROM MANAGER ORDER BY manager_id");
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );		
				rs = pstmt.executeQuery();

				int start = ((currentPage-1) * countPerPage) + 1;
				
				List<Mnginfo> userinfoList = null;
				if ( (start >= 0) && rs.absolute(start) ) {
					userinfoList = new ArrayList<Mnginfo>();				
					do {
						Mnginfo userinfo = new Mnginfo();
						userinfo.setManager_id(rs.getString("manager_id"));
						userinfo.setManager_pwd(rs.getString("manager_pwd"));
						userinfo.setManager_name(rs.getString("manager_name"));
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
	public boolean existedUser(String mng_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM MANAGER ");
			existedQuery.append("WHERE manager_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, mng_id);
			
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
