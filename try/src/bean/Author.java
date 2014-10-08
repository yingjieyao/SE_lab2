package bean;

public class Author {
	private int Authorid;
	private String Name;
	private int Age;
	private String Country;
	public void print(){
		System.out.println(Authorid+" "+Name+" "+Age+" "+Country);
	}
	public void setAuthorid(int a){
		this.Authorid=a;
	}
	public void setName(String a){
		this.Name=a;
	}
	public void setAge(int a){
		this.Age=a;
	}
	public void setCountry(String a){
		this.Country=a;
	}
	public int getAuthorid(){
		return this.Authorid;
	}
	public String getName(){
		return this.Name;
	}
	public String getCountry(){
		return this.Country;
	}
	public int getAge(){
		return this.Age;
	}
}
