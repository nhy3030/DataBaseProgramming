package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Userinfo;
import model.UserinfoManager;


public class UpdateAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� update�޽�带 ȣ���Ͽ� ������ ����� ������ �����Ѵ�. 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 Userinfo userinfo = new Userinfo();
		 userinfo.setUser_id(request.getParameter("user_id"));
		 userinfo.setUser_pwd(request.getParameter("user_pwd"));
		 userinfo.setUser_name(request.getParameter("user_name"));
		 userinfo.setPhone(request.getParameter("phone"));

		 UserinfoManager manager = UserinfoManager.getInstance();
		 manager.update(userinfo);
			
		 ActionForward forward = new ActionForward();
		 forward.setPath("user_list.m2");
		 forward.setRedirect(true);
				
		 return forward;
	}
}
