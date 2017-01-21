package model.elist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.elist.Elist;
import model.product.Product;

public class ElistDAO {
private DataSource ds;
	
	// ������
	public ElistDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	// ���ο� �̺�Ʈ ����
	public int create(String userId, Elist elist) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String brand_id = null;
		try {
			con = ds.getConnection();
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT brand_id from BRAND where manager_id = ?");
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				brand_id = rs.getString("brand_id");
			}
			
			pstmt = null;
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO ELIST VALUES ");
			insertQuery.append("(elist_seq.nextval, ?, ?, ?, ?, ?, ?)");		
		
			//con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, elist.getEvent_id());
			pstmt.setString(2, brand_id);
			pstmt.setString(3, elist.getRec_id());
			pstmt.setString(4, elist.getElist_content());
			pstmt.setDate(5, elist.getStart_date());
			pstmt.setDate(6, elist.getEnd_date());

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

	// �̺�Ʈ ���� ����
	public int update(Elist elist) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE ELIST SET ");
			updateQuery.append("event_name=?, content=?, discount=?");
			updateQuery.append("WHERE event_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, elist.getElist_id());
			pstmt.setString(2, elist.getEvent_id());
			pstmt.setString(3, elist.getBrand_id());
			pstmt.setString(4, elist.getEvent_id());
			
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

	// �̺�Ʈ ����
	public int remove(int elist_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM ELIST ");
			removeQuery.append("WHERE elist_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setInt(1, elist_id);
			
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

	// �̺�Ʈ ������ ��ȯ
	public Elist findElist(String elist_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("el.event_id, ev.content, ev.discount ");
			findQuery.append("FROM ELIST el, event ev ");
			findQuery.append("WHERE el.event_id = ev.event_id and el.elist_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, elist_id);
			
			rs = pstmt.executeQuery();
			
			Elist elist = null; // �̺�Ʈ ������ Event ������ Ŭ������ �����Ͽ� ��ȯ
			if ( rs.next() ){
				elist = new Elist();
				elist.setElist_id(elist_id);
				elist.setEvent_id(rs.getString("event_id"));
				elist.setContent(rs.getString("content"));
				elist.setDiscount(rs.getInt("discount"));
			}
			return elist;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	// �̺�Ʈ ����Ʈ (���� ������ + �������� ī��Ʈ �� --�̿�--> �ش�κ��� �̺�Ʈ�� list�� ����)
	public List<Elist> findElistList(String userId, int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String brand_id = null;
		try {
			con = ds.getConnection();
			
			StringBuffer findIdQuery = new StringBuffer();
			findIdQuery.append("SELECT brand_id from BRAND where manager_id = ?");
			pstmt = con.prepareStatement(findIdQuery.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				brand_id = rs.getString("brand_id");
			}
			
			pstmt = null;
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select elist_id, ev.content, discount, brand_name, product_name, elist_content, el.start_date, el.end_date ");
			findQuery.append("from elist el, event ev, brand b, receiving r, product p ");
			findQuery.append("where el.EVENT_ID = ev.EVENT_ID and el.BRAND_ID = b.BRAND_ID and el.REC_ID = r.REC_ID "
					+ "and r.PRODUCT_ID = p.PRODUCT_ID and b.brand_id = ? ");
			findQuery.append("order by el.start_date");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			pstmt.setString(1, brand_id);
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Elist> elistList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				elistList = new ArrayList<Elist>();				
				do {
					Elist elist = new Elist();
					elist.setElist_id(rs.getString("elist_id"));
					elist.setEvent_name(rs.getString("content"));
					elist.setDiscount(rs.getInt("discount"));
					elist.setBrand_name(rs.getString("brand_name"));
					elist.setProduct_name(rs.getString("product_name"));
					elist.setElist_content(rs.getString("elist_content"));
					elist.setStart_date(rs.getDate("start_date"));
					elist.setEnd_date(rs.getDate("end_date"));
					elistList.add(elist);					
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return elistList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	// �̺�Ʈ ����Ʈ (���� ������ + �������� ī��Ʈ �� --�̿�--> �ش�κ��� �̺�Ʈ�� list�� ����)
		public List<Elist> findAllElistList(int currentPage, int countPerPage) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				StringBuffer findQuery = new StringBuffer();
				findQuery.append("select elist_id, ev.content, discount, brand_name, product_name, elist_content, el.start_date, el.end_date ");
				findQuery.append("from elist el, event ev, brand b, receiving r, product p ");
				findQuery.append("where el.EVENT_ID = ev.EVENT_ID and el.BRAND_ID = b.BRAND_ID and el.REC_ID = r.REC_ID "
						+ "and r.PRODUCT_ID = p.PRODUCT_ID and sysdate between el.start_date and el.end_date ");
				findQuery.append("order by el.start_date");
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
				rs = pstmt.executeQuery();

				int start = ((currentPage-1) * countPerPage) + 1;
				
				List<Elist> elistList = null;
				if ( (start >= 0) && rs.absolute(start) ) {
					elistList = new ArrayList<Elist>();				
					do {
						Elist elist = new Elist();
						elist.setElist_id(rs.getString("elist_id"));
						elist.setEvent_name(rs.getString("content"));
						elist.setDiscount(rs.getInt("discount"));
						elist.setBrand_name(rs.getString("brand_name"));
						elist.setProduct_name(rs.getString("product_name"));
						elist.setElist_content(rs.getString("elist_content"));
						elist.setStart_date(rs.getDate("start_date"));
						elist.setEnd_date(rs.getDate("end_date"));
						elistList.add(elist);					
					} while ((rs.next()) && (--countPerPage > 0));				
				}
				return elistList;
			} finally {
				if ( pstmt != null ){
					pstmt.close();
				}			
				if ( con != null ){
					con.close();
				}
			}
		}
		
	// ī�װ��� �̺�Ʈ ���� �˻�
		public List<Elist> searchElistByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuffer findQuery = new StringBuffer();
				findQuery.append("SELECT elist_id, elist_content ");
				findQuery.append("FROM ELIST ");
				findQuery.append("WHERE CATEGORY_ID = ? ORDER BY elist_id");
		
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );		
				pstmt.setInt(1, category_id);
				
				rs = pstmt.executeQuery();

				int start = ((currentPage-1) * countPerPage) + 1;
				
				List<Elist> elistList = null;
				if ( (start >= 0) && rs.absolute(start) ) {
					elistList = new ArrayList<Elist>();				
					do {
						Elist elist = new Elist();
						elist.setElist_id(rs.getString("elist_id"));
						elist.setElist_content(rs.getString("elist_content"));
					
						elistList.add(elist);					
					} while ((rs.next()) && (--countPerPage > 0));				
				}
				return elistList;
			} finally {
				if ( pstmt != null ){
					pstmt.close();
				}			
				if ( con != null ){
					con.close();
				}
			}
		}

	/* �̺�Ʈ ���� ���� �Ǻ�
	public boolean existedElist(String elist_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM Elist ");
			existedQuery.append("WHERE elist_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, elist_id);
			
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
	}*/
}
