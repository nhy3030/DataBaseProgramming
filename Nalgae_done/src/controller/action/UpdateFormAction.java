package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Userinfo;
import model.UserinfoManager;


public class UpdateFormAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� User��ü�� �����Ͽ� 
	 * UserManager�� update�޽�带 ȣ���Ͽ� ������ ����� ������ �����Ѵ�. 
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