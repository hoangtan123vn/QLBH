package DanhMuc;

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
import javafx.scene.control.TableCell;
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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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
import QLBH.Chitietdathang;
import QLBH.Chitiethoadon;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.Sanpham;
import QLBH.Phieudathang;

public class DathangDetailController implements Initializable {

	@FXML
	private Label lbMadathang;

	@FXML
	private Label lbThoigiandat;

	@FXML
	private Label lbTongtien;

	@FXML
	private Label lbMancc;

	@FXML
	private Button backDH;

	@FXML
	private TableView tbChitietDatHang;

	@FXML
	private TableColumn<Chitietdathang, Sanpham> tenhang;

	@FXML
	private TableColumn soluong;

	@FXML
	private TableColumn dongia;

	@FXML
	private TableColumn tongtien;

	@FXML
	private Label lbManv;

	@FXML
	void trolai(ActionEvent e) throws IOException {
		Stage stage = (Stage) backDH.getScene().getWindow();
		stage.close();
	}

	public ObservableList<Chitietdathang> getChitietdathang(Phieudathang phieudathang) {
		int Phieudathang = phieudathang.getMadathang();
		int madathang = Integer.parseInt(lbMadathang.getText());
		ObservableList<Chitietdathang> tablePhieuDatHang = FXCollections.observableArrayList();
		Session session = HibernateUtils.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Chitietdathang> query = builder.createQuery(Chitietdathang.class);
		Root<Chitietdathang> root = query.from(Chitietdathang.class); // FROM
		Join<Chitietdathang, Sanpham> SanphamJoin = root.join("sanpham", JoinType.INNER);
		Join<Chitietdathang, Phieudathang> PhieuJoin = root.join("phieudathang", JoinType.INNER);
		query.where(builder.equal(PhieuJoin.get("madathang"), madathang));
		List<Chitietdathang> ctdh = session.createQuery(query).getResultList();
		for (Chitietdathang b : ctdh) {
			tablePhieuDatHang.add(b);
			System.out.println(b);
		}
		return tablePhieuDatHang;
	}

	public void IntitlizeChitietdathang(Phieudathang phieudathang) {
		tenhang.setCellFactory(tbChitietDatHang -> new TableCell<Chitietdathang, Sanpham>() {
			@Override
			protected void updateItem(Sanpham item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getTensanpham());
				}
			}
		});
		tenhang.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		soluong.setCellValueFactory(new PropertyValueFactory<Chitietdathang, Integer>("soluong"));
		tongtien.setCellValueFactory(new PropertyValueFactory<Chitietdathang, Double>("thanhtien"));
		dongia.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		dongia.setCellFactory(tbChitietDatHang -> new TableCell<Chitietdathang, Sanpham>() {
			@Override
			protected void updateItem(Sanpham item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(String.valueOf(item.getGiatien()));
				}
			}
		});

		tbChitietDatHang.setItems(getChitietdathang(phieudathang));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setPhieudathang(Phieudathang phieudathang) {
		lbMadathang.setText(String.valueOf((phieudathang.getMadathang())));
		lbThoigiandat.setText(String.valueOf(phieudathang.getThoigian()));
		lbTongtien.setText(String.valueOf((phieudathang.getTongtien())));
		if (phieudathang.getNhacungcap() == null) {
			lbMancc.setText("[Nhà cung cấp đã bị xóa]");
		} else {
			lbMancc.setText(phieudathang.getNhacungcap().getTenncc());
		}
		if (phieudathang.getNhanvien() == null) {
			lbManv.setText("[Nhân viên đã bị xóa]");
		} else {
			lbManv.setText(phieudathang.getNhanvien().getHovaten());
		}
		IntitlizeChitietdathang(phieudathang);
	}
}
