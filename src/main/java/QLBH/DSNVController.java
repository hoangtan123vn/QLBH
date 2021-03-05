package QLBH;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaQuery;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;
import QLBH.Hoadon;
import QLBH.Phieudathang;
import QLBH.Phieunhaphang;
import QLBH.Phieutrahang;

public class DSNVController extends Application implements  Initializable  {
	
	
	
	
	
	
	/////////////////////////////AUTHOR : LÊ HOÀNG TÂN/////////////////////////************************** 
	//////////////////////////////////CHỨC NĂNG : QUẢN LÝ NHÂN VIÊN ///////////*************************
									///////////////////
	
	
	private Image image;
    @FXML
    private Button idaddNV;
    
    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    private TableView<Nhanvien> tableNV;
    
    @FXML
    private TableColumn id;

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
    private TableColumn diachi;
    
    @FXML
    private Button idreload;
    
    @FXML
    private TextField hovaten_nv;

    @FXML
    private TextField ns_nv;

    @FXML
    private TextField cv_nv;

    @FXML
    private TextField sdt_nv;

    @FXML
    private TextField cmnd_nv;

    @FXML
    private TextField diachi_nv;

    @FXML
    private Button xoa_nv;

    @FXML
    private Button capnhat_nv;

    @FXML
    private TextField id_nv;
    
    @FXML
    private ImageView imgnhanvien;
    
    @FXML
    private Button luucapnhat;
    
    @FXML
    private Button reset;
    
    @FXML
    private TextField search;

    
    @FXML
    private TextField gt_nv;
    
