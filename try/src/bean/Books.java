package bean;

public class Books {
	private String ISBN;
	private String Title;
	private int Authorid;
	private String Publisher;
	private String Publishdate;
	private String Price;
	public void print(){
		System.out.println(ISBN+" "+Title+" "+Authorid+" "+Publisher+" "+Publishdate+" "+Price);
	}
	public String getISBN(){
		return this.ISBN;
	}
	public String getTitle(){
		return this.Title;
	}
	public int getAuthorid(){
		return this.Authorid;
	}
	public String getPublisher(){
		return this.Publisher;
	}
	public String getPublishdate(){
		return this.Publishdate;
	}
	public String getPrice(){
		return this.Price;
	}
	public void setISBN(String ISBN){
		this.ISBN=ISBN;
	}
	public void setTitle(String title){
		this.Title=title;
	}
	public void setAuthorid(int t){
		this.Authorid=t;
	}
	public void setPublisher(String a){
		this.Publisher=a;
	}
	public void setPublishdate(String a){
		this.Publishdate=a;
	}
	public void setPrice(String a){
		this.Price=a;
	}
}
