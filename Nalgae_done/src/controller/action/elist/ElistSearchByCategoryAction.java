package controller.action.elist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.elist.Elist;
import model.elist.ElistManager;

public class ElistSearchByCategoryAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		
		String category_id_str = request.getParameter("category_id");
		int category_id = Integer.parseInt(category_id_str);
		
				
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		ElistManager manager = ElistManager.getInstance();
		List<Elist> elistList = manager.searchElistByCategory(category_id, currentPage, countPerPage);
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("elistList", elistList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("elist_write.jsp");
		return forward;
	}
}
