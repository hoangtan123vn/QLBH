package QLBH;
import javax.persistence.*;
@Entity
@Table(name="taikhoannv")
public class Taikhoannv {
	@Id
	private int manv;
	private String username;
	private String password;
	
	public Taikhoannv() {
		super();
	}
	public Taikhoannv(String username, String password) {
		super();
		this.manv=manv;
		this.username=username;
		this.password=password;
	}
	public int getmanv() {
		return manv;
	}
	public void setmanv(int manv) {
		this.manv = manv;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
}
