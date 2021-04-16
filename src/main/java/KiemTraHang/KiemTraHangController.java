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

import QLBH.Chitiethoadon;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.KhachHang;
import QLBH.Nhacungcap;
import QLBH.Phieudathang;
import QLBH.Sanpham;
import QLBH.Taikhoannv;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class KiemTraHangController implements Initializable {

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
	private TableColumn<Phieudathang, Nhacungcap> mancckt;
	
	 @FXML
	private Button chitiet;


	ObservableList<Phieudathang> listPDHkt;

	public ObservableList<Phieudathang> getPhieudathangkt() {
		ObservableList<Phieudathang> phieudathangkt = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

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
				//	 Dathang.setPhieudathang(selected);
					 thongbaoKT.setVisible(false);
					 
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
		phieudathangkt.setItems(getPhieudathangkt());
	}

}

