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
		//user id넘겨서 편의점 브랜드 번호 가져오기
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		ElistManager manager = ElistManager.getInstance();
		List<Elist> elistList = manager.findAllElistList(currentPage, countPerPage);
		
		//EventtList 객체를 response에 저장하여 전달
		request.setAttribute("elistList", elistList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("customer_elist_list.jsp");
		return forward;
	}
}
