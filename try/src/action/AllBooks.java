package action;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;
import bean.Books;
import db.DbPool;

public class AllBooks extends ActionSupport {
	private List<Books> list;
	Map<String, Object> map;
	public void setRequest(Map<String, Object> map) {
	  this.map = map;
	}
	public List<Books> getList(){
		return this.list;
	}
	public void setList(List<Books> list){
		this.list=list;
	}
	public String execute() throws Exception{
		DbPool pool = new DbPool();
		list=new ArrayList<Books>();
		if(pool.getConne()==null){
			pool.getConn();
		}
		String sql="select * from book";
		PreparedStatement ps=null;
		try{
			ps=pool.getConne().prepareStatement(sql);
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next()){
				Books books=new Books();
				books.setISBN(rs.getString(1));
				books.setTitle(rs.getString(2));
				books.setAuthorid(rs.getInt(3));
				books.setPublisher(rs.getString(4));
				books.setPublishdate(rs.getString(5));
				books.setPrice(rs.getString(6));
				list.add(books);
				books.print();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
