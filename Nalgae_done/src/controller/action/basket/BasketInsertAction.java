package controller.action.basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.basket.BasketManager;


public class BasketInsertAction implements Action {
	/**
	 * request�� ���޵� ID �̿�. BoardManager�� delete�޽�带 
	 * ȣ���Ͽ� �ش� �Խù��� �����ϴ� �ҽ��ڵ尡 ����.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String strProduct_id = (String)request.getAttribute("product_id");
		//int product_id = Integer.parseInt(strProduct_id);
		//int product_id = Integer.parseInt(request.getParameter("product_id"));
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		String[] product_ids = request.getParameterValues("basket_add");
		
		BasketManager manager = BasketManager.getInstance();
		for(int i=0;i<product_ids.length;i++)
		{
			manager.create(userId, Integer.parseInt(product_ids[i]));
		}

		ActionForward forward = new ActionForward();
		forward.setPath("basket_list.m2?command=basketList");
		forward.setRedirect(true);
			
		return forward;
	}
}
