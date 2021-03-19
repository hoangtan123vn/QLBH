package QLBH;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="sanpham")
public class Sanpham {
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id 
	private int masanpham;
	//private int id;
	@OneToMany(mappedBy = "sanpham")
	private List<Chitiethoadon> chitiethoadon;
	private String tensanpham;
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
	
	public Sanpham(int masanpham, String tensanpham,String loaisanpham,String donvi,int giatien,String donvitinh) {
			this.tensanpham=tensanpham;
			this.masanpham=masanpham;
			this.donvi=donvi;
			this.giatien=giatien;
			this.donvitinh=donvitinh;
			this.loaisanpham=loaisanpham;
	}
	public Sanpham(String tensanpham,String loaisanpham,String donvi,int giatien,String donvitinh) {
		super();
		//this.id=id;
		this.tensanpham=tensanpham;
	//	this.masanpham=masanpham;
		this.donvi=donvi;
		this.giatien=giatien;
		this.donvitinh=donvitinh;
		this.loaisanpham=loaisanpham;
		
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
		public List<Chitiethoadon> getChitiethoadon() {
			return chitiethoadon;
		}
		public void setChitiethoadon(List<Chitiethoadon> chitiethoadon) {
			this.chitiethoadon = chitiethoadon;
		}
		
	
		
	}
