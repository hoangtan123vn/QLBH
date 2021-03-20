package QLBH;

import java.io.IOException;
import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TrahangDetailController implements Serializable{

    @FXML
    private Label lbMaphieutra;

    @FXML
    private Label lbThoigiantra;

    @FXML
    private Label lbTongtien;

    @FXML
    private Label lbMancc;

    @FXML
    private Label lbManv;

    @FXML
    private Button backPT;

    @FXML
    private TableView tbChitietPhieuTra;

    @FXML
    private TableColumn<Chitietphieutra, Sanpham> tenhang;

    @FXML
    private TableColumn soluong;

    @FXML
    private TableColumn dongia;

    @FXML
    private TableColumn tongtien;

    @FXML
    void backPT(ActionEvent event) throws IOException {
    	Stage stage = (Stage) backPT.getScene().getWindow();
        stage.close();
    }
    
    public void setPhieutrahang(Phieutrahang phieutrahang) {
    	lbMaphieutra.setText(String.valueOf(phieutrahang.getMaphieutra()));
    	lbThoigiantra.setText(String.valueOf(phieutrahang.getThoigiantra()));
    }

}
