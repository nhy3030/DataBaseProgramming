package controller.action.basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.UserinfoManager;


public class ClistAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		ActionForward forward = new ActionForward();

		try {
			//�𵨿� �α��� ó���� ����.
			UserinfoManager manager = UserinfoManager.getInstance();
			manager.login(user_id, user_pwd);
	
			//���ǿ� ����� ���̵� ����.
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			
			//�̵��� �������� ����.
			//forward.setPath("user_list.m2");
			forward.setPath("customer_main.jsp");
			forward.setRedirect(true);
			
		} catch (Exception e) {
			/* ExistedUserException�̳� PasswordMismatchException�� �߻� ��
			 * �ٽ� login form (login.jsp)�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
			request.setAttribute("exception", e);
			forward.setPath("login.jsp");
			forward.setRedirect(false);					
		}		
		
		return forward;
	}
}
