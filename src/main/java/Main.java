import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import model.Aplicacion;
import Controller.Controlador;
import GUI.LoginView;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*Añadir objetos del modelo, vista y controlador*/

		Aplicacion juego = null;
		
		
		/*hilo principal de la aplicacion, creamos el modelo que es juego 
		 * el controlador que manejara la aplicacion y la vista, que nos mostrara
		 * el login desde el cual podremos acceder a la vista principal*/
		
		
		try {
			juego = new Aplicacion();
		} catch (SQLException | IOException | PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Controlador controlador = new Controlador(juego);
		LoginView vista = new LoginView(controlador);				
		controlador.AddObserver(vista);
		
	
		
	}
		
	

}
