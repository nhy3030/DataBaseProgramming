package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class ProductUpdateAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� update�޽�带 ȣ���Ͽ� ������ ����� ������ �����Ѵ�. 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 Product product = new Product();
		 product.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		 product.setProduct_name(request.getParameter("product_name"));
		 product.setProduct_price(Integer.parseInt(request.getParameter("product_price")));
		 product.setCategory_id(Integer.parseInt(request.getParameter("category_id")));

		 ProductManager manager = ProductManager.getInstance();
		 manager.update(product);
			
		 ActionForward forward = new ActionForward();
		 forward.setPath("product_list.m2?command=productList");
		 forward.setRedirect(true);
				
		 return forward;
	}
}
