package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.event.Event;
import model.event.EventManager;

public class EventUpdateAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 User객체를 생성하여 
	 * UserManager의 update메써드를 호출하여 기존의 사용자 정보를 수정한다. 
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
