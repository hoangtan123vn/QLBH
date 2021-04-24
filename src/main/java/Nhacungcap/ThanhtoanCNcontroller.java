package Nhacungcap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.stage.FileChooser;

import java.util.List;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

import com.google.protobuf.StringValue;

import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;
import QLBH.HibernateUtils;
import QLBH.Nhacungcap;

public class ThanhtoanCNcontroller extends Application implements Initializable {
	@FXML
	private Label tfnoht;

	@FXML
	private TextField tfthanhtoan;

	@FXML
	private Label tfdate;

	@FXML
	private Label tfnoconlai;

	@FXML
	private Button bttaophieu;

	@FXML
	private Button btquaylai;


	@FXML
	private Label tenncc;

	@FXML
	private Label thongbao;
	
	@FXML
	private Label thongbao1;

	@FXML
    private ImageView exit;
	
	  @FXML
	    void exit(MouseEvent event) {
		  Stage stage = (Stage) exit.getScene().getWindow();
			stage.close();
			nhacungcapController.getInstance().falsedisable();
	    }
	
	@FXML
	void close(ActionEvent event) {
		Stage stage = (Stage) btquaylai.getScene().getWindow();
		stage.close();
		nhacungcapController.getInstance().falsedisable();
	}
//	@FXML
//	public void actionComboBox() {
//		if(cbb.getValue() == "Trực Tiếp") {
//			thongbao1.setVisible(true);
//			thongbao1.setText("Mời bạn gặp nhân viên A");
//			tfthanhtoan.setVisible(false);
//		}
//		else if (cbb.getValue() == "Chuyển Khoản"){
//			thongbao1.setVisible(true);
//			thongbao1.setText("Mời bạn nhập vào ô thanh toán");
//			tfthanhtoan.setVisible(true);
//		}
//	}

	public void setThanhtoan(Nhacungcap nhacungcap) {
		tenncc.setText(nhacungcap.getTenncc());
		tfnoht.setText(String.valueOf(nhacungcap.getSotienno()));
		java.util.Date date = new java.util.Date();
		tfdate.setText(String.valueOf(date));
	
		tfthanhtoan.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			
				try {
					if (e.getCode() == KeyCode.ENTER) {
						if (Integer.parseInt(tfthanhtoan.getText()) > Integer.parseInt(tfnoht.getText()) ) {
	
							tfthanhtoan.setText("");
							thongbao.setVisible(true);
							thongbao.setText("Không hợp lệ! Mời Nhập Lại!!!");
							tfnoconlai.setText(" ");
	
						} else if (Integer.parseInt(tfthanhtoan.getText()) <= Integer.parseInt(tfnoht.getText()) ) {
	
							tfnoconlai.setText(String
									.valueOf(Integer.parseInt(tfnoht.getText()) - Integer.parseInt(tfthanhtoan.getText())));
							tfnoconlai.setVisible(true);
							thongbao.setText(" ");
							
						}
						else if(tfthanhtoan.getText().isEmpty()) {
							thongbao.setText("Bạn không được bỏ trống ");
							
						}
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					thongbao.setText("Vui lòng nhập số ");
					
				}
				
				
		});
		bttaophieu.setOnMouseClicked(event ->  {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thanh Toán Công Nợ");
			 
	    	//Integer mancc = Integer.parseInt(tfncc.getText());
	   // 	String ten = tenncc.getText();
	    	Integer sotienno = Integer.parseInt(tfnoconlai.getText());
	    //	String noio = diachi.getText();
	    	//Integer sdt = Integer.parseInt(sodienthoai.getText());;
	    	//String em = email.getText();
	    
	    	 Session session = HibernateUtils.getSessionFactory().openSession();
			
			
	    	try {		
	    		 session.beginTransaction();
				 Nhacungcap nv = new Nhacungcap();
				 nv = session.get(Nhacungcap.class, nhacungcap.getMancc());
				 nv.setSotienno(sotienno);
				 session.save(nv);
	    		 session.getTransaction().commit();
	    		 //Nhacungcap ncc = new Nhacungcap(tenncc,diachi,sodienthoai,email);
	    		 Stage stage = (Stage) bttaophieu.getScene().getWindow();
	    		 
	        	 stage.close();
	        	 alert.setContentText("Cập nhật số tiền nợ thành công");
	        	 alert.showAndWait();    
	        	 
	        	 
	        	nhacungcapController.getInstance().ReloadNHACUNGCAP();
	        	nhacungcapController.getInstance().falsedisable();
	        	
	    	}
	    	catch (RuntimeException error){
	    		 System.out.print(error);
	    		 alert.setContentText("Cập nhật số tiền nợ thất bại");
	    		 alert.showAndWait();
	    		session.getTransaction().rollback();
	    	}
        });
		
//		cbb.getItems().addAll(
//					"Trực Tiếp",
//					"Chuyển Khoản"
//				);
//		cbb.setValue("Chuyển Khoản");
	
						
//					if (cbb.getValue()=="Chuyển Khoản") {
//						thongbao1.setVisible(true);
//						thongbao1.setText("Mời bạn nhập vào ô thanh toán");
//
//					} else {
//						thongbao1.setVisible(true);
//						thongbao1.setText("Bạn nên tới quầy lễ tân ");
//					}				
	

		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	

}
