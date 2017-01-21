package controller.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class ProductListAction implements Action {
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		ProductManager manager = ProductManager.getInstance();
		List<Product> productList = manager.findProductList(currentPage, countPerPage);
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("productList", productList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("product_list.jsp");
		return forward;
	}

}
