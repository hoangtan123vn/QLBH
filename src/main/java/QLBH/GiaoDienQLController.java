package QLBH;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.query.Query;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;
import QLBH.Nhanvien;
import BanHang.BanHangController;
import DanhMuc.HoadonDetailController;
import KhachHang.khachhangController;
import Nhanvien.NhanvienController;
import QLKho.QLKhoController;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.derby.vti.Restriction.AND;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import KiemTraHang.KiemTraHangController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GiaoDienQLController implements Initializable {

	@FXML
	private Button banhang;

	@FXML
	private Button nhanvien;

	@FXML
	private Button thongke;

	@FXML
	private Button kho;

	@FXML
	private Button kiemtrahang;

	@FXML
	private Button danhmuc;

	@FXML
	private Button nhacungcap;

	@FXML
	private Button khachhang;

	@FXML
	private AnchorPane mainpane;

	@FXML
	private Label username;

	@FXML
	private ImageView imgnhanvien;

	@FXML
	private ImageView close;

	@FXML
	private ImageView minimize;

	@FXML
	void close(MouseEvent event) {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	@FXML
	void minimize(MouseEvent event) {
		Stage stage = (Stage) minimize.getScene().getWindow();
		stage.setIconified(true);
	}

	void setNhanvien(Nhanvien nhanvien) {

	}

	@FXML
	void ChangeNhacungcap(ActionEvent event) {

	}

	@FXML
	private Button dangxuat;

	@FXML
	void ChangeNhanvien(ActionEvent event) throws IOException {
		// Scene scene = new Scene(login);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/Nhanvien/nhanvien.fxml"));
		mainpane.getChildren().setAll(pane);
		if (mainpane.getChildren().setAll(pane)) {
			// nhanvien.setClickable(false);
		}
	}

	public void LoadData(Taikhoannv taikhoan) {

		username.setText("Xin chào " + taikhoan.getusername());
		username.setUnderline(true);
		// tennhanvien.setText("Xin chao" + taikhoan.getNhanvien().getHovaten());
		byte[] getImageInBytes = taikhoan.getNhanvien().getImage();
		try {
			FileOutputStream fos = new FileOutputStream(new File("photo1.jpg"));
			fos.write(getImageInBytes);
			fos.close();
			Image image = new Image("file:photo1.jpg", imgnhanvien.getFitHeight(), imgnhanvien.getFitHeight(), true,
					true);
			imgnhanvien.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		username.setOnMouseClicked(event -> {
			profileNhanvien(taikhoan);

		});

		banhang.setOnMouseClicked(event -> {
			SceneBanHang(taikhoan);
		});

		kho.setOnMouseClicked(event -> {
			SceneQLKho(taikhoan);
		});

		khachhang.setOnMouseClicked(event -> {
			SceneKhachHang();
		});
		nhacungcap.setOnMouseClicked(event -> {
			SceneNhaCungCap();
		});
		danhmuc.setOnMouseClicked(event -> {
			SceneDanhMuc();
		});
		thongke.setOnMouseClicked(event -> {
			SceneThongKe();
		});
		kiemtrahang.setOnMouseClicked(event -> {
			SceneKiemTraHang(taikhoan);
		});
	}

	public void profileNhanvien(Taikhoannv taikhoan) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilenhanvien.fxml"));
			Parent tmp;
			tmp = loader.load();
			Scene scene = new Scene(tmp);
			Stage stage = new Stage();
			// Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
			ProfilesNhanvienController profiles = loader.getController();
			profiles.loadData(taikhoan);
			stage.hide();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SceneBanHang(Taikhoannv taikhoan) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/BanHang/banhang.fxml"));
			AnchorPane pane = loader.load();
			mainpane.getChildren().setAll(pane);
			/*
			 * if(mainpane.getChildren().setAll(pane)) { //nhanvien.setClickable(false); }
			 */
			BanHangController banHangController = loader.getController();
			banHangController.loadData(taikhoan);
			System.out.println(taikhoan);
			/*
			 * stage.hide(); stage.setScene(scene); stage.show();
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void SceneQLKho(Taikhoannv taikhoan) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/QLKho/QLKho.fxml"));
			AnchorPane pane = loader.load();

			mainpane.getChildren().setAll(pane);
			/*
			 * if(mainpane.getChildren().setAll(pane)) { //nhanvien.setClickable(false); }
			 */
			// Parent tmp;
			// tmp = loader.load();
			// Scene scene = new Scene(tmp);
			// Stage stage = new Stage();
			// Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
			QLKhoController qlKhoController = loader.getController();
			qlKhoController.loadData(taikhoan);
			System.out.println(taikhoan);
			/*
			 * stage.hide(); stage.setScene(scene); stage.show();
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	public void SceneKhachHang() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/KhachHang/khachhang.fxml"));
			AnchorPane pane = loader.load();

			mainpane.getChildren().setAll(pane);
			/*
			 * if(mainpane.getChildren().setAll(pane)) { //nhanvien.setClickable(false); }
			 */
			// Parent tmp;
			// tmp = loader.load();
			// Scene scene = new Scene(tmp);
			// Stage stage = new Stage();
			// Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
			/*
			 * khachhangController khachhangController = loader.getController();
			 * khachhangController.loadData(taikhoan); System.out.println(taikhoan);
			 */
			/*
			 * stage.hide(); stage.setScene(scene); stage.show();
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	public void SceneNhaCungCap() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nhacungcap/nhacungcap.fxml"));
			AnchorPane pane = loader.load();

			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	public void SceneDanhMuc() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/danhmuc.fxml"));
			AnchorPane pane = loader.load();

			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	public void SceneThongKe() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ThongKe/thongkedoanhthutheongay.fxml"));
			AnchorPane pane = loader.load();

			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	public void SceneKiemTraHang(Taikhoannv taikhoan) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/KiemTraHang/Kiemtrahang.fxml"));
			AnchorPane pane = loader.load();
			// KiemtrahangController ktController =
			// Hoadon selected = tableHoaDon.getSelectionModel().getSelectedItem();
			KiemTraHangController KTHang = loader.getController();
			KTHang.loadData(taikhoan);
			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void DangXuat(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginform.fxml"));
		Parent tmp;
		tmp = loader.load();
		Scene scene = new Scene(tmp);
		// Stage stage = new Stage();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void ChangeThongKe(ActionEvent event) throws IOException {

	}

	@FXML
	void changeBanHang(ActionEvent event) throws Exception {

	}

	@FXML
	void changeDanhmuc(ActionEvent event) {

	}

	@FXML
	void changeKTH(ActionEvent event) {

	}

	@FXML
	void changekho(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
