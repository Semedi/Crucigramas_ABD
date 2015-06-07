package model;

import java.awt.Image;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import DataBase.AmigosMapper;
import DataBase.CrContienePaMapper;
import DataBase.CrucigramasMapper;
import DataBase.DbManager;
import DataBase.ListaAyudaMapper;
import DataBase.ListaCrucigramasMapper;
import DataBase.PalabrasMapper;
import DataBase.UsuariosMapper;
import GUI.LoginView;
import Observer.CrosswordObserver;
import Observer.FriendObserver;
import Observer.LoginObserver;
import Observer.RequestObserver;
import Observer.userObserver;
import Transfer.Word;

public class Aplicacion {

	private boolean _login;
	private String _user;

	private DbManager _database;

	private LoginObserver _lobs;
	private CrosswordObserver _cobs;
	private FriendObserver _aobs;
	private RequestObserver _robs;
	private userObserver _uobs;

	public Aplicacion() throws SQLException, IOException, PropertyVetoException {
		_login = false;
		_database = new DbManager();
	}

	@SuppressWarnings({ "deprecation" })
	public void login(String nick, String pass) {
		// TODO Auto-generated method stub
		
		
		UsuariosMapper um = new UsuariosMapper(_database.getDataSource());
		Usuario u = um.findById(nick);
		
		

		
		 if (u == null) _lobs.onFailed();
		 else if (u.getPassword().equals(pass)){ 
			 
			 _user=nick;
			 _login=true;
			 
			 int edad = 0;
			 
			 try{
			 edad = 115 - u.getFechaNacimiento().getYear();
			 } catch (Exception e){
				 
			 }
			 
				_lobs.onLogin(nick,ImageConverter.arrayToImage(u.getFoto()) , edad, u.getFechaNacimiento().toString(), pass);
			
				// TODO Auto-generated catch block
				
			
		 	}
		 else _lobs.onFailed();
	
	}

	public void registro(String nick, String pass, String fecha, String ruta) {
		// TODO Auto-generated method stub
		
		Date date = null;
		byte[] blob = null;
				
		try {
			date = parseFecha(fecha);	
			blob =ImageConverter.Imageconverter(ruta);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		
		}
		
		
		
		UsuariosMapper um = new UsuariosMapper(_database.getDataSource());
		Usuario u = new Usuario(nick, pass,date,blob );
		
		
		um.insert(u);

		
		
	}

	@SuppressWarnings("deprecation")
	public Date parseFecha(String fecha) throws Exception{
		String[] partido = fecha.split("-");
		
		
		int dia = Integer.parseInt(partido[2]);
		int mes = Integer.parseInt(partido[1]);
		int año = Integer.parseInt(partido[0]);
		
		mes--;
		año-=1900;
			
		Date fecha1 = new Date(año, mes, dia);
		
		return (fecha1);
	}
	
	public boolean isLogin() {
		return _login;
	}

	
	
	
/*Metodos que añaden observadores -------------->*/
	
	public void addObserver(LoginObserver observador) {
		// TODO Auto-generated method stub

		_lobs = observador;

	}
	
	
	

	public void AddObserver(CrosswordObserver ob) {
		// TODO Auto-generated method stub
		
		_cobs = ob;
		
	}
	
	

	public void AddObserver(FriendObserver obs) {
		// TODO Auto-generated method stub
		
		_aobs=obs;
	}
	
	

	public void AddObserver(RequestObserver obs) {
		// TODO Auto-generated method stub
		_robs=obs;
		
	}
	
	
	public void AddObserver(userObserver obs) {
		// TODO Auto-generated method stub
		_uobs=obs;
	}


	/*---------------------------------------------------------------*/








	public String get_user() {
		return _user;
	}
	

	public Object[][] getAyuda() {
		// TODO Auto-generated method stub
		
		ListaAyudaMapper lam = new ListaAyudaMapper(_database.getDataSource());
		CrucigramasMapper cm = new CrucigramasMapper(_database.getDataSource());
		
		
	
		
		List<Object> lista = lam.getListaAyuda(_user);
		Object[][] crucigramas = new Object[lista.size()][2];
		
		
		int i = 0;
		String[] cadena;
		String aux;
		while ( i < lista.size()){
			
			aux=(String) lista.get(i);
			cadena = aux.split(" ");
			
			crucigramas[i][0] = cadena[0];
			crucigramas[i][1]= cm.findById(Integer.parseInt(cadena[1])).getTitulo();
			
			
			i++;
				
		}
		
		
		
		
		return crucigramas;
	}



	
	public Object[][] getCrucigramas() {
		// TODO Auto-generated method stub
		
	
		
		
		
		Crucigrama aux;
		ListaCrucigramasMapper lcm = new ListaCrucigramasMapper(_database.getDataSource());
		CrucigramasMapper cm = new CrucigramasMapper(_database.getDataSource());
		
		
		
		List<Object> lista =lcm.getLista(this._user);
		
		Object[][] crucigramas = new Object[lista.size()][2];
		int i = 0;
		
		while ( i < lista.size()){
			
			aux = cm.findById((Integer)lista.get(i));
			
			
			
			crucigramas[i][0] = aux.getTitulo();
			crucigramas[i][1] = ""+aux.getFecha();
			
			i++;
				
		}
		
		return crucigramas;
			
	}

	
	public String[] buscarCrucigramas(String text) {
		// TODO Auto-generated method stub
		
		CrucigramasMapper cm = new CrucigramasMapper(_database.getDataSource());
		
		List<Integer> lista = cm.getLista(text);
		
		String[] crucigramas= new String[lista.size()];
		Crucigrama aux;
		
		for (int i = 0 ; i < lista.size(); i++){
			aux = cm.findById(lista.get(i));
			crucigramas[i] = aux.getTitulo();
			
		}
		
		
		return crucigramas;
	
	}

