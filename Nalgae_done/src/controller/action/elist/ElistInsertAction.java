package controller.action.elist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.action.Action;
import model.ExistedUserException;
import model.elist.Elist;
import model.elist.ElistManager;
import oracle.sql.DATE;

public class ElistInsertAction implements Action {
	/**
	 * request�� ����Ǿ� �ִ� ���ڰ����� Event��ü�� �����Ͽ� 
	 * UserManager�� create�޽�带 ȣ���Ͽ� ���ο� �Խù���
	 * �Է��Ѵ�. 
	 * �Է��� �Ϸ��� �� 
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//user id�Ѱܼ� ������ �귣�� ��ȣ ��������
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user_id");
		String[] elist_recs = (String[])session.getAttribute("elist_recs");
		
		ActionForward forward = new ActionForward();
		
		try {
			ElistManager manager = ElistManager.getInstance();
		
			for(int i=0;i<elist_recs.length;i++) {
				
				Elist elist = new Elist();
				//elist.setElist_id(request.getParameter("elist_id"));
				elist.setEvent_id(request.getParameter("event_id"));
				elist.setRec_id(elist_recs[i]);
				System.out.println(elist_recs[i]);
				elist.setElist_content(request.getParameter("elist_content"));
				//elist.setContent(request.getParameter("contents"));
				elist.setStart_date(java.sql.Date.valueOf(request.getParameter("start_date")));
				elist.setEnd_date(java.sql.Date.valueOf(request.getParameter("end_date")));
				
				manager.create(userId, elist);
			}
			
			forward.setPath("elist_list.m2?command=elistList");
			forward.setRedirect(true);

		} catch (ExistedUserException e) {
			request.setAttribute("exception", e);
			forward.setPath("elist_write.jsp");
			forward.setRedirect(false);					
		}	

		return forward;
	}
}
