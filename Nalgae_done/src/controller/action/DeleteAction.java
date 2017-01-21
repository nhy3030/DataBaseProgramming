package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.UserinfoManager;


public class DeleteAction implements Action {
	/**
	 * request에 전달된 ID 이용. BoardManager의 delete메써드를 
	 * 호출하여 해당 게시물을 삭제하는 소스코드가 들어간다.
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		
		UserinfoManager manager = UserinfoManager.getInstance();
		manager.remove(user_id);

		ActionForward forward = new ActionForward();
		forward.setPath("user_list.m2");
		forward.setRedirect(true);
			
		return forward;
	}
}
