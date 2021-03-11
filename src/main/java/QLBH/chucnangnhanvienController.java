package QLBH;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
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
import QLBH.Nhanvien;
import QLBH.Hoadon;
import QLBH.Phieudathang;
import QLBH.Phieunhaphang;
import QLBH.Phieutrahang;

public class chucnangnhanvienController {

    @FXML
    private TabPane tabPaneQLDM;

    @FXML
    private Tab sTab_PhieuHoaDon;

    @FXML
    private TableView<?> tableHoaDon;

    @FXML
    private TableColumn<?, ?> mahoadon;

    @FXML
    private TableColumn<?, ?> thoigianmua;

    @FXML
    private TableColumn<?, ?> tonggia;

    @FXML
    private TableColumn<?, ?> makh;

    @FXML
    private TableColumn<?, ?> manv1;

    @FXML
    private TextField searchPHD;

    @FXML
    private Label lbDanhMucPHD;

    @FXML
    private ScrollBar verticalPHD;

    @FXML
    private Tab sTab_PhieuDatHang;

    @FXML
    private Label lbDanhMucPDH;

    @FXML
    private TextField searchPDH;

    @FXML
    private Button btnSearchPDH;

    @FXML
    private TableView<?> tablePhieuDatHang;

    @FXML
    private TableColumn<?, ?> madathang;

    @FXML
    private TableColumn<?, ?> mancc;

    @FXML
    private TableColumn<?, ?> thoigiandat;

    @FXML
    private TableColumn<?, ?> manv;

    @FXML
    private TableColumn<?, ?> tongtien;

    @FXML
    private ScrollBar verticalPDH;

    @FXML
    private Tab sTab_PhieuNhapHang;

    @FXML
    private Label lbDanhMucPNH;

    @FXML
    private TextField searchPNH;

    @FXML
    private Button btnSearchPNH;

    @FXML
    private TableView<?> tablePhieuNhapHang;

    @FXML
    private TableColumn<?, ?> manhaphang;

    @FXML
    private TableColumn<?, ?> mancc2;

    @FXML
    private TableColumn<?, ?> thoigiannhap;

    @FXML
    private TableColumn<?, ?> manv2;

    @FXML
    private ScrollBar verticalPNH;

    @FXML
    private Tab sTab_PhieuTraHang;

    @FXML
    private Button btnSearchPTH;

    @FXML
    private TableView<?> tablePhieuTraHang;

    @FXML
    private TableColumn<?, ?> maphieutra;

    @FXML
    private TableColumn<?, ?> mancc3;

    @FXML
    private TableColumn<?, ?> thoigiantra;

    @FXML
    private TableColumn<?, ?> lido;

    @FXML
    private TextField searchPTH;

    @FXML
    private Label lbDanhMucPTH;

    @FXML
    private ScrollBar verticalPTH;

    @FXML
    private Text txtTitle_store;

    @FXML
    private Label lbTitleStore;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView avata;

    @FXML
    private TextField searchKH;

    @FXML
    private TableView<?> tableKH;

    @FXML
    private TableColumn<?, ?> idKH;

    @FXML
    private TableColumn<?, ?> hvtKH;

    @FXML
    private TableColumn<?, ?> sdtKH;

    @FXML
    private TableColumn<?, ?> nsKH;

    @FXML
    private TableColumn<?, ?> gtKH;

    @FXML
    private TableColumn<?, ?> diemtichluy;

    @FXML
    private TableColumn<?, ?> emailKH;

    @FXML
    void changeSceneHoadonDetail(ActionEvent event) {

    }

    @FXML
    void searchPDH(ActionEvent event) {

    }

    @FXML
    void searchPNH(ActionEvent event) {

    }

    @FXML
    void searchPTH(ActionEvent event) {

    }

}
