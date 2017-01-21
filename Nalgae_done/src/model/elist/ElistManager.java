package model.elist;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.UserNotFoundException;
import model.elist.Elist;

public class ElistManager {
	private static ElistManager elistMan = new ElistManager();
	private ElistDAO elistDAO;

	// ������
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

	// �̺�Ʈ ����
	public int create(String userId, Elist elist) throws SQLException, ExistedUserException {
		/*if (eventDAO.existedEvent(event.getEvent_id())) {
			throw new ExistedUserException(event.getEvent_id() + "�� �����ϴ� ���̵��Դϴ�.");
		}*/
		return elistDAO.create(userId, elist);
	}

	// �̺�Ʈ ����
	public int update(Elist elist) throws SQLException {
		return elistDAO.update(elist);
	}	

	// �̺�Ʈ ����
	public int remove(int event_id) throws SQLException {
		return elistDAO.remove(event_id);
	}

	// �̺�Ʈ ���̵��� ���� ����
	public Elist findElist(String elist_id) throws SQLException, UserNotFoundException {
		Elist elist = elistDAO.findElist(elist_id);

		/*if (elist == null) {
			throw new UserNotFoundException(elist_id + "�� �������� �ʴ� ���̵��Դϴ�.");
		}*/
		return elist;
	}

	// �̺�Ʈ ����Ʈ
	public List<Elist> findElistList(String userId, int currentPage, int countPerPage) throws SQLException {
		return elistDAO.findElistList(userId, currentPage, countPerPage);
	}
	
	// �̺�Ʈ ����Ʈ
	public List<Elist> findAllElistList(int currentPage, int countPerPage) throws SQLException {
		return elistDAO.findAllElistList(currentPage, countPerPage);
	}

	// ī�װ����� �˻�
	public List<Elist> searchElistByCategory(int category_id, int currentPage, int countPerPage) throws SQLException {

		return elistDAO.searchElistByCategory(category_id, currentPage, countPerPage);
	}

	/*
	public boolean login(String user_id, String user_pwd) throws SQLException, UserNotFoundException, PasswordMismatchException {
		Userinfo userinfo = findUser(user_id);

		if (!userinfo.isMatchPassword(user_pwd)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}*/

	// UserinfoDAO Ŭ������ �������ΰ�..?
	public ElistDAO getEventDAO() {
		return this.elistDAO;
	}
}
