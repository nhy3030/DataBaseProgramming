package controller.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.event.EventManager;

public class EventDeleteAction implements Action {
	/**
	 * request�� ���޵� ID �̿�. BoardManager�� delete�޽�带 
	 * ȣ���Ͽ� �ش� �Խù��� �����ϴ� �ҽ��ڵ尡 ����.
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
