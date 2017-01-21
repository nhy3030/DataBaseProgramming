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
	 * request에 저장되어 있는 인자값으로 Product객체를 생성하여 
	 * UserManager의 create메써드를 호출하여 새로운 게시물을
	 * 입력한다. 
	 * 입력을 완료한 후 
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
