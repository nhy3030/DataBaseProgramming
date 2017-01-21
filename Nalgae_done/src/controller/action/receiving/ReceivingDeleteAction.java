package controller.action.receiving;

import java.util.List;
import controller.action.Action;
import model.receiving.Receiving;
import model.receiving.ReceivingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;


public class ReceivingDeleteAction implements Action { 
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("user_id");
		
		String rec_id_str = request.getParameter("rec_id");
		int rec_id = Integer.parseInt(rec_id_str);
		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		//System.out.println(rec_id_str);
		
		ReceivingManager manager = ReceivingManager.getInstance();
		manager.remove(rec_id);
		
	
		
		List<Receiving> receivingtList = manager.findReceivingList(currentPage, countPerPage, userID); //+user_id
		
		//productList 객체를 response에 저장하여 전달
		request.setAttribute("receivingList", receivingtList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("receiving_list.jsp");
		return forward;
		
	}

}
