package DataBase;



import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Crucigrama;
import model.ListaCrucigramas;
import model.Usuario;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class CrosswordDAO {
	
	DataSource ds;
	/**
	 * Aquí se debe inicializar el pool de conexiones, mediante
	 * la creación de un DataSource, que deberá ser asignado a
	 * la variable ds.
	 * @throws SQLException 
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 */
	
	/*UsuarioP1 esta creado en el fichero sql como usuario local con los permisos pertientes
	 * si hay algun problema cambiar setUser por root*/
	public CrosswordDAO() throws SQLException, IOException, PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl(("jdbc:mysql://localhost/practica1_510"));
		cpds.setUser("UsuarioP1");
		cpds.setPassword("");
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		
		
		this.ds = cpds;		 		
	}

	
	/**
	 * Devuelve la contraseña del usuario cuyo nick se pasa como
	 * parámetro. Devuelve null si el usuario no existe. 
	 */
	public String getPassword(String nick) {
		
		UsuariosMapper um = new UsuariosMapper(ds);
		Usuario u = um.findById(nick);
		 if (u != null) return u.getPassword(); else return null;	
	}
	
	/**
	 * Modifica la contraseña del usuario pasado como parámetro 
	 */
	public void modifyPassword(String nick, String newPassword) {
		
		UsuariosMapper um = new UsuariosMapper(ds);
		Usuario u = um.findById(nick);
		 if (u != null){ 
			 
			 u.setPassword(newPassword);
			 um.update(u);
		 }
		
	}

	/**
	 * Devuelve una lista de las claves de aquellos crucigramas
	 * cuyo título contenga str.
	 * 
	 * Si escogisteis una clave numérica para la tabla de crucigramas,
	 * se debe devolver una lista de Integer. Si escogisteis el título
	 * como clave, se debe devolver una lista de String. Si, por el contrario,
	 * escogisteis una clave compuesta, debéis crear una clase para almacenar
	 * los atributos de dicha clave. 
	 */
	public List<?> findCrosswordsByTitle(String str) {
		
		CrucigramasMapper cm = new CrucigramasMapper(ds);		
		return cm.getLista(str);		
	}

	/**
	 * Devuelve el título del crucigrama cuya clave se pasa como
	 * parámetro.
	 */
	public String getCrosswordTitle(Object id) {
		
		CrucigramasMapper cm = new CrucigramasMapper(ds);
		Crucigrama c = cm.findById((Integer) id);
		
		
		
		if (c != null) return c.getTitulo();
		else return null;
	}
	
	/**
	 * Añade un crucigrama a la lista de crucigramas activos de un usuario.
	 * 
	 * El crucigrama se especifica mediante su clave
	 * @throws SQLException 
	 */
	
		public void addCrosswordToUser(String nick, Object crosswordId) throws SQLException {
			
			ListaCrucigramasMapper lcm = new ListaCrucigramasMapper(ds);
			ListaCrucigramas lista = new ListaCrucigramas(nick,(Integer) crosswordId,true);
			lcm.insert(lista);
					
	}
	
	/**
	 * Devuelve la lista de identificadores de los crucigramas activos
	 * del usuario pasado como parámetro
	 */
	
		public List<Object> getCrosswordsOf(String nick) {
			
		ListaCrucigramasMapper lcm = new ListaCrucigramasMapper(ds);
		return lcm.getLista(nick);
		
	}

	/**
	 * Cierra el dataSource
	 */
	public void close() {
		 ((ComboPooledDataSource)ds).close();	
	}
}
