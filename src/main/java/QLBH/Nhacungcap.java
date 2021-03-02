package QLBH;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import antlr.collections.List;

@Entity
@Table(name = "nhacungcap")
public class Nhacungcap {
	@Id
	private Integer mancc;
	private String tenncc;
	private String diachi;
	private Integer sodienthoai;
	private String email;
	//private Integer sotienno;
	//private Integer thoigianno;

	public Nhacungcap() {
		super();
	}

	public Nhacungcap(Integer mancc, String tenncc, String diachi, Integer sodienthoai, String email) {
		super();
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;
		this.email = email;
	}

	public Integer getMancc() {
		return mancc;
	}

	public void setMancc(Integer mancc) {
		this.mancc = mancc;
	}

	public String getTenncc() {
		return tenncc;
	}

	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public Integer getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(Integer sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
