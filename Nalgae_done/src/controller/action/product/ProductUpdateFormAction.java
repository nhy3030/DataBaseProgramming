package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class ProductUpdateFormAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 User객체를 생성하여 
	 * UserManager의 update메써드를 호출하여 기존의 사용자 정보를 수정한다. 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String product_id = request.getParameter("product_id");

		ProductManager manager = ProductManager.getInstance();
		Product product = manager.findProduct(product_id);

		request.setAttribute("product", product);

		ActionForward forward = new ActionForward();
		forward.setPath("product_modify.jsp");

		return forward;
	}
}