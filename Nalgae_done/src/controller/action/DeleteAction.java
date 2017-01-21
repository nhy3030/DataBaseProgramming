package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.UserinfoManager;


public class DeleteAction implements Action {
	/**
	 * request�� ���޵� ID �̿�. BoardManager�� delete�޽�带 
	 * ȣ���Ͽ� �ش� �Խù��� �����ϴ� �ҽ��ڵ尡 ����.
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
