package controller.action.elist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;

public class ElistInsertFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		ActionForward forward = new ActionForward();
		forward.setPath("elist_write.jsp");
		forward.setRedirect(true);

		return forward;		
	}
}
