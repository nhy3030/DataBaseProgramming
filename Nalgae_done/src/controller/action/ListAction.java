package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.*;


public class ListAction implements Action {

	/**
	 * UserManager�� list�޽�带 ȣ���Ͽ� 
	 * List�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * list.jsp���� response�� ����� List��ü�� �̿��Ѵ�.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageStr = request.getParameter("currentPage");		

		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;

		UserinfoManager manager = UserinfoManager.getInstance();
		List<Userinfo> userinfoList = manager.findUserList(currentPage, countPerPage);
		
		//userList��ü�� response�� �����Ͽ� ����.
		request.setAttribute("userinfoList", userinfoList);		
		
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("user_list.jsp");
				
		return forward;
	}
}
