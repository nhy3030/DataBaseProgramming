package model.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SearchDAO {
	private DataSource ds;
	
	public SearchDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}

	public List<Search> searchNewProduct(int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select p.product_id, p.product_name, p.product_price, p.score, r.rec_date, b.brand_name, c.CATEGORY_NAME ");
			findQuery.append("from RECEIVING r, PRODUCT p, BRAND b, CATEGORY c ");
			findQuery.append("where r.rec_date between SYSDATE-7 and SYSDATE AND r.PRODUCT_ID = p.PRODUCT_ID AND b.BRAND_ID=r.BRAND_ID AND c.CATEGORY_ID=p.CATEGORY_ID ");
			findQuery.append("order by r.brand_id");
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
			
			rs = pstmt.executeQuery();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Search> searchList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				searchList = new ArrayList<Search>();				
				do {
					Search search = new Search();

					search.setProduct_id(rs.getInt("product_id"));
					search.setProduct_name(rs.getString("product_name"));
					search.setProduct_price(rs.getInt("product_price"));
					search.setScore(rs.getInt("score"));
					search.setRec_date(rs.getDate("rec_date"));
					search.setBrand_name(rs.getString("brand_name"));
					search.setCategory_name(rs.getString("category_name"));
					searchList.add(search);					
				} while ((rs.next()) && (--countPerPage > 0));		
			}
			return searchList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	public List<Search> searchHighScore(int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select distinct p.product_id, p.product_name, p.product_price, p.score ");
			findQuery.append("from RECEIVING r, PRODUCT p, BRAND b ");
			findQuery.append("where r.PRODUCT_ID = p.PRODUCT_ID AND b.BRAND_ID=r.BRAND_ID and p.score>0 ");
			findQuery.append("order by p.score DESC");
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
			
			rs = pstmt.executeQuery();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Search> searchList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				searchList = new ArrayList<Search>();				
				do {
					Search search = new Search();

					search.setProduct_id(rs.getInt("product_id"));
					search.setProduct_name(rs.getString("product_name"));
					search.setProduct_price(rs.getInt("product_price"));
					search.setScore(rs.getInt("score"));
					searchList.add(search);					
				} while ((rs.next()) && (--countPerPage > 0));		
			}
			return searchList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public List<Search> searchHighBasket(int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select p.product_id, count(*) as cnt, p.product_name, p.product_price, c.category_name, p.score ");
			findQuery.append("from basket b, product p, category c ");
			findQuery.append("where b.product_id = p.product_id AND c.category_id = p.category_id ");
			findQuery.append("group by p.product_id, p.product_name, p.product_price, c.category_name, p.score ");
			findQuery.append("having count(p.product_id)>=1 ");
			findQuery.append("order by count(p.product_id) DESC");
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
			
			rs = pstmt.executeQuery();
			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Search> searchList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				searchList = new ArrayList<Search>();				
				do {
					Search search = new Search();

					search.setProduct_id(rs.getInt("product_id"));
					search.setCount(rs.getInt("cnt"));
					search.setProduct_name(rs.getString("product_name"));
					search.setProduct_price(rs.getInt("product_price"));
					search.setCategory_name(rs.getString("category_name"));
					search.setScore(rs.getInt("score"));		
					searchList.add(search);								
				} while ((rs.next()) && (--countPerPage > 0));		
			}
			return searchList;
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