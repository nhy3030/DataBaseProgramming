package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {
	private DataSource ds;
	
	//생성자
	public ProductDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		
	}
	// 카테고리로 물품 검색
	public List<Product> searchProductListByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT product_id, product_name, product_price, score ");
			findQuery.append("FROM PRODUCT ");
			findQuery.append("WHERE CATEGORY_ID = ? ORDER BY product_id");
	
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
	
	public List<Product> findProductList(int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT product_id, product_name, product_price, score ");
			findQuery.append("FROM PRODUCT ORDER BY category_id");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
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
	
	
	//새로운 물품 등록
	public int create(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO PRODUCT VALUES ");
			insertQuery.append("(product_seq.nextval, ?, ?, ?, 0)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, product.getProduct_name());
			pstmt.setInt(2, product.getProduct_price());
			pstmt.setInt(3, product.getCategory_id());

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
	
	// 물품 점수 추가(좋아요)
	public int scorePlus(String product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		/*
		 * UPDATE PRODUCT
			SET SCORE = SCORE+1
			WHERE PRODUCT_ID = 1189;
		 */
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE PRODUCT ");
			updateQuery.append("SET SCORE = SCORE+1 ");
			updateQuery.append("WHERE product_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setInt(1, Integer.parseInt(product_id));

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
	public int remove(String product_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM PRODUCT ");
			removeQuery.append("WHERE product_id=?");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, product_id);
			
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
			findQuery.append("product_name, product_price, category_id, score ");
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
				product.setScore(Integer.parseInt(rs.getString("score")));
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
