package QLBH;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import QLBH.KhachHang;
import QLBH.Nhanvien;
@Entity
@Table(name="hoadon")
//@Access(AccessType.FIELD)
public class Hoadon implements Serializable{
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id 
	private int mahoadon;
	//@ManyToOne
	//@PrimaryKeyJoinColumn(name ="makh")
	//@PrimaryKeyJoinColumn(name=" manv")
	//private Nhanvien nv;
	//private KhachHang kh;
	@OneToMany(mappedBy="hoadon",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Chitiethoadon> chitiethoadon;
//	@JoinColumn()
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="makh")
	@GeneratedValue
	private KhachHang khachhang;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="manv")
	@GeneratedValue
	private Nhanvien nhanvien;
	
	
	private String thoigianmua;
	private int tonggia;
//	private int makh;
//	private int manv;
	
	
		
	public Hoadon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Hoadon(int mahoadon, String thoigianmua, int tonggia,Nhanvien nhanvien,KhachHang khachhang) {
		super();
		//this.id=id;
		this.mahoadon=mahoadon;
		this.thoigianmua=thoigianmua;
		this.tonggia=tonggia;
		this.nhanvien = nhanvien;
		this.khachhang = khachhang;
	//	this.manv=manv;
	}
	public Hoadon( String thoigianmua, int tonggia,Nhanvien nhanvien,KhachHang khachhang) {
		super();
		//this.id=id;
		//this.mahoadon=mahoadon;
		this.thoigianmua=thoigianmua;
		this.tonggia=tonggia;
		this.nhanvien = nhanvien;
		this.khachhang = khachhang;
	//	this.manv=manv;
	}
	public int getMahoadon() {
		return mahoadon;
	}
	public void setMahoadon(int mahoadon) {
		this.mahoadon = mahoadon;
	}
	public String getThoigianmua() {
		return thoigianmua;
	}
	public void setThoigianmua(String thoigianmua) {
		this.thoigianmua = thoigianmua;
	}
	
/*	public Nhanvien getNv() {
		return nv;
	}

	public void setNv(Nhanvien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}
*/
	public int getTonggia() {
		return tonggia;
	}
	public void setTonggia(int tonggia) {
		this.tonggia = tonggia;
	}
/*	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}*/
/*	public int getManv() {
		return manv;
	}
	public void setManv(int manv) {
		this.manv = manv;
	}*/

	public List<Chitiethoadon> getChitiethoadon() {
		return chitiethoadon;
	}

	public void setChitiethoadon(List<Chitiethoadon> chitiethoadon) {
		this.chitiethoadon = chitiethoadon;
	}


	public KhachHang getKhachhang() {
		return khachhang;
	}


	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}


	public Nhanvien getNhanvien() {
		return nhanvien;
	}


	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}
	
	
	
	
	

}
