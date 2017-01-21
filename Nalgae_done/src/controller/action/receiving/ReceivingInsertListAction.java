package controller.action.receiving;

import java.util.List;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;


public class ReceivingInsertListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String currentPageStr = request.getParameter("currentPage");
	
	int currentPage = 1;
	if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
		currentPage = Integer.parseInt(currentPageStr);
	}

	int countPerPage = 1000;
	
	ProductManager manager = ProductManager.getInstance();
	List<Product> productList = manager.findProductList(currentPage, countPerPage);
	
	//productList ��ü�� response�� �����Ͽ� ����
	request.setAttribute("productList", productList);
	
	ActionForward forward = new ActionForward();
	forward.setPath("receiving_write.jsp");
	return forward;
}

}
