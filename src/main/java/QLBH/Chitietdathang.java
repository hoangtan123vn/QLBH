package QLBH;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name="chitietdathang")
public class Chitietdathang implements Serializable{
	
//	private int madathang;
//	private int masanpham;
	
	@Id
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name="madathang")
//	@GeneratedValue
	private Phieudathang phieudathang;
	
	@Id
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="masanpham")
//	@GeneratedValue
	private Sanpham sanpham;
	
	private int soluong;
	
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
	
}
