package model.receiving;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.product.Product;

public class ReceivingDAO {
	private DataSource ds;
	
	//생성자
	public ReceivingDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		
	}
	/*
	// 카테고리로 물품 검색
	public List<Receiving> searchReceivingListByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT rec_id, rec_date, brand_id, product_id ");
			findQuery.append("FROM RECEIVING ");
			findQuery.append("WHERE rec_id = ? ORDER BY rec_id");
	
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			pstmt.setInt(1, category_id);
			
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Product> productList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				productList = new ArrayList<Product>();				
				do {
					Product product = new Product();
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setScore(rs.getInt("score"));	
					productList.add(product);					
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return productList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	*/
	public List<Receiving> findReceivingList(int currentPage, int countPerPage, String userID) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select r.rec_id, r.rec_date, b.brand_name, p.product_name, p.product_id, p.product_price ");
			findQuery.append("from RECEIVING r, PRODUCT p, BRAND b ");
			findQuery.append("where r.brand_id = (select brand_id from BRAND where manager_id=?) ");
			findQuery.append("and p.product_id = r.product_id and b.BRAND_ID = r.BRAND_ID ");
			findQuery.append("order by r.rec_date");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Receiving> receivingList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				receivingList = new ArrayList<Receiving>();				
				do {
					Receiving receiving = new Receiving();
					receiving.setRec_id(rs.getInt("rec_id"));
					receiving.setRec_date(rs.getDate("rec_date"));
					receiving.setBrand_name(rs.getString("brand_name"));
					receiving.setProduct_name(rs.getString("product_name"));
					receiving.setProduct_id(rs.getInt("product_id"));
					receiving.setProduct_price(rs.getInt("product_price"));
					receivingList.add(receiving);					
				} while ((rs.next()) && (--countPerPage > 0));				
			}
			return receivingList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	//새로운 물품 등록
	public int create(String userID, int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int brand_id = 0;
		try {
			con = ds.getConnection();
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT brand_id from BRAND where manager_id = ?");
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				brand_id = rs.getInt("brand_id");
			}
			
			pstmt = null;
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO RECEIVING VALUES ");
			insertQuery.append("(receiving_seq.nextval, SYSDATE, ?, ?)");		
		
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setInt(1, brand_id);
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
	
	// 물품 정보 수정
	public int update(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE PRODUCT SET ");
			updateQuery.append("product_name=?, product_price=?, category_id=? ");
			updateQuery.append("WHERE product_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, product.getProduct_name());
			pstmt.setInt(2, product.getProduct_price());
			pstmt.setInt(3, product.getCategory_id());
			pstmt.setInt(4, product.getProduct_id());
			
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
	public int remove(int rec_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM RECEIVING WHERE rec_id=?");
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setInt(1, rec_id);
			
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
	public Product findProduct(String product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("product_name, product_price, category_id ");
			findQuery.append("FROM PRODUCT ");
			findQuery.append("WHERE product_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, product_id);
			
			rs = pstmt.executeQuery();
			
			Product product = null; // 사용자 정보를 Userinfo 도메인 클래스에 저장하여 반환
			if ( rs.next() ){
				product = new Product();
				product.setProduct_id(Integer.parseInt(product_id));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_price(Integer.parseInt(rs.getString("product_price")));
				product.setCategory_id(Integer.parseInt(rs.getString("category_id")));
			}
			return product;
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
