package KiemTraHang;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import Nhacungcap.nhacungcapController;
import QLBH.Chitiethoadon;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.KhachHang;
import QLBH.Nhacungcap;
import QLBH.Phieudathang;
import QLBH.Sanpham;
import QLBH.Taikhoannv;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class KiemTraHangController implements Initializable {

	public static KiemTraHangController instance;

	public KiemTraHangController() {
		instance = this;
	}

	public static KiemTraHangController getInstance() {
		return instance;
	}
	
	@FXML
	private TableView<Phieudathang> phieudathangkt;
	@FXML
	private TableColumn madathangkt;

	@FXML
	private TableColumn thoigiandatkt;
	
	@FXML
	private Label thongbaoKT;

	@FXML
	private TableColumn tongtienkt;
	
    @FXML
	 private TableColumn<Phieudathang,Boolean> select;

	@FXML
	private TableColumn<Phieudathang, Nhacungcap> mancckt;
	
	 @FXML
	private Button chitiet;


	ObservableList<Phieudathang> listPDHkt;
	
	@FXML
    private AnchorPane ap;
	
	public void falsedisable() {
		ap.setDisable(false);
	}
	public void truedisable() {
		ap.setDisable(true);
	}

	public ObservableList<Phieudathang> getPhieudathangkt() {
		ObservableList<Phieudathang> phieudathangkt = FXCollections.observableArrayList();
		Session session = HibernateUtils.getSessionFactory().openSession();
		CriteriaQuery<Phieudathang> pdhkt = session.getCriteriaBuilder().createQuery(Phieudathang.class);
		pdhkt.from(Phieudathang.class);
		List<Phieudathang> eList = session.createQuery(pdhkt).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Phieudathang ent : eList) {
			phieudathangkt.add(ent);
		}
		return phieudathangkt;
	}


	public void loadData(Taikhoannv taikhoan) {
		chitiet.setOnMouseClicked(event ->  {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NhapHangvaTraHang.fxml"));
				Parent kiemtraViewParent = loader.load();
				Stage stage1 = new Stage();
				Scene scene1 = new Scene(kiemtraViewParent);
				Phieudathang selected = phieudathangkt.getSelectionModel().getSelectedItem();
				NhapHangvaTraHang Dathang1 = loader.getController();
		//		Dathang1.setKiemtrahang(selected);
				if(selected == null) {
					 thongbaoKT.setVisible(true);
					 thongbaoKT.setText("Không có phiếu đặt hàng được chọn!!!");
					 System.out.print("Không có phiếu đặt hàng được chọn!!!");
					 return;
				 }
				 else if(selected != null){
					 Dathang1.setKiemtrahang(selected);
					 thongbaoKT.setVisible(false);
					// truedisable();
					 
					 
					 
				 }
				Dathang1.loadData(taikhoan);
				stage1.setTitle("Kiem tra hang");
				stage1.setScene(scene1);
				stage1.show();
			}catch (Exception e) {
				// TODO: handle exception
			}
		});
		
	}
	
	public void khoitao() {
		// TODO Auto-generated method stub
				//phieudathangkt.setEditable(true);
				madathangkt.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("madathang"));
				thoigiandatkt.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("thoigian"));
				tongtienkt.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("tongtien"));
				mancckt.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
				mancckt.setCellFactory(phieudathangkt -> new TableCell<Phieudathang, Nhacungcap>() {
					@Override
					protected void updateItem(Nhacungcap item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
						} else {
							setText(String.valueOf(item.getMancc()));
						}
					}

				});
				
				 
				  
				//final TableColumn<Os, Boolean> loadedColumn = new TableColumn<>( "Delete" );
				select.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getKiemtrahang()));
				select.setCellFactory(tc -> new CheckBoxTableCell<>());
			    //  select.setCellFactory( column -> new CheckBoxTableCell<>());
			     // bookMarks.addValueChangeListener(event -> contact.setBookMarks(bookMarks.getValue()));
				phieudathangkt.setItems(getPhieudathangkt());
				//phieudathangkt.setEditable(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		khoitao();
	}

}

