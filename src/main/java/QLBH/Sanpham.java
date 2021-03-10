package QLBH;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="sanpham")
public class Sanpham {
	@Id 
	//private int id;
	private String tensanpham;
	private int masanpham;
	private String loaisanpham;
	private String donvi;
	private int giatien;
	private String donvitinh;
	
	public Sanpham() {
		super();
	}
	public Sanpham(int masanpham) {
		super();
	}
	public Sanpham(String tensanpham,int masanpham,String donvi,int giatien,String donvitinh,String loaisanpham) {
		super();
		//this.id=id;
		this.tensanpham=tensanpham;
		this.masanpham=masanpham;
		this.loaisanpham=loaisanpham;
		this.donvi=donvi;
		this.giatien=giatien;
		this.donvitinh=donvitinh;
		
}
	public String getTensanpham() {
		return tensanpham;
	}
	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}
	public int getMasanpham() {
		return masanpham;
	}
	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}
	public String getLoaisanpham() {
		return loaisanpham;
	}
	public void setLoaisanpham(String loaisanpham) {
		this.loaisanpham = loaisanpham;
	}
	public String getDonvi() {
		return donvi;
	}
	public void setDonvi(String donvi) {
		this.donvi = donvi;
	}
	public int getGiatien() {
		return giatien;
	}
	public void setGiatien(int giatien) {
		this.giatien = giatien;
	}
	public String getDonvitinh() {
		return donvitinh;
	}
	public void setDonvitinh(String donvitinh) {
		this.donvitinh = donvitinh;
	}
}
