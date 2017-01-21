package model.basket;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * BasketDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */

public class BasketManager {
	private static BasketManager proMan = new BasketManager();
	private BasketDAO BasketDAO;

	//생성자
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

	//물품 추가
	public int create(String userId, int product_id) throws SQLException, ExistedUserException {
		return BasketDAO.create(userId, product_id);
	}

	public List<Basket> findBasketList(int currentPage, int countPerPage, String userId) throws SQLException {
		return BasketDAO.findBasketList(currentPage, countPerPage, userId);
	}
/*
	// 물품 수정
	public int update(Basket Basket) throws SQLException {
		return BasketDAO.update(Basket);
	}	
*/
	// 물품 삭제
	public int remove(String user_id, int product_id) throws SQLException {
		return BasketDAO.remove(user_id, product_id);
	}
/*
	// 물품 아이디의 존재 여부
	public Basket findBasket(String Basket_id) throws SQLException, UserNotFoundException {
		Basket Basket = BasketDAO.findBasket(Basket_id);

		if (Basket == null) {
			throw new UserNotFoundException(Basket_id + "는 존재하지 않는 물품입니다.");
		}		
		return Basket;
	}
*/
	// 카테고리별로 검색
	public List<Basket> BasketByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return BasketDAO.BasketByCategory(category_id, currentPage, countPerPage);
	}

}
