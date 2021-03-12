package QLBH;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="taikhoannv")
public class Taikhoannv implements Serializable{
	@Id
	@GeneratedValue
	@JoinColumn(name="manv")
	@OneToOne(cascade = CascadeType.ALL)
	
	private Nhanvien nhanvien;
	//private int manv;
	private String username;
	private String password;
	
	
	public Taikhoannv() {
		super();
	}

	public Taikhoannv(String username, String password ,Nhanvien nhanvien) {
		super();
	//	this.manv = manv;
		this.username=username;
		this.password=password;
		this.nhanvien=nhanvien;
	}
	public Taikhoannv(String username, String password) {
		super();
	//	this.manv=manv;
		this.username=username;
		this.password=password;
	}
	
/*	public int getmanv() {
		return manv;
	}
	public void setmanv(int manv) {
		this.manv = manv;
	}*/
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

	public Nhanvien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}
	
	
	
}
