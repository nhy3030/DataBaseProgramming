package model;

import java.sql.SQLException;
import java.util.List;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserinfoDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class UserinfoManager {
	private static UserinfoManager userMan = new UserinfoManager();
	private UserinfoDAO userinfoDAO;

	// ������
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
	
	// ����� ����
	public int create(Userinfo userinfo) throws SQLException, ExistedUserException {
		if (userinfoDAO.existedUser(userinfo.getUser_id())) {
			throw new ExistedUserException(userinfo.getUser_id() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userinfoDAO.create(userinfo);
	}

	// ����� ����
	public int update(Userinfo userinfo) throws SQLException {
		return userinfoDAO.update(userinfo);
	}	

	// ����� ����
	public int remove(String user_id) throws SQLException {
		return userinfoDAO.remove(user_id);
	}

	// ����� ���̵��� ���� ����
	public Userinfo findUser(String user_id) throws SQLException, UserNotFoundException {
		Userinfo userinfo = userinfoDAO.findUser(user_id);
		
		if (userinfo == null) {
			throw new UserNotFoundException(user_id + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return userinfo;
	}

	// ����� ����Ʈ
	public List<Userinfo> findUserList(int currentPage, int countPerPage) throws SQLException {
		return userinfoDAO.findUserList(currentPage, countPerPage);
	}

	// ����� ���̵�-����� ��ġ ����
	public boolean login(String user_id, String user_pwd) throws SQLException, UserNotFoundException, PasswordMismatchException {
		Userinfo userinfo = findUser(user_id);

		if (!userinfo.isMatchPassword(user_pwd)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
	
	// UserinfoDAO Ŭ������ �������ΰ�..?
	public UserinfoDAO getUserinfoDAO() {
		return this.userinfoDAO;
	}
}
