package GUI;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import model.Word;

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

	
	private List<Word> _lista; 
	private CrosswordPanel<Word> _panel;
	private Controlador _controlador;
	
	
	public GameView(Controlador controlador, String Crucigrama) {
		super(Crucigrama);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creamos la lista inicial con tres palabras
		_lista = new LinkedList<Word>();
		_controlador = controlador;
		
		
		Word[] palabras = controlador.getPalabras(Crucigrama);
		
		
		
		
		final Word word1 = new Word(1,2,"ERLANG",true);
		final Word word2 = new Word(4,1,"HASKELL",false);
		final Word word3 = new Word(1,6,"SCALA",true);
		_lista.add(word1);
		_lista.add(word2);
		_lista.add(word3);
		
		// Creamos el CrosswordPanel a partir de la lista.
		// Lo incrustamos en un JScrollPane para obtener barras de desplazamiento
		JScrollPane jScrollPane = new JScrollPane();
		this.add(jScrollPane);		
		_panel = new CrosswordPanel<Word>(jScrollPane, _lista);
		jScrollPane.setViewportView(_panel);
		
		// Registramos los manejadores de eventos del CrosswordPanel
        _panel.addEventListener(new CrosswordPanelEventListener<Word>() {
            public void wordSelected(CrosswordPanel<Word> source, Word newPalabra) {
                if (newPalabra != null) {
                    System.out.println("Seleccionada la palabra " + newPalabra.getWord());
                } else {
                    System.out.println("Deseleccionada palabra");
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
