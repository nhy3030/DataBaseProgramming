package model.product;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.UserNotFoundException;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * ProductDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */

public class ProductManager {
	private static ProductManager proMan = new ProductManager();
	private ProductDAO productDAO;

	//������
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
	//��ǰ ���� �߰�(���ƿ�)
	public int scorePlus(String product_id) throws SQLException {
		return productDAO.scorePlus(product_id);
	}
	
	
	//��ǰ �߰�
	public int create(Product product) throws SQLException, ExistedUserException {
		return productDAO.create(product);
	}

	public List<Product> findProductList(int currentPage, int countPerPage) throws SQLException {
		return productDAO.findProductList(currentPage, countPerPage);
	}

	// ��ǰ ����
	public int update(Product product) throws SQLException {
		return productDAO.update(product);
	}	

	// ��ǰ ����
	public int remove(String product_id) throws SQLException {
		return productDAO.remove(product_id);
	}

	// ��ǰ ���̵��� ���� ����
	public Product findProduct(String product_id) throws SQLException, UserNotFoundException {
		Product Product = productDAO.findProduct(product_id);

		if (Product == null) {
			throw new UserNotFoundException(product_id + "�� �������� �ʴ� ��ǰ�Դϴ�.");
		}		
		return Product;
	}

	// ī�װ����� �˻�
	public List<Product> searchProductListByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return productDAO.searchProductListByCategory(category_id, currentPage, countPerPage);
	}

}
