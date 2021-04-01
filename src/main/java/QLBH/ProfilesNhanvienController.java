package QLBH;

import java.io.File;
import java.io.FileOutputStream;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfilesNhanvienController {

	 @FXML
	    private Text hovatennv;

	    @FXML
	    private Text chucvunhanvien;

	    @FXML
	    private Text gioitinhnhanvien;

	    @FXML
	    private Text sdtnhanvien;

	    @FXML
	    private Text cmndnhanvien;

	    @FXML
	    private Text diachinhanvien;

	    @FXML
	    private ImageView imagenhanvien;

	    @FXML
	    private DatePicker ngaysinhnhanvien;

	    @FXML
	    private DatePicker ngayvaolam;

    
    
    public void loadData(Taikhoannv taikhoannv) {
    	hovatennv.setText(taikhoannv.getNhanvien().getHovaten());
    	ngaysinhnhanvien.setValue(taikhoannv.getNhanvien().getNgaysinh());
    	gioitinhnhanvien.setText(taikhoannv.getNhanvien().getGioitinh());
    	sdtnhanvien.setText(String.valueOf(taikhoannv.getNhanvien().getSdt()));
    	chucvunhanvien.setText(taikhoannv.getNhanvien().getChucvu());
    	cmndnhanvien.setText(String.valueOf(taikhoannv.getNhanvien().getCmnd()));
    	diachinhanvien.setText(taikhoannv.getNhanvien().getDiachi());
    	ngayvaolam.setValue(taikhoannv.getNhanvien().getNgayvaolam());
    	byte[] getImageInBytes = taikhoannv.getNhanvien().getImage();

		try {
			FileOutputStream fos = new FileOutputStream(new File("photo3.jpg"));
			fos.write(getImageInBytes);
			fos.close();
			Image image = new Image("file:photo3.jpg", imagenhanvien.getFitHeight(), imagenhanvien.getFitHeight(), true, true);
			imagenhanvien.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}
