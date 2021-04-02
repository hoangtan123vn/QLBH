package QLBH;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class NhapHangController{

    @FXML
    private TableColumn masanpham;

    @FXML
    private TableColumn tensanpham;

    @FXML
    private TableColumn soluongnhap;

    @FXML
    private TableColumn donvitinh;

    @FXML
    private TableColumn dongia;

    @FXML
    private Label idphieunhaphang;
    
    @FXML
    private TableView<Sanpham> ListSP;
    

}
