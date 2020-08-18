package com.montran.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.montran.dao.BookDAO;
import com.montran.form.AddNewBookForm;
import com.montran.form.GetDetailsForm;
import com.montran.pojo.Book_issue_return_pojo;

public class GetNumber extends Action {
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("in getnumberaction");
		
		GetDetailsForm getdetails=(GetDetailsForm) form;
		String member_code=getdetails.getMember_code();
		System.out.println("membercode from jsp"+member_code);	
		
		BookDAO adddao=new BookDAO();
		List<String> getmemberList=new ArrayList<String>();
		getmemberList=adddao.getmembercode();
		System.out.println("list"+getmemberList);
		Map<String, String> memberName = new HashMap<>();
		String name;
		
		
			for (String string : getmemberList) {
				if(string.equals(member_code))
				{
						memberName=adddao.getmember();
						if(memberName.containsKey(member_code))
						{
							name=memberName.get(member_code);
							request.setAttribute("name", name);
					
						}
				
				}
				
			}
		
		return mapping.findForward("success");

	}
	
	
	
}
