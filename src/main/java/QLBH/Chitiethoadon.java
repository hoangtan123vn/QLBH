package QLBH;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="chitiethoadon")


public class Chitiethoadon implements Serializable{
	@Id
/*	private String mahoadon;
	@Id
	private int masanpham*/
	//@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mahoadon")	
	private Hoadon hoadon;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="masanpham")
	//@JoinColumn(name="masanpham")
	private Sanpham sanpham;
	//@PrimaryKeyJoinColumn(name="masanpham")
//	private int mahoadon;
//	private int masanpham;
	private int soluong;
	
	
	public Chitiethoadon(int soluong,Hoadon hoadon,Sanpham sanpham) {
		super();
		this.soluong=soluong;
		this.hoadon= hoadon;
		this.sanpham=sanpham;
	
	}
	

/*	public String getMahoadon() {
		return mahoadon;
	}


	public void setMahoadon(String mahoadon) {
		this.mahoadon = mahoadon;
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


	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	public Hoadon getHoadon() {
		return hoadon;
	}


	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
	}
	

	public Sanpham getSanpham() {
		return sanpham;
	}

	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}

	public Chitiethoadon() {
		
	}

}
