package com.montran.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.montran.dao.BookDAO;
import com.montran.form.AddNewBookForm;
import com.montran.pojo.Book_issue_return_pojo;

public class DeleteBookAction extends Action{

		
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("in delete action");
		
		AddNewBookForm newform=(AddNewBookForm) form;
		String bookcode=newform.getBook_code();
		System.out.println("bookcode from jsp"+bookcode);	
		
		String membercode=newform.getMember_code();
		System.out.println("membercode from jsp"+membercode);	
		
		BookDAO dao=new BookDAO();
		List <String> deletebookList=new ArrayList<String>();
		deletebookList=dao.deletebook();
		
		
		List<String> deletememberList=new ArrayList<String>();
		deletememberList=dao.deletemember();
		
		Book_issue_return_pojo details = new Book_issue_return_pojo();
		
		for (String string : deletememberList) 
		{
			if(string.equals(membercode))
				{
					System.out.println("member deleted from delete action ");
					dao.deletebookmember(details);
					
				}
		}
			
		return mapping.findForward("success");
	}
}
