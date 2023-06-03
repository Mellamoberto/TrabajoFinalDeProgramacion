package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import clases.Usuario;
import excepciones.PassInvalidaException;
import excepciones.UsuarioNoExisteException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PantallaRegistro extends JPanel {
	private Ventana ventana;
	private JTextField campoEmail;
	private JTextField campoNick;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField campoPassword;

	public PantallaRegistro(Ventana v) {
		setBackground(new Color(192, 192, 192));
		this.ventana = v;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, -145, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 65, 0, 0, 0, 35, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel labelTitulo = new JLabel("Registrate");
		labelTitulo.setForeground(new Color(255, 255, 255));
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
		gbc_labelTitulo.gridheight = 2;
		gbc_labelTitulo.gridwidth = 3;
		gbc_labelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitulo.gridx = 1;
		gbc_labelTitulo.gridy = 0;
		add(labelTitulo, gbc_labelTitulo);

		JLabel labelEmail = new JLabel("Email");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.WEST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 2;
		add(labelEmail, gbc_labelEmail);

		campoEmail = new JTextField();
		GridBagConstraints gbc_campoEmail = new GridBagConstraints();
		gbc_campoEmail.gridwidth = 2;
		gbc_campoEmail.insets = new Insets(0, 0, 5, 5);
		gbc_campoEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoEmail.gridx = 2;
		gbc_campoEmail.gridy = 2;
		add(campoEmail, gbc_campoEmail);
		campoEmail.setColumns(10);

		JLabel labelNick = new JLabel("Nick");
		GridBagConstraints gbc_labelNick = new GridBagConstraints();
		gbc_labelNick.anchor = GridBagConstraints.WEST;
		gbc_labelNick.insets = new Insets(0, 0, 5, 5);
		gbc_labelNick.gridx = 1;
		gbc_labelNick.gridy = 3;
		add(labelNick, gbc_labelNick);

		campoNick = new JTextField();
		GridBagConstraints gbc_campoNick = new GridBagConstraints();
		gbc_campoNick.gridwidth = 2;
		gbc_campoNick.insets = new Insets(0, 0, 5, 5);
		gbc_campoNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNick.gridx = 2;
		gbc_campoNick.gridy = 3;
		add(campoNick, gbc_campoNick);
		campoNick.setColumns(10);

		JLabel passLabel = new JLabel("Password");
		GridBagConstraints gbc_passLabel = new GridBagConstraints();
		gbc_passLabel.anchor = GridBagConstraints.WEST;
		gbc_passLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passLabel.gridx = 1;
		gbc_passLabel.gridy = 4;
		add(passLabel, gbc_passLabel);

		campoPassword = new JPasswordField();
		GridBagConstraints gbc_campoPassword = new GridBagConstraints();
		gbc_campoPassword.gridwidth = 2;
		gbc_campoPassword.insets = new Insets(0, 0, 5, 5);
		gbc_campoPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoPassword.gridx = 2;
		gbc_campoPassword.gridy = 4;
		add(campoPassword, gbc_campoPassword);

		JLabel labelModerador = new JLabel("¿Es moderador?");
		GridBagConstraints gbc_labelModerador = new GridBagConstraints();
		gbc_labelModerador.insets = new Insets(0, 0, 5, 5);
		gbc_labelModerador.gridx = 1;
		gbc_labelModerador.gridy = 5;
		add(labelModerador, gbc_labelModerador);

		JRadioButton radioSi = new JRadioButton("Si");
		buttonGroup.add(radioSi);
		radioSi.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_radioSi = new GridBagConstraints();
		gbc_radioSi.insets = new Insets(0, 0, 5, 5);
		gbc_radioSi.gridx = 2;
		gbc_radioSi.gridy = 5;
		add(radioSi, gbc_radioSi);

		JRadioButton radioNo = new JRadioButton("No");
		buttonGroup.add(radioNo);
		radioNo.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_radioNo = new GridBagConstraints();
		gbc_radioNo.insets = new Insets(0, 0, 5, 5);
		gbc_radioNo.gridx = 3;
		gbc_radioNo.gridy = 5;
		add(radioNo, gbc_radioNo);

		JButton botonRegistrar = new JButton("Registrarse");
		botonRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String nombre = campoNick.getText().toString();
					String pass = new String(campoPassword.getPassword());
					String email = campoEmail.getText();
					boolean esModerador = false;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String fechaRegistroTexto = LocalDate.now().format(formatter);
					LocalDate fechaRegistro = LocalDate.parse(fechaRegistroTexto, formatter);
					if (radioSi.isSelected()) {
						esModerador = true;
					} else if (radioNo.isSelected()) {
						esModerador = false;
					}
					;

					new Usuario(nombre, email, pass, fechaRegistro, esModerador);
					JOptionPane.showMessageDialog(ventana, "Registrado correctamente", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
					ventana.cambiarAPantalla(PantallaLogin.class);
				} catch (SQLIntegrityConstraintViolationException e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "No se puede conectar a la BD",
							JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(ventana, "El email ya existe", "No se pudo registrar",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e3) {
					e3.printStackTrace();
				}
			}
		});
		botonRegistrar.setForeground(new Color(255, 255, 255));
		botonRegistrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		botonRegistrar.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.gridwidth = 3;
		gbc_botonRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrar.gridx = 1;
		gbc_botonRegistrar.gridy = 6;
		add(botonRegistrar, gbc_botonRegistrar);

		JButton botonLimpiar = new JButton("Limpiar formulario");
		botonLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoNick.setText("");
				campoEmail.setText("");
				campoPassword.setText("");
				buttonGroup.clearSelection();
			}
		});
		GridBagConstraints gbc_botonLimpiar = new GridBagConstraints();
		gbc_botonLimpiar.gridwidth = 3;
		gbc_botonLimpiar.fill = GridBagConstraints.BOTH;
		gbc_botonLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_botonLimpiar.gridx = 1;
		gbc_botonLimpiar.gridy = 8;
		add(botonLimpiar, gbc_botonLimpiar);

		JButton botonAtras = new JButton("Cancelar");
		botonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaLogin.class);
			}
		});
		GridBagConstraints gbc_botonAtras = new GridBagConstraints();
		gbc_botonAtras.gridwidth = 3;
		gbc_botonAtras.fill = GridBagConstraints.VERTICAL;
		gbc_botonAtras.insets = new Insets(0, 0, 5, 5);
		gbc_botonAtras.gridx = 1;
		gbc_botonAtras.gridy = 10;
		add(botonAtras, gbc_botonAtras);
	}

}
