package GUI;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import model.Palabra;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Controller.Controlador;
import es.ucm.abd.crossword.CrosswordPanel;
import es.ucm.abd.crossword.CrosswordPanelEventListener;


/* CLase vista de los crucigramas */
public class GameView extends JFrame {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<Palabra> _lista; 
	private CrosswordPanel<Palabra> _panel;
	private Controlador _controlador;
	
	
	public GameView(Controlador controlador, String Crucigrama) {
		super(Crucigrama);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creamos la lista inicial con tres palabras
		_lista = new LinkedList<Palabra>();
		_controlador = controlador;
		
		
		Palabra[] palabras = controlador.getPalabras(Crucigrama);
		
		
		
		
		final Palabra word1 = new Palabra(1,2,"ERLANG",true);
		final Palabra word2 = new Palabra(4,1,"HASKELL",false);
		final Palabra word3 = new Palabra(1,6,"SCALA",true);
		_lista.add(word1);
		_lista.add(word2);
		_lista.add(word3);
		
		// Creamos el CrosswordPanel a partir de la lista.
		// Lo incrustamos en un JScrollPane para obtener barras de desplazamiento
		JScrollPane jScrollPane = new JScrollPane();
		this.add(jScrollPane);		
		_panel = new CrosswordPanel<Palabra>(jScrollPane, _lista);
		jScrollPane.setViewportView(_panel);
		
		// Registramos los manejadores de eventos del CrosswordPanel
        _panel.addEventListener(new CrosswordPanelEventListener<Palabra>() {
            public void wordSelected(CrosswordPanel<Palabra> source, Palabra newPalabra) {
                if (newPalabra != null) {
                    System.out.println("Seleccionada la palabra " + newPalabra.getWord());
                } else {
                    System.out.println("Deseleccionada palabra");
                }
            }

            public void cellSelected(CrosswordPanel<Palabra> source, Point newCell) {
                if (newCell != null) {
                    System.out.println("Seleccionada la celda (" + newCell.x + ", " + newCell.y + ")");
                } else {
                    System.out.println("Deseleccionada celda");
                }
            }

            public void deselected(CrosswordPanel<Palabra> source) {
                System.out.println("DeselecciÃ³n!");
            }
        });
        
        // AÃ±adimos un botÃ³n para mostrar las palabras del crucigrama
		JButton botonMostrar = new JButton("Mostrar palabras");
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < _lista.size(); i++)
					_panel.showWord(_lista.get(i));
			
			}
		});
		
		
		this.add(botonMostrar, BorderLayout.SOUTH);
		this.setSize(500, 500);
	}
	

}
