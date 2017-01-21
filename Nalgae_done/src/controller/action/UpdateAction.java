package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Userinfo;
import model.UserinfoManager;


public class UpdateAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 User객체를 생성하여 
	 * UserManager의 update메써드를 호출하여 기존의 사용자 정보를 수정한다. 
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
