package QLBH;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

@Entity
@Table(name = "nhacungcap")
public class Nhacungcap implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mancc;
	private String tenncc;
	private String diachi;
	private String sodienthoai;
	private String email;
	private String sotienno;
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY,mappedBy = "nhacungcap")
	private Set<Phieutrahang> phieutrahang = new HashSet<Phieutrahang>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY,mappedBy = "nhacungcap")
	private Set<Phieunhaphang> phieunhaphang = new HashSet<Phieunhaphang>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY,mappedBy = "nhacungcap")
	private Set<Phieudathang> phieudathang = new HashSet<Phieudathang>();
	//private Button xoa;
	
	public Nhacungcap() {
		super();
	}
	
	
	
	public Nhacungcap(int mancc) {
		super();
		this.mancc = mancc;
	}
	
//	public Nhacungcap(String tenncc) {
//		super();
//		this.tenncc=tenncc;
//	}
	
	public Nhacungcap(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	

	public Nhacungcap(Integer mancc, String tenncc, String diachi, String sodienthoai, String email,String sotienno) {
		super();
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;
		this.email = email;
		this.sotienno = sotienno; 
	//	this.xoa = xoa;
	}
	
	

//	public Button getXoa() {
	//	return xoa;
	//}



	//public void setXoa(Button xoa) {
		//this.xoa = xoa;
//	}



	public Nhacungcap(String tenncc, String diachi, String sodienthoai, String email,String sotienno) {
		super();

		this.tenncc = tenncc;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;
		this.email = email;
		this.sotienno = sotienno;
	}
	
	

	public String getSotienno() {
		return sotienno;
	}

	public void setSotienno(String sotienno) {
		this.sotienno = sotienno;
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

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return String.valueOf(mancc);
	}



	public Set<Phieutrahang> getPhieutrahang() {
		return phieutrahang;
	}



	public void setPhieutrahang(Set<Phieutrahang> phieutrahang) {
		this.phieutrahang = phieutrahang;
	}



	public Set<Phieunhaphang> getPhieunhaphang() {
		return phieunhaphang;
	}



	public void setPhieunhaphang(Set<Phieunhaphang> phieunhaphang) {
		this.phieunhaphang = phieunhaphang;
	}



	public Set<Phieudathang> getPhieudathang() {
		return phieudathang;
	}
	
	



	public void setPhieudathang(Set<Phieudathang> phieudathang) {
		this.phieudathang = phieudathang;
	}

}
