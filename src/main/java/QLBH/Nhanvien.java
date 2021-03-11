package QLBH;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="nhanvien")
@Access(AccessType.FIELD)
public class Nhanvien implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@OneToMany(mappedBy="nhanvien")
//	private List<Hoadon> hoadon;
//	private Image imgnhanvien;
	private int manv;
	private String hovaten;
	private int ngaysinh;
	private String chucvu;
	private int sdt;
	private int cmnd;
	private String gioitinh;
	private String diachi;
	private byte[] image;
	
	private String username;
	private String password;
	
	public Nhanvien() {
		super();
	}
	public Nhanvien(String username, String password) {
		super();
		
		this.username=username;
		this.password=password;
	}
	
	
	public Nhanvien(int manv,String hovaten,int ngaysinh,String chucvu,String gioitinh,int sdt,int cmnd,String diachi,byte[] image) {
		super();
		this.manv=manv;
		this.hovaten=hovaten;
		this.ngaysinh=ngaysinh;
		this.chucvu=chucvu;
		this.gioitinh=gioitinh;
		this.sdt=sdt;
		this.cmnd=cmnd;
		this.diachi=diachi;
		this.image = image;
	}
	public Nhanvien(int manv,String hovaten,int ngaysinh,String chucvu,String gioitinh,int sdt,int cmnd,String diachi) {
		super();
		this.manv=manv;
		this.hovaten=hovaten;
		this.ngaysinh=ngaysinh;
		this.chucvu=chucvu;
		this.gioitinh=gioitinh;
		this.sdt=sdt;
		this.cmnd=cmnd;
		this.diachi=diachi;
		
	}
	public Nhanvien(String hovaten,int ngaysinh,String chucvu,String gioitinh,int sdt,int cmnd,String diachi,byte[] image) {
		super();
		this.hovaten=hovaten;
		this.ngaysinh=ngaysinh;
		this.chucvu=chucvu;
		this.gioitinh=gioitinh;
		this.sdt=sdt;
		this.cmnd=cmnd;
		this.diachi=diachi;
		this.image = image;
		
	}
	
	public Nhanvien(int manv) {
		super();
		this.manv=manv;
	}
			
	public Nhanvien(String hovaten,int ngaysinh,String chucvu,int sdt,int cmnd,String diachi) {
		super();
		//this.id=id;
		this.hovaten=hovaten;
		this.ngaysinh=ngaysinh;
		this.chucvu=chucvu;
		this.sdt=sdt;
		this.cmnd=cmnd;
		this.diachi=diachi;
	}
	
	/*public Nhanvien(Image imgnhanvien,String hovaten,int ngaysinh,String chucvu,int sdt,int cmnd,String diachi ) {
		super();
		this.imgnhanvien=imgnhanvien;
		//this.id=id;
		this.hovaten=hovaten;
		this.ngaysinh=ngaysinh;
		this.chucvu=chucvu;
		this.sdt=sdt;
		this.cmnd=cmnd;
		this.diachi=diachi;
	}*/
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public int getManv() {
		return manv;
	}
	public void setManv(int manv) {
		this.manv = manv;
	}
	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	//	public Image getImgnhanvien() {
	//	return imgnhanvien;
	//}
//	public void setImgnhanvien(Image imgnhanvien) {
	//	this.imgnhanvien = imgnhanvien;
//	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getHovaten() {
		return hovaten;
	}
	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}
	public int getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(int ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getChucvu() {
		return chucvu;
	}
	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	public int getCmnd() {
		return cmnd;
	}
	public void setCmnd(int cmnd) {
		this.cmnd = cmnd;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
 

	

}
