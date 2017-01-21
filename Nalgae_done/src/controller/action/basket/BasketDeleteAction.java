package controller.action.basket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.basket.Basket;
import model.basket.BasketManager;


public class BasketDeleteAction implements Action {
	/**
	 * request에 전달된 ID 이용. BoardManager의 delete메써드를 
	 * 호출하여 해당 게시물을 삭제하는 소스코드가 들어간다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String strProduct_id = (String)request.getAttribute("product_id");
		//int product_id = Integer.parseInt(strProduct_id);
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		
		BasketManager manager = BasketManager.getInstance();
		manager.remove(userId, product_id);

		List<Basket> basketList = manager.findBasketList(1, 100, userId); //+user_id
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("basketList", basketList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("basket_list.m2?command=basketList");
		forward.setRedirect(true);
			
		return forward;
	}
}
