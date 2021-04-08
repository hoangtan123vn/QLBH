package QLBH;

import java.awt.Checkbox;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name="chitietdathang")
public class Chitietdathang implements Serializable{
	
//	private int madathang;
//	private int masanpham;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name="madathang")
	private Phieudathang phieudathang;
	
	@Id
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="masanpham")
//	@GeneratedValue
	private Sanpham sanpham;
	
	private int soluong;
	
	private double thanhtien;

	
/*	public Chitietdathang(Phieudathang phieudathang, Sanpham sanpham, int soluong,Checkbox cbhang) {
		super();

		this.phieudathang=phieudathang;
		this.sanpham=sanpham;
		this.soluong = soluong;
		this.cbhang = new Checkbox();
	}*/
	

	
	public double getThanhtien() {
		return thanhtien;
	}

	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}

	public Chitietdathang() {
		super();
	}

	public Sanpham getSanpham() {
		return sanpham;
	}
	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
//	public int getMadathang() {
//		return madathang;
//	}
//	public void setMadathang(int madathang) {
//		this.madathang = madathang;
//	}
//	public int getMasanpham() {
//		return masanpham;
//	}
//	public void setMasanpham(int masanpham) {
//		this.masanpham = masanpham;
//	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public Chitietdathang(Phieudathang phieudathang, Sanpham sanpham, int soluong) {
		super();

		this.phieudathang=phieudathang;
		this.sanpham=sanpham;
		this.soluong = soluong;
	}
	
	public Chitietdathang(int soluong,Sanpham sanpham,double thanhtien) {
		super();
		this.thanhtien=thanhtien;
		//this.phieudathang=phieudathang;
		this.sanpham=sanpham;
		this.soluong = soluong;
	}
	

	public Phieudathang getPhieudathang() {
		return phieudathang;
	}



	public void setPhieudathang(Phieudathang phieudathang) {
		this.phieudathang = phieudathang;
	}
	
	public Chitietdathang(Phieudathang phieudathang, Sanpham sanpham, int soluong , double thanhtien) {
		super();

		this.phieudathang=phieudathang;
		this.sanpham=sanpham;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
	}
	
}
