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
			//모델에 로그인 처리를 위임.
			UserinfoManager manager = UserinfoManager.getInstance();
			manager.login(user_id, user_pwd);
	
			//세션에 사용자 아이디 저장.
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			
			//이동할 페이지를 결정.
			//forward.setPath("user_list.m2");
			forward.setPath("customer_main.jsp");
			forward.setRedirect(true);
			
		} catch (Exception e) {
			/* ExistedUserException이나 PasswordMismatchException이 발생 시
			 * 다시 login form (login.jsp)을 사용자에게 전송하고 오류 메세지도 출력
			 */
			request.setAttribute("exception", e);
			forward.setPath("login.jsp");
			forward.setRedirect(false);					
		}		
		
		return forward;
	}
}
