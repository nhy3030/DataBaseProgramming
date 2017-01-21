package controller.action.elist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.elist.Elist;
import model.elist.ElistManager;

public class ElistViewAction implements Action {
	/**
	 * UserManager�� findUser�޽�带 ȣ���Ͽ� ��ȯ�� 
	 * User�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * user_view.jsp���� response�� ����� User�� ����ϰ� �ȴ�.
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
