package QLBH;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="khachhang")
public class KhachHang {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int makh;
	private String tenkh;
	private String diachi;
	private int sodienthoai;
	private int ngaysinh;
	private	String gioitinh;
	private int diemtichluy;
	private String email;
	
	public KhachHang() {
		super();
	}
	
	public KhachHang(int makh,String tenkh,String diachi,int sodienthoai,int ngaysinh,String gioitinh,int diemtichluy,String email) {
		super();
		this.makh = makh;
		this.tenkh = tenkh;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diemtichluy = diemtichluy;
		this.email = email;
	}
	
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public int getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(int sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public int getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(int ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public int getDiemtichluy() {
		return diemtichluy;
	}
	public void setDiemtichluy(int diemtichluy) {
		this.diemtichluy = diemtichluy;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	
	
	
	
}
