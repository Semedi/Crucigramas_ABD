package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Controller.Controlador;
import Observer.userObserver;



/*Ventana principal con todas las pestañas*/
public class MainView extends JFrame implements userObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private String Username;
	
	
	private JLabel infoUser;
	private JLabel infoEdad;
	private JLabel infoPuntos;
	private JButton avatar;
	
	public MainView(Controlador controlador, String Username, ImageIcon avatar, int edad, String fecha, String pass) {
		
		super("");
		this.Username = Username;
		this.controlador= controlador;
		
		this.setSize(650, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel principal = new JPanel();
		
		build(principal, edad, avatar, fecha, pass);
		this.controlador.AddObserver(this);
		
		
		this.add(principal);
		this.setVisible(true);
	
		
	}
	
	
	
	private  void build(JPanel panel, int edad, final ImageIcon imagen, final String fecha, final String pass){
		
		
		panel.setLayout(new BorderLayout());
		
		
		
		
		/*------------------Parte de arriba, informacion del usuario-----------------------------*/
		JPanel cabecera = new JPanel();
		cabecera.setLayout(new BorderLayout());
		
		
		
		
		avatar = new JButton("");	
		avatar.setBorderPainted(false);
		avatar.setPreferredSize(new Dimension(150,30));
	
		
			
				
		try{
		
		avatar.setIcon(imagen);
		}
		catch (NullPointerException e){}
		
		avatar.addActionListener(
				new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {						
						new userMod(Username, fecha, imagen, pass);								
					}			
				});
		
		
		
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		
		 infoUser = new JLabel(Username);
		 infoEdad = new JLabel(edad+" años");
		 infoPuntos = new JLabel("0 puntos");
		
		aux.add(infoUser, BorderLayout.NORTH);
		aux.add(infoEdad, BorderLayout.CENTER);
		aux.add(infoPuntos, BorderLayout.SOUTH);
		
		aux.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		
	
		
		
		cabecera.add(avatar, BorderLayout.WEST);
		cabecera.add(aux);
		
		
	
		/*****************************************************************************************/
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
	
	

	/*    Clase Privada  que crea un JFrame para modificar el usuario
	 * ----------------------------------------------------------------------------*/
								
				
				private class userMod extends JFrame{
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					private JPasswordField _passwordText;
					private JTextField _fecha;
					private JTextField _ruta;
					
					private userMod(String usuario, String fecha,ImageIcon image, String pass){
						super("Modificar usuario");	
					
						
						
						
						this.setSize(400, 200);
						this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						this.setLocation(600,380);

						/*Panel donde vamos a poner todos los componentes*/
						JPanel principal = new JPanel();
						build(principal, usuario, fecha, image, pass);
						this.add(principal);
						
						this.setVisible(true);		
						
					}
					
					
					private void cerrarVentana(){
						this.dispose();
					}
					
					
					private void build(JPanel panel, String usuario, String fecha, ImageIcon avatar, String pass) {

						panel.setLayout(null);

						/* etiqueta usuario*/
						JLabel userLabel = new JLabel("Nombre de usuario:");
						userLabel.setBounds(10, 10, 120, 25);
						panel.add(userLabel);

						/* Campo a rellenar al lado de la etiqueta usuario*/
						JLabel userText = new JLabel(usuario);
						userText.setBounds(130, 10, 160, 25);
						
						panel.add(userText);

						/* etiqueta de contraseña*/
						JLabel passwordLabel = new JLabel("Contraseña:");
						passwordLabel.setBounds(10, 40, 80, 25);
						panel.add(passwordLabel);

						/* campo a rellenar para la etiqueta de contraseña*/
						this._passwordText = new JPasswordField(20);
						this._passwordText.setBounds(130, 40, 160, 25);
						this._passwordText.setText(pass);
						panel.add(this._passwordText);
						
						JLabel fechaLabel = new JLabel("Nacimiento:");
						fechaLabel.setBounds(10,70,80,25);
						panel.add(fechaLabel);
						
						this._fecha= new JTextField(20);
						this._fecha.setBounds(130,70,80,25);
						this._fecha.setText(fecha);
						panel.add(_fecha);
						
						
						
						
						JLabel avatarU = new JLabel("");	
					
						
							
								
						try{
						
						avatarU.setIcon(avatar);
						}
						catch (NullPointerException e){}
						
						JLabel avatarLabel = new JLabel("Avatar:");
						avatarLabel.setBounds(10, 100, 80, 25);
						panel.add(avatarLabel);
					
						
						
						this._ruta = new JTextField(20);
						this._ruta.setBounds(130,100,80,25);
						
						panel.add(_ruta);
						
						
						
						
						
						Button fileButton = new Button("Examinar");
						fileButton.setBounds(220,100,80,25);
						
						fileButton.addActionListener(
								new ActionListener() {					
									@Override
									public void actionPerformed(ActionEvent e) {
										
										JFileChooser fileChooser = new JFileChooser();
								        int returnValue = fileChooser.showOpenDialog(null);
									        if (returnValue == JFileChooser.APPROVE_OPTION) {
									          File selectedFile = fileChooser.getSelectedFile();
									          
									          
									          _ruta.setText(selectedFile.getAbsolutePath());
									         
									        }
									} 
											
								});
						
						
						
						panel.add(fileButton);
						
						

						
						/*Los dos botones: Aceptar y Nuevo usuario*/
						JButton loginButton = new JButton("Aceptar");
						loginButton.setBounds(135, 130, 80, 25);
						
						loginButton.addActionListener(
								new ActionListener() {					
									@SuppressWarnings("deprecation")
									@Override
									public void actionPerformed(ActionEvent e) {
										
										
										controlador.modificar(_passwordText.getText(),
												_fecha.getText(), _ruta.getText()
												);
										
										
										JOptionPane.showMessageDialog(null,
												"Usuario modificado",
												"Infor",
												JOptionPane.INFORMATION_MESSAGE
												);
										
										cerrarVentana();
										
										
										
										
									}			
								});
						
						
						
						panel.add(loginButton);
						
					}
				
					
				}



				@Override
				public void onRefresh(ImageIcon avatar,
						int edad) {
					// TODO Auto-generated method stub
					

			
					this.avatar.setIcon(avatar);
					this.infoEdad.setText(edad+" años");
					
					
					
					
				}
				
	/*----------------------------------------------------------------------------------*/
	
	
	

}



