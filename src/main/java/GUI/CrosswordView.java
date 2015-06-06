package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Controller.Controlador;
import Observer.CrosswordObserver;

public class CrosswordView extends JPanel implements CrosswordObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*Clase anidada, es una ventana emergente que nos permitira buscar crucigramas y añadirlos*/
	/* --------------------------------------------------------------------------------------------*/
					private class SearchCrossword extends JFrame{
						
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						private JTextField _userText;
						Controlador _controlador;
						 DefaultListModel<String> _model;
						 JList<String> _lista;
						
						private SearchCrossword(Controlador controlador){
							super("Buscar crucigrama");
							
							_controlador=controlador;
							this.setSize(500, 300);
							this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							this.setLocation(500,280);
				
							
							
							
							
							/*Panel donde vamos a poner todos los componentes*/
							JPanel principal = new JPanel();
							this.build(principal);
							
						
							this.add(principal);
							
							this.setVisible(true);
							
						
						}
						
						private void build(JPanel panel) {
				
							panel.setLayout(new FlowLayout());
				
							/* etiqueta usuario*/
							JLabel userLabel = new JLabel("Nombre de crucigrama:");
							panel.add(userLabel);
				
							/* Campo a rellenar al lado de la etiqueta usuario*/
							this._userText = new JTextField(20);
							this._userText.setBounds(130, 10, 160, 25);
							panel.add(this._userText);
				
							
							/*Los dos botones: Aceptar y Nuevo usuario*/
							JButton boton1 = new JButton("Buscar");
							JButton boton2 = new JButton("Añadir");
							
							boton1.addActionListener(
									new ActionListener() {					
										@Override
										public void actionPerformed(ActionEvent e) {
											
											/* LLamar al controlador*/
											String[] datos = _controlador.buscarCrucigramas(_userText.getText());
											_model.clear();
											for (int i = 0; i < datos.length; i++){
												_model.add(0, datos[i]);
												
											}
											
										}			
									});
							
							
							boton2.addActionListener(
									new ActionListener() {					
										@Override
										public void actionPerformed(ActionEvent e) {
											
											/* LLamar al controlador*/
											_controlador.addCrucigrama(_lista.getSelectedValue());
											
										}			
									});
							
							  _model = new DefaultListModel<String>();
							 _model.add(0, "ejemplo");
							
							
							 _lista = new JList<String>(_model);
							 _lista.setPreferredSize(new Dimension(400,400));
							 
							 
							 JScrollPane scroll = new JScrollPane(_lista);
							 scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
							 //scroll.setPreferredSize(new Dimension(0400,400));	
							
							
							panel.add(boton1);
							panel.add(scroll);
							panel.add(boton2);
						}
				
						
					}
					
					
	/*-----------------------------------------------------------------------------------------------------------------*/
	
	
	/* atributos del jframe*/
	private JTable _tablaCruci;
	private DefaultTableModel _model;
	private Controlador _controlador;
	
	
	
	public CrosswordView(Controlador controlador){
		
		super();
		
		controlador.AddObserver(this);
		this.setLayout(new BorderLayout());
		_controlador=controlador;
		
		Object[][] datos = controlador.getCrucigramas();

		
		String[] columns = {"Titulo", "Fecha" };
		_model = new DefaultTableModel(datos, columns);
		
		_tablaCruci = new JTable(_model);
		
		
		JScrollPane scrollCrucigramas = new JScrollPane(_tablaCruci);
		scrollCrucigramas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollCrucigramas.setPreferredSize(new Dimension(0,400));
		
		
		 JPanel botones3 = new JPanel();
		 botones3.setLayout(new FlowLayout());
		 
		 
		 /* Boton para abrir el crucigrama */
		 JButton boton1 = new JButton("Abrir crucigrama");
		 
		 boton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String crucigrama =(String)_tablaCruci.getValueAt(_tablaCruci.getSelectedRow(), 0);
					new GameView(_controlador, crucigrama).setVisible(true);
					
				}
			});
		 
		 JButton boton2 = new JButton("Busqueda de crucigramas");
		 
		 boton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					new SearchCrossword(_controlador);
					
				}
			});
		 
		 botones3.add(boton1);
		 botones3.add(boton2);
		 
		 
		 this.add(scrollCrucigramas, BorderLayout.NORTH);
		 this.add(botones3, BorderLayout.CENTER);
		
		
	}
	@Override
	public void OnAdd(String[] Crucigrama) {
		// TODO Auto-generated method stub
		_model.addRow(Crucigrama);
		
		_tablaCruci.setModel(_model);
		
		
	}

}
