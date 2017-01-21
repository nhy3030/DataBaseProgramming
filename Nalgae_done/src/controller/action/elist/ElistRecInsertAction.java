package controller.action.elist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.basket.BasketManager;


public class ElistRecInsertAction implements Action {
	/**
	 * request에 전달된 ID 이용. BoardManager의 delete메써드를 
	 * 호출하여 해당 게시물을 삭제하는 소스코드가 들어간다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String strProduct_id = (String)request.getAttribute("product_id");
		//int product_id = Integer.parseInt(strProduct_id);
		//int product_id = Integer.parseInt(request.getParameter("product_id"));
		String[] elist_recs = request.getParameterValues("elist_rec");
		
		BasketManager manager = BasketManager.getInstance();
		for(int i=0;i<elist_recs.length;i++)
		{
			//manager.create(Integer.parseInt(elist_recs[i]));
		}

		ActionForward forward = new ActionForward();
		forward.setPath("basket_list.m2?command=basketList");
		forward.setRedirect(true);
			
		return forward;
	}
}
