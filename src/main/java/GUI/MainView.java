package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Controller.Controlador;


/*Ventana principal con todas las pestañas*/
public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private String Username;
	
	public MainView(Controlador controlador, String Username, ImageIcon avatar, int edad) {
		
		super("");
		this.Username = Username;
		this.controlador= controlador;
		
		this.setSize(650, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel principal = new JPanel();
		
		build(principal, edad, avatar);
		this.add(principal);
		this.setVisible(true);
	
		
	}
	
	
	
	private  void build(JPanel panel, int edad, ImageIcon imagen){
		
		
		panel.setLayout(new BorderLayout());
		
		
		
		
		/*------------------Parte de arriba, informacion del usuario-----------------------------*/
		JPanel cabecera = new JPanel();
		cabecera.setLayout(new BorderLayout());
		
		
		
		
		JLabel avatar = new JLabel("");	
			
				
		try{
			
		
		avatar.setIcon(imagen);
		avatar.setBorder(BorderFactory.createEmptyBorder(20,20,5,200));
		
		}
		catch (NullPointerException e){}
		
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		
		JLabel infoUser = new JLabel(Username);
		JLabel infoEdad = new JLabel(edad+" años");
		JLabel infoPuntos = new JLabel("0 puntos");
		
		aux.add(infoUser, BorderLayout.NORTH);
		aux.add(infoEdad, BorderLayout.CENTER);
		aux.add(infoPuntos, BorderLayout.SOUTH);
		
		aux.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		
		
		cabecera.add(avatar, BorderLayout.WEST);
		cabecera.add(aux, BorderLayout.CENTER);
		
	
		
		/*-----------------Parte de las pestañas en el centro -----------------------------------*/
		JTabbedPane pestañas = new JTabbedPane();
		
		
		
		/*------------------ PESTAÑA CRUCIGRAMAS-------------------------*/
		CrosswordView crucigramas = new CrosswordView(controlador);
			
		pestañas.addTab("Crucigramas", null, crucigramas,
		                  "Pestaña de crucigramas activos");
		
		pestañas.setMnemonicAt(0, KeyEvent.VK_1);
		
		
		
		/*------------------PESTAÑA AMIGOS--------------------------------------*/
		
		FriendView amigos = new FriendView(controlador);
		
		pestañas.addTab("Amigos", null, amigos,
		                  "Muestra los amigos");
		pestañas.setMnemonicAt(1, KeyEvent.VK_2);
		
		
		/*----------------------------PESTAÑA PETICIONES DE AYUDA ---------------------------------*/

		RequestView peticiones = new RequestView(controlador);
		
		pestañas.addTab("Peticiones de ayuda", null, peticiones,
		                  "Muestra las peticiones de ayuda");
		pestañas.setMnemonicAt(2, KeyEvent.VK_3);
		
		/*---------------------------FIN DE LA PARTE DE LAS PESTAÑAS------------------------*/

		
		
		panel.add(pestañas, BorderLayout.CENTER);
		panel.add(cabecera, BorderLayout.NORTH);
		
		
		
	}

}
