package interfaces;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Insets;

import clases.Videojuego;
import excepciones.UsuarioNoExisteException;
import excepciones.VideojuegoNoExisteException;

public class PantallaVideojuegos extends JPanel {
	private Ventana ventana;
	
	
	public PantallaVideojuegos(Ventana v) {
		this.ventana=v;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		JButton botonInsertarBD = new JButton("Insertar VG en BD");
		botonInsertarBD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaInsertarJuegoEnBD.class);
			}
		});
		GridBagConstraints gbc_botonInsertarBD = new GridBagConstraints();
		gbc_botonInsertarBD.gridwidth = 2;
		gbc_botonInsertarBD.insets = new Insets(0, 0, 0, 5);
		gbc_botonInsertarBD.gridx = 3;
		gbc_botonInsertarBD.gridy = 8;
		add(botonInsertarBD, gbc_botonInsertarBD);
	
	}
	
}
