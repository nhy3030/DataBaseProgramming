package function;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.product.Product;
import model.product.ProductManager;


public class RecommendListAction implements Action {
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		//사용자의 id 받아오기
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		ProductManager manager = ProductManager.getInstance();
		
		RecommendDAO dao = new RecommendDAO();
		List<Product> recommendList = dao.findRecommendList(userId, currentPage, countPerPage);
		List<Product> priceList = dao.findPriceList(userId, currentPage, countPerPage);
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("recommendList", recommendList);
		request.setAttribute("priceList", priceList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("recommend_list.jsp");
		return forward;
	}

}
