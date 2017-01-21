package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.ExistedUserException;
import model.event.Event;
import model.event.EventManager;

public class EventInsertAction implements Action{
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� Event��ü�� �����Ͽ� 
	 * UserManager�� create�޽�带 ȣ���Ͽ� ���ο� �Խù���
	 * �Է��Ѵ�. 
	 * �Է��� �Ϸ��� �� 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Event event = new Event();
		event.setEvent_id(request.getParameter("event_id"));
		event.setEvent_name(request.getParameter("event_name"));
		event.setContent(request.getParameter("content"));
		event.setDiscount(Integer.parseInt(request.getParameter("discount")));

		ActionForward forward = new ActionForward();
		try {
			EventManager manager = EventManager.getInstance();
			manager.create(event);

			forward.setPath("event_list.m2?command=eventList");
			forward.setRedirect(true);

		} catch (ExistedUserException e) {
			request.setAttribute("exception", e);
			forward.setPath("event_write.jsp");
			forward.setRedirect(false);					
		}	

		return forward;
	}
}
