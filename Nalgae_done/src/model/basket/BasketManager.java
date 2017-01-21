package model.basket;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * BasketDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */

public class BasketManager {
	private static BasketManager proMan = new BasketManager();
	private BasketDAO BasketDAO;

	//������
	private BasketManager() {
		try {
			BasketDAO = new BasketDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static BasketManager getInstance() {
		return proMan;
	}

	//��ǰ �߰�
	public int create(String userId, int product_id) throws SQLException, ExistedUserException {
		return BasketDAO.create(userId, product_id);
	}

	public List<Basket> findBasketList(int currentPage, int countPerPage, String userId) throws SQLException {
		return BasketDAO.findBasketList(currentPage, countPerPage, userId);
	}
/*
	// ��ǰ ����
	public int update(Basket Basket) throws SQLException {
		return BasketDAO.update(Basket);
	}	
*/
	// ��ǰ ����
	public int remove(String user_id, int product_id) throws SQLException {
		return BasketDAO.remove(user_id, product_id);
	}
/*
	// ��ǰ ���̵��� ���� ����
	public Basket findBasket(String Basket_id) throws SQLException, UserNotFoundException {
		Basket Basket = BasketDAO.findBasket(Basket_id);

		if (Basket == null) {
			throw new UserNotFoundException(Basket_id + "�� �������� �ʴ� ��ǰ�Դϴ�.");
		}		
		return Basket;
	}
*/
	// ī�װ����� �˻�
	public List<Basket> BasketByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return BasketDAO.BasketByCategory(category_id, currentPage, countPerPage);
	}

}
