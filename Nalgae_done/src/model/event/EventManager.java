package model.event;

import java.sql.SQLException;
import java.util.List;

import model.ExistedUserException;
import model.UserNotFoundException;

public class EventManager {
	
		private static EventManager eventMan = new EventManager();
		private EventDAO eventDAO;

		// 생성자
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
		
		// 이벤트 생성
		public int create(Event event) throws SQLException, ExistedUserException {
			/*if (eventDAO.existedEvent(event.getEvent_id())) {
				throw new ExistedUserException(event.getEvent_id() + "는 존재하는 아이디입니다.");
			}*/
			return eventDAO.create(event);
		}

		// 이벤트 수정
		public int update(Event event) throws SQLException {
			return eventDAO.update(event);
		}	

		// 이벤트 삭제
		public int remove(String event_id) throws SQLException {
			return eventDAO.remove(event_id);
		}

		// 이벤트 아이디의 존재 여부
		public Event findEvent(String event_id) throws SQLException, UserNotFoundException {
			Event event = eventDAO.findEvent(event_id);
			
			/*if (event == null) {
				throw new UserNotFoundException(event_id + "는 존재하지 않는 아이디입니다.");
			}*/		
			return event;
		}

		// 이벤트 리스트
		public List<Event> findEventList(int currentPage, int countPerPage) throws SQLException {
			return eventDAO.findEventList(currentPage, countPerPage);
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
		public EventDAO getEventDAO() {
			return this.eventDAO;
		}
}
