package controller.action.receiving;

import java.util.List;
import controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.product.*;
import model.receiving.ReceivingManager;


public class ReceivingAddAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("user_id");
		
		String product_id_str = request.getParameter("product_id");
		int product_id = Integer.parseInt(product_id_str);
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		System.out.println(product_id_str);
		
		ReceivingManager manager = ReceivingManager.getInstance();
		manager.create(userID, product_id);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("receiving_write.jsp");
		return forward;
		
	}
}
