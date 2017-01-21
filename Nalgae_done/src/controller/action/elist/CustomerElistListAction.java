package controller.action.elist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.elist.Elist;
import model.elist.ElistManager;

public class CustomerElistListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		//user id�Ѱܼ� ������ �귣�� ��ȣ ��������
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		ElistManager manager = ElistManager.getInstance();
		List<Elist> elistList = manager.findAllElistList(currentPage, countPerPage);
		
		//EventtList ��ü�� response�� �����Ͽ� ����
		request.setAttribute("elistList", elistList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("customer_elist_list.jsp");
		return forward;
	}
}
