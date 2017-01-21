package model.event;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.UserNotFoundException;

public class EventManager {
	
		private static EventManager eventMan = new EventManager();
		private EventDAO eventDAO;

		// ������
		private EventManager() {
			try {
				eventDAO = new EventDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		public static EventManager getInstance() {
			return eventMan;
		}
		
		// �̺�Ʈ ����
		public int create(Event event) throws SQLException, ExistedUserException {
			/*if (eventDAO.existedEvent(event.getEvent_id())) {
				throw new ExistedUserException(event.getEvent_id() + "�� �����ϴ� ���̵��Դϴ�.");
			}*/
			return eventDAO.create(event);
		}

		// �̺�Ʈ ����
		public int update(Event event) throws SQLException {
			return eventDAO.update(event);
		}	

		// �̺�Ʈ ����
		public int remove(String event_id) throws SQLException {
			return eventDAO.remove(event_id);
		}

		// �̺�Ʈ ���̵��� ���� ����
		public Event findEvent(String event_id) throws SQLException, UserNotFoundException {
			Event event = eventDAO.findEvent(event_id);
			
			/*if (event == null) {
				throw new UserNotFoundException(event_id + "�� �������� �ʴ� ���̵��Դϴ�.");
			}*/		
			return event;
		}

		// �̺�Ʈ ����Ʈ
		public List<Event> findEventList(int currentPage, int countPerPage) throws SQLException {
			return eventDAO.findEventList(currentPage, countPerPage);
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
		public EventDAO getEventDAO() {
			return this.eventDAO;
		}
}
