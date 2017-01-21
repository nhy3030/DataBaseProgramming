package model.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BasketDAO {
private DataSource ds;
	
	public BasketDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	/**
	 * 장바구니  테이블에 새로운 장바구니 생성.
	 */
	public int create(String userId, int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO Basket VALUES ");
			insertQuery.append("(sysdate, ?, ?)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, userId);
			pstmt.setInt(2, product_id);

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

	/**
	 * 장바구니를 삭제.
	 */
	public int remove(String userId, int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM basket ");
			removeQuery.append("WHERE user_id=? and product_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, userId);
			pstmt.setInt(2, product_id);
			
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

	/**
	 * 장바구니 정보를 데이터베이스에서 찾아 Basket 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Basket findBasket(String userId, int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("product_id ");
			findQuery.append("FROM basket ");
			findQuery.append("WHERE user_id=? and  product_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, userId);
			pstmt.setInt(2, product_id);
			
			rs = pstmt.executeQuery();
			
			Basket basket = null;
			if ( rs.next() ){
				basket = new Basket();
				basket.setUserId(userId);
				basket.setProduct_id(rs.getInt("product_id"));
				basket.setBasket_date(rs.getDate("basket_date"));
			}
			return basket;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	// 장바구니 리스트 (현재 페이지 + 페이지당 카운트 수 --이용--> 해당부분의 사용자만 list에 저장)
	public List<Basket> findBasketList(int currentPage, int countPerPage, String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT b.user_id, b.product_id, b.basket_date, p.product_name ");
			findQuery.append("FROM BASKET B, PRODUCT P ");
			findQuery.append("WHERE user_id=? and b.product_id = p.product_id ");
			findQuery.append("ORDER BY b.basket_date ");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Basket> basketList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				basketList = new ArrayList<Basket>();				
				do {
					Basket basket = new Basket();
					basket.setUserId(rs.getString("user_id"));
					basket.setProduct_id(Integer.parseInt(rs.getString("product_id")));
					basket.setBasket_date(rs.getDate("basket_date"));
					basket.setProduct_name(rs.getString("product_name"));
					basketList.add(basket);
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return basketList;
		} 
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	// 카테고리로 물품 검색
	public List<Basket> BasketByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT user_id, product_id, basket_date ");
			findQuery.append("FROM BASKET ORDER BY basket_date");
	
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			pstmt.setInt(1, category_id);
			
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Basket> basketList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				basketList = new ArrayList<Basket>();				
				do {
					Basket basket = new Basket();
					basket.setUserId(rs.getString("user_id"));
					basket.setProduct_id(Integer.parseInt(rs.getString("product_id")));
					basket.setBasket_date(java.sql.Date.valueOf(rs.getString("basket_date")));
					basketList.add(basket);					
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return basketList;
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