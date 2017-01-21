package controller.action.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.search.Search;
import model.search.SearchManager;

public class SearchHighScoreAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");

		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		
		SearchManager manager = SearchManager.getInstance();
		List<Search> productList = manager.searchHighScore(currentPage, countPerPage);
		
		//productList ��ü�� response�� �����Ͽ� ����
		request.setAttribute("productList", productList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("searchPageForScore.jsp");
		return forward;
	}


}
