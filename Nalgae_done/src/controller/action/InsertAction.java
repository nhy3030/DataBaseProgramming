package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.ExistedUserException;
import model.Userinfo;
import model.UserinfoManager;


public class InsertAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� Userinfo��ü�� �����Ͽ� 
	 * UserManager�� create�޽�带 ȣ���Ͽ� ���ο� �Խù���
	 * �Է��Ѵ�. 
	 * �Է��� �Ϸ��� �� 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Userinfo userinfo = new Userinfo();
		userinfo.setUser_id(request.getParameter("user_id"));
		userinfo.setUser_pwd(request.getParameter("user_pwd"));
		userinfo.setUser_name(request.getParameter("user_name"));
		userinfo.setPhone(request.getParameter("phone"));

		ActionForward forward = new ActionForward();
		try {
			UserinfoManager manager = UserinfoManager.getInstance();
			manager.create(userinfo);

			forward.setPath("user_list.m2");
			forward.setRedirect(true);
					
		} catch (ExistedUserException e) {
			request.setAttribute("exception", e);
			forward.setPath("user_write.jsp");
			forward.setRedirect(false);					
		}	
		
		return forward;
	}
}