    @FXML
    private ComboBox<String> Listnhanvien;
    
    
    ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());
       		   
    @FXML
    void Reset(ActionEvent event) {
    	 Nhanvien nv = tableNV.getItems().get(tableNV.getSelectionModel().getSelectedIndex());
    	 id_nv.setText(Integer.toString(nv.getManv()));
		 hovaten_nv.setText(nv.getHovaten());
		 ns_nv.setText(Integer.toString(nv.getNgaysinh()));
		 cv_nv.setText(nv.getChucvu());
		 gt_nv.setText(nv.getGioitinh());
		 sdt_nv.setText(Integer.toString(nv.getSdt()));
		 diachi_nv.setText(nv.getDiachi());
		 cmnd_nv.setText(Integer.toString(nv.getCmnd()));
    }
    
    @FXML
    private void ThemNV(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("themnhanvien.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Them nhan vien");
		stage.show();		
    }
    
    
    @FXML
    void ListNhanvien(ActionEvent event) throws IOException {
    	if(Listnhanvien.getValue()=="Danh sách nhân viên") {
    		Parent root = FXMLLoader.load(getClass().getResource("danhsachnhanvien.fxml"));
    		
    	//	Scene scene = new Scene(root);
   // 		Stage stage = new Stage();
    	//	stage.setScene(scene);
    	//	stage.show();
    		
    		


    	}
    	else if(Listnhanvien.getValue()=="Lịch làm") {
    		Parent calamnhanvien = FXMLLoader.load(getClass().getResource("calamnhanvien.fxml"));
    		Scene calam_scene = new Scene(calamnhanvien);
    		Stage calam_stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
    		calam_stage.setScene(calam_scene);
    		calam_stage.show();
    	}
    }
    
    
	
  /*  public ObservableList<Nhanvien> findAll() {
    	initialize1();
    	getNhanvien();    	
    	 // Wrap the ObservableList in a FilteredList (initially display all data).
      
               
        
    }    */
    @FXML
    void luucapnhat(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cap nhat thanh cong ");
    	int idnv = (Integer.parseInt(id_nv.getText()));
    	String hovatennv = hovaten_nv.getText();
    	int ngaysinhnv = (Integer.parseInt(ns_nv.getText()));
    	String chucvunv = cv_nv.getText();
    	String gioitinhnv = gt_nv.getText();
    	int sdtnv = (Integer.parseInt(sdt_nv.getText()));
    	int cmndnv = (Integer.parseInt(cmnd_nv.getText()));
    	String diachinv = diachi_nv.getText();
    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Nhanvien nv2 = new Nhanvien(idnv,hovatennv,ngaysinhnv,chucvunv,gioitinhnv,sdtnv,cmndnv,diachinv);
			nv2=session.get(Nhanvien.class, idnv);
			if (nv2 != null) {
			//	nv2.setid(idnv);
				nv2.setHovaten(hovatennv);
				nv2.setChucvu(chucvunv);
				nv2.setGioitinh(gioitinhnv);
				nv2.setDiachi(diachinv);
				nv2.setNgaysinh(ngaysinhnv);
				nv2.setSdt(sdtnv);
				nv2.setCmnd(cmndnv);
				//person2.setAge(t2);
			///	person2.setAddress(t3);
				 session.save(nv2);
				 alert.setContentText("Cap nhat nhan vien thanh cong !");
	        	 alert.showAndWait();
			}
			session.getTransaction().commit();	
		}catch (RuntimeException error) {
			session.getTransaction().rollback();
		}
		
		reloadNHANVIEN();
    }
    
    @FXML
    void search() {
    	ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());
    	FilteredList<Nhanvien> filterData = new FilteredList<>(table,p -> true);
    	search.textProperty().addListener((observable,oldvalue,newvalue) -> {
    		filterData.setPredicate(nv -> {
    			if(newvalue == null || newvalue.isEmpty()) {
    				 return true; 
    			}
    			String typetext = newvalue.toLowerCase();
    			if(nv.getHovaten().toLowerCase().indexOf(typetext) !=-1) {
    				return true;
    			}
    			if(nv.getChucvu().toLowerCase().indexOf(typetext) !=-1) {
    				return true;
    			}
    			return false;
    			
    		});
    		SortedList<Nhanvien> sortedList = new SortedList<>(filterData);
    		sortedList.comparatorProperty().bind(tableNV.comparatorProperty());
    		tableNV.setItems(sortedList);
    	});
    }
    
    
    
    @FXML
    void CapNhatNhanvien(ActionEvent event) {
   // 	id_nv.setEditable(true);
    	hovaten_nv.setEditable(true);
    	ns_nv.setEditable(true);
    	cv_nv.setEditable(true);
    	gt_nv.setEditable(true);
    	cmnd_nv.setEditable(true);
    	sdt_nv.setEditable(true);
    	diachi_nv.setEditable(true);
    	luucapnhat.setVisible(true);
    	reset.setVisible(true);
    }
    
    @FXML
    void XoaNhanvien(ActionEvent event) {
    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		 alert.setTitle("Current project is modified");
		 alert.setContentText("Save?");
		 ButtonType okButton = new ButtonType("Yes");
		 ButtonType noButton = new ButtonType("NO");
		 alert.getButtonTypes().setAll(okButton, noButton);
		 alert.showAndWait().ifPresent(type -> {
		         if (type == okButton) {
		        	int t1 = (Integer.parseInt(id_nv.getText()));
		         	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
		     				.configure("hibernate.cfg.xml")
		     				.build();
		     		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		     		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		     		Session session = sessionFactory.openSession();
		     		Nhanvien nv = new Nhanvien(t1);
		     		nv=session.get(Nhanvien.class, t1);
		     		try {
		     			session.beginTransaction();
		     			
		     			if(nv != null) {
		     				session.delete(nv);
		     				
		     			}		
		     			session.getTransaction().commit();	
		     		} catch (RuntimeException error) {
		     			session.getTransaction().rollback();
		     			
		     		}
		     		reloadNHANVIEN();
		     		id_nv.setText("");
		     		hovaten_nv.setText("");
		     		ns_nv.setText("");
		     		cv_nv.setText("");
		     		sdt_nv.setText("");
		     		cmnd_nv.setText("");
		     		diachi_nv.setText("");
		     		gt_nv.setText("");
		     		imgnhanvien.setImage(null);
		         } else if (type == ButtonType.NO) {
		        	 alert.close();
		         }
		         ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());
		 });
    }
  
    

	public void initializeNHANVIEN()  {
		
	//	id.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("id"));
        hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("hovaten"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("ngaysinh"));
        chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("chucvu"));
        sdt.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("sdt"));
        cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("cmnd"));
        diachi.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("diachi"));
    //    ObservableList<Nhanvien> table = FXCollections.observableArrayList(
   ///     		getNhanvien()
        	
    //   );
        tableNV.setItems(getNhanvien());  
        search();
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
	 	 
	 
//	 public void setNhanviendata(ObservableList<Nhanvien> TableNV) {
	//	this.TableNV = TableNV; 
	//}
	 void reloadNHANVIEN() {
		//
		 initializeNHANVIEN();
	     getNhanvien(); 
	    }
	 
	 
	 @FXML
	    void reload(ActionEvent event) {
		//
		 initializeNHANVIEN();    
		 //   ObservableList<Nhanvien> table = FXCollections.observableArrayList(
		    	//	getNhanvien()
		    	
		  // );
//	123212
		 getNhanvien();  
	    }
	
	 
	 private void setCellValueFromTabletoTexfField()  {
		 tableNV.setOnMouseClicked(event -> {
			 //
			 Nhanvien nv = tableNV.getItems().get(tableNV.getSelectionModel().getSelectedIndex());
			 id_nv.setText(Integer.toString(nv.getManv()));
			 hovaten_nv.setText(nv.getHovaten());
			 ns_nv.setText(Integer.toString(nv.getNgaysinh()));
			 cv_nv.setText(nv.getChucvu());
			 gt_nv.setText(nv.getGioitinh());
			 sdt_nv.setText(Integer.toString(nv.getSdt()));
			 diachi_nv.setText(nv.getDiachi());
			 cmnd_nv.setText(Integer.toString(nv.getCmnd()));
			 byte[] getImageInBytes = nv.getImage();
			  try{
		            FileOutputStream fos = new FileOutputStream(new File("photo.jpg")); 
		            fos.write(getImageInBytes);
		            fos.close();
		            image = new Image("file:photo.jpg",imgnhanvien.getFitHeight(),imgnhanvien.getFitHeight(),true,true);
		            imgnhanvien.setImage(image);
		        }catch(Exception e){
		            e.printStackTrace();
		        }			 
		 });
		 
		
	 }
	 
	 
