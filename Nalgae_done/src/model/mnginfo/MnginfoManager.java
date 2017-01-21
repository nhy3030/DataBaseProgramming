package model.mnginfo;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.PasswordMismatchException;
import model.UserNotFoundException;
import model.mnginfo.MnginfoDAO;

public class MnginfoManager {
	private static MnginfoManager mngMan = new MnginfoManager();
	private MnginfoDAO mnginfoDAO;

	// 생성자
	private MnginfoManager() {
		try {
			mnginfoDAO = new MnginfoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MnginfoManager getInstance() {
		return mngMan;
	}
	
	// 사용자 생성
	public int create(Mnginfo mnginfo) throws SQLException, ExistedUserException {
		if (mnginfoDAO.existedUser(mnginfo.getManager_id())) {
			throw new ExistedUserException(mnginfo.getManager_id() + "는 존재하는 아이디입니다.");
		}
		return mnginfoDAO.create(mnginfo);
	}

	// 사용자 수정
	public int update(Mnginfo userinfo) throws SQLException {
		return mnginfoDAO.update(userinfo);
	}	

	// 사용자 삭제
	public int remove(String user_id) throws SQLException {
		return mnginfoDAO.remove(user_id);
	}

	// 사용자 아이디의 존재 여부
	public Mnginfo findUser(String mng_id) throws SQLException, UserNotFoundException {
		Mnginfo mnginfo = mnginfoDAO.findUser(mng_id);
		
		if (mnginfo == null) {
			throw new UserNotFoundException(mng_id + "는 존재하지 않는 아이디입니다.");
		}		
		return mnginfo;
	}

	// 사용자 리스트
	public List<Mnginfo> findUserList(int currentPage, int countPerPage) throws SQLException {
		return mnginfoDAO.findUserList(currentPage, countPerPage);
	}

	// 사용자 아이디-비번의 일치 여부
	public boolean login(String mng_id, String mng_pwd) throws SQLException, UserNotFoundException, PasswordMismatchException {
		Mnginfo mnginfo = findUser(mng_id);

		if (!mnginfo.isMatchPassword(mng_pwd)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	// UserinfoDAO 클래스의 접근자인가..?
	public MnginfoDAO getUserinfoDAO() {
		return this.mnginfoDAO;
	}
}
