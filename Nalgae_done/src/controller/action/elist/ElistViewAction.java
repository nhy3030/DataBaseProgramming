package controller.action.elist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.elist.Elist;
import model.elist.ElistManager;

public class ElistViewAction implements Action {
	/**
	 * UserManager의 findUser메써드를 호출하여 반환된 
	 * User를 response에 저장하는 소스코드가 들어간다. 
	 * user_view.jsp에서 response에 저장된 User를 사용하게 된다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String elist_id = request.getParameter("elist_id");

		ElistManager manager = ElistManager.getInstance();
		Elist elist = manager.findElist(elist_id);

		request.setAttribute("elist", elist);		

		ActionForward forward = new ActionForward();
		forward.setPath("elist_view.jsp");

		return forward;
	}
}
