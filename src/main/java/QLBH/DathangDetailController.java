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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.bytebuddy.implementation.bytecode.Addition;
import javafx.stage.FileChooser;

import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;

public class DathangDetailController implements Initializable{

    @FXML
    private Label lbMadathang;

    @FXML
    private Label lbThoigiandat;

    @FXML
    private Label lbTongtien;

    @FXML
    private Label lbMancc;

    @FXML
    private Label lbManv;

    @FXML
    private Button back1;

    @FXML
    private TableView tbChitietDatHang;

    @FXML
    private TableColumn tenhang;

    @FXML
    private TableColumn soluong;

    @FXML
    private TableColumn dongia;

    @FXML
    private TableColumn tongtien;

    
    @FXML
    void trolai(ActionEvent e) throws IOException {
    	Stage stage = (Stage) back1.getScene().getWindow();
        stage.close();
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	 public void setPhieudathang (Phieudathang phieudathang) {
	    	lbMadathang.setText(String.valueOf((phieudathang.getMadathang())));
	    	lbThoigiandat.setText(phieudathang.getThoigiandat());
	  //  	lbTongtien.setText(String.valueOf((phieudathang.getTongtien())));
	    	lbMancc.setText(phieudathang.getNhacungcap().toString());
	    	lbManv.setText(phieudathang.getNhanvien().toString());
	  
	    }	
}
