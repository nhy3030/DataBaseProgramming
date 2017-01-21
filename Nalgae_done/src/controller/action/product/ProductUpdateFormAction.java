package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class ProductUpdateFormAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� update�޽�带 ȣ���Ͽ� ������ ����� ������ �����Ѵ�. 
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