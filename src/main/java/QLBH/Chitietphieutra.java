package QLBH;

import java.io.Serializable;

import javax.persistence.*;



@Entity
@Table(name="chitietphieutra")
public class Chitietphieutra implements Serializable {
	
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name= "maphieutra")
	private Phieutrahang phieutrahang;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name = "masanpham")
	private Sanpham sanpham;
	
//	private int maphieutra;
//	private int masanpham;
	private int soluong;
	private String lydo;
	
	
	
	public Chitietphieutra(Phieutrahang phieutrahang, Sanpham sanpham, int soluong, String lydo) {
		super();
		this.phieutrahang = phieutrahang;
		this.sanpham = sanpham;
		this.soluong = soluong;
		this.lydo = lydo;
	}
/*	public int getMaphieutra() {
		return maphieutra;
	}
	public void setMaphieutra(int maphieutra) {
		this.maphieutra = maphieutra;
	}
	public int getMasanpham() {
		return masanpham;
	}
	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}*/
	public int getSoluong() {
		return soluong;
	}
	public Phieutrahang getPhieutrahang() {
		return phieutrahang;
	}
	public void setPhieutrahang(Phieutrahang phieutrahang) {
		this.phieutrahang = phieutrahang;
	}
	public Sanpham getSanpham() {
		return sanpham;
	}
	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getLydo() {
		return lydo;
	}
	public void setLydo(String lydo) {
		this.lydo = lydo;
	}
	public Chitietphieutra() {
		super();
	}
	
	
}
