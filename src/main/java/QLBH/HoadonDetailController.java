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
import javax.persistence.criteria.CriteriaQuery;

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

public class HoadonDetailController implements Initializable {
	

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
    
    @FXML
    private TableView<Object[]> tbChitietHoaDon;

    @FXML
    private TableColumn tenhang;

    @FXML
    private TableColumn soluong;

    @FXML
    private TableColumn dongia;

    @FXML
    private TableColumn thanhtien;
    
  /*  ObservableList<Object[]> tableHoadon = FXCollections.observableArrayList(
    		new Object(tensanpham,soluong,giatien));*/
   
    
  /*  public String toString() {
        return QLBH.Khachhang()+"@"+Integer.toHexString(hashCode());
    }*/
    
    public void setHoadon (Hoadon hoadon) {
    	lbMahoadon.setText(String.valueOf((hoadon.getMahoadon())));
    	lbThoigianmua.setText(hoadon.getThoigianmua());
    //	lbTonggia.setText(String.valueOf(hoadon.getMahoadon()));
    	lbMakh.setText((hoadon.getKhachhang()).toString());
    	lbManv.setText((hoadon.getNhanvien()).toString());
   // 	int mahoadon = hoadon.getMahoadon();
    	IntilizeChitietHoadon(hoadon);
    	getChitietHoadon(hoadon);
    }
   
void read() {
	 
}
    //add column
    
    
    
    
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
    public ObservableList<Object[]> getChitietHoadon(Hoadon hoadon) {
    	String mahoadon = hoadon.getMahoadon();
    	ObservableList<Object[]> TableHD = FXCollections.observableArrayList();
    	 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
    	// String hql = "SELECT SP.tensanpham , C.soluong , SP.giatien FROM Chitiethoadon C,Sanpham SP,Hoadon H WHERE H.mahoadon=C.hoadon.mahoadon and C.sanpham.masanpham=SP.masanpham and H.mahoadon = :hoadon";
    	 String hql = "SELECT SP.tensanpham , C.soluong , SP.giatien FROM Chitiethoadon C INNER JOIN C.sanpham SP INNER JOIN C.hoadon H WHERE H.mahoadon = :hoadon";
    //	 String hql = " SELECT C.soluong FROM Chitiethoadon C INNER JOIN C.sanpham SP INNER JOIN C.hoadon H WHERE H.mahoadon = :hoadon";
    	 Query query = session.createQuery(hql);
	    query.setParameter("hoadon", mahoadon);
		
		// List<Object[]> tk1 = query.list();
		// ObservableList<Object[]> list = FXCollections.observableArrayList(query.list());
		// List<Object> eList = session.createQuery(query).getResultList();
		 List<Object[]> tk1 = query.list();
		 for(Object[] b : tk1) {
			String tensanpham = String.valueOf(b[0]);
			String soluong  = String.valueOf(b[1]);
			String giatien = String.valueOf(b[2]);
	
			TableHD.addAll(b);
		 } 
		 return TableHD;
		// return tk1;
    }

    public void IntilizeChitietHoadon(Hoadon hoadon) {
    //	tenhang.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
  	    tenhang.setCellValueFactory(new PropertyValueFactory<Object[],Sanpham>("tensanpham"));
    	soluong.setCellValueFactory(new PropertyValueFactory<Chitiethoadon,Integer>("soluong"));
     	dongia.setCellValueFactory(new PropertyValueFactory<Chitiethoadon,Sanpham>("sanpham"));
    	tbChitietHoaDon.setItems(getChitietHoadon(hoadon));
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//IntilizeChitietHoadon();
	//	LoadBangChitiet();
	//	String masanpham
	}

    

}
