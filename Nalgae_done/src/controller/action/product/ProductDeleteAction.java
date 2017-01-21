package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.ProductManager;


public class ProductDeleteAction implements Action {
	/**
	 * request�� ���޵� ID �̿�. BoardManager�� delete�޽�带 
	 * ȣ���Ͽ� �ش� �Խù��� �����ϴ� �ҽ��ڵ尡 ����.
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
