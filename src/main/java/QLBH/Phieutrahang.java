package QLBH;

import javax.persistence.*;

@Entity
@Table (name="phieutra")

public class Phieutrahang {
	
	@Id 
	private String maphieutra;
	private String thoigiantra;
	private int mancc;
	private int manv;
	
	
	
	public Phieutrahang(String maphieutra, String thoigiantra, int mancc, int manv) {
		super();
		this.maphieutra = maphieutra;
		this.thoigiantra = thoigiantra;
		this.mancc = mancc;
		this.manv = manv;
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

	public int getMancc() {
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
	}

	public Phieutrahang() {
		// TODO Auto-generated constructor stub
	}

}
