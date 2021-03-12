package QLBH;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.query.Query;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.derby.vti.Restriction.AND;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController implements  Initializable{

    @FXML
    private TextField emailIdField;

    @FXML
    private Button submitButton;

    @FXML
	private PasswordField passwordField;
    @FXML
    private Label thongbao;
     
   @FXML
    void login(ActionEvent event) throws IOException {
	 Window owner = submitButton.getScene().getWindow();

      System.out.println(emailIdField.getText());
      System.out.println(passwordField.getText());

       if (emailIdField.getText().isEmpty()) {
           thongbao.setText("Bạn chưa nhập tài khoản !");
           return;
       }
       else if (passwordField.getText().isEmpty()) {
           thongbao.setText("Bạn chưa nhập mật khẩu !");
           return ;
       }
      

   	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			.configure("hibernate.cfg.xml")
			.build();
	Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
	SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
	Session session = sessionFactory.openSession();
	String username = emailIdField.getText();
	String password =passwordField.getText();
	//String hql = "SELECT u.username, u.password FROM taikhoannv u WHERE u.username = :username, u.password= :password";	
	//String hql = "SELECT u.username FROM Taikhoannv u WHERE u.username = :username";
	Taikhoannv taikhoan = new Taikhoannv (username,password);
//		Nhanvien nhanvien = new Nhanvien(username, password);

	//Nhanvien nhanvien = new Nhanvien (chucvu);
	try {
		session.getTransaction().begin();
		String hql = "FROM Taikhoannv A WHERE A.username = :username and A.password = :password";
      //  String hql1 = "SELECT A.username,A.password,B.chucvu FROM Taikhoannv A,Nhanvien B WHERE A.username = :username and A.password = :password and B.chucvu='Quản lý' ";
		String hql1 = "SELECT A.username,A.password,B.chucvu FROM Taikhoannv A,Nhanvien B WHERE A.manv=B.manv and A.username = :username and A.password = :password and B.chucvu='Quản lý' ";
    //   String hql2 = "SELECT A.username,A.password,B.chucvu FROM Taikhoannv A,Nhanvien B WHERE A.username = :username and A.password = :password and B.chucvu='Nhân viên' ";
            String hql2 = "SELECT A.username,A.password,B.chucvu FROM Taikhoannv A,Nhanvien B WHERE A.manv=B.manv and A.username = :username and A.password = :password and B.chucvu='Nhân viên' ";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Query query1 = session.createQuery(hql1);
        query1.setParameter("username", username);
        query1.setParameter("password", password);
        Query query2 = session.createQuery(hql2);
        query2.setParameter("username", username);
        query2.setParameter("password", password);
        List<Object[]> tk1 = query1.list();
        List<Object[]> tk2 = query2.list();
        List<Taikhoannv> checktaikhoan = query.list(); 
        for(Taikhoannv checktk1 : checktaikhoan) {
        	//KIEM TRA XEM DANG NHAP DUOC HAY KHONG
        	if(checktk1.getusername().contains(taikhoan.getusername()) && checktk1.getpassword().contains(taikhoan.getpassword())) {
        	//	System.out.println("Thanh cong?");
        		
        		//KIEM TRA XEM CHUC VU == QUAN LY
        		for(Object[] singleRowValues : tk1 ) {
        			String tentaikhoan = (String)singleRowValues[0];
                	String matkhau = (String)singleRowValues[1];
                	String cvString = (String)singleRowValues[2];
                //	System.out.println(tentaikhoan);
                //	System.out.println(matkhau);
                //	System.out.println(cvString);
                	if(tentaikhoan.contains(checktk1.getusername())&& matkhau.contains(checktk1.getpassword()) && cvString.contains("Quản lý")) {
                	   Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
                   	   FXMLLoader loader = new FXMLLoader();
                   	   loader.setLocation(getClass().getResource("chucnangquanly.fxml"));
                   	   Parent sampleparent =loader.load();
                   	   Scene scene = new Scene(sampleparent);
                   	   stage.setScene(scene);
                	}else {
                		break;
                	}
                	 break;
        		}
        		thongbao.setText(" đăng nhập thất bại !!  ");
  
        }
        	
        	
       
   }
        
        for(Taikhoannv checktk1 : checktaikhoan) {
        	//KIEM TRA XEM DANG NHAP DUOC HAY KHONG
        	if(checktk1.getusername().contains(taikhoan.getusername()) && checktk1.getpassword().contains(taikhoan.getpassword())) {
       
        		//KIEM TRA  XEM CHUC VU == NHANVIEN
        		for(Object[] singleRowValues1 : tk2 ) {
        		//	System.out.println("thanh cong");
        			String tentaikhoan1 = (String)singleRowValues1[0];
                	String matkhau1 = (String)singleRowValues1[1];
                	String cvString1 = (String)singleRowValues1[2];
                	if(tentaikhoan1.contains(checktk1.getusername())&& matkhau1.contains(checktk1.getpassword()) &&cvString1.contains("Nhân viên")) {
                	   Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
                   	   FXMLLoader loader = new FXMLLoader();
                   	   loader.setLocation(getClass().getResource("chucnangnhanvien.fxml"));
                   	   Parent sampleparent =loader.load();
                   	   Scene scene = new Scene(sampleparent);
                   	   stage.setScene(scene);
                	}
                	 break;
        		}
        	}
        }
        thongbao.setText(" đăng nhập thất bại !!  ");
	}
        
        catch(HibernateException e) {
    		e.printStackTrace();
       
        }
   } 		


   public void initialize(URL url, ResourceBundle rb) {
	   
	      
   }
 
   
}
