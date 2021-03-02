package QLBH;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nocong")
public class Nocong {
	@Id
	private Integer manocong;
	private Integer sotienno;
	private Integer thoigianno;
	private Integer mancc;

	public Nocong() {
		super();

	}

	public Nocong(Integer manocong, Integer sotienno, Integer thoigianno, Integer mancc) {
		this.manocong = manocong;
		this.sotienno = sotienno;
		this.thoigianno = thoigianno;
		this.mancc = mancc;
	}

	public Integer getMancc() {
		return mancc;
	}

	public void setMancc(Integer mancc) {
		this.mancc = mancc;
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
