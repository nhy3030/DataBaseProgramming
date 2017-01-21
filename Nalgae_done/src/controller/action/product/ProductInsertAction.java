package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.ExistedUserException;
import model.product.Product;
import model.product.ProductManager;

public class ProductInsertAction implements Action{
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� Product��ü�� �����Ͽ� 
	 * UserManager�� create�޽�带 ȣ���Ͽ� ���ο� �Խù���
	 * �Է��Ѵ�. 
	 * �Է��� �Ϸ��� �� 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Product product = new Product();
		product.setProduct_name(request.getParameter("product_name"));
		product.setCategory_id(Integer.parseInt(request.getParameter("category_id")));
		product.setProduct_price(Integer.parseInt(request.getParameter("product_price")));

		ActionForward forward = new ActionForward();
		try {
			ProductManager manager = ProductManager.getInstance();
			manager.create(product);

			forward.setPath("product_list.m2?command=productList");
			forward.setRedirect(true);
					
		} catch (ExistedUserException e) {
			request.setAttribute("exception", e);
			forward.setPath("product_write.jsp");
			forward.setRedirect(false);					
		}	
		
		return forward;
	}
}
