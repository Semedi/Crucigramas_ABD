package DataBase;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CrowsswordTest {
	private CrosswordDAO dao;

	@Before
	public void setUp() throws Exception {
		this.dao = new CrosswordDAO();
	}

	@After
	public void tearDown() throws Exception {
		this.dao.close();
	}

	@Test
	public void getPasswordForUser() {
		String pw = dao.getPassword("user");
		assertEquals("Contraseña de 'user'", "pass", pw);
	}

	@Test
	public void getPasswordForUser2() {
		String pw = dao.getPassword("user2");
		assertEquals("Contraseña de 'user2'", "pass2", pw);
	}
	
	@Test
	public void getPasswordForNotExistingUser() {
		String pw = dao.getPassword("usuario_que_no_existe");
		assertNull("Contraseña de 'usuario_que_no_existe'", pw);
	}
 	
	@Test
	public void changePassword() {
		dao.modifyPassword("user", "krocsyldiphic");
		String pw = dao.getPassword("user");
		assertEquals("Contraseña cambiada de 'user'", "krocsyldiphic", pw);
		
		dao.modifyPassword("user", "pass");
		String pw2 = dao.getPassword("user");
		assertEquals("Contraseña restaurada de 'user'", "pass", pw2);
	}
	
	
	@Test
	public void dosCrucigramas() {
		List<?> l = dao.findCrosswordsByTitle("ruci");
		assertTrue("La búsqueda por nombre con 'ruci' debe tener dos elementos como mínimo", 
				l.size() >= 2);
		Set<String> titles = new HashSet<String>();
		for (Object x : l) titles.add(dao.getCrosswordTitle(x));
		
		assertTrue("La búsqueda por nombre con 'ruci' debe devolver 'Crucigrama1' y 'Crucigrama2'",
				titles.contains("Crucigrama1") && titles.contains("Crucigrama2"));
	}
	
	@Test
	public void crucigramasDeUser() {
		List<?> l = dao.findCrosswordsByTitle("Crucigrama2");
		assertTrue("Debe existir un crucigrama con título 'Crucigrama2'", l.size() >= 1);
		dao.addCrosswordToUser("user", l.get(0));
		List<?> cw = dao.getCrosswordsOf("user");
		assertTrue("Tras añadir 'Crucigrama2' user debe tener al menos un crucigrama asociado", cw.size() >= 1);
		boolean foundCW2 = false;
		for (Object x : cw) {
			if (dao.getCrosswordTitle(x).equals("Crucigrama2")) {
				foundCW2 = true;
			}
		}
		assertTrue("Tras añadir 'Crucigrama2' a 'user', éste debe estar en su lista de crucigramas", foundCW2);
	}
	
}
