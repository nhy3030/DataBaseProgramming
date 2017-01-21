package controller.action.receiving;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;

import controller.action.Action;
import model.receiving.ReceivingManager;

public class ReceivingInsertAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String strProduct_id = (String)request.getAttribute("product_id");
		//int product_id = Integer.parseInt(strProduct_id);
		//int product_id = Integer.parseInt(request.getParameter("product_id"));
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		String[] product_ids = request.getParameterValues("receiving_add");
		
		ReceivingManager manager = ReceivingManager.getInstance();
		for(int i=0;i<product_ids.length;i++)
		{
			manager.create(userId, Integer.parseInt(product_ids[i]));
		}

		ActionForward forward = new ActionForward();
		forward.setPath("receiving_list.m2?command=receivingList");
		forward.setRedirect(true);
			
		return forward;
	}
}
