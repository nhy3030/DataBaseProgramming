package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.event.EventManager;

public class EventDeleteAction implements Action {
	/**
	 * request에 전달된 ID 이용. BoardManager의 delete메써드를 
	 * 호출하여 해당 게시물을 삭제하는 소스코드가 들어간다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String event_id = request.getParameter("event_id");
		
		EventManager manager = EventManager.getInstance();
		manager.remove(event_id);

		ActionForward forward = new ActionForward();
		forward.setPath("event_list.m2?command=eventList");
		forward.setRedirect(true);
			
		return forward;
	}
}
