package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.event.Event;
import model.event.EventManager;

public class EventUpdateAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� update�޽�带 ȣ���Ͽ� ������ ����� ������ �����Ѵ�. 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 Event event = new Event();
		 event.setEvent_id(request.getParameter("event_id"));
		 event.setEvent_name(request.getParameter("event_name"));
		 event.setContent(request.getParameter("content"));
		 event.setDiscount(Integer.parseInt(request.getParameter("discount")));

		 EventManager manager = EventManager.getInstance();
		 manager.update(event);
			
		 ActionForward forward = new ActionForward();
		 forward.setPath("event_list.m2?command=eventList");
		 forward.setRedirect(true);
				
		 return forward;
	}
}
