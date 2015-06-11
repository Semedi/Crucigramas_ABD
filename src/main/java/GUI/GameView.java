package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Transfer.Word;
import Controller.Controlador;
import es.ucm.abd.crossword.CrosswordPanel;
import es.ucm.abd.crossword.CrosswordPanelEventListener;


/* CLase vista de los crucigramas */
public class GameView extends JFrame {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<Word> _lista; 
	private CrosswordPanel<Word> _panel;
	private Controlador _controlador;
	
	private int _selected;
	
	//components...
	private JTextArea _texto;
	private JTextField _respuesta;
	private JButton _aceptar;
	private JButton _enviar;
	private JLabel _xletras;
	
	private boolean _acertadas[];
	
	
	
	public GameView(Controlador controlador, String Crucigrama) {
		super(Crucigrama);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JPanel juego = new JPanel();
		juego.setLayout(new BorderLayout());
		
		// Creamos la lista inicial con tres palabras
		_lista = new LinkedList<Word>();
		_controlador = controlador;
		_selected = -1;
		
		
		/*CONSTRUIMOS EL PANEL DE ARRIBA (juego)*/
		/********************************************************************************************************/
		
		_lista = _controlador.getPalabras(Crucigrama);
	
		
		if (_lista.size()>0)
			_acertadas=_controlador.getAcertadas(_lista.get(0).getIdC(), _lista);
		
	
		// Creamos el CrosswordPanel a partir de la lista.
		// Lo incrustamos en un JScrollPane para obtener barras de desplazamiento
		JScrollPane jScrollPane = new JScrollPane();
		juego.add(jScrollPane);		
		_panel = new CrosswordPanel<Word>(jScrollPane, _lista);
		jScrollPane.setViewportView(_panel);
		
		
		
		// Registramos los manejadores de eventos del CrosswordPanel
        _panel.addEventListener(new CrosswordPanelEventListener<Word>() {
            public void wordSelected(CrosswordPanel<Word> source, Word newPalabra) {
                if (newPalabra != null) {
                	_texto.setText(newPalabra.getTexto());
                	
                	_xletras.setText(newPalabra.getWord().length()+" letras");
                	_selected=newPalabra.geti();
                	
                
                } else {
                    _selected = -1;
                }
            }

            public void cellSelected(CrosswordPanel<Word> source, Point newCell) {
                if (newCell != null) {
                    System.out.println("Seleccionada la celda (" + newCell.x + ", " + newCell.y + ")");
                } else {
                    System.out.println("Deseleccionada celda");
                }
            }

            public void deselected(CrosswordPanel<Word> source) {
                System.out.println("DeselecciÃ³n!");
            }
        });
        
        
    	for (int i = 0; i < _acertadas.length; i++){
			if (_acertadas[i])
				_panel.showWord(_lista.get(i));		
		}
			
        
        
        // AÃ±adimos un botÃ³n para mostrar las palabras del crucigrama
		JButton botonMostrar = new JButton("Mostrar palabras");
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < _lista.size(); i++)
					_panel.showWord(_lista.get(i));
			
			}
		});
		
		
		juego.add(botonMostrar, BorderLayout.SOUTH);
		
		
		/*********************************************************************************************************************/
		
		
		/*CONSTRUIMOS EL PANEL DE ABAJO, para poder dar las respuestas*/
		/************************************************************************************************************************/
		JPanel interfaz = new JPanel();
		interfaz.setLayout(new BorderLayout());
		
		_texto = new JTextArea();
		_texto.setPreferredSize(new Dimension(80, 50));
		
		
		
		
		
		/*Panel auxiliar para la respuesta*/
			JPanel aux = new JPanel();
			aux.setLayout(new FlowLayout());
			
			_respuesta = new JTextField(15);
			_aceptar = new JButton("aceptar");
			_enviar = new JButton("enviar amigo");
			_xletras = new JLabel("x letras");
			
			
			_aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					if(!_acertadas[_selected]){
						if (_respuesta.getText().equalsIgnoreCase(_lista.get(_selected).getWord())){
							_panel.showWord(_lista.get(_selected));
							
							_acertadas[_selected] = true;
						}
							
						
						else{
							
							JOptionPane.showMessageDialog(null,
									"Palabra incorrecta",
									"Info",
									JOptionPane.INFORMATION_MESSAGE
									);
							
						}
						_controlador.answer(null, _respuesta.getText(), _lista.get(_selected).getIdP(), _lista.get(_selected).getIdC());
				
					}else{
						
						JOptionPane.showMessageDialog(null,
								"ya respondida",
								"Info",
								JOptionPane.INFORMATION_MESSAGE
								);
						
						
					}
					
			
				
				
				}
			});
			
			
			
		
			aux.add(_xletras);
			aux.add(_respuesta);
			
			aux.add(_aceptar);
			aux.add(_enviar);
		/**/
			
			interfaz.add(_texto, BorderLayout.CENTER);
			interfaz.add(aux, BorderLayout.SOUTH);
			

		/*************************************************************************************************************************/
		
		
		this.add(juego, BorderLayout.NORTH);
		this.add(interfaz, BorderLayout.CENTER);
		this.setSize(600, 800);

	}
	

}
