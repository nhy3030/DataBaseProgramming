package controller.action.elist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;
import model.receiving.Receiving;
import model.receiving.ReceivingManager;

public class ElistRecAddFormAction implements Action {	
		
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		ReceivingManager manager = ReceivingManager.getInstance();
		List<Receiving> receivingList = manager.findReceivingList(currentPage, countPerPage, userId);
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("receivingList", receivingList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("elist_addlist.jsp");
		return forward;
	}
}
