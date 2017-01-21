package controller.action.basket;

import controller.ActionForward;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ScorePlusAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		HttpSession session = request.getSession();
		
		String product_id = request.getParameter("product_id");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;

		ProductManager manager = ProductManager.getInstance();
		manager.scorePlus(product_id);
		
		
		Product product = manager.findProduct(product_id);
		request.setAttribute("product", product);		
		
		ActionForward forward = new ActionForward();
		forward.setPath("basket_product_view_for_add.jsp");
				
		return forward;
	}

}
