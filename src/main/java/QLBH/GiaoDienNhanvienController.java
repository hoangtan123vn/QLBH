package QLBH;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import entities.*;
import BanHang.BanHangController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GiaoDienNhanvienController{

    @FXML
    private Button banhang;

    @FXML
    private Button danhmuc;

    @FXML
    private Button khachhang;

    @FXML
    private Label username;

    @FXML
    private ImageView imgnhanvien;

    @FXML
    private Button dangxuat;

    @FXML
    private AnchorPane mainpane;

    @FXML
    private ImageView load;

    @FXML
    private ImageView close;

    @FXML
    private ImageView minimize;

    @FXML
	void DangXuat(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginform.fxml"));
		Parent tmp;
		tmp = loader.load();
		Scene scene = new Scene(tmp);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

    
    public void LoadData(Taikhoannv taikhoan) throws Exception {
    	username.setText("Xin chào " + taikhoan.getusername());
		username.setUnderline(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/lichsubanhang.fxml"));
		AnchorPane pane = loader.load();
		mainpane.getChildren().setAll(pane);
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
		
		khachhang.setOnMouseClicked(event -> {
			SceneKhachHang();
		});
		danhmuc.setOnMouseClicked(event -> {
			SceneLichSuBanHang();
		});
    }
    
    public void SceneBanHang(Taikhoannv taikhoan) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/BanHang/banhang.fxml"));
			AnchorPane pane = loader.load();
			mainpane.getChildren().setAll(pane);
			BanHangController banHangController = loader.getController();
			banHangController.loadData(taikhoan);
			System.out.println(taikhoan);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
    
    public void SceneKhachHang() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/KhachHang/khachhang.fxml"));
			AnchorPane pane = loader.load();
			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}
	}
    
    public void profileNhanvien(Taikhoannv taikhoan) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilenhanvien.fxml"));
			Parent tmp;
			tmp = loader.load();
			Scene scene = new Scene(tmp);
			Stage stage = new Stage();
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
    public void SceneLichSuBanHang() {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/lichsubanhang.fxml"));
			AnchorPane pane = loader.load();
			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e);
			
		}
    }

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

}