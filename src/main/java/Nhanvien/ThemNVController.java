package Nhanvien;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.util.List;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;

import Nhacungcap.nhacungcapController;

import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import QLBH.GiaoDienQLController;
import QLBH.HibernateUtils;
import QLBH.Nhanvien;
import QLBH.Taikhoannv;

public class ThemNVController implements Initializable{
	private FileChooser filechooser;
	private Image image;
	private Blob image1;
	private File file;
	
	//private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private TextField tfhovaten;
    
    @FXML
	private ImageView exit;
	
	 
    
    @FXML
    private AnchorPane ap;
    
    @FXML
    private ImageView imgview;

    @FXML
    private TextField tfsdt;

    @FXML
    private ComboBox<String> tfcv;

    @FXML
    private TextField tfcmnd;

    @FXML
    private TextField tfdc;

    @FXML
    private Button addImage;

    @FXML
    private Button add;

    @FXML
    private Button huy;
    
    @FXML
    private TextField tfid;
    
    @FXML
    private ComboBox<String> tfgt;
    
    @FXML
    private DatePicker tfngayvaolam;
    
    @FXML
    private DatePicker tfns;
    
    @FXML
    private TextField user;

    @FXML
    private PasswordField xacnhanpass;

    @FXML
    private Label thongbao;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private Label check_hoten;

    @FXML
    private Label check_ngaysinh;

    @FXML
    private Label check_chucvu;

    @FXML
    private Label check_gioitinh;

    @FXML
    private Label check_sdt;

    @FXML
    private Label check_cmnd;

    @FXML
    private Label check_diachi;

    @FXML
    private Label check_ngayvaolam;

    @FXML
    private Label check_anhdaidien;
    
    @FXML
    private Label check_matkhau;

    @FXML
    private Label check_xacnhanmk;
    
    @FXML
    private Label check_taikhoan;
    
    @FXML
     void AddImage(ActionEvent event) {
    	 Stage stage = (Stage) ap.getScene().getWindow();
    	 FileChooser fileChooser = new FileChooser();
    	 FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
    	 fileChooser.getExtensionFilters().add(imageFilter);
    	 file = fileChooser.showOpenDialog(stage);
    	 if(file!=null) {
    		image = new Image(file.toURI().toString(),100,150,false,true);
    		imgview.setImage(image);
    	}
    }
    
    @FXML
    void exit(MouseEvent event) {
	 Stage stage = (Stage) exit.getScene().getWindow();
		stage.close();
		GiaoDienQLController.getInstance().falsedisable();
    }
    

    @FXML
     void HuyThemNV(ActionEvent event) {
    	 Stage stage = (Stage) huy.getScene().getWindow();
    	 stage.close();
    	 GiaoDienQLController.getInstance().falsedisable();
    	 
    }
    
    private boolean KiemTraTenTaiKhoan() {
    	Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
    	Matcher m = p.matcher(user.getText());
    	if(user.getText().isEmpty()) {
			check_taikhoan.setText("Vui lòng điền tài khoản hợp lệ");
			return false;
		}
    	else if(m.find() && m.group().equals(user.getText())){
			check_taikhoan.setText(null);
			return true;
		}
    	else {
			check_taikhoan.setText("Vui lòng điền tài khoản hợp lệ");
			return false;
		}
    }
    private boolean KiemTraMatKhau() {
    	Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
    	Matcher m = p.matcher(pass.getText());
    	if(pass.getText().isEmpty()) {
			check_matkhau.setText("Vui lòng điền mật khẩu hợp lệ");
			return false;
		} else if (m.find() && m.group().equals(pass.getText())){
			check_matkhau.setText(null);
			return true;
		}
    	return true;
    }
    private boolean SosanhMatKhau() {
    	if(xacnhanpass.getText().equals(pass.getText())) {
    		check_xacnhanmk.setText(null);
    		return true;
    	}else {
    		check_xacnhanmk.setText("Xác nhận mật khẩu không đúng mật khẩu");
			return false;
    	}
    }
    
    private boolean KiemTraTenKH() {
		Pattern p = Pattern.compile("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$");
		Matcher m = p.matcher(tfhovaten.getText());
		if(m.find() && m.group().equals(tfhovaten.getText())){
			check_hoten.setText(null);
			return true;
		}
		else {
			check_hoten.setText("Vui lòng điền tên hợp lệ");
			return false;
		}
	}
    private boolean KiemTraNgaySinh() {
		if(tfns.getValue() == null){
			check_ngaysinh.setText("Vui lòng điền số ngày ở hợp lệ");
			return false;
		}
		check_ngaysinh.setText(null);
		return true;
		
	}
    private boolean KiemTraChucVu() {
		if(tfcv.getValue() == null){
			check_chucvu.setText("Vui lòng chọn chức vụ nhân viên");
			return false;
		}
		check_chucvu.setText(null);
		return true;
		
	}
    private boolean KiemTraGioiTinh() {
		if(tfgt.getValue() == null){
			check_gioitinh.setText("Vui lòng chọn giới tính nhân viên");
			return false;
		}
		check_gioitinh.setText(null);
		return true;
		
	}
    
