package QLBH;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="taikhoannv")
public class Taikhoannv implements Serializable{
	
	@GeneratedValue
	private int manv;
	@Id
	private String username;
	@Id
	private String password;
	
	public Taikhoannv() {
		super();
	}
	public Taikhoannv(String username, String password,int manv) {
		super();
		this.manv=manv;
		this.username=username;
		this.password=password;
	}
	public Taikhoannv(String username, String password) {
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
