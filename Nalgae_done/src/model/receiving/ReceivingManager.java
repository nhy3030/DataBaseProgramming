package model.receiving;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;

public class ReceivingManager {
	
	private static ReceivingManager recvMan = new ReceivingManager();
	private ReceivingDAO receivingDAO;

	//������
	private ReceivingManager() {
		try {
			receivingDAO = new ReceivingDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static ReceivingManager getInstance() {
		return recvMan;
	}

	public List<Receiving> findReceivingList(int currentPage, int countPerPage, String userID) throws SQLException {
		return receivingDAO.findReceivingList(currentPage, countPerPage, userID);
	}

	
	//�԰�ǰǰ �߰�
	public int create(String userID, int product_id) throws SQLException, ExistedUserException {
		return receivingDAO.create(userID, product_id);
	}
	/*
	// �԰�ǰ ����
	public int update(Receiving receiving) throws SQLException {
		//return receivingDAO.update(receiving);
	}	

	*/
	public int remove(int rec_id) throws SQLException {
		System.out.println(rec_id);
		return receivingDAO.remove(rec_id);
	}
	
	/*

	// ��ǰ ���̵��� ���� ����
	public Receiving findReceiving(String product_id) throws SQLException, UserNotFoundException {
		Product Product = receivingDAO.findProduct(product_id);

		if (Product == null) {
			throw new UserNotFoundException(product_id + "�� �������� �ʴ� ��ǰ�Դϴ�.");
		}		
		return Product;
	}

	// ī�װ����� �˻�
	public List<Product> searchProductListByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return productDAO.searchProductListByCategory(category_id, currentPage, countPerPage);
	}
	*/

}