    private boolean KiemTraSDT() {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(tfsdt.getText());
		if(m.find() && m.group().equals(tfsdt.getText()) && tfsdt.getText().matches("\\d{10}|\\d{11}")){
			check_sdt.setText(null);
			return true;
		}
		else if(tfsdt.getText().length()<10){
			check_sdt.setText("Số điện thoại không đủ 10 số");
			return false;
		}
		else{
			check_sdt.setText("Vui lòng điền số điện thoại hợp lệ");
			return false;
		}
	}
    private boolean KiemTraCMND() {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(tfcmnd.getText());
		if(m.find() && m.group().equals(tfcmnd.getText())){
			check_cmnd.setText(null);
			return true;
		}
		else {
			check_cmnd.setText("Vui lòng điền cmnd hợp lệ");
			return false;
		}
	}
    private boolean KiemTraDiaChi() {
		if(tfdc.getText().isEmpty()){
			check_diachi.setText("Vui lòng điền địa chỉ phù hợp");
			return false;
		}
		check_diachi.setText(null);
		return true;
		}
    
    private boolean KiemTraNgayVaoLam() {
  		if(tfngayvaolam.getValue() == null){
  			check_ngayvaolam.setText("Vui lòng điền số ngày ở hợp lệ");
  			return false;
  		}
  		check_ngayvaolam.setText(null);
  		return true;
  		
  	}
    
    private boolean KiemTraAnhDaiDien() throws Exception {
			 if(imgview.getImage() == null) {
				 check_anhdaidien.setText("Vui lòng thêm ảnh đại diện");
			 return false;
		}
		check_anhdaidien.setText(null);
		return true;
    }

    @FXML
     void ThemNVButton(ActionEvent event) throws Exception  {
    	Session session = HibernateUtils.getSessionFactory().openSession();
    	if(KiemTraTenKH() & KiemTraNgaySinh() & KiemTraChucVu() & KiemTraCMND() &
    			KiemTraDiaChi() & KiemTraGioiTinh() & KiemTraSDT() & KiemTraNgayVaoLam() & KiemTraAnhDaiDien() &KiemTraTenTaiKhoan()
    			 &KiemTraMatKhau() & SosanhMatKhau()) {
    		try {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Thêm nhân viên");
    			LocalDate t2 = tfns.getValue();
    			String t3 = tfcv.getValue();
    			String t4 = tfgt.getValue();
    			String t1 = tfhovaten.getText();
    			int t5 = Integer.parseInt(tfsdt.getText());
    			String sodienthoai =String.format("%010d", t5);
    			int t6 = Integer.parseInt(tfcmnd.getText());
    			String t7 = tfdc.getText();
    			LocalDate t8 = tfngayvaolam.getValue();
    			String taikhoan = user.getText();
    			String matkhau = pass.getText();
    			String xacnhanmatkhau = xacnhanpass.getText();
    			FileInputStream fis = new FileInputStream(file);
    			byte[] bFile = new byte[(int) (file.length())];
    			fis.read(bFile);
    		    Nhanvien nv = new Nhanvien(t1,t2,t3,t4,sodienthoai,t6,t7,bFile,t8);
    			Taikhoannv tk = new Taikhoannv(taikhoan,matkhau,nv);
    			session.beginTransaction();
    			String hql = "FROM Taikhoannv A WHERE A.username = :username";
    			Query query = session.createQuery(hql);
    			query.setParameter("username", taikhoan);
    			List<Taikhoannv> taikhoannv =query.getResultList();
    			for(Taikhoannv checkTaikhoannv : taikhoannv) {
    				 if(checkTaikhoannv.getusername().equals(taikhoan)) {
    					 check_taikhoan.setText("Tài khoản đã có");
    					 return;
    				 } 
    			 }
    			 session.save(nv);
            	 session.save(tk);
            	 session.getTransaction().commit();
            	 Stage stage = (Stage) ap.getScene().getWindow();
                 stage.close();
                 alert.setContentText("Thêm nhân viên thành công !");
                 alert.showAndWait(); 
        	}
        	catch (RuntimeException error){
        			error.printStackTrace();
        		 Alert alert = new Alert(AlertType.INFORMATION);
    			 alert.setTitle("Thêm nhân viên");
        		 alert.setContentText("Thêm nhân viên thất bại  !");
        		 alert.showAndWait();
        		 session.getTransaction().rollback();
        		 return;
        	}
    		 NhanvienController.getInstance().reloadNHANVIEN();
    		 GiaoDienQLController.getInstance().falsedisable();	
			}
    
		
    	 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list=FXCollections.observableArrayList("Nhân viên","Quản lý");
		ObservableList<String> list1=FXCollections.observableArrayList("Nam","Nữ");
		tfcv.setItems(list);
		tfgt.setItems(list1);	
	}

}