/////////////////////////////AUTHOR :LÊ HOÀNG TÂN /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : QL KHÁCH HÀNG  ///////////*************************
///////////////////
	 
	 
	 
/////////////////////////////AUTHOR :HỒNG THÁI/////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : QUẢN LÝ KHO ///////////*************************
				///////////////////
	 
	   @FXML
	    private TableColumn donvitinh;

	    @FXML
	    private TableColumn donvi;

	    @FXML
	    private TableColumn giatien;

	    @FXML
	    private TableColumn tensanpham;

	    @FXML
	    private TableColumn masanpham;
	    
	    @FXML
	    private TextField tf1;
	    
	    @FXML
	    private TextField tf2;
	    
	    @FXML
	    private TextField tf3;
	    
	    @FXML
	    private TextField tf4;
	    
	    @FXML
	    private TextField tf5;
	    
	   
	    @FXML
	    private TextField Timkiem;
	    
	    ObservableList<Sanpham> listM;

	    @FXML
	    private Button idaddSP;
	    
	    @FXML
	    private Button iddelSP;
	    
	    @FXML
	    private Button idupdSP;

	    @FXML
	    private TableView<Sanpham> tableSP;

	    void Timkiem() {   
	    	ObservableList<Sanpham> TableSP = FXCollections.observableArrayList(getSanpham());
	    	
	        FilteredList<Sanpham> filteredData = new FilteredList<>(TableSP, b -> true);  
	        Timkiem.textProperty().addListener((observable, oldValue, newValue) -> {
	        filteredData.setPredicate(sanpham -> {
	           if (newValue == null || newValue.isEmpty()) {
	            return true;
	           }    
	           String lowerCaseFilter = newValue.toLowerCase();
	           
	           if (sanpham.getTensanpham().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	            return true; // Filter matches username
	           } 
	           else if (sanpham.getDonvi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	            return true; // Filter matches password
	           }               
	                else  
	                 return false; // Does not match.
	          });
	         });  
	         SortedList<Sanpham> sortedData = new SortedList<>(filteredData);  
	         sortedData.comparatorProperty().bind(tableSP.comparatorProperty());  
	         tableSP.setItems(sortedData);      
	           
	    	        
	    	    }    


	    @FXML
	    private void ThemSP(ActionEvent event) {
	    	   // 	ta.setText("");
	    	    	String t1 = tf1.getText();
	    	    	int t2 = Integer.parseInt(tf2.getText());
	    	    	String t3 = tf3.getText();
	    	    	int t5 = Integer.parseInt(tf5.getText());
	    	    	int t4 = Integer.parseInt(tf4.getText());
	    	    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
	    					.configure("hibernate.cfg.xml")
	    					.build();
	    			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
	    			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
	    			Session session = sessionFactory.openSession();
	    			Sanpham sanpham = new Sanpham(t1,t2,t3,t4,t5);
	    		//	person=session.get(Person.class, t1);
	    			try {
	    				session.beginTransaction();
	    				session.save(sanpham);
	    				session.getTransaction().commit();	
	    		//		ta.appendText("Them Thanh Cong  ! ! !");
	    				ReloadSANPHAM();
	    			} catch (RuntimeException error) {
	    				session.getTransaction().rollback();
	    		//		ta.appendText("Khong the thuc hien thao tac ! ");
	    			}
	  

	    }
	    @FXML
	    void XoaSP(ActionEvent event) {
	//    	ta.setText("");
	    	String t1 = tf1.getText();
	    	int t2 = Integer.parseInt(tf2.getText());
	    	String t3 = tf3.getText();
	    	int t5 = Integer.parseInt(tf5.getText());
	    	int t4 = Integer.parseInt(tf4.getText());
	    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			Sanpham sanpham = new Sanpham(t1,t2,t3,t4,t5);
			sanpham=session.get(Sanpham.class, t1);
			try {
				session.beginTransaction();
				
				if(sanpham != null) {
					session.delete(sanpham);
				}		
				session.getTransaction().commit();	
		//		ta.appendText("Xoa Thanh Cong  ! ! !");
			} catch (RuntimeException error) {
				session.getTransaction().rollback();
		//		ta.appendText("Khong the thuc hien thao tac ! ");
			}
	    }

	    @FXML
	    void SuaSP(ActionEvent event) {
	//    	ta.setText("");
	    	String t1 = tf1.getText();	
	    	int t2 = Integer.parseInt(tf2.getText());
	    	String t3 = tf3.getText();
	    	int t5 = Integer.parseInt(tf5.getText());
	    	int t4 = Integer.parseInt(tf4.getText());
	    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			Sanpham sanpham = new Sanpham(t1,t2,t3,t4,t5);
			sanpham=session.get(Sanpham.class, t1);
			try {
				session.beginTransaction();
				if (sanpham != null) {
					sanpham.setTensanpham(t1);
					sanpham.setMasanpham(t2);
					sanpham.setDonvi(t3);
					sanpham.setDonvitinh(t4);
					sanpham.setGiatien(t5);
				session.save(sanpham);
				}
				session.getTransaction().commit();	
			//	ta.appendText("Update Thanh Cong  ! ! !");
			} catch (RuntimeException error) {
				session.getTransaction().rollback();
			//	ta.appendText("Khong the thuc hien thao tac ! ");
			}
	    }
	    
	    
	    public ObservableList<Sanpham> getSanpham() {
	        ObservableList<Sanpham> TableSP = FXCollections.observableArrayList();
	        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			
			CriteriaQuery<Sanpham> sp = session.getCriteriaBuilder().createQuery(Sanpham.class);
			sp.from(Sanpham.class);
			List<Sanpham> eList = session.createQuery(sp).getResultList();
		//	List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
	    //    List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
	        for (Sanpham ent : eList) {
	            TableSP.add(ent);
	        }
	        return TableSP;
	    }
	   
	   void ReloadSANPHAM() {
	    	initialize1();
	    	getSanpham();
	    
	    }

	    void initialize1() {
	    	tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
	        masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
	        //loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
	        donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
	        giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
	        donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
	        tableSP.setItems(getSanpham());
			Timkiem();
	    }
	    
