package Nhanvien;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import QLBH.HibernateUtils;
import entities.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

@javax.annotation.processing.Generated(value = "org.junit-tools-1.1.0")
public class NhanvienControllerTest extends Main{

	private NhanvienController createTestSubject() {
		return new NhanvienController();
	}
	
	@Test
	public void testGetNhanvien() throws Exception {
		NhanvienController testSubject;
		ObservableList<Nhanvien> result;
		testSubject = createTestSubject();
		result = testSubject.getNhanvien();
		assertNotNull(result);
		assertTrue("Hiển thị nhân viên thành công",result.size()>0);
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
		testSubject = createTestSubject();
		testSubject.initializeNHANVIEN();
	}

	
	@Test
	public void testLuucapnhat() throws Exception {
		Session session = HibernateUtils.getSessionFactory().openSession();
		NhanvienController testSubject;
		
		testSubject = createTestSubject();
	}

	
	@Test
	public void testSearch() throws Exception {
		NhanvienController testSubject;
		testSubject = createTestSubject();
		
	}

	
/*	@Test
	public void testSetCellValueFromTabletoTexfField() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		Whitebox.invokeMethod(testSubject, "setCellValueFromTabletoTexfField");
	}
*/
	
	@Test
	public void testTruedisable() throws Exception {
		NhanvienController testSubject;

		// default test
		testSubject = createTestSubject();
		testSubject.truedisable();
	}


}