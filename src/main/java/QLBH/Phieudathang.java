package QLBH;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

//import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table (name = "dathang")


public class Phieudathang implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int madathang;
	private LocalDateTime thoigian;
	private double tongtien;
	
	@OneToMany(mappedBy = "phieudathang",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Chitietdathang> chitietdathang;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="mancc")
	@GeneratedValue
	private Nhacungcap nhacungcap;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="manv")
	@GeneratedValue
	private Nhanvien nhanvien;
	

	
	
	public Phieudathang(int madathang, LocalDateTime thoigian, double tongtien, Nhacungcap nhacungcap) {
		super();
		this.madathang = madathang;
		this.thoigian = thoigian;
		this.tongtien = tongtien;
		this.nhacungcap=nhacungcap;
		//this.nhanvien=nhanvien;
		
	}


	public int getMadathang() {
		return madathang;
	}


	public void setMadathang(int madathang) {
		this.madathang = madathang;
	}


	public LocalDateTime getThoigian() {
		return thoigian;
	}


	public void setThoigiandat(LocalDateTime thoigian) {
		this.thoigian = thoigian;
	}


	public double getTongtien() {
		return tongtien;
	}


	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}





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


	public void setChitietdathang(List<Chitietdathang> chitietdathang) {
		this.chitietdathang = chitietdathang;
	}

	

	public Nhanvien getNhanvien() {
		return nhanvien;
	}


	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}
	public Phieudathang(LocalDateTime thoigian, double tongtien, Nhacungcap nhacungcap, Nhanvien nhanvien) {
		super();
		this.thoigian = thoigian;
		this.tongtien = tongtien;
		this.nhacungcap=nhacungcap;
		this.nhanvien=nhanvien;
	}
}
