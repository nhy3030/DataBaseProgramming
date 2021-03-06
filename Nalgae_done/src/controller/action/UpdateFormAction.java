package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Userinfo;
import model.UserinfoManager;


public class UpdateFormAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 User객체를 생성하여 
	 * UserManager의 update메써드를 호출하여 기존의 사용자 정보를 수정한다. 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");

		UserinfoManager manager = UserinfoManager.getInstance();
		Userinfo userinfo = manager.findUser(user_id);

		request.setAttribute("userinfo", userinfo);

		ActionForward forward = new ActionForward();
		forward.setPath("user_modify.jsp");

		return forward;
	}
}