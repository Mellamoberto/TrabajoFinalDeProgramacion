package interfaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import clases.Usuario;
import excepciones.PassInvalidaException;
import excepciones.UsuarioNoExisteException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PantallaLogin extends JPanel{
	private JTextField campoUsuario;
	private JPasswordField campoPassword;
	private Ventana ventana;
	
	public PantallaLogin(Ventana v) {
		setBackground(new Color(224, 255, 255));
		this.ventana=v;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{-20, 0, -90, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton botonLogin = new JButton("INICIAR SESION");
		botonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				String email = campoUsuario.getText();
				String pass = new String (campoPassword.getPassword());
				System.out.println(email+" : "+pass);
					ventana.usuarioLogado=new Usuario(email,pass);
					JOptionPane.showMessageDialog(ventana, "Bienvenid@, "
					+ventana.usuarioLogado.getNombre(), "Inicio de sesion realizado",
					JOptionPane.INFORMATION_MESSAGE);
					ventana.cambiarAPantalla(PantallaInicio.class);
				} catch (SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(ventana, "El email ya existe", "No se pudo registrar",
					JOptionPane.ERROR_MESSAGE);
				}catch (SQLException e3) {
					JOptionPane.showMessageDialog(ventana, e3.getMessage(),
					"Login fallido", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioNoExisteException e4) {
					JOptionPane.showMessageDialog(ventana, "El email con el que estas intentando "
					+ "acceder no existe. Intentalo con otro emai", "Email no valido",
					JOptionPane.ERROR_MESSAGE);
				} catch (PassInvalidaException e5) {
					JOptionPane.showMessageDialog(ventana, "Password incorrecta", "Password no valida",
					JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonLogin.setBackground(Color.GREEN);
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel labelTitulo = new JLabel("DAILY-PLAYS");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Georgia", Font.PLAIN, 33));
		GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
		gbc_labelTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_labelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitulo.gridwidth = 3;
		gbc_labelTitulo.gridx = 1;
		gbc_labelTitulo.gridy = 1;
		add(labelTitulo, gbc_labelTitulo);
		
		JLabel labelEmail = new JLabel("Correo Usuario");
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.fill = GridBagConstraints.BOTH;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 2;
		add(labelEmail, gbc_labelEmail);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_campoUsuario = new GridBagConstraints();
		gbc_campoUsuario.fill = GridBagConstraints.BOTH;
		gbc_campoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_campoUsuario.gridwidth = 3;
		gbc_campoUsuario.gridx = 1;
		gbc_campoUsuario.gridy = 3;
		add(campoUsuario, gbc_campoUsuario);
		campoUsuario.setColumns(10);
		
		JLabel labelPassword = new JLabel("Contrase\u00F1a");
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.fill = GridBagConstraints.BOTH;
		gbc_labelPassword.insets = new Insets(0, 0, 5, 5);
		gbc_labelPassword.gridx = 1;
		gbc_labelPassword.gridy = 4;
		add(labelPassword, gbc_labelPassword);
		
		campoPassword = new JPasswordField();
		GridBagConstraints gbc_campoPassword = new GridBagConstraints();
		gbc_campoPassword.fill = GridBagConstraints.BOTH;
		gbc_campoPassword.insets = new Insets(0, 0, 5, 5);
		gbc_campoPassword.gridwidth = 3;
		gbc_campoPassword.gridx = 1;
		gbc_campoPassword.gridy = 5;
		add(campoPassword, gbc_campoPassword);
		
		JButton botonRegistrar = new JButton("REGISTRARSE");
		botonRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaRegistro.class);
			}
		});
		botonRegistrar.setForeground(Color.WHITE);
		botonRegistrar.setBackground(Color.BLUE);
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.fill = GridBagConstraints.BOTH;
		gbc_botonRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrar.gridx = 1;
		gbc_botonRegistrar.gridy = 6;
		add(botonRegistrar, gbc_botonRegistrar);
		GridBagConstraints gbc_botonLogin = new GridBagConstraints();
		gbc_botonLogin.insets = new Insets(0, 0, 5, 5);
		gbc_botonLogin.fill = GridBagConstraints.BOTH;
		gbc_botonLogin.gridx = 3;
		gbc_botonLogin.gridy = 6;
		add(botonLogin, gbc_botonLogin);
	}
}