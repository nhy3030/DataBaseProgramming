package controller.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.action.Action;

public class ProductInsertFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		ActionForward forward = new ActionForward();
		forward.setPath("product_write.jsp");
		forward.setRedirect(true);

		return forward;		
	}
}
