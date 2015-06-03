package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Controller.Controlador;
import Observer.FriendObserver;

/* Vista para la pestaña de los amigos*/
public class FriendView extends JPanel implements FriendObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
				private class AddFriend extends JFrame{
					
					private JTextField _userText;
					private Controlador _controlador;
					
					
					private AddFriend(Controlador controlador){
						
						super("añadir amigo");
						_controlador = controlador;
						this.setSize(400, 150);
						this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						this.setLocation(500,280);
			
						/*Panel donde vamos a poner todos los componentes*/
						JPanel principal = new JPanel();
						this.build(principal);
						
					
						this.add(principal);
						
						this.setVisible(true);
						
					
					}
					
					private void cerrarVentana(){
						this.dispose();
					}
					
					private void build(JPanel panel) {
			
						panel.setLayout(new FlowLayout());
			
						/* etiqueta usuario*/
						JLabel userLabel = new JLabel("Nombre de usuario:");
						panel.add(userLabel);
			
						/* Campo a rellenar al lado de la etiqueta usuario*/
						this._userText = new JTextField(20);
						this._userText.setBounds(130, 10, 160, 25);
						panel.add(this._userText);
			
						
						/*Los dos botones: Aceptar y Nuevo usuario*/
						JButton boton1 = new JButton("Añadir");
						
						
						boton1.addActionListener(
								new ActionListener() {					
									@Override
									public void actionPerformed(ActionEvent e) {
										
										/* LLamar al controlador*/
										
										_controlador.addFriend(_userText.getText());
										
										cerrarVentana();
									}			
								});
						
						
						
						panel.add(boton1);
					}
			
					
					
					
				}
				
				
/*----------------------------------------------------------------------------*/
				
	private Controlador _controlador;
	private JList<String> _lista;
	private  DefaultListModel<String> _model;

	public FriendView(Controlador controlador){
		
		
		super();
		_controlador=controlador;
		
		controlador.AddObserver(this);
		this.setLayout(new BorderLayout());
		
		  _model = new DefaultListModel<String>();
		 
		 List<Object> amigos = controlador.getAmigos();
		 
		 for (int i= 0 ; i < amigos.size();i++)
			 _model.add(0, (String)amigos.get(i));
		 
		 
		  _lista = new JList<String>(_model);
		 
		 
		 JScrollPane scrollAmigos = new JScrollPane(_lista);
		 scrollAmigos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollAmigos.setPreferredSize(new Dimension(0,400));	
		 
		 
		 JPanel _botones = new JPanel();
		 _botones.setLayout(new FlowLayout());
		 
		 JButton boton1 = new JButton("Añadir amigo");
		 _botones.add(boton1);
		 
		 
		 JButton boton2 = new JButton("Eliminar");
		 
		 _botones.add(boton2);
		 
		 
		 boton2.addActionListener(
					new ActionListener() {					
						@Override
						public void actionPerformed(ActionEvent e) {
							
							_controlador.eliminarAmigo(_lista.getSelectedValue());
							
						}			
					});
		 
		 
		 
		 
		 boton1.addActionListener(
					new ActionListener() {					
						@Override
						public void actionPerformed(ActionEvent e) {
							
							new AddFriend(_controlador);
							
						}			
					});
		 
		 
		 
		 
		 
		 this.add(scrollAmigos, BorderLayout.NORTH);
		 this.add(_botones, BorderLayout.CENTER);
		 
				
		
	}

	@Override
	public void onAdd(String amigo) {
		// TODO Auto-generated method stub
		
		_model.add(0, amigo);
		_lista.setModel(_model);
		
	}

	@Override
	public void onDelete(String amigo) {
		// TODO Auto-generated method stub
		_model.clear();
		
		 List<Object> amigos = _controlador.getAmigos();
		 
		 for (int i= 0 ; i < amigos.size();i++)
			 _model.add(0, (String)amigos.get(i));
		 
		_lista.setModel(_model);
	}
}
