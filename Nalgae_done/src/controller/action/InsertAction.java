package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.ExistedUserException;
import model.Userinfo;
import model.UserinfoManager;


public class InsertAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 Userinfo객체를 생성하여 
	 * UserManager의 create메써드를 호출하여 새로운 게시물을
	 * 입력한다. 
	 * 입력을 완료한 후 
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
