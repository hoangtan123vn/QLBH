package QLBH;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name="chitietdathang")
public class Chitietdathang implements Serializable{
	@Id
	private int madathang;
	private int masanpham;
	private int soluong;
	
	public int getMadathang() {
		return madathang;
	}
	public void setMadathang(int madathang) {
		this.madathang = madathang;
	}
	public int getMasanpham() {
		return masanpham;
	}
	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public Chitietdathang(int madathang, int masanpham, int soluong) {
		super();
		this.madathang = madathang;
		this.masanpham = masanpham;
		this.soluong = soluong;
	}
	
}
