package QLBH;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="phieutra")

public class Phieutrahang implements Serializable{
	
	@Id 
	private String maphieutra;
	private String thoigiantra;
	//private int mancc;
	//private int manv;
	
	@OneToMany(mappedBy = "phieutrahang")
	private List<Chitietphieutra> chitietphieutra;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mancc")
	private Nhacungcap nhacungcap;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "manv")
	private Nhanvien nhanvien;
	
	public Phieutrahang(String maphieutra, String thoigiantra, Nhacungcap nhacungcap, Nhanvien nhanvien) {
		super();
		this.maphieutra = maphieutra;
		this.thoigiantra = thoigiantra;
		this.nhacungcap = nhacungcap;
		this.nhanvien = nhanvien;
	}

	public String getMaphieutra() {
		return maphieutra;
	}

	public void setMaphieutra(String maphieutra) {
		this.maphieutra = maphieutra;
	}

	public String getThoigiantra() {
		return thoigiantra;
	}

	public void setThoigiantra(String thoigiantra) {
		this.thoigiantra = thoigiantra;
	}

/*	public int getMancc() {
		return mancc;
	}

	public void setMancc(int mancc) {
		this.mancc = mancc;
	}

	public int getManv() {
		return manv;
	}

	public void setManv(int manv) {
		this.manv = manv;
	}*/

	public Phieutrahang() {
		// TODO Auto-generated constructor stub
	}

	public List<Chitietphieutra> getChitietphieutra() {
		return chitietphieutra;
	}

	public void setChitietphieutra(List<Chitietphieutra> chitietphieutra) {
		this.chitietphieutra = chitietphieutra;
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
