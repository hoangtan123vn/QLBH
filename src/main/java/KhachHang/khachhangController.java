package KhachHang;

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

import entities.*;
import QLBH.HibernateUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class khachhangController implements Initializable {

	@FXML
	private TextField searchKH;

	@FXML
	private TableColumn idKH;

	@FXML
	private TableColumn hvtKH;

	@FXML
	private TableColumn sdtKH;

	@FXML
	private TableColumn nsKH;

	@FXML
	private TableColumn gtKH;

	@FXML
	private TableColumn diemtichluy;

	@FXML
	private TableColumn emailKH;

	@FXML
	private TableView<KhachHang> tableKH;

    //LICH SU MUA HANG
    @FXML
    private TableView<Hoadon> tb_LSMH;

    @FXML
    private TableColumn id_HoaDon;

    @FXML
    private TableColumn thoigianmua;

    @FXML
    private TableColumn soluong;

    @FXML
    private TableColumn tongtien;
    
    @FXML
    private Label thongbao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SearchLichSuBanHang();
		idKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("makh"));
		hvtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("tenkh"));
		// loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
		sdtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("sodienthoai"));
		nsKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("ngaysinh"));
		gtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("gioitinh"));
		diemtichluy.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("diemtichluy"));
		diemtichluy.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		diemtichluy.setOnEditCommit(new EventHandler<CellEditEvent<KhachHang,Integer>>() {
			@Override
			public void handle(CellEditEvent<KhachHang, Integer> event) {
				Session session = HibernateUtils.getSessionFactory().openSession();
				session.beginTransaction();
				KhachHang khachHang = new KhachHang();
			//	Nhacungcap person = new Nhacungcap();
				khachHang = event.getRowValue();
				khachHang = session.get(KhachHang.class, khachHang.getMakh());
				khachHang.setDiemtichluy(event.getNewValue());
				session.save(khachHang);
				session.getTransaction().commit();
				//ReloadNHACUNGCAP();
			}
		});
		emailKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("email"));
		tableKH.setItems(getKhachHang());
		searchKH();
		
		id_HoaDon.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("mahoadon"));
		thoigianmua.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("thoigianmua"));
		//soluong.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("thoigianmua"));
		tongtien.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("tonggia"));
		tb_LSMH.setItems(null);
		tableKH.setEditable(true);
		
	}
	
	private void SearchLichSuBanHang()  {
		 tableKH.setOnMouseClicked(event -> {
			 event();
			 if(event == null) {
				 System.out.println("Thành công");
			 }
		 });
	 }
	
	public void reloadKH() {
		idKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("makh"));
		hvtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("tenkh"));
		// loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
		sdtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("sodienthoai"));
		nsKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("ngaysinh"));
		gtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("gioitinh"));
		diemtichluy.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("diemtichluy"));
		tableKH.setItems(getKhachHang());
	}
	
	private void event() {
		if(tableKH.getSelectionModel().getSelectedIndex() == -1) {
			thongbao.setText("Chưa có khách hàng nào được chọn");
			return;
		}
		else {
		thongbao.setText(null);
		KhachHang khachHang = tableKH.getItems().get(tableKH.getSelectionModel().getSelectedIndex());
		//KhachHang khachHang = tableKH.getItems().get(tableKH.getSelectionModel().getSelectedIndex());
		int makh = khachHang.getMakh();
	//	int mahoadon = hoadon.getMahoadon();
    	ObservableList<Hoadon> TableHD = FXCollections.observableArrayList();
    	 Session session = HibernateUtils.getSessionFactory().openSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Hoadon> query = builder.createQuery(Hoadon.class);
			Root<Hoadon> root = query.from(Hoadon.class); // FROM
			Join<Hoadon, KhachHang> KhachHangJoin = root.join("khachhang", JoinType.INNER);
			//Join<Chitiethoadon, Hoadon> HoadonJoin = root.join("hoadon",JoinType.INNER);
			query.where(builder.equal(KhachHangJoin.get("makh"), makh));
			//WHERE HOADON.MAHOADON = 
			List<Hoadon> cthd = session.createQuery(query).getResultList();
		 for(Hoadon b : cthd) {
			 TableHD.add(b);
			 System.out.println(b);
		 }
		 tb_LSMH.setItems(TableHD);
		
	}
	}

	
	
	
	
	public ObservableList<KhachHang> getKhachHang() {
		ObservableList<KhachHang> TableKH = FXCollections.observableArrayList();
		Session session = HibernateUtils.getSessionFactory().openSession();

		CriteriaQuery<KhachHang> kh = session.getCriteriaBuilder().createQuery(KhachHang.class);
		kh.from(KhachHang.class);
		List<KhachHang> eList = session.createQuery(kh).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (KhachHang ent : eList) {
			TableKH.add(ent);
		}
		return TableKH;

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
				if (kh.getSodienthoai().toLowerCase().indexOf(typetext) != -1) {
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

}

