package QLBH;

import java.util.List;

//import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table (name = "dathang")


public class Phieudathang {
	
	@Id
	private String madathang;
	private String thoigiandat;
	private int tongtien;
	
	@OneToMany(mappedBy = "phieudathang")
	private List<Chitietdathang> chitietdathang;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="mancc")
	@GeneratedValue
	private Nhacungcap nhacungcap;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="manv")
	@GeneratedValue
	private Nhanvien nhanvien;
	
//	private int mancc;
//	private int manv;
	
	
	public Phieudathang(String madathang, String thoigiandat, int tongtien, Nhacungcap nhacungcap, Nhanvien nhanvien) {
		super();
		this.madathang = madathang;
		this.thoigiandat = thoigiandat;
		this.tongtien = tongtien;
		this.nhacungcap=nhacungcap;
		this.nhanvien=nhanvien;
		//this.mancc = mancc;
		//this.manv = manv;
	}


	public String getMadathang() {
		return madathang;
	}


	public void setMadathang(String madathang) {
		this.madathang = madathang;
	}


	public String getThoigiandat() {
		return thoigiandat;
	}


	public void setThoigiandat(String thoigiandat) {
		this.thoigiandat = thoigiandat;
	}


	public int getTongtien() {
		return tongtien;
	}


	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}


//	public int getMancc() {
//		return mancc;
//	}


//	public void setMancc(int mancc) {
//		this.mancc = mancc;
//	}


//	public int getManv() {
//		return manv;
//	}


//	public void setManv(int manv) {
//		this.manv = manv;
//	}


	public Phieudathang() {
		// TODO Auto-generated constructor stub
	}


	public List<Chitietdathang> getChitietdathang() {
		return chitietdathang;
	}


	public void setChitietdathangs(List<Chitietdathang> chitietdathang) {
		this.chitietdathang = chitietdathang;
	}


	public Nhacungcap getNhacungcap() {
		return nhacungcap;
	}


	public void setNhacungcap(Nhacungcap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}


	public Nhanvien getNhanvien() {
		return nhanvien;
	}


	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

}
