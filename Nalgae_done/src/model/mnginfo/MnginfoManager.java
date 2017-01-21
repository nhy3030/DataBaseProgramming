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

	// ������
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
	
	// ����� ����
	public int create(Mnginfo mnginfo) throws SQLException, ExistedUserException {
		if (mnginfoDAO.existedUser(mnginfo.getManager_id())) {
			throw new ExistedUserException(mnginfo.getManager_id() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return mnginfoDAO.create(mnginfo);
	}

	// ����� ����
	public int update(Mnginfo userinfo) throws SQLException {
		return mnginfoDAO.update(userinfo);
	}	

	// ����� ����
	public int remove(String user_id) throws SQLException {
		return mnginfoDAO.remove(user_id);
	}

	// ����� ���̵��� ���� ����
	public Mnginfo findUser(String mng_id) throws SQLException, UserNotFoundException {
		Mnginfo mnginfo = mnginfoDAO.findUser(mng_id);
		
		if (mnginfo == null) {
			throw new UserNotFoundException(mng_id + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return mnginfo;
	}

	// ����� ����Ʈ
	public List<Mnginfo> findUserList(int currentPage, int countPerPage) throws SQLException {
		return mnginfoDAO.findUserList(currentPage, countPerPage);
	}

	// ����� ���̵�-����� ��ġ ����
	public boolean login(String mng_id, String mng_pwd) throws SQLException, UserNotFoundException, PasswordMismatchException {
		Mnginfo mnginfo = findUser(mng_id);

		if (!mnginfo.isMatchPassword(mng_pwd)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
	
	// UserinfoDAO Ŭ������ �������ΰ�..?
	public MnginfoDAO getUserinfoDAO() {
		return this.mnginfoDAO;
	}
}