/////////////////////////////AUTHOR :HỒNG THÁI/////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : THỐNG KÊ ///////////*************************
///////////////////
	    

		 
/////////////////////////////AUTHOR :TRƯƠNG NGUYỄN YẾN NHI /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : KIỂM TRA NHẬP HÀNG  ///////////*************************
///////////////////

 
/////////////////////////////AUTHOR :TRƯƠNG NGUYỄN YẾN NHI /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : DANH MỤC  ///////////*************************
///////////////////
	    
	    @FXML
	    private Label lbTitleStore;
	 
	@FXML
    private Tab tabDanhMuc;
	
	@FXML
    private ImageView logo;
	
    @FXML
    private ImageView avata;

    @FXML
    private TabPane tabPaneQLDM;
    
    @FXML
    private TabPane tabPaneid;
	
	/*
	 * 
	 * --------------------------FXML Danh Mục Phiếu Hóa Đơn------------------------- 
	 * 
	 */
	
    @FXML
    private Tab sTab_PhieuHoaDon;


    @FXML
    private TableColumn mahoadon;


    @FXML
    private TableColumn thoigianmua;

    @FXML
    private TableColumn  tonggia;

    @FXML
    private TableColumn  makh;

    @FXML
    private TableColumn  manv;

    @FXML
    private TextField searchPHD;

  

    @FXML
    private Label lbDanhMucPHD;
 
    @FXML
    private ScrollBar verticalPHD;
    
    
    @FXML
    private TableView <Hoadon> tableHoaDon;
    
     ObservableList<Hoadon> listPHD;
    
     
     //HÓA ĐƠN
     public ObservableList <Hoadon> getHoadon() {
 		ObservableList <Hoadon> tableHoaDon = FXCollections.observableArrayList();
 		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
 				.build();
 		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
 		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
 		Session session = sessionFactory.openSession();

 		CriteriaQuery <Hoadon> hd= session.getCriteriaBuilder().createQuery(Hoadon.class);
 		hd.from(Hoadon.class);
 		List<Hoadon> eList = session.createQuery(hd).getResultList();
 		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
 		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
 		for (Hoadon ent : eList) {
 			tableHoaDon.add(ent);
 		}
 		return tableHoaDon;		
 	}
     
     void searchPHD() {   
     	ObservableList<Hoadon> tbHoaDon = FXCollections.observableArrayList(getHoadon());
     	
         FilteredList<Hoadon> filteredData = new FilteredList<>(tbHoaDon, b -> true);  
         searchPHD.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(hoadon -> {
            if (newValue == null || newValue.isEmpty()) {
             return true;
            }    
            String lowerCaseFilter = newValue.toLowerCase();
            
            if (hoadon.getMahoadon().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
             return true; // Filter matches username
            } 
            else if (hoadon.getThoigianmua().toLowerCase().indexOf(lowerCaseFilter) != -1) {
             return true; // Filter matches password
            }               
                 else  
                  return false; // Does not match.
           });
          });  
         
      
 		SortedList<Hoadon> sortedData = new SortedList<>(filteredData);
 		sortedData.comparatorProperty().bind(tableHoaDon.comparatorProperty());
 		tableHoaDon.setItems(sortedData);  
     	        
     	    }    
    
    
    
    /*
     * 
     *--------------------- FXML Danh mục phiếu đặt hàng-----------------------------
     * 
     */
    
    
    
     @FXML
     private Tab sTab_PhieuDatHang;

     @FXML
     private Label lbDanhMucPDH;

     @FXML
     private TextField searchPDH;

     @FXML
     private Button btnSearchPDH;

     @FXML
     private TableView<Phieudathang> tablePhieuDatHang;

     @FXML
     private TableColumn madathang;

     @FXML
     private TableColumn mancc;
     
     @FXML
     private TableColumn thoigiandat;

     @FXML
     private TableColumn tongtien;

     @FXML
     private ScrollBar verticalPDH;
     
     
     ObservableList<Phieudathang> listPDH;
     
     @FXML
     void searchPDH() {   
      	ObservableList<Phieudathang> tbPhieuDatHang = FXCollections.observableArrayList(getPhieudathang());
      	
          FilteredList<Phieudathang> filteredData = new FilteredList<>(tbPhieuDatHang, b -> true);  
          searchPDH.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(phieudathang -> {
             if (newValue == null || newValue.isEmpty()) {
              return true;
             }    
             String lowerCaseFilter = newValue.toLowerCase();
             
             if (phieudathang.getMadathang().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
              return true; // Filter matches username
             } 
             else if (phieudathang.getThoigiandat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
              return true; // Filter matches password
             }               
                  else  
                   return false; // Does not match.
            });
           });  
          
       
  		SortedList<Phieudathang> sortedData = new SortedList<>(filteredData);
  		sortedData.comparatorProperty().bind(tablePhieuDatHang.comparatorProperty());
  		tablePhieuDatHang.setItems(sortedData);  
      	        
      	    }    
     
     
     
     //PHIẾU ĐẶT HÀNG 
     public ObservableList <Phieudathang> getPhieudathang() {
 		ObservableList <Phieudathang> tablePhieuDatHang = FXCollections.observableArrayList();
 		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
 				.build();
 		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
 		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
 		Session session = sessionFactory.openSession();

 		CriteriaQuery <Phieudathang> pdh= session.getCriteriaBuilder().createQuery(Phieudathang.class);
 		pdh.from(Phieudathang.class);
 		List<Phieudathang> eList = session.createQuery(pdh).getResultList();
 		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
 		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
 		for (Phieudathang ent : eList) {
 			tablePhieuDatHang.add(ent);
 		}
 		return tablePhieuDatHang;		
 	}
     
     
     /*
      * 
      *--------------------- FXML Danh mục phiếu nhập hàng-----------------------------
      * 
      */
     @FXML
     private Tab sTab_PhieuNhapHang;

     @FXML
     private Label lbDanhMucPNH;

     @FXML
     private TextField searchPNH;

     @FXML
     private Button btnSearchPNH;

     @FXML
     private TableView<Phieunhaphang> tablePhieuNhapHang;

     @FXML
     private TableColumn manhaphang;
    
     @FXML
     private TableColumn thoigiannhap;

     @FXML
     private ScrollBar verticalPNH;
     
     ObservableList<Phieunhaphang> listPNH;
     @FXML
     void searchPNH() {   
      	ObservableList<Phieunhaphang> tbPhieuNhapHang = FXCollections.observableArrayList(getPhieunhaphang());
      	
          FilteredList<Phieunhaphang> filteredData = new FilteredList<>(tbPhieuNhapHang, b -> true);  
          searchPNH.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(phieunhaphang -> {
             if (newValue == null || newValue.isEmpty()) {
              return true;
             }    
             String lowerCaseFilter = newValue.toLowerCase();
             
             if (phieunhaphang.getManhaphang().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
              return true; // Filter matches username
             } 
             else if (phieunhaphang.getThoigiannhap().toLowerCase().indexOf(lowerCaseFilter) != -1) {
              return true; // Filter matches password
             }               
                  else  
                   return false; // Does not match.
            });
           });  
          
       
  		SortedList<Phieunhaphang> sortedData = new SortedList<>(filteredData);
  		sortedData.comparatorProperty().bind(tablePhieuNhapHang.comparatorProperty());
  		tablePhieuNhapHang.setItems(sortedData);  
      	        
     }
     
     public ObservableList <Phieunhaphang> getPhieunhaphang() {
  		ObservableList <Phieunhaphang> tablePhieuNhapHang = FXCollections.observableArrayList();
  		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
  				.build();
  		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
  		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
  		Session session = sessionFactory.openSession();

  		CriteriaQuery <Phieunhaphang> pnh= session.getCriteriaBuilder().createQuery(Phieunhaphang.class);
  		pnh.from(Phieunhaphang.class);
  		List<Phieunhaphang> eList = session.createQuery(pnh).getResultList();
  		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
  		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
  		for (Phieunhaphang ent : eList) {
  			tablePhieuNhapHang.add(ent);
  		}
  		return tablePhieuNhapHang;		
  	}
 	
     
     
     /*
      * 
      *--------------------- FXML Danh mục phiếu trả hàng-----------------------------
      * 
      */
   
     @FXML
     private Tab sTab_PhieuTraHang;

     @FXML
     private Button btnSearchPTH;

     @FXML
     private TableView<Phieutrahang> tablePhieuTraHang;

     @FXML
     private TableColumn maphieutra;

     @FXML
     private TableColumn lido;
     
     @FXML
     private TableColumn thoigiantra;

     @FXML
     private TextField searchPTH;

     @FXML
     private Label lbDanhMucPTH;

     @FXML
     private ScrollBar verticalPTH;
     
     ObservableList<Phieutrahang> listPTH;

     
     @FXML
     void searchPTH() {   
       	ObservableList<Phieutrahang> tbPhieuTraHang = FXCollections.observableArrayList(getPhieutrahang());
       	
           FilteredList<Phieutrahang> filteredData = new FilteredList<>(tbPhieuTraHang, b -> true);  
           searchPTH.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(phieutrahang -> {
              if (newValue == null || newValue.isEmpty()) {
               return true;
              }    
              String lowerCaseFilter = newValue.toLowerCase();
              
              if (phieutrahang.getMaphieutra().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
               return true; // Filter matches username
              } 
              else if (phieutrahang.getThoigiantra().toLowerCase().indexOf(lowerCaseFilter) != -1) {
               return true; // Filter matches password
              }               
                   else  
                    return false; // Does not match.
             });
            });  
           
        
   		SortedList<Phieutrahang> sortedData = new SortedList<>(filteredData);
   		sortedData.comparatorProperty().bind(tablePhieuTraHang.comparatorProperty());
   		tablePhieuTraHang.setItems(sortedData);  
       	        
      }

    @FXML
    private Text txtTitle_store;
   
    
  //PHIẾU TRẢ HÀNG
    public ObservableList <Phieutrahang> getPhieutrahang() {
		ObservableList <Phieutrahang> tablePhieuTraHang = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery <Phieutrahang> pth= session.getCriteriaBuilder().createQuery(Phieutrahang.class);
		pth.from(Phieutrahang.class);
		List<Phieutrahang> eList = session.createQuery(pth).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Phieutrahang ent : eList) {
			tablePhieuTraHang.add(ent);
		}
		return tablePhieuTraHang;		
	}
  // NHÀ CUNG CẤP
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
	    
	    
	    
