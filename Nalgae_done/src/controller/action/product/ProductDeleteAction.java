package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.ProductManager;


public class ProductDeleteAction implements Action {
	/**
	 * request에 전달된 ID 이용. BoardManager의 delete메써드를 
	 * 호출하여 해당 게시물을 삭제하는 소스코드가 들어간다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String product_id = request.getParameter("product_id");
		
		ProductManager manager = ProductManager.getInstance();
		manager.remove(product_id);

		ActionForward forward = new ActionForward();
		forward.setPath("product_list.m2?command=productList");
		forward.setRedirect(true);
			
		return forward;
	}
}
