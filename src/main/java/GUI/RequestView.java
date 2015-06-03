package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Controller.Controlador;
import Observer.RequestObserver;

/*Ventana de peticiones de ayuda*/
public class RequestView extends JPanel implements RequestObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private JTable _listaPeticiones;
	private Controlador _controlador;
	DefaultTableModel _tmodel;
	String[] _columnNames= {"Usuario","Crucigrama"};
	
	public RequestView(Controlador controlador){
		
		
		super();
		_controlador = controlador;
		this.setLayout(new BorderLayout());
		
		controlador.AddObserver(this);
		
		
		Object[][] datos ={
				{"Kosmo", "Banderas"},
				{"Kosmo", "Crucigramas del dia"}					
		};
		
		Object[][]datos2 = controlador.getAyuda();
		
		_tmodel = new DefaultTableModel(datos2,_columnNames);
		
		
		
		
		_listaPeticiones = new JTable(_tmodel);
		//_listaPeticiones.setPreferredSize(new Dimension(0,400));
		
		JScrollPane scrollPeticiones = new JScrollPane(_listaPeticiones);
		scrollPeticiones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPeticiones.setPreferredSize(new Dimension(0,400));
		
		 JPanel _botones2 = new JPanel();
		 _botones2.setLayout(new FlowLayout());
		 
		 JButton boton1 = new JButton("Abrir crucigrama");
		 JButton boton2 = new JButton("Descartar peticion");
		 
		 _botones2.add(boton1);
		 _botones2.add(boton2);
		 
		 boton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String crucigrama =(String)_listaPeticiones.getValueAt(_listaPeticiones.getSelectedRow(), 1);
					new GameView(_controlador, crucigrama).setVisible(true);
					
				}
			});
		 
		 
		 boton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] ayuda = new String[2];
					
					ayuda[0]=(String)_listaPeticiones.getValueAt(_listaPeticiones.getSelectedRow(), 0);
					ayuda[1]=(String)_listaPeticiones.getValueAt(_listaPeticiones.getSelectedRow(), 1);
					

					_controlador.borraAyuda(ayuda);
					

					
					
				}
			});
		 
		 
		 
		
		this.add(scrollPeticiones, BorderLayout.NORTH);
		this.add(_botones2, BorderLayout.CENTER);
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
			Object[][]datos = _controlador.getAyuda();
		
			_tmodel = new DefaultTableModel(datos,_columnNames);
		
			_listaPeticiones.setModel(_tmodel);;
	}
	
}
