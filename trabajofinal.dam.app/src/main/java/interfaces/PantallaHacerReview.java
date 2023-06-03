package interfaces;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import clases.Videojuego;
import utils.DAO;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JList;

public class PantallaHacerReview extends JPanel {
	private Ventana ventana;
	private JTextField campoCalificacion;
	private JTextField campoDuracion;
	private JTextField campoComentario;
	private DefaultListModel<String> listModel;
	private JTextField campoNombre;
	private JList<String> list;

	public PantallaHacerReview(Ventana v) throws SQLException {
		this.ventana = v;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel labelReview = new JLabel("Review");
		labelReview.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GridBagConstraints gbc_labelReview = new GridBagConstraints();
		gbc_labelReview.gridwidth = 2;
		gbc_labelReview.gridheight = 2;
		gbc_labelReview.insets = new Insets(0, 0, 5, 5);
		gbc_labelReview.gridx = 3;
		gbc_labelReview.gridy = 1;
		add(labelReview, gbc_labelReview);

		JLabel labelCalificacion = new JLabel("Calificacion");
		GridBagConstraints gbc_labelCalificacion = new GridBagConstraints();
		gbc_labelCalificacion.anchor = GridBagConstraints.WEST;
		gbc_labelCalificacion.insets = new Insets(0, 0, 5, 5);
		gbc_labelCalificacion.gridx = 3;
		gbc_labelCalificacion.gridy = 3;
		add(labelCalificacion, gbc_labelCalificacion);

		campoCalificacion = new JTextField();
		campoCalificacion.setText("");
		GridBagConstraints gbc_campoCalificacion = new GridBagConstraints();
		gbc_campoCalificacion.insets = new Insets(0, 0, 5, 5);
		gbc_campoCalificacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoCalificacion.gridx = 4;
		gbc_campoCalificacion.gridy = 3;
		add(campoCalificacion, gbc_campoCalificacion);
		campoCalificacion.setColumns(10);

		JLabel labelNombreReview = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombreReview = new GridBagConstraints();
		gbc_labelNombreReview.anchor = GridBagConstraints.WEST;
		gbc_labelNombreReview.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombreReview.gridx = 3;
		gbc_labelNombreReview.gridy = 4;
		add(labelNombreReview, gbc_labelNombreReview);

		campoNombre = new JTextField();
		GridBagConstraints gbc_campoNombre = new GridBagConstraints();
		gbc_campoNombre.insets = new Insets(0, 0, 5, 5);
		gbc_campoNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNombre.gridx = 4;
		gbc_campoNombre.gridy = 4;
		add(campoNombre, gbc_campoNombre);
		campoNombre.setColumns(10);

		JLabel labelComentario = new JLabel("Comentario");
		GridBagConstraints gbc_labelComentario = new GridBagConstraints();
		gbc_labelComentario.anchor = GridBagConstraints.WEST;
		gbc_labelComentario.insets = new Insets(0, 0, 5, 5);
		gbc_labelComentario.gridx = 3;
		gbc_labelComentario.gridy = 5;
		add(labelComentario, gbc_labelComentario);

		campoComentario = new JTextField();
		GridBagConstraints gbc_campoComentario = new GridBagConstraints();
		gbc_campoComentario.gridwidth = 2;
		gbc_campoComentario.insets = new Insets(0, 0, 5, 5);
		gbc_campoComentario.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoComentario.gridx = 4;
		gbc_campoComentario.gridy = 5;
		add(campoComentario, gbc_campoComentario);
		campoComentario.setColumns(10);

		JLabel labelDuracion = new JLabel("Duracion (min)");
		GridBagConstraints gbc_labelDuracion = new GridBagConstraints();
		gbc_labelDuracion.anchor = GridBagConstraints.WEST;
		gbc_labelDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDuracion.gridx = 3;
		gbc_labelDuracion.gridy = 6;
		add(labelDuracion, gbc_labelDuracion);

		campoDuracion = new JTextField();
		GridBagConstraints gbc_campoDuracion = new GridBagConstraints();
		gbc_campoDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_campoDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoDuracion.gridx = 4;
		gbc_campoDuracion.gridy = 6;
		add(campoDuracion, gbc_campoDuracion);
		campoDuracion.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 7;
		gbc_scrollPane.gridwidth = 2;
		add(scrollPane, gbc_scrollPane);

		listModel = new DefaultListModel<>();

		List<String> juegos = DAO.obtenerListaJuegos();
		for (String juego : juegos) {
			listModel.addElement(juego);
		}

		JButton botonReview = new JButton("HACER REVIEW");
		botonReview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String juegoSeleccionado = list.getSelectedValue();
				String nombre = campoNombre.getText();
				float calificacion = Float.parseFloat(campoCalificacion.getText());
				String comentario = campoComentario.getText();
				int duracion = Integer.parseInt(campoDuracion.getText());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date fechaCalificacion = new Date();
				String fechaCalificacionStr = dateFormat.format(fechaCalificacion);

				try {
					HashMap<String, Object> columnas = new HashMap<>();
					columnas.put("usuario_email", ventana.usuarioLogado.getEmail());
					columnas.put("videojuego_nombre", juegoSeleccionado);
					columnas.put("nombre", nombre);
					columnas.put("comentario", comentario);
					columnas.put("calificacion", calificacion);
					columnas.put("duracion", duracion);
					columnas.put("fecha_calificacion", fechaCalificacionStr);

					DAO.insertar("review", columnas);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		JLabel labelElegirJuego = new JLabel("Elige el videojuego");
		GridBagConstraints gbc_labelElegirJuego = new GridBagConstraints();
		gbc_labelElegirJuego.insets = new Insets(0, 0, 5, 5);
		gbc_labelElegirJuego.gridx = 2;
		gbc_labelElegirJuego.gridy = 8;
		add(labelElegirJuego, gbc_labelElegirJuego);

		list = new JList<>(listModel);
		JScrollPane scrollPaneList = new JScrollPane(list);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridx = 3;
		gbc_list.gridy = 8;
		gbc_list.gridwidth = 2;
		gbc_list.fill = GridBagConstraints.BOTH; 
		add(scrollPaneList, gbc_list); 

		GridBagConstraints gbc_botonReview = new GridBagConstraints();
		gbc_botonReview.gridwidth = 2;
		gbc_botonReview.insets = new Insets(0, 0, 0, 5);
		gbc_botonReview.gridx = 3;
		gbc_botonReview.gridy = 9;
		add(botonReview, gbc_botonReview);
	}
}