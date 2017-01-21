package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.event.Event;
import model.event.EventManager;

public class EventViewAction implements Action {
	/**
	 * UserManager�� findUser�޽�带 ȣ���Ͽ� ��ȯ�� 
	 * User�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * user_view.jsp���� response�� ����� User�� ����ϰ� �ȴ�.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String event_id = request.getParameter("event_id");

		EventManager manager = EventManager.getInstance();
		Event event = manager.findEvent(event_id);
		
		request.setAttribute("event", event);		
		
		ActionForward forward = new ActionForward();
		forward.setPath("event_view.jsp");
				
		return forward;
	}
}
