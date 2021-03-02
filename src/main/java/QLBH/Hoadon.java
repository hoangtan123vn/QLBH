package QLBH;
import javax.persistence.*;
@Entity
@Table(name="hoadon")
public class Hoadon {
	@Id 
	private String mahoadon;
	private String thoigianmua;
	private int tonggia;
	private int makh;
	private int manv;
	
	
	public Hoadon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Hoadon(String mahoadon, String thoigianmua, int tonggia, int makh, int manv) {
		super();
		//this.id=id;
		this.mahoadon=mahoadon;
		this.thoigianmua=thoigianmua;
		this.tonggia=tonggia;
		this.makh=makh;
		this.manv=manv;
	}
	public String getMahoadon() {
		return mahoadon;
	}
	public void setMahoadon(String mahoadon) {
		this.mahoadon = mahoadon;
	}
	public String getThoigianmua() {
		return thoigianmua;
	}
	public void setThoigianmua(String thoigianmua) {
		this.thoigianmua = thoigianmua;
	}
	public int getTonggia() {
		return tonggia;
	}
	public void setTonggia(int tonggia) {
		this.tonggia = tonggia;
	}
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public int getManv() {
		return manv;
	}
	public void setManv(int manv) {
		this.manv = manv;
	}
	
	
	

}
