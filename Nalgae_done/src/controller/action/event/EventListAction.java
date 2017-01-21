package controller.action.event;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.event.Event;
import model.event.EventManager;

public class EventListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		EventManager manager = EventManager.getInstance();
		List<Event> eventList = manager.findEventList(currentPage, countPerPage);
		
		//EventtList 객체를 response에 저장하여 전달
		request.setAttribute("eventList", eventList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("event_list.jsp");
		return forward;
	}
}
