package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.event.Event;
import model.event.EventManager;

public class EventUpdateFormAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� update�޽�带 ȣ���Ͽ� ������ ����� ������ �����Ѵ�. 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String event_id = request.getParameter("event_id");

		EventManager manager = EventManager.getInstance();
		Event event = manager.findEvent(event_id);

		request.setAttribute("event", event);

		ActionForward forward = new ActionForward();
		forward.setPath("event_modify.jsp");

		return forward;
	}
}
