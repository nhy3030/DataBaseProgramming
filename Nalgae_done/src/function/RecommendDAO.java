package function;

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

public class RecommendDAO {
	private DataSource ds;
	
	//생성자
	public RecommendDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		
	}
	public List<Product> findPriceList(String userId, int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select b.product_id, p.PRODUCT_PRICE, p.PRODUCT_NAME, br.brand_name, e.EVENT_ID, e.ELIST_CONTENT, ev.CONTENT ");
			findQuery.append("from BASKET b, ELIST e, RECEIVING r, product p, BRAND br, EVENT ev ");
			findQuery.append("where e.REC_ID = r.REC_ID AND b.product_id = r.product_id ");
			findQuery.append("AND b.PRODUCT_ID=p.PRODUCT_ID ");
			findQuery.append("AND br.brand_id = r.BRAND_ID ");
			findQuery.append("AND e.event_id = ev.event_id ");
			findQuery.append("AND SYSDATE BETWEEN e.START_DATE AND e.END_DATE ");
			findQuery.append("AND b.USER_ID=?");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Product> productList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				productList = new ArrayList<Product>();				
				do {
					Product product = new Product();
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_name(rs.getString("product_name"));
					product.setBrand_name(rs.getString("brand_name"));
					product.setEvent_id(rs.getInt("event_id")); //일반 이벤트 아이디
					product.setElist_content(rs.getString("elist_content")); //진행중인 이벤트 내용
					product.setContent(rs.getString("content")); //일반 이벤트 이름
					
					switch(product.getEvent_id()) {
						case 112:
							product.setDc_price(product.getProduct_price() / 2);
							break;
							
						case 212:
							product.setDc_price(product.getProduct_price() / 3);
							break;
							
						case 312:
							product.setDc_price(product.getProduct_price() / 4);
							break;
							
						case 101:
							product.setDc_price((int)(product.getProduct_price() * 0.9));
							break;
							
						case 201:
							product.setDc_price((int)(product.getProduct_price() * 0.8));
							break;
							
						case 301:
							product.setDc_price((int)(product.getProduct_price() * 0.7));
							break;
							
						case 401:
							product.setDc_price((int)(product.getProduct_price() * 0.6));
							break;
							
						case 501:
							product.setDc_price((int)(product.getProduct_price() * 0.5));
							break;
							
						case 601:
							product.setDc_price((int)(product.getProduct_price() * 0.4));
							break;
							
						case 701:
							product.setDc_price((int)(product.getProduct_price() * 0.3));
							break;
							
						case 801:
							product.setDc_price((int)(product.getProduct_price() * 0.2));
							break;
							
						case 901:
							product.setDc_price((int)(product.getProduct_price() * 0.1));
							break;
							
						default:
							product.setDc_price(product.getProduct_price());
							break;
							
					}
					
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
	
	public List<Product> findRecommendList(String userId, int currentPage, int countPerPage) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cate = 0;
		try {
			con = ds.getConnection();
			StringBuffer findCQuery = new StringBuffer();
			
			findCQuery.append("select cate from ");
			findCQuery.append("(select c.category_id as cate, count(*) as cnt ");
			findCQuery.append("from category c, basket b, product p ");
			findCQuery.append("where p.category_id=c.category_id and b.user_id=? and b.product_id=p.product_id ");
			findCQuery.append("group by c.category_id) ");
			findCQuery.append("where cnt = (select max(cnt) ");
			findCQuery.append("from (select c.category_id, count(*) as cnt ");
			findCQuery.append("from category c, basket b, product p ");
			findCQuery.append("where p.category_id=c.category_id and b.user_id=? and b.product_id=p.product_id ");
			findCQuery.append("group by c.category_id))");
			
			
			pstmt = con.prepareStatement(findCQuery.toString());
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			rs.next();
				cate = rs.getInt("cate");
			
			System.out.print(cate);
			pstmt = null;
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("select p.product_id, p.product_name, b.brand_name, p.score, c.category_name, p.product_price ");
			findQuery.append("from product p, receiving r, brand b, category c ");
			findQuery.append("where r.rec_date between sysdate-30 and sysdate and p.category_id=c.category_id and b.brand_id=r.brand_id and r.product_id = p.product_id and p.category_id = ?");
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );
			
			pstmt.setInt(1, cate);
			
			rs = pstmt.executeQuery();
			
			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Product> productList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				productList = new ArrayList<Product>();				
				do {
					Product product = new Product();
					product.setProduct_id(rs.getInt("product_id"));
					product.setBrand_name(rs.getString("brand_name"));
					product.setProduct_name(rs.getString("product_name"));
					product.setScore(rs.getInt("score"));
					product.setCategory_name(rs.getString("category_name"));
					product.setProduct_price(rs.getInt("product_price"));
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
