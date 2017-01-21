package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Userinfo;
import model.UserinfoManager;


public class ViewAction implements Action {
	/**
	 * UserManager의 findUser메써드를 호출하여 반환된 
	 * User를 response에 저장하는 소스코드가 들어간다. 
	 * user_view.jsp에서 response에 저장된 User를 사용하게 된다.
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