/////////////////////////////AUTHOR :LÊ QUANG SANG /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : NHÀ CUNG CẤP  ///////////*************************
///////////////////
    @FXML
    private Button thanhtoancongno;

    @FXML
    private Button taonhacungcap;
    
    @FXML
    private TableView<Nhacungcap> Nhacungcap;

    @FXML
    private TableColumn mancc1;

    @FXML
    private TableColumn tenncc;

    @FXML
    private TableColumn diachi1;

    @FXML
    private TableColumn sotienno;

    @FXML
    private TableColumn email;

    @FXML
    private TableColumn sodienthoai;

    @FXML
    private TableColumn thoigianno;
    
    @FXML
    private TextField tfncc;

    @FXML
    private TextField tftenncc;

    @FXML
    private TextField tfsdt;

    @FXML
    private TextField tfdiachi1;

    @FXML
    private TextField tfemail;

    @FXML
    private Button idaddncc;
    
    @FXML
    private ComboBox<String> cbb;
    
    @FXML
    void addncc(ActionEvent event) {
    	ObservableList<Nhacungcap> Tablencc= FXCollections.observableArrayList(getNhacungcap());
    	 // 	ta.setText("");
    	 
    	Integer mancc = Integer.parseInt(tfncc.getText());
    	String tenncc = tftenncc.getText();
    	String diachi = tfdiachi1.getText();
    	Integer sodienthoai = Integer.parseInt(tfsdt.getText());
    	String email = tfemail.getText();
    
    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		Nhacungcap ncc = new Nhacungcap(mancc,tenncc,diachi,sodienthoai,email);
	//	person=session.get(Person.class, t1);
		try {
			session.beginTransaction();
			session.save(ncc);
			session.getTransaction().commit();	
	//		ta.appendText("Them Thanh Cong  ! ! !");
			ReloadSANPHAM();
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
	//		ta.appendText("Khong the thuc hien thao tac ! ");
		}
    }
   
    @FXML
    void Taonhacungcap(ActionEvent event)  {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taonhacungcap.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.setTitle("Tạo nhà cung cấp");
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
    

/////////////////////////////AUTHOR :LÊ QUANG SANG /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : SẢN PHẨM  ///////////*************************
///////////////////
	
    	    
/////////////////////////////AUTHOR :TỪ CHÍ HUY/////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : BÁN HÀNG  ///////////*************************
///////////////////


	    
	    
	 
	 
	 
	 
	
	 

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	public void initialize(URL url, ResourceBundle rb)  {
		//QL NHÂN VIÊN //HOÀNG TÂN 
    	ObservableList<String> list=FXCollections.observableArrayList("Danh sách nhân viên ","Lịch làm");
    	Listnhanvien.setItems(list);
    	Listnhanvien.getSelectionModel().select("Danh sách nhân viên");
    	initializeNHANVIEN();
    	setCellValueFromTabletoTexfField();
    	search();
    	
    	//QL KHO//HỒNG THÁI
    	tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
        masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
        //loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
        donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
        giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
        donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
        tableSP.setItems(getSanpham());
		Timkiem();
		//QL danh mục phiếu hóa đơn //Nhi
		mahoadon.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("mahoadon"));
		thoigianmua.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("thoigianmua"));
		tonggia.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("tonggia"));
		makh.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("makh"));
		manv.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("manv"));
		tableHoaDon.setItems(getHoadon());
	 	searchPHD();	
	 	
	 	//QL danh mục phiếu đặt hàng //Nhi
	 	madathang.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("madathang"));
	 	thoigiandat.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("thoigiandat"));
	 	tongtien.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("tongtien"));
	 	mancc.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("mancc"));
	 	manv.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("manv"));
    	tablePhieuDatHang.setItems(getPhieudathang());
    	searchPDH();
    	
    	//QL danh mục phiếu nhập hàng // Nhi
    	manhaphang.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, String>("manhaphang"));
	 	thoigiannhap.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, String>("thoigiannhap"));
	 	tongtien.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("tongtien"));
	 	mancc.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("mancc"));
	 	manv.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("manv"));
    	tablePhieuNhapHang.setItems(getPhieunhaphang());
    	searchPNH();
    	
    	//QL danh mục phiếu trả hàng //Nhi
    	maphieutra.setCellValueFactory(new PropertyValueFactory<Phieutrahang, String>("maphieutra"));
    	thoigiantra.setCellValueFactory(new PropertyValueFactory<Phieutrahang, String>("thoigiantra"));
    	mancc.setCellValueFactory(new PropertyValueFactory<Phieutrahang, Integer>("mancc"));
    	manv.setCellValueFactory(new PropertyValueFactory<Phieutrahang, Integer>("manv"));
    	tablePhieuTraHang.setItems(getPhieutrahang());
    	searchPTH();
    	
    	
    	// QL nhà cung cấp //Sang
    	mancc1.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("mancc"));
        tenncc.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("tenncc"));
        diachi1.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("diachi"));
        sodienthoai.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("sodienthoai"));
       // sotienno.setCellValueFactory(new PropertyValueFactory<Nocong, Integer>("sotienno"));
       // thoigianno.setCellValueFactory(new PropertyValueFactory<Nocong, Integer>("thoigianno"));
        email.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("email"));
        Nhacungcap.setItems(getNhacungcap());
    }
	
	

	
	
	

}

