package QLBH;
import javax.persistence.*;

@Entity
@Table(name="chitiethoadon")
@Access(AccessType.FIELD)
public class Chitiethoadon {

	private int mahoadon;
	private int masanpham;
	private int soluong;
	
	
	
	public Chitiethoadon(int mahoadon, int masanpham, int soluong) {
		super();
		this.mahoadon = mahoadon;
		this.masanpham = masanpham;
		this.soluong = soluong;
	}


	public int getMahoadon() {
		return mahoadon;
	}


	public void setMahoadon(int mahoadon) {
		this.mahoadon = mahoadon;
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


	public Chitiethoadon() {
		// TODO Auto-generated constructor stub
	}

}
