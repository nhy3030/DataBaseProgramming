package model.event;

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
 * 이벤트에서 데이터베이스와의 작업을 전담하는 클래스.
 * Event 테이블에 사용자를 추가, 수정, 삭제, 검색등의 작업을 한다. 
 */
public class EventDAO {
	private DataSource ds;
	
	// 생성자
	public EventDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	// 새로운 이벤트 생성
	public int create(Event event) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO EVENT VALUES ");
			insertQuery.append("(?, ?, ?, ?)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, event.getEvent_id());
			pstmt.setString(2, event.getEvent_name());
			pstmt.setString(3, event.getContent());
			pstmt.setInt(4, event.getDiscount());

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

	// 이벤트 정보 수정
	public int update(Event event) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE EVENT SET ");
			updateQuery.append("event_name=?, content=?, discount=?");
			updateQuery.append("WHERE event_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, event.getEvent_name());
			pstmt.setString(2, event.getContent());
			pstmt.setInt(3, event.getDiscount());
			pstmt.setString(4, event.getEvent_id());
			
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

	// 이벤트 삭제
	public int remove(String event_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM EVENT ");
			removeQuery.append("WHERE event_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, event_id);
			
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

	// 이벤트 정보를 반환
	public Event findEvent(String event_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("event_name, content, discount ");
			findQuery.append("FROM EVENT ");
			findQuery.append("WHERE event_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, event_id);
			
			rs = pstmt.executeQuery();
			
			Event event = null; // 이벤트 정보를 Event 도메인 클래스에 저장하여 반환
			if ( rs.next() ){
				event = new Event();
				event.setEvent_id(event_id);
				event.setEvent_name(rs.getString("event_name"));
				event.setContent(rs.getString("content"));
				event.setDiscount(rs.getInt("discount"));
			}
			return event;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	// 이벤트 리스트 (현재 페이지 + 페이지당 카운트 수 --이용--> 해당부분의 이벤트만 list에 저장)
	public List<Event> findEventList(int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT event_id, event_name, content, discount ");
			findQuery.append("FROM EVENT ORDER BY event_id");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Event> eventList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				eventList = new ArrayList<Event>();				
				do {
					Event event = new Event();
					event.setEvent_id(rs.getString("event_id"));
					event.setEvent_name(rs.getString("event_name"));
					event.setContent(rs.getString("content"));
					event.setDiscount(rs.getInt("discount"));	
					eventList.add(event);					
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return eventList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	// 이벤트 존재 여부 판별
	public boolean existedEvent(String event_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM Event ");
			existedQuery.append("WHERE event_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, event_id);
			
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
