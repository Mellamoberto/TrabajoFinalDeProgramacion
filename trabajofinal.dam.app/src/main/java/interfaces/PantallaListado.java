package interfaces;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class PantallaListado extends JPanel {
	private Ventana ventana;
	
	public PantallaListado(Ventana v) {
		this.ventana=v;
		setLayout(new BorderLayout(0, 0));
		
		JLabel labelBienvenido = new JLabel("Bienvenid@, "+ventana.usuarioLogado.getNombre());
		add(labelBienvenido, BorderLayout.NORTH);
		
		JScrollPane listaVideogame = new JScrollPane();
		add(listaVideogame, BorderLayout.CENTER);
		
		JPanel contenedorElementos = new JPanel();
		listaVideogame.setViewportView(contenedorElementos);
		contenedorElementos.setLayout(new BoxLayout(contenedorElementos, BoxLayout.X_AXIS));
		
		for(byte i=0;i<5;i++) {
			contenedorElementos.add(new ElementoListaUsuario(ventana, ventana.usuarioLogado));
		}
		
	}

}
