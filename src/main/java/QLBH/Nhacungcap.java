package QLBH;

import java.io.Serializable;
import java.util.List;

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
	private Integer sodienthoai;
	private String email;
	private Integer sotienno;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true,mappedBy = "nhacungcap")
	private List<Phieudathang> phieudathang;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true,mappedBy = "nhacungcap")
	private List<Phieunhaphang> phieunhaphang;
	//private Button xoa;
	
	public Nhacungcap() {
		super();
	}
	
	
	
	public Nhacungcap(int mancc) {
		super();
		this.mancc = mancc;
	}
	
	public Nhacungcap(String tenncc) {
		super();
		this.tenncc=tenncc;
	}
	
	public Nhacungcap(Integer sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	

	public Nhacungcap(Integer mancc, String tenncc, String diachi, Integer sodienthoai, String email,Integer sotienno) {
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



	public Nhacungcap(String tenncc, String diachi, Integer sodienthoai, String email,Integer sotienno) {
		super();

		this.tenncc = tenncc;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;
		this.email = email;
		this.sotienno = sotienno;
	}
	
	

	public Integer getSotienno() {
		return sotienno;
	}

	public void setSotienno(Integer sotienno) {
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

	public String toString() {
		return String.valueOf(mancc);
	}



	public List<Phieudathang> getPhieudathang() {
		return phieudathang;
	}



	public void setPhieudathang(List<Phieudathang> phieudathang) {
		this.phieudathang = phieudathang;
	}



	public List<Phieunhaphang> getPhieunhaphang() {
		return phieunhaphang;
	}



	public void setPhieunhaphang(List<Phieunhaphang> phieunhaphang) {
		this.phieunhaphang = phieunhaphang;
	}

	
	


}
