package model;

import java.sql.SQLException;
import java.util.List;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserinfoDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class UserinfoManager {
	private static UserinfoManager userMan = new UserinfoManager();
	private UserinfoDAO userinfoDAO;

	// 생성자
	private UserinfoManager() {
		try {
			userinfoDAO = new UserinfoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserinfoManager getInstance() {
		return userMan;
	}
	
	// 사용자 생성
	public int create(Userinfo userinfo) throws SQLException, ExistedUserException {
		if (userinfoDAO.existedUser(userinfo.getUser_id())) {
			throw new ExistedUserException(userinfo.getUser_id() + "는 존재하는 아이디입니다.");
		}
		return userinfoDAO.create(userinfo);
	}

	// 사용자 수정
	public int update(Userinfo userinfo) throws SQLException {
		return userinfoDAO.update(userinfo);
	}	

	// 사용자 삭제
	public int remove(String user_id) throws SQLException {
		return userinfoDAO.remove(user_id);
	}

	// 사용자 아이디의 존재 여부
	public Userinfo findUser(String user_id) throws SQLException, UserNotFoundException {
		Userinfo userinfo = userinfoDAO.findUser(user_id);
		
		if (userinfo == null) {
			throw new UserNotFoundException(user_id + "는 존재하지 않는 아이디입니다.");
		}		
		return userinfo;
	}

	// 사용자 리스트
	public List<Userinfo> findUserList(int currentPage, int countPerPage) throws SQLException {
		return userinfoDAO.findUserList(currentPage, countPerPage);
	}

	// 사용자 아이디-비번의 일치 여부
	public boolean login(String user_id, String user_pwd) throws SQLException, UserNotFoundException, PasswordMismatchException {
		Userinfo userinfo = findUser(user_id);

		if (!userinfo.isMatchPassword(user_pwd)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	// UserinfoDAO 클래스의 접근자인가..?
	public UserinfoDAO getUserinfoDAO() {
		return this.userinfoDAO;
	}
}
