package model.elist;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.UserNotFoundException;
import model.elist.Elist;

public class ElistManager {
	private static ElistManager elistMan = new ElistManager();
	private ElistDAO elistDAO;

	// 생성자
	private ElistManager() {
		try {
			elistDAO = new ElistDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static ElistManager getInstance() {
		return elistMan;
	}

	// 이벤트 생성
	public int create(String userId, Elist elist) throws SQLException, ExistedUserException {
		/*if (eventDAO.existedEvent(event.getEvent_id())) {
			throw new ExistedUserException(event.getEvent_id() + "는 존재하는 아이디입니다.");
		}*/
		return elistDAO.create(userId, elist);
	}

	// 이벤트 수정
	public int update(Elist elist) throws SQLException {
		return elistDAO.update(elist);
	}	

	// 이벤트 삭제
	public int remove(int event_id) throws SQLException {
		return elistDAO.remove(event_id);
	}

	// 이벤트 아이디의 존재 여부
	public Elist findElist(String elist_id) throws SQLException, UserNotFoundException {
		Elist elist = elistDAO.findElist(elist_id);

		/*if (elist == null) {
			throw new UserNotFoundException(elist_id + "는 존재하지 않는 아이디입니다.");
		}*/
		return elist;
	}

	// 이벤트 리스트
	public List<Elist> findElistList(String userId, int currentPage, int countPerPage) throws SQLException {
		return elistDAO.findElistList(userId, currentPage, countPerPage);
	}
	
	// 이벤트 리스트
	public List<Elist> findAllElistList(int currentPage, int countPerPage) throws SQLException {
		return elistDAO.findAllElistList(currentPage, countPerPage);
	}

	// 카테고리별로 검색
	public List<Elist> searchElistByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return elistDAO.searchElistByCategory(category_id, currentPage, countPerPage);
	}

	/*
	public boolean login(String user_id, String user_pwd) throws SQLException, UserNotFoundException, PasswordMismatchException {
		Userinfo userinfo = findUser(user_id);

		if (!userinfo.isMatchPassword(user_pwd)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}*/

	// UserinfoDAO 클래스의 접근자인가..?
	public ElistDAO getEventDAO() {
		return this.elistDAO;
	}
}
