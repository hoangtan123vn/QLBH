package QLBH;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javax.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;

public class DSNVController implements  Initializable {

	@FXML
    private Button idaddNV;

    @FXML
    private TableView<Nhanvien> tableNV;

    @FXML
	private TableColumn hovaten;

    @FXML
    private TableColumn ngaysinh;

    @FXML
    private TableColumn chucvu;

    @FXML
    private TableColumn sdt;

    @FXML
    private TableColumn cmnd;

    @FXML
    private Button thanhtoancongno;

    @FXML
    private Button taonhacungcap;
    
    @FXML
    private TableView<Nhacungcap> Nhacungcap;

    @FXML
    private TableColumn mancc;

    @FXML
    private TableColumn tenncc;

    @FXML
    private TableColumn diachi;

    @FXML
    private TableColumn sotienno;

    @FXML
    private TableColumn email;

    @FXML
    private TableColumn sodienthoai;

    @FXML
    private TableColumn thoigianno;
    
    @FXML
    private ComboBox<String> cbb;

    @FXML
    void Taonhacungcap(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taonhacungcap.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Tạo Nhà Cung Cấp");
			stage.show();	
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    @FXML
    void Thanhtoancongno(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("thanhtoancongno.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.setTitle("Thanh Toán Công Nợ");
			stage.show();	
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    @FXML
    void ThemNV(ActionEvent event) {

    }
   

	public void initialize(URL url, ResourceBundle rb) {
		 ObservableList<String> list = FXCollections.observableArrayList("Chuyển khoản","Thanh toán trực tiếp");
	
		//cbb.getItems().addAll("Chuyển khoản","Thanh toán trực tiếp");
		cbb.setItems(list);
	//	id.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("id"));
        hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("hovaten"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("ngaysinh"));
        chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("chucvu"));
        sdt.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("sdt"));
        cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("cmnd"));
        tableNV.setItems(getNhanvien());
        // Nhà cung cấp
        mancc.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("mancc"));
        tenncc.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("tenncc"));
        diachi.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("diachi"));
        sodienthoai.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("sodienthoai"));
        sotienno.setCellValueFactory(new PropertyValueFactory<Nocong, Integer>("sotienno"));
        thoigianno.setCellValueFactory(new PropertyValueFactory<Nocong, Integer>("thoigianno"));
        email.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("email"));
        Nhacungcap.setItems(getNhacungcap());
        // Nợ công
       // sotienno.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("mancc"));
       // thoigianno.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("tenncc"));
        
		
		
		
		
	/*	id.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("idNV"));
        hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("Há»� vÃ  tÃªn"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("NgÃ y sinh"));
        chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("Chá»©c vá»¥"));
        sdt.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("Sá»‘ Ä‘iá»‡n thoáº¡i"));
        cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("CMND"));
        tableNV.setItems(getNhanvien());*/
			
		}
	 public ObservableList<Nhanvien> getNhanvien() {
	        ObservableList<Nhanvien> TableNV = FXCollections.observableArrayList();
	        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			
			CriteriaQuery<Nhanvien> nv = session.getCriteriaBuilder().createQuery(Nhanvien.class);
			nv.from(Nhanvien.class);
			List<Nhanvien> eList = session.createQuery(nv).getResultList();
		//	List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
	    //    List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
	        for (Nhanvien ent : eList) {
	            TableNV.add(ent);
	        }
	        return TableNV;
	    }
	 // tạo tableview cho nhà cung cấp
	 public ObservableList<Nhacungcap> getNhacungcap() {
	        ObservableList<Nhacungcap> TableNhacungcap = FXCollections.observableArrayList();
	        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			
			CriteriaQuery<Nhacungcap> ncc = session.getCriteriaBuilder().createQuery(Nhacungcap.class);
			ncc.from(Nhacungcap.class);
			List<Nhacungcap> eList = session.createQuery(ncc).getResultList();
		//	List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
	    //    List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
	        for (Nhacungcap ent : eList) {
	            TableNhacungcap.add(ent);
	        }
	        return TableNhacungcap;
	    }
	 public ObservableList<Nocong> getNocong() {
	        ObservableList<Nocong> TableNocong = FXCollections.observableArrayList();
	        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			
			CriteriaQuery<Nocong> nocong = session.getCriteriaBuilder().createQuery(Nocong.class);
			nocong.from(Nocong.class);
			List<Nocong> eList = session.createQuery(nocong).getResultList();
		//	List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
	    //    List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
	        for (Nocong ent : eList) {
	            TableNocong.add(ent);
	        }
	        return TableNocong;
	    }

}

