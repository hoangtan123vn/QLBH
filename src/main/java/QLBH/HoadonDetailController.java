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
import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;

public class HoadonDetailController {
	

	@FXML
    private Label lbMahoadon;

    @FXML
    private Label lbThoigianmua;

    @FXML
    private Label lbTonggia;

    @FXML
    private Label lbMakh;

    @FXML
    private Label lbManv;
    @FXML
    private Button back;
    
    public void setHoadon (Hoadon hoadon) {
    	lbMahoadon.setText(hoadon.getMahoadon());
    	lbThoigianmua.setText(hoadon.getThoigianmua());
    	lbTonggia.setText(String.valueOf(hoadon.getMahoadon()));
    	lbMakh.setText(String.valueOf(hoadon.getMakh()));
    	lbManv.setText(String.valueOf(hoadon.getManv()));
    }
    
    @FXML
    void goBack(ActionEvent e) throws IOException {
      /* Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("danhsachnhanvien.fxml"));
        Parent QLBHhdParent = loader.load();
        Scene scene = new Scene(QLBHhdParent);
        stage.setScene(scene);
  */
    	Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    	
    }
    
    

}
