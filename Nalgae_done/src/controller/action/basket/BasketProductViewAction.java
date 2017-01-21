package controller.action.basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class BasketProductViewAction implements Action {
	/**
	 * UserManager의 findUser메써드를 호출하여 반환된 
	 * User를 response에 저장하는 소스코드가 들어간다. 
	 * user_view.jsp에서 response에 저장된 User를 사용하게 된다.
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
