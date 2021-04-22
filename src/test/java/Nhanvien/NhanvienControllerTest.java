package Nhanvien;

import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.Generated;

import org.junit.Test;

import QLBH.Nhanvien;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

@Generated(value = "org.junit-tools-1.1.0")
public class NhanvienControllerTest {

	private NhanvienController createTestSubject() {
		return new NhanvienController();
	}

	
/*	@Test
	public void testCapNhatNhanvien() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "CapNhatNhanvien", new Object[] { ActionEvent.class });
	}

	
	@Test
	public void testListNhanvien() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "ListNhanvien", new Object[] { ActionEvent.class });
	}

	
	@Test
	public void testReset() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "Reset", new Object[] { ActionEvent.class });
	}

	
	@Test
	public void testThemNV() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "ThemNV", new Object[] { ActionEvent.class });
	}

	
	@Test
	public void testXoaNhanvien() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "XoaNhanvien", new Object[] { ActionEvent.class });
	}*/

	
	@Test
	public void testFalsedisable() throws Exception {
		NhanvienController testSubject;
		
		// default test
		testSubject =createTestSubject();
		testSubject.falsedisable();
	}

	
	@Test
	public void testGetNhanvien() throws Exception {
		NhanvienController testSubject;
		ObservableList<Nhanvien> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getNhanvien();
	}

	
	@Test
	public void testInitialize() throws Exception {
		NhanvienController testSubject;
		URL location = null;
		ResourceBundle resources = null;

		// default test
		testSubject = createTestSubject();
		testSubject.initialize(location, resources);
	}

	
	@Test
	public void testInitializeNHANVIEN() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		testSubject.initializeNHANVIEN();
	}

	
	/*@Test
	public void testLuucapnhat() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "luucapnhat", new Object[] { ActionEvent.class });
	}*/


/*	@Test
	public void testReload() throws Exception {
		NhanvienController testSubject;
		ActionEvent event = null;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "reload", new Object[] { ActionEvent.class });
	}

	
	@Test
	public void testReloadNHANVIEN() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "reloadNHANVIEN");
	}

	
	@Test
	public void testSearch() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "search");
	}

	
	@Test
	public void testSetCellValueFromTabletoTexfField() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "setCellValueFromTabletoTexfField");
	}*/

	
	@Test
	public void testTruedisable() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		testSubject.truedisable();
	}
}