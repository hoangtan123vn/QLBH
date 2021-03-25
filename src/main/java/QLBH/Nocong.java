package QLBH;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nocong")
public class Nocong {
//	private Integer mancc;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mancc")
	private Nhacungcap nhacungcap;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer manocong;
	private Integer sotienno;
	private Integer thoigianno;
	private String email;
	private Integer sodienthoai;
	private String tenncc;
	private String diachi;

	public Nocong() {
		super();

	}

	// Nocong nocong = new Nocong();
	// Nhacungcap ncc = nocong.getNhacungcap();
	// String email = ncc.getEmail();

	public Nocong(Integer manocong, Integer sotienno, Integer thoigianno, Nhacungcap nhacungcap, String email,
			Integer sodienthoai, String tenncc, String diachi) {
		this.manocong = manocong;
		this.sotienno = sotienno;
		this.thoigianno = thoigianno;
		this.nhacungcap = nhacungcap;
		this.email = email;
		this.tenncc = tenncc;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;

	}

	public String getTenncc() {
		return nhacungcap.getTenncc();
	}

	public void setTenncc(String tenncc) {
		this.tenncc = nhacungcap.getTenncc();
	}

	public String getDiachi() {
		return nhacungcap.getDiachi();
	}

	public void setDiachi(String diachi) {
		this.diachi = nhacungcap.getDiachi();
	}

	public String getEmail() {
		return nhacungcap.getEmail();
	}

	public void setEmail(String email) {
		this.email = nhacungcap.getEmail();
	}

	public Integer getSodienthoai() {
		return nhacungcap.getSodienthoai();
	}

	public void setSodienthoai(Integer sodienthoai) {
		this.sodienthoai = nhacungcap.getSodienthoai();
	}

	public Nhacungcap getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(Nhacungcap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public Integer getManocong() {
		return manocong;
	}

	public void setManocong(Integer manocong) {
		this.manocong = manocong;
	}

	public Integer getSotienno() {
		return sotienno;
	}

	public void setSotienno(Integer sotienno) {
		this.sotienno = sotienno;
	}

	public Integer getThoigianno() {
		return thoigianno;
	}

	public void setThoigianno(Integer thoigianno) {
		this.thoigianno = thoigianno;
	}

}
