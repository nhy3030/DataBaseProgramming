package controller.action.basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class BasketProductViewAction implements Action {
	/**
	 * UserManager�� findUser�޽�带 ȣ���Ͽ� ��ȯ�� 
	 * User�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * user_view.jsp���� response�� ����� User�� ����ϰ� �ȴ�.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String product_id = request.getParameter("product_id");

		ProductManager manager = ProductManager.getInstance();
		Product product = manager.findProduct(product_id);
		
		request.setAttribute("product", product);		
		
		ActionForward forward = new ActionForward();
		forward.setPath("basket_product_view.jsp");
				
		return forward;
	}
}
