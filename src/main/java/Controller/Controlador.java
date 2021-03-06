package Controller;

import java.util.List;

import Observer.CrosswordObserver;
import Observer.FriendObserver;
import Observer.LoginObserver;
import Observer.RequestObserver;
import Observer.userObserver;
import Transfer.Word;
import model.Aplicacion;

public class Controlador {
	
	Aplicacion app;
	
	public Controlador(Aplicacion app){
		this.app = app;		
	}
	

	public void registro(String nick, String pass, String fecha, String ruta) {
		// TODO Auto-generated method stub
		
		
		
		app.registro(nick, pass, fecha, ruta);	
		
	}

	public void login(String nick, String pass) {
				
		app.login(nick, pass);		
	}
	
	
	public void AddObserver(LoginObserver observador){
		app.addObserver(observador);
		
	}


	public Object[][] getCrucigramas() {
		// TODO Auto-generated method stub
		
		return app.getCrucigramas();
		
	}


	public String[] buscarCrucigramas(String text) {
		// TODO Auto-generated method stub
		return app.buscarCrucigramas(text);
	}


	public void addCrucigrama(String selectedValue) {
		// TODO Auto-generated method stub
		app.addCrucigrama(selectedValue);
		
	}


	public List<Object> getAmigos() {
		// TODO Auto-generated method stub
		return app.getAmigos();
	}


	public void addFriend(String amigo) {
		// TODO Auto-generated method stub
		app.addFriend(amigo);
	}


	public void eliminarAmigo(String selectedValue) {
		// TODO Auto-generated method stub
		app.eliminarAmigo(selectedValue);
		
	}


	public void AddObserver(CrosswordObserver ob) {
		// TODO Auto-generated method stub
		
		app.AddObserver(ob);
		
	}


	public void AddObserver(FriendObserver obs) {
		// TODO Auto-generated method stub
		app.AddObserver(obs);
		
	}

	public void AddObserver(userObserver obs) {
		// TODO Auto-generated method stub
		app.AddObserver(obs);
		
	}

	public Object[][] getAyuda() {
		// TODO Auto-generated method stub
		return app.getAyuda();
	}


	public void borraAyuda(String[] ayuda) {
		// TODO Auto-generated method stub
		app.borraAyuda(ayuda);
		
	}


	public void AddObserver(RequestObserver obs) {
		// TODO Auto-generated method stub
		
		app.AddObserver(obs);
		
	}


	public List<Word> getPalabras(String crucigrama) {
		// TODO Auto-generated method stub
		return app.getPalabras(crucigrama);
	}


	public void modificar(String pass, String fecha, String ruta) {
		// TODO Auto-generated method stub
		app.modificar(pass, fecha, ruta);	
	}


	public void answer(String usuario, String respuesta, int palabra, int crucigrama) {
		// TODO Auto-generated method stub
		app.answer(usuario, respuesta, palabra, crucigrama);
	}


	public boolean[] getAcertadas(int idC, List<Word> _lista) {
		// TODO Auto-generated method stub
		return app.getAcertadas(idC, _lista);
	}


	
	


}
