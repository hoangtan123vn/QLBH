package QLBH;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import org.hibernate.query.Query;
import org.hibernate.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import entities.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable {

	@FXML
	private TextField emailIdField;

	@FXML
	private Button submitButton;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label thongbao;

	private Taikhoannv taikhoan;

	@FXML
	private ImageView close;

	@FXML
	private ImageView minimize;

	@FXML
	private Label thongbaologin;

	@FXML
	private Button load;

	@FXML
	private CheckBox ghinho;

	@FXML
	void load(ActionEvent event) {
		try {

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("load.fxml"));
			Parent sampleParent = loader.load();
			Scene scene = new Scene(sampleParent);
			stage.setScene(scene);
		} catch (Exception e) {
			// TODO: handle exception

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
	public Preferences pref = Preferences.userRoot().node("Rememberme");
	private static double xoffset = 0;
	private static double yoffset = 0;
	public void savenmailpass(String Email, String Pass) {
		if (Email == null || Pass == null) {
			System.out.println("Loi khong luu duoc email passs trong refrenrences");

		} else {
			pref.put("Email", Email);
			String pass = Pass;
			pref.put("Password", pass);

		}
	}

	public void checked(boolean remember) {
		if (remember) {
			savenmailpass(emailIdField.getText(), passwordField.getText());
		} else {
			System.out.println("null");
		}
	}
	@FXML
	void login(ActionEvent event) {

		try {
			System.out.println(emailIdField.getText());
			System.out.println(passwordField.getText());

			if (emailIdField.getText().isEmpty()) {
				thongbaologin.setVisible(false);
				thongbao.setVisible(true);
				thongbao.setText("Bạn chưa nhập tài khoản !");
				return;
			} else if (passwordField.getText().isEmpty()) {
				thongbaologin.setVisible(false);
				thongbao.setVisible(true);
				thongbao.setText("Bạn chưa nhập mật khẩu !");
				return;

			} else {
				thongbao.setVisible(false);
				thongbaologin.setVisible(true);
				thongbaologin.setText("Kiểm tra lại tài khoản,mật khẩu");
			}
			if(ghinho.isSelected()) {
				pref.put("Email", emailIdField.getText());

				pref.put("Password", passwordField.getText());
			}
			Session session = HibernateUtils.getSessionFactory().openSession();
			String username = emailIdField.getText();
			String password = passwordField.getText();
			Taikhoannv taikhoannv = new Taikhoannv(username, password);
			session.getTransaction().begin();
			String hql = "FROM Taikhoannv A WHERE A.username = :username and A.password = :password";
			String hql1 = "SELECT A.username,A.password,B.chucvu FROM Taikhoannv A INNER JOIN A.nhanvien B WHERE A.username = :username and A.password = :password ";
			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);
			Query query1 = session.createQuery(hql1);
			query1.setParameter("username", username);
			query1.setParameter("password", password);
			List<Object[]> tk1 = query1.list();
			List<Taikhoannv> checktaikhoan = query.list();
			for (Taikhoannv checktk1 : checktaikhoan) {
				if (checktk1.getusername().equals(taikhoannv.getusername().trim())
						&& checktk1.getpassword().equals(taikhoannv.getpassword().trim())) {
					for (Object[] singleRowValues : tk1) {
						String tentaikhoan = (String) singleRowValues[0];
						String matkhau = (String) singleRowValues[1];
						String cvString = (String) singleRowValues[2];

						if (tentaikhoan.equals(checktk1.getusername().trim())
								&& matkhau.equals(checktk1.getpassword().trim()) && cvString.equals("Quản lý")) {
							String tk = checktk1.getusername();
							String mk = checktk1.getpassword();
							taikhoan = session.get(Taikhoannv.class, tk);
							FXMLLoader loader = new FXMLLoader(getClass().getResource("giaodienquanly.fxml"));
							Parent tmp = loader.load();
							Scene scene = new Scene(tmp);

							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							scene.setOnMousePressed(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent mouseEvent) {
									xoffset = stage.getX() - mouseEvent.getScreenX();
									yoffset = stage.getY() - mouseEvent.getScreenY();
								}
							});
							scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent mouseEvent) {
									stage.setX(mouseEvent.getScreenX() + xoffset);
									stage.setY(mouseEvent.getScreenY() + yoffset);
								}
							});
							GiaoDienQLController quanly = loader.getController();
							quanly.LoadData(taikhoan);
							stage.hide();
							stage.setScene(scene);
							stage.show();
						} else if (tentaikhoan.equals(checktk1.getusername().trim())
								&& matkhau.equals(checktk1.getpassword().trim()) && cvString.equals("Nhân viên")) {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("giaodiennhanvien.fxml"));
							Parent tmp = loader.load();
							Scene scene = new Scene(tmp);
							
							String tk = checktk1.getusername();
							taikhoan = session.get(Taikhoannv.class, tk);

							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							scene.setOnMousePressed(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent mouseEvent) {
									xoffset = stage.getX() - mouseEvent.getScreenX();
									yoffset = stage.getY() - mouseEvent.getScreenY();
								}
							});
							scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent mouseEvent) {
									stage.setX(mouseEvent.getScreenX() + xoffset);
									stage.setY(mouseEvent.getScreenY() + yoffset);
								}
							});
							GiaoDienNhanvienController quanly1 = loader.getController();
							quanly1.LoadData(taikhoan);
							stage.hide();
							stage.setScene(scene);
							stage.show();
						}

					}
				}
			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		String usr = null;
		usr = pref.get("Email", usr);
		String pass = null;
		pass = pref.get("Password", pass);
		emailIdField.setText(usr);
		passwordField.setText(pass);
	}

}
