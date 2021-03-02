package QLBH;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.scene.control.ChoiceBox;

import java.util.List;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javax.persistence.criteria.CriteriaQuery;
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
import QLBH.Sanpham;



public class DSSPController implements  Initializable {

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
    private TextArea ta;	
    
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
    	    	ta.setText("");
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
    				ta.appendText("Them Thanh Cong  ! ! !");
    				Reload();
    			} catch (RuntimeException error) {
    				session.getTransaction().rollback();
    				ta.appendText("Khong the thuc hien thao tac ! ");
    			}
  

    }
    @FXML
    void XoaSP(ActionEvent event) {
    	ta.setText("");
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
			ta.appendText("Xoa Thanh Cong  ! ! !");
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
			ta.appendText("Khong the thuc hien thao tac ! ");
		}
    }

    @FXML
    void SuaSP(ActionEvent event) {
    	ta.setText("");
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
			ta.appendText("Update Thanh Cong  ! ! !");
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
			ta.appendText("Khong the thuc hien thao tac ! ");
		}
    }
   
   void Reload() {
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
	public void initialize(URL url, ResourceBundle rb) {
		
	//	id.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("id"));
        tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
        masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
        //loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
        donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
        giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
        donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
        tableSP.setItems(getSanpham());
		Timkiem();
		
		
	/*	id.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("idNV"));
        hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("Há»� vÃ  tÃªn"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("NgÃ y sinh"));
        chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("Chá»©c vá»¥"));
        sdt.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("Sá»‘ Ä‘iá»‡n thoáº¡i"));
        cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("CMND"));
        tableNV.setItems(getNhanvien());*/
			
		}
	/* private ObservableList<Sanpham> getSanpham() {
		 ObservableList<Sanpham> TableNV = FXCollections.observableArrayList();
	        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			
			CriteriaQuery<Sanpham> nv = session.getCriteriaBuilder().createQuery(Sanpham.class);
			nv.from(Sanpham.class);
			List<Sanpham> eList = session.createQuery(nv).getResultList();
		//	List<Sanpham> eList = session.createQuery(criteriaQuery).getResultList();
	    //    List<Sanpham> eList = session.createQuery(Nhanvien.class).list();
	        for (Sanpham ent : eList) {
	            TableSP.add(ent);
	        }
	        return (ObservableList<Sanpham>) TableSP;
	  
		// TODO Auto-generated method stub
	
	}
}*/


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

}

