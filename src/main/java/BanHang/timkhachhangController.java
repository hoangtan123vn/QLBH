package BanHang;



import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//import QLBH.HoadonDetailController;
import QLBH.Hoadon;
import QLBH.chucnangnhanvienController;
import QLBH.KhachHang;
import QLBH.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class timkhachhangController implements Initializable{

    @FXML
    private TableColumn nsKH;

    @FXML
    private TableColumn diemtichluy;

    @FXML
    private TableColumn hvtKH;

    @FXML
    private TableColumn sdtKH;

    @FXML
    private TableColumn emailKH;

    @FXML
    private TableColumn idKH;

    @FXML
    private TableColumn gtKH;

    @FXML
    private TableView<KhachHang> tableKH;
    
    @FXML
    private TextField searchKH ;
    @FXML
    private Button them;
    
    @FXML
    private AnchorPane ap;
    
    private Stage thisStage;
    
    private final BanHangController banHangController;
    
    
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//masanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		idKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("makh"));
		hvtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("tenkh"));
		sdtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("sodienthoai"));
		nsKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("ngaysinh"));
		gtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("gioitinh"));
		emailKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("email"));
		diemtichluy.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("diemtichluy"));
		tableKH.setItems(getKhachHang());
		
		
		
	}
	
	public timkhachhangController(BanHangController banHangController ) {
		 this.banHangController = banHangController;

	        // Create the new stage
	        thisStage = new Stage();

	        // Load the FXML file
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("timkhachhang.fxml"));

	            // Set this class as the controller
	            loader.setController(this);

	            // Load the scene
	            thisStage.setScene(new Scene(loader.load()));

	            // Setup the window/stage
	            thisStage.setTitle("Chon khach hang");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	
	public void showStage() {
        thisStage.showAndWait();
    }
	
	

	public ObservableList<KhachHang> getKhachHang() {
		ObservableList<KhachHang> tableKH = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<KhachHang> kh = session.getCriteriaBuilder().createQuery(KhachHang.class);
		kh.from(KhachHang.class);
		List<KhachHang> eList = session.createQuery(kh).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (KhachHang ent : eList) {
			tableKH.add(ent);
		}
		return tableKH;
	}
	void searchKH() {
		ObservableList<KhachHang> table = FXCollections.observableArrayList(getKhachHang());
		FilteredList<KhachHang> filterData = new FilteredList<>(table, p -> true);
		searchKH.textProperty().addListener((observable, oldvalue, newvalue) -> {
			filterData.setPredicate(kh -> {
				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}
				String typetext = newvalue.toLowerCase();
				if (kh.getTenkh().toLowerCase().indexOf(typetext) != -1) {
					return true;
				}
				// if(kh.getSodienthoai().toLowerCase().indexOf(typetext) !=-1) {
				// return true;
				// }
				return false;

			});
			SortedList<KhachHang> sortedList = new SortedList<>(filterData);
			sortedList.comparatorProperty().bind(tableKH.comparatorProperty());
			tableKH.setItems(sortedList);
		});
	}
	//

	 
	 @FXML
	 void them(ActionEvent event) throws IOException  {
//		 	System.out.print("111");
		//    FXMLLoader loader = new FXMLLoader(getClass().getResource("chucnangnhanvien.fxml"));
		   // root= loader.load();
		//	Parent hoadonViewParent = loader.load();
			//Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
		//	Stage stage = new Stage();
		//	Scene scene = new Scene(hoadonViewParent);
	/*	 FXMLLoader loader = new FXMLLoader();  
		 loader.setLocation(getClass().getResource("chucnangnhanvien.fxml"));
		 Parent root = loader.load();
		 chucnangnhanvienController nv = loader.getController();
		 KhachHang selected = tableKH.getSelectionModel().getSelectedItem();
		 nv.setkhachhang(selected);
		
			/*chucnangnhanvienController Controller = loader.getController();
			Controller.setkhachhang(selected);
		
			Stage stage1 = (Stage) ap.getScene().getWindow();
       	 	stage1.close();*/
			//Controller.setStageAnd
			/*stage.setTitle("Chi tiet hoa don");
			stage.setScene(scene);
			stage.show();*/
		  	//Stage stage = (Stage) back.getScene().getWindow();
	        //stage.close();*/
		 
		 
		 
		 KhachHang selected = tableKH.getSelectionModel().getSelectedItem();
		 banHangController.setkhachhang(selected);
		 Stage stage1 = (Stage) ap.getScene().getWindow();
    	 stage1.close();

	 }
	
}

