package QLBH;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="nhaphang")


public class Phieunhaphang {
	
	@OneToMany (mappedBy = "phieunhaphang")
	private List<Chitietnhaphang> chitietnhaphang;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="manv")
	@GeneratedValue
	private Nhanvien nhanvien;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="mancc")
	@GeneratedValue
	private Nhacungcap nhacungcap;
	
	@Id
	private String manhaphang;
	private String thoigiannhap;
	private int tongtien;
//	private int mancc;
//	private int manv;
	
	
	
	
//	public String getManhaphang() {
//		return manhaphang;
//	}

//	public void setManhaphang(String manhaphang) {
//		this.manhaphang = manhaphang;
//	}
	
	

	public Phieunhaphang(String manhaphang, String thoigiannhap, int tongtien,Nhacungcap nhacungcap, Nhanvien nhanvien) {
	super();
	this.manhaphang = manhaphang;
	this.thoigiannhap = thoigiannhap;
	this.tongtien = tongtien;
	this.nhacungcap = nhacungcap;
	this.nhanvien = nhanvien;
}


	public String getThoigiannhap() {
		return thoigiannhap;
	}
	

	public List<Chitietnhaphang> getChitietnhaphang() {
		return chitietnhaphang;
	}

	public void setChitietnhaphang(List<Chitietnhaphang> chitietnhaphang) {
		this.chitietnhaphang = chitietnhaphang;
	}

	public Nhanvien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Nhacungcap getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(Nhacungcap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public String getManhaphang() {
		return manhaphang;
	}

	public void setManhaphang(String manhaphang) {
		this.manhaphang = manhaphang;
	}

	public int getTongtien() {
		return tongtien;
	}

	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}

	public void setThoigiannhap(String thoigiannhap) {
		this.thoigiannhap = thoigiannhap;
	}

	public Phieunhaphang() {
		super();
		// TODO Auto-generated constructor stub
	}

}
