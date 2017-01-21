package controller.action.basket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.basket.Basket;
import model.basket.BasketManager;


public class BasketListAction implements Action {
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		//String userId = request.getParameter("user_id");
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		BasketManager manager = BasketManager.getInstance();
		List<Basket> basketList = manager.findBasketList(currentPage, countPerPage, userId);
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("basketList", basketList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("basket_list.jsp");
		return forward;
	}

}
