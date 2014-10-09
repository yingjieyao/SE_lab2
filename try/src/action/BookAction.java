package action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.*;
import bean.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//this is another change test for git test
public class BookAction extends ActionSupport implements ModelDriven {
	private Books book = new Books();
	private Author au = new Author();
	private List<Books> list;
	private List<Author> list2;

	public List<Books> getList() {
		return this.list;
	}

	public void setList(List<Books> list) {
		this.list = list;
	}

	public List<Author> getList2() {
		return this.list2;
	}

	public void setList2(List<Author> list) {
		this.list2 = list;
	}

	public Books getBook() {
		return this.book;
	}

	public void setAuthor(Author a) {
		this.au = a;
	}

	public Author getAuthor() {
		return this.au;
	}

	public void setBook(Books a) {
		this.book = a;
	}

	public Object getModel() {
		return this.book;
	}

	public String delBook() {
		int result = 0;
		String sql = "delete from book where title= " + "\"" + book.getTitle()
				+ "\"";
		result = DbUtils.getInstance().delete(sql);
		if (result == 0)
			return "ERROR";
		else
			return "SUCCESS";
	}

	public String All() {
		String sql = "select * from book";
		ResultSet rs = null;
		rs = DbUtils.getInstance().Query(sql);
		try {
			list = new ArrayList<Books>();
			while (rs.next()) {
				Books books = new Books();
				books.setISBN(rs.getString(1));
				books.setTitle(rs.getString(2));
				books.setAuthorid(rs.getInt(3));
				books.setPublisher(rs.getString(4));
				books.setPublishdate(rs.getString(5));
				books.setPrice(rs.getString(6));
				list.add(books);
				books.print();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}

	public String QueryAu() {
		ResultSet rs = null;
		String sql = "select * from author where Name=\"" + au.getName() + "\"";
		rs = DbUtils.getInstance().Query(sql);
		System.out.println(sql);
		Author aut = new Author();
		try {
			if (rs.next()) {
				aut = new Author();
				aut.setAuthorid(rs.getInt(1));
//				System.out.println(aut.getAuthorid());
				aut.setName(rs.getString(2));
				aut.setAge(rs.getInt(3));
				aut.setCountry(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		sql = "select * from book where Authorid=" + aut.getAuthorid();
		System.out.println(sql);
		rs = DbUtils.getInstance().Query(sql);
		try {
			list = new ArrayList<Books>();
			while (rs.next()) {
				Books books = new Books();
				books.setISBN(rs.getString(1));
				books.setTitle(rs.getString(2));
				books.setAuthorid(rs.getInt(3));
				books.setPublisher(rs.getString(4));
				books.setPublishdate(rs.getString(5));
				books.setPrice(rs.getString(6));
				list.add(books);
				books.print();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}

	public String QueryTit() {
		ResultSet rs = null, rs2 = null;
		String sqlb = "select * from book where Title=\"" + book.getTitle()
				+ "\"";
		rs = DbUtils.getInstance().Query(sqlb);
		
		
		try {
			list = new ArrayList<Books>();
			while (rs.next()) {
				Books books = new Books();
				books.setISBN(rs.getString(1));
				books.setTitle(rs.getString(2));
				books.setAuthorid(rs.getInt(3));
				books.setPublisher(rs.getString(4));
				books.setPublishdate(rs.getString(5));
				books.setPrice(rs.getString(6));
				String sqla = "select * from author where AuthorId="
						+ books.getAuthorid();
				rs2 = DbUtils.getInstance().Query(sqla);
				try {
					while (rs2.next()) {
						list2 = new ArrayList<Author>();
						Author au = new Author();
						au.setAuthorid(rs2.getInt(1));
						au.setName(rs2.getString(2));
						au.setAge(rs2.getInt(3));
						au.setCountry(rs2.getString(4));
						list2.add(au);
						au.print();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.add(books);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}
}
