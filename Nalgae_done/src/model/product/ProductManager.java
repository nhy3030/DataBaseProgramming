package model.product;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.UserNotFoundException;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * ProductDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */

public class ProductManager {
	private static ProductManager proMan = new ProductManager();
	private ProductDAO productDAO;

	//생성자
	private ProductManager() {
		try {
			productDAO = new ProductDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static ProductManager getInstance() {
		return proMan;
	}
	//물품 점수 추가(좋아요)
	public int scorePlus(String product_id) throws SQLException {
		return productDAO.scorePlus(product_id);
	}
	
	
	//물품 추가
	public int create(Product product) throws SQLException, ExistedUserException {
		return productDAO.create(product);
	}

	public List<Product> findProductList(int currentPage, int countPerPage) throws SQLException {
		return productDAO.findProductList(currentPage, countPerPage);
	}

	// 물품 수정
	public int update(Product product) throws SQLException {
		return productDAO.update(product);
	}	

	// 물품 삭제
	public int remove(String product_id) throws SQLException {
		return productDAO.remove(product_id);
	}

	// 물품 아이디의 존재 여부
	public Product findProduct(String product_id) throws SQLException, UserNotFoundException {
		Product Product = productDAO.findProduct(product_id);

		if (Product == null) {
			throw new UserNotFoundException(product_id + "는 존재하지 않는 물품입니다.");
		}		
		return Product;
	}

	// 카테고리별로 검색
	public List<Product> searchProductListByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return productDAO.searchProductListByCategory(category_id, currentPage, countPerPage);
	}

}