	public void addCrucigrama(String selectedValue) {
		// TODO Auto-generated method stub
		
			CrucigramasMapper cm = new CrucigramasMapper(_database.getDataSource());
		
		List<Integer> lista = cm.getLista(selectedValue);
		
		
		Crucigrama aux = cm.findById(lista.get(0));
		
		ListaCrucigramasMapper lcm = new ListaCrucigramasMapper(_database.getDataSource());
		ListaCrucigramas elem = new ListaCrucigramas(_user, aux.getId(), true);
		
		lcm.insert(elem);
		
		String[] cruci = {aux.getTitulo(),""+aux.getFecha()};
		this._cobs.OnAdd(cruci);
		
	}

	
	
	
	public List<Object> getAmigos() {
		// TODO Auto-generated method stub
		AmigosMapper am = new AmigosMapper(_database.getDataSource());
		
		return am.getListaAmigos(_user);
		
		}
	
	
	

	public void addFriend(String amigo) {
		// TODO Auto-generated method stub
		AmigosMapper am = new AmigosMapper(_database.getDataSource());
		
		Amigos relacion = new Amigos(_user, amigo);
		Amigos relacion2 = new Amigos(amigo, _user);
		
		am.insert(relacion);
		am.insert(relacion2);
		_aobs.onAdd(amigo);
		
		
		
	}
	
	
	
	
	public void eliminarAmigo(String selectedValue) {
		// TODO Auto-generated method stub
		
		AmigosMapper am = new AmigosMapper(_database.getDataSource());
		
		Amigos relacion = new Amigos(_user, selectedValue);
		Amigos relacion2 = new Amigos(selectedValue, _user);
		
		am.delete(relacion);
		am.delete(relacion2);
		
		_aobs.onDelete(selectedValue);
		
	}
	
	

	public void borraAyuda(String[] ayuda) {
		// TODO Auto-generated method stub
		ListaAyudaMapper lam = new ListaAyudaMapper(_database.getDataSource());
		CrucigramasMapper cam = new CrucigramasMapper(_database.getDataSource());
		
		int id =cam.findById(cam.getLista(ayuda[1]).get(0)).getId();
		
		ListaAyuda relacion = new ListaAyuda(this._user,ayuda[0], id );
		
		lam.delete(relacion);
		_robs.onDelete();
		
	}
	
	
	public void answer() {
		// TODO Auto-generated method stub
		
	}




	public List<Word> getPalabras(String crucigrama) {
		// TODO Auto-generated method stub
		
		List<Word> lista = new LinkedList<Word>();
		
		CrContienePaMapper contains = new CrContienePaMapper(_database.getDataSource());
		CrucigramasMapper cm = new CrucigramasMapper(_database.getDataSource());
		PalabrasMapper pm = new PalabrasMapper(_database.getDataSource());
		
		
		List<CrContienePa> listaRelaciones = contains.getPalabras(cm.findByName(crucigrama).getId());
		Palabra palabra;
		CrContienePa relacion;
		boolean horizontal;
		
		Word transfer;
		
		
		for (int i = 0; i < listaRelaciones.size(); i++){
			
			relacion = listaRelaciones.get(i);
			palabra = pm.findById(relacion.getId_palabra());
			horizontal = (relacion.getOrientacion().equalsIgnoreCase("horizontal"));
			
			transfer = new Word(relacion.getX(), relacion.getY(), palabra.getCaracteres(), horizontal, palabra.getTexto(), palabra.getImagen(), i, relacion.getId_palabra(), relacion.getId_crucigrama());
			
			
			lista.add(transfer);
		}
		
		return lista;
	
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void modificar(String pass, String fecha, String ruta) {
		// TODO Auto-generated method stub
		
		Date date = null;
		byte[] blob = null;
				
		try {
			date = parseFecha(fecha);	
			blob =ImageConverter.Imageconverter(ruta);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		
		}
		
	
		
		UsuariosMapper um = new UsuariosMapper(_database.getDataSource());
		Usuario u = new Usuario(_user, pass,date,blob );
		
		um.update(u);
		
		
		
		
		_uobs.onRefresh(ImageConverter.arrayToImage(blob), 115 - u.getFechaNacimiento().getYear());
		
		
	}




	
	
	

	/*
	 * Clase estatica que convierte imagenes :
	 * ----------------------------------
	 * ------------------------------------------------------------
	 */

	public static class ImageConverter {

		@SuppressWarnings("resource")
		public static byte[] Imageconverter(String ruta) throws Exception {

			File file = new File(ruta);

			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			try {
				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					// Writes to this byte array output stream
					bos.write(buf, 0, readNum);
					
				}
			} catch (IOException ex) {
				Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE,
						null, ex);
			}

			byte[] bytes = bos.toByteArray();

			return bytes;
		}

		public static ImageIcon arrayToImage(byte[] bytes) {
			ByteArrayInputStream bis = null;
			try {
				bis = new ByteArrayInputStream(bytes);

			} catch (NullPointerException e) {
				return new ImageIcon(
						LoginView.class.getResource("imagen/avatar.jpg"));

			}
			Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");

			

			ImageReader reader = (ImageReader) readers.next();
			Object source = bis;
			ImageInputStream iis = null;
			try {
				iis = ImageIO.createImageInputStream(source);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("excepcion de la imagen");
			}
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();

			Image image = null;

			try {
				image = reader.read(0, param);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error al leer imagenes");
			}
			// got an image file

			return new ImageIcon(image);

		}

	}// Image converter








	










/*---------------------------------------------------------------------------------------------*/

	/*----------------------------------------------------------------------------------------*/

}
