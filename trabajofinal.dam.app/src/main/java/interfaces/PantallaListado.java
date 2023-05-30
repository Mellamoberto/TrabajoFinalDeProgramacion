package interfaces;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class PantallaListado extends JPanel {
	private Ventana ventana;
	
	public PantallaListado(Ventana v) {
		this.ventana=v;
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane listaVideogame = new JScrollPane();
		add(listaVideogame, BorderLayout.CENTER);
		
		JPanel contenedorElementos = new JPanel();
		listaVideogame.setViewportView(contenedorElementos);
		GridBagLayout gbl_contenedorElementos = new GridBagLayout();
		gbl_contenedorElementos.columnWidths = new int[]{0, -221, 0, 0, 0, 0};
		gbl_contenedorElementos.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contenedorElementos.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contenedorElementos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contenedorElementos.setLayout(gbl_contenedorElementos);
		
		JLabel labelBienvenido = new JLabel("Bienvenid@ a Daily-Plays, "+ventana.usuarioLogado.getNombre());
		GridBagConstraints gbc_labelBienvenido = new GridBagConstraints();
		gbc_labelBienvenido.insets = new Insets(0, 0, 5, 5);
		gbc_labelBienvenido.gridx = 1;
		gbc_labelBienvenido.gridy = 1;
		contenedorElementos.add(labelBienvenido, gbc_labelBienvenido);
		
		JButton button = new JButton("New button");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		contenedorElementos.add(button, gbc_button);
		
		JButton labelMiUsuario = new JButton("Mi Usuario");
		GridBagConstraints gbc_labelMiUsuario = new GridBagConstraints();
		gbc_labelMiUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelMiUsuario.gridx = 3;
		gbc_labelMiUsuario.gridy = 1;
		contenedorElementos.add(labelMiUsuario, gbc_labelMiUsuario);
		
		for(byte i=0;i<5;i++) {
			contenedorElementos.add(new ElementoListaUsuario(ventana, ventana.usuarioLogado));
		}
		
	}

}
