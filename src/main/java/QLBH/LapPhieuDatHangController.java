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
import javafx.scene.control.Labeled;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

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
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Sanpham;

public class LapPhieuDatHangController implements Initializable{
	
	@FXML
	private Button huy;
	
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
	private TableColumn loaisanpham;
	
	@FXML
	private TableColumn donvitinh1;

	@FXML
	private TableColumn donvi1;
	
	@FXML
	private TableColumn thanhtien;

	@FXML
	private TableColumn giatien1;
	
	@FXML
	private TableColumn tensanpham1;

	@FXML	
	private TableColumn masanpham1;
	
	@FXML	
	private TableColumn loaisanpham1;
	
	@FXML	
	private TableColumn xoaSP;
	
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
	private TextField tf6;
	
	@FXML
	private TextField tf7;
	
	@FXML
	private TextField tftongtien;

	@FXML
	private TextField Timkiem;
	
	 @FXML
    private ComboBox<Integer> cbnhacungcap;
	 
	 @FXML
	 private Button idDatHang;
	
	 
	
	ObservableList<Sanpham> table = FXCollections.observableArrayList(getSanpham());

	@FXML
	private TableView<Sanpham> tableSP;
	
	@FXML
	private TableView<Sanpham> tableSP1;
	
	@FXML
	void CanCel(ActionEvent event) {
		Stage stage = (Stage) huy.getScene().getWindow();
   	 stage.close();
	}
	void Timkiem() {
		ObservableList<Sanpham> TableSP = FXCollections.observableArrayList(getSanpham());

		FilteredList<Sanpham> filteredData = new FilteredList<>(TableSP, b -> true);
		Timkiem.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(sanpham -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (sanpham.getTensanpham().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
				} else if (sanpham.getDonvi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				} else
					return false; // Does not match.
			});
		});
		SortedList<Sanpham> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableSP.comparatorProperty());
		tableSP.setItems(sortedData);

	}
	public ObservableList<Sanpham> getSanpham() {
		ObservableList<Sanpham> TableSP = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Sanpham> sp = session.getCriteriaBuilder().createQuery(Sanpham.class);
		sp.from(Sanpham.class);
		List<Sanpham> eList = session.createQuery(sp).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Sanpham ent : eList) {
			TableSP.add(ent);
		}
		return TableSP;
	}

	void ReloadSANPHAM() {
		getSanpham();

	}
	 private void setCellValueFromTabletoTexfFieldd()  {
		 tableSP.setOnMouseClicked(event -> {
			 //
			 Sanpham sp = tableSP.getItems().get(tableSP.getSelectionModel().getSelectedIndex());
			 tf1.setText(sp.getTensanpham());
			 tf2.setText(Integer.toString(sp.getMasanpham()));
			 tf3.setText(sp.getDonvi());
			 tf5.setText(Integer.toString(sp.getGiatien()));
			 //tf4.setText(sp.getDonvitinh()); 
			 tf6.setText(sp.getLoaisanpham());
		 });
	 }
	 public ObservableList<Integer> getNhaCungCap() {
		 ObservableList<Integer> tbNCC = FXCollections.observableArrayList();
		 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
		 Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
		 session.getTransaction().begin();
		 String hql = "SELECT P.mancc FROM Nhacungcap P";
		 Query query = session.createQuery(hql);
		 ArrayList<Integer> listnhcc = (ArrayList) query.list();
		 for(int t1 : listnhcc) {
			 System.out.println(t1);
			 tbNCC.add(t1);
		 }
		 return tbNCC;				 
	 }	
	
	 private void thanhtien() {
	      
		 	//thanhtien = soluong1*giatien1
	        
	            }
	 private void ButtonXoaSP() {
	      

	        Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>> cellFactory = new Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>>() {
	            @Override
	            public TableCell<Sanpham, Void> call(final TableColumn<Sanpham, Void> param) {
	                final TableCell<Sanpham, Void> cell = new TableCell<Sanpham, Void>() {

	                    private final Button btn = new Button("Xóa");

	                    {
	                    	///////////////////////////
	                    btn.setOnAction((ActionEvent event) -> {
	                    	 Sanpham sp = getTableView().getItems().get(getIndex());
	                    	 if(tableSP1.getItems().contains(sp)) {
	               			 sp.setDonvitinh(sp.getDonvitinh()-sp.getDonvitinh());
	               			 tableSP1.refresh();
	               			 if(sp.getDonvitinh()==0) {
	               				 tableSP1.getItems().remove(sp);
	               				 tableSP1.refresh();
	               			 }
	               		 }
	                        });
	                    }
	                    @Override
	                    public void updateItem(Void item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };
	        xoaSP.setCellFactory(cellFactory);
	 }
	 
	 
	 
	 
	 @FXML
	    void DatHang(ActionEvent event) {
		// String nhacungcap = cbnhacungcap.getValue();
		// Sanpham sp = tableSP.getItems().get(tableSP.getSelectionModel().getSelectedIndex());
		 String tensanpham1 = tf1.getText();
		 int masanpham1 = Integer.parseInt(tf2.getText());
		 String donvi1 = tf3.getText();
		 String loaisanpham1 = tf6.getText();
		 int donvitinh1 = Integer.parseInt(tf4.getText());
		 int giatien1 = Integer.parseInt(tf5.getText());
		 String thanhtien = tf7.getText();
	//	 addItem(masanpham1, tensanpham1, loaisanpham1, donvi1, giatien1, donvitinh1);
		 tableSP1.refresh();
		 Sanpham sp = new Sanpham(tensanpham1,masanpham1,donvi1,donvitinh1,giatien1,loaisanpham1);
		 tableSP1.getItems().add(sp);
	//	 System.out.println(sp);
		 int sum = 0;
         for (Sanpham thanhtien1 : tableSP1.getItems()) {
             sum = sum + (thanhtien1.getGiatien()* thanhtien1.getDonvitinh());
         }
         tftongtien.setText(String.valueOf(sum));

}	
	    
/*	 	public void addItem(int masanpham,String tensanpham, String loaisanpham, String donvi,int giatien,int donvitinh) {
		    Sanpham entry = tableSP1.getItems().stream()
		        .filter(item -> item.getTensanpham().equals(tensanpham))
		        .findAny()
		        .orElseGet(()-> {
		             Sanpham newItem = new Sanpham(masanpham,tensanpham, loaisanpham,donvi,0, 0);
		             tableSP1.getItems().add(newItem);
		             return newItem ;
		        });
		    
		    entry.setDonvitinh(entry.getDonvitinh() + donvitinh);
	 	    entry.setGiatien(entry.getGiatien() + giatien);
	 	   // TableSP.refresh();
		   // entry.s
		}*/
	 @FXML
		void xacnhan(ActionEvent event) {
			//Sanpham sp = new Sanpham(10,"A","a","s",1000,2000);
			//sp.getMasanpham();

			Alert alert = new Alert(AlertType.INFORMATION);
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			int tongtien = Integer.parseInt(tftongtien.getText());
			int mancc = cbnhacungcap.getValue();
			Nhacungcap nhacungcap = new Nhacungcap();
			nhacungcap = session.get(Nhacungcap.class, mancc);
			Phieudathang dathang = new Phieudathang(null,tongtien,nhacungcap,null);
			 try {
	    		 session.beginTransaction();
	    		 session.save(dathang);
	    	//	 hoadonn = session.get(Hoadon.class, hoadonn.getMahoadon());
	    		 //session.save(chitiethoadon);
	    		// session.save(spp);
	    		 session.getTransaction().commit();
	    		 alert.setContentText("Thêm phieu dat hang thành công !");
	    		 alert.showAndWait();   
			 }
		    	catch (RuntimeException error){
		    		System.out.println(error);
		    		 alert.setContentText("Lỗi " + error);
		    		 alert.showAndWait();
		    		session.getTransaction().rollback();
		    	}
			 for (Sanpham sp : tableSP1.getItems()) {
					Sanpham spp= new Sanpham();
					int masp = sp.getMasanpham();
					spp = session.get(Sanpham.class, masp);
					int soluong = sp.getDonvitinh();
					System.out.println(" masp : " +masp);
			 
			
			Chitietdathang chitietdathang = new Chitietdathang(dathang,spp,soluong);
			 try {
	    		 session.beginTransaction();
	    		 session.save(chitietdathang);
	    	//	 hoadonn = session.get(Hoadon.class, hoadonn.getMahoadon());
	    		 //session.save(chitiethoadon);
	    		// session.save(spp);
	    		 session.getTransaction().commit();
	    		 alert.setContentText("Thêm phieu dat hang thành công !");
	    		 alert.showAndWait();   
			 }
			 catch (RuntimeException error){
		    		System.out.println(error);
		    		 alert.setContentText("Lỗi " + error);
		    		 alert.showAndWait();
		    		session.getTransaction().rollback();
		    	
			 }	
			 }
	 }
	 	
	 public void loadData(Taikhoannv taikhoan) throws IOException{
		 
	 }
	 
	 
	 
	 
	 
	 

			
			@Override
			public void initialize(URL url, ResourceBundle rb) {
				setCellValueFromTabletoTexfFieldd();
				tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
				masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
				//loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
				donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
				giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
				donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
			//	comboboxNCC();
				
				tensanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
				masanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
				loaisanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
				donvi1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
				giatien1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
				donvitinh1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
				ObservableList<Integer> list = FXCollections.observableArrayList(getNhaCungCap());
				cbnhacungcap.setItems(list);
				tableSP.setItems(getSanpham());
				Timkiem();
				ButtonXoaSP();
				tf4.addEventHandler(KeyEvent.KEY_PRESSED, e->{
		            if(e.getCode() == KeyCode.ENTER) {
		            	
		            	float soluong1 = Float.parseFloat(tf4.getText());
		            	float giatien1 = Float.parseFloat(tf5.getText());
		            	float thanhtien = soluong1*giatien1;
		            	
		            	tf7.setText(String.valueOf(thanhtien));
			}
				});
				
			}
}

