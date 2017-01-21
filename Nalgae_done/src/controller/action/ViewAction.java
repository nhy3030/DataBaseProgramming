package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Userinfo;
import model.UserinfoManager;


public class ViewAction implements Action {
	/**
	 * UserManager�� findUser�޽�带 ȣ���Ͽ� ��ȯ�� 
	 * User�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * user_view.jsp���� response�� ����� User�� ����ϰ� �ȴ�.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String user_id = request.getParameter("user_id");

		UserinfoManager manager = UserinfoManager.getInstance();
		Userinfo userinfo = manager.findUser(user_id);
		
		request.setAttribute("userinfo", userinfo);		
		
		ActionForward forward = new ActionForward();
		forward.setPath("user_view.jsp");
				
		return forward;
	}
}
