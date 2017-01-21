package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.event.Event;
import model.event.EventManager;

public class EventViewAction implements Action {
	/**
	 * UserManager의 findUser메써드를 호출하여 반환된 
	 * User를 response에 저장하는 소스코드가 들어간다. 
	 * user_view.jsp에서 response에 저장된 User를 사용하게 된다.
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
