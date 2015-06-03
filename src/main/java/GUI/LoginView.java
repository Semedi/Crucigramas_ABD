package GUI;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Controlador;
import Observer.LoginObserver;


/*clase de login (vista) es la primera ventana que nos aparece al iniciar la aplicacion*/
public class LoginView extends JFrame implements LoginObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controlador _controlador;
	
	private JTextField __userText;
	private JPasswordField _passwordText;
	
	public LoginView(Controlador controlador){
		
		super("Bienvenido");	
		this._controlador=controlador;
		
		
		
		this.setSize(400, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500,280);

		/*Panel donde vamos a poner todos los componentes*/
		JPanel principal = new JPanel();
		build(principal);
		this.add(principal);
		
		this.setVisible(true);
		

	}
	
	
	
		
	private void build(JPanel panel) {

		panel.setLayout(null);

		/* etiqueta usuario*/
		JLabel userLabel = new JLabel("Nombre de usuario:");
		userLabel.setBounds(10, 10, 120, 25);
		panel.add(userLabel);

		/* Campo a rellenar al lado de la etiqueta usuario*/
		this.__userText = new JTextField(20);
		this.__userText.setBounds(130, 10, 160, 25);
		panel.add(this.__userText);

		/* etiqueta de contraseña*/
		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setBounds(52, 40, 80, 25);
		panel.add(passwordLabel);

		/* campo a rellenar para la etiqueta de contraseña*/
		this._passwordText = new JPasswordField(20);
		this._passwordText.setBounds(130, 40, 160, 25);
		panel.add(this._passwordText);

		
		/*Los dos botones: Aceptar y Nuevo usuario*/
		JButton loginButton = new JButton("Aceptar");
		loginButton.setBounds(80, 80, 80, 25);
		
		loginButton.addActionListener(
				new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						_controlador.login(__userText.getText(), _passwordText.getText());
						
					}			
				});
		
		
		
		panel.add(loginButton);
		
		JButton registerButton = new JButton("Nuevo usuario");
		registerButton.setBounds(170, 80, 120, 25);
		
		registerButton.addActionListener(
				new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {						
						new NewUser();								
					}			
				});
		
		
		
		panel.add(registerButton);
	}




	@Override
	public void onLogin(String Username, ImageIcon avatar, int edad) {
		// TODO Auto-generated method stub
		this.dispose();
		MainView view =new MainView(_controlador, Username, avatar, edad);
		
	}




	@Override
	public void onFailed() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,
				"Usuario o contraseña incorrectos",
				"Error",
				JOptionPane.ERROR_MESSAGE
				);
		
		
	}
	
	/*    Clase Privada  que crea un JFrame para el nuevo Usuario
	 * ----------------------------------------------------------------------------*/
								
				private class NewUser extends JFrame{
					
					private JTextField _userText;
					private JPasswordField _passwordText;
					private JTextField _fecha;
					private JTextField _ruta;
					
					private NewUser(){
						super("Nuevo Usuario");	
					
						
						
						
						this.setSize(400, 200);
						this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						this.setLocation(600,380);

						/*Panel donde vamos a poner todos los componentes*/
						JPanel principal = new JPanel();
						build(principal);
						this.add(principal);
						
						this.setVisible(true);		
						
					}
					
					
					private void cerrarVentana(){
						this.dispose();
					}
					
					
					private void build(JPanel panel) {

						panel.setLayout(null);

						/* etiqueta usuario*/
						JLabel userLabel = new JLabel("Nombre de usuario:");
						userLabel.setBounds(10, 10, 120, 25);
						panel.add(userLabel);

						/* Campo a rellenar al lado de la etiqueta usuario*/
						this._userText = new JTextField(20);
						this._userText.setBounds(130, 10, 160, 25);
						panel.add(this._userText);

						/* etiqueta de contraseña*/
						JLabel passwordLabel = new JLabel("Contraseña:");
						passwordLabel.setBounds(10, 40, 80, 25);
						panel.add(passwordLabel);

						/* campo a rellenar para la etiqueta de contraseña*/
						this._passwordText = new JPasswordField(20);
						this._passwordText.setBounds(130, 40, 160, 25);
						panel.add(this._passwordText);
						
						JLabel fechaLabel = new JLabel("Nacimiento:");
						fechaLabel.setBounds(10,70,80,25);
						panel.add(fechaLabel);
						
						this._fecha= new JTextField(20);
						this._fecha.setBounds(130,70,80,25);
						this._fecha.setText("año/mes/dia");
						panel.add(_fecha);
						
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
									@Override
									public void actionPerformed(ActionEvent e) {
										
										_controlador.registro(_userText.getText(), _passwordText.getText(),
												_fecha.getText(), _ruta.getText()
												);
										
										JOptionPane.showMessageDialog(null,
												"Usuario creado",
												"Infor",
												JOptionPane.INFORMATION_MESSAGE
												);
										
										cerrarVentana();
										
									}			
								});
						
						
						
						panel.add(loginButton);
						
					}
				
					
				}
				
	/*----------------------------------------------------------------------------------*/
				

}








