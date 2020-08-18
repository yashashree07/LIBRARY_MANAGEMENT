package com.montran.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.montran.form.AddNewBookForm;
import com.montran.pojo.Book_issue_return_pojo;
import com.montran.pojo.Book_pojo;
import com.montran.pojo.Member_Pojo;
import com.montran.util.SessionFactoryUtil;

public class BookDAO {
	
	private Session session;
	private Transaction transaction;
	private SessionFactory factory=SessionFactoryUtil.getFactory();
	private int issue_serial_no;
	private Member_Pojo member;
	
	public List<Book_issue_return_pojo> getAllBooks()
	{
		session=factory.openSession();
		Query query=session.createQuery("from Book_issue_return");
		List<Book_issue_return_pojo> bookList = query.list();
		System.out.println(bookList);
		return bookList;
	}

	public Map<String,String> getmember()
	{
		session=factory.openSession();
		Query query=session.createQuery("from Book_issue_return");
		List<Book_issue_return_pojo> getmemberList = query.list();
		//List<String> membernameList=new ArrayList<String>();
		Map<String, String> membernameList = new HashMap<>();
		System.out.println("in getmember");
		for (Book_issue_return_pojo book_issue_return_pojo : getmemberList) {
			
			Member_Pojo membername=book_issue_return_pojo.getMember();
			//System.out.println("memberlist"+membername.getName());
			
			membernameList.put(membername.getMember_code(),membername.getName());
			System.out.println("maplist"+membernameList);
			
		}	
		
		return membernameList;

	}
	public int addnewBook(Book_issue_return_pojo bookIssueReturn)
	{
		session=factory.openSession();
		transaction=session.beginTransaction();
		issue_serial_no=(int)session.save(bookIssueReturn);
		session.save(bookIssueReturn);
		transaction.commit();
		System.out.println(bookIssueReturn);
		session.close();
		return issue_serial_no;
		
	}
	public List<String> deletemember() 
	{	
		
		session=factory.openSession();
		Query query=session.createQuery("from Book_issue_return");
		List<Book_issue_return_pojo> bookList = query.list();
		List<String> membercodeList=new ArrayList<String>();
		for (Book_issue_return_pojo book_issue_return_pojo : bookList) {
			
			Member_Pojo membercode=book_issue_return_pojo.getMember();
			//System.out.println(membercode.getMember_code());
			
			membercodeList.add(membercode.getMember_code());
			//System.out.println(membercodeList);
		}
		return membercodeList;
	}
	
	public List<String> getmembercode() 
	{	
		
		session=factory.openSession();
		Query query=session.createQuery("from Book_issue_return");
		List<Book_issue_return_pojo> memberList = query.list();
		List<String> codeList=new ArrayList<String>();
		for (Book_issue_return_pojo book_issue_return_pojo : memberList) {
			
			Member_Pojo membercode=book_issue_return_pojo.getMember();
			//System.out.println(membercode.getMember_code());
			
			codeList.add(membercode.getMember_code());
			//System.out.println(codeList);
		}
		return codeList;
	}
	
	
	public List<String> deletebook() 
	{	
		
		session=factory.openSession();
		Query query=session.createQuery("from Book_issue_return");
		List<Book_issue_return_pojo> bookList = query.list();
		List<String> bookcodeList=new ArrayList<String>();
		for (Book_issue_return_pojo book_issue_return_pojo : bookList) {
			Book_pojo bookcode=book_issue_return_pojo.getBook();
			//System.out.println(bookcode.getBook_code());
			
			bookcodeList.add(bookcode.getBook_code());
			//System.out.println(bookcodeList);
		}
		return bookcodeList;
	}
	
	public void deletebookmember(Book_issue_return_pojo book_issue_return) {
		
		session=factory.openSession();
		transaction=session.beginTransaction();
		session.delete(book_issue_return);
		transaction.commit();
		session.close();
		System.out.println("member deleted from delete func");
		
	}
	
}
