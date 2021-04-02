package QLBH;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.util.List;
import java.awt.Desktop;
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
    private ComboBox cbb;
    
    @FXML
    private Label tenncc;
    
    @FXML
    void close(ActionEvent event) {
    	Stage stage = (Stage) btquaylai.getScene().getWindow();
   	 	stage.close();
    }
    
    public void setThanhtoan(Nhacungcap nhacungcap) {
    	tenncc.setText(nhacungcap.getTenncc());
    	tfnoht.setText(String.valueOf(nhacungcap.getSotienno()));
    	java.util.Date date=new java.util.Date();  
    	tfdate.setText(String.valueOf(date));
    	tfthanhtoan.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			if(e.getCode() == KeyCode.ENTER) {
				if( Integer.parseInt(tfthanhtoan.getText()) < Integer.parseInt(tfnoht.getText())) {
				
                 
                 khachtra.setText("");
                 thongbao.setText("Khong Du Tien, Nhap lai");
                 

                 
                 
			}
				else {
					 float tiengiam = Float.parseFloat(giamgia.getText())/100;
					 float tongtiengiam = Float.parseFloat(tongtien.getText())*(1-tiengiam);
					 float tientrakhach = Float.parseFloat(khachtra.getText())- tongtiengiam;
					// System.out.print(tiengiam);
	                // System.out.print(tongtiengiam);
	                 tienthua.setText(String.valueOf(tientrakhach));
				}
			}
		});
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
