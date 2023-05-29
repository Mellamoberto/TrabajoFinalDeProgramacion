package interfaces;

import javax.swing.JPanel;

import clases.Usuario;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;

public class ElementoListaUsuario extends JPanel {
	Ventana ventana;
	Usuario usuarioActual;
	public ElementoListaUsuario(Ventana v, Usuario u) {
		this.ventana=v;
		this.usuarioActual=u;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 101, 0, 75, 0, 59, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelEmail = new JLabel(usuarioActual.getEmail());
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 3;
		gbc_labelEmail.gridy = 2;
		add(labelEmail, gbc_labelEmail);
		
		JButton btnNewButton = new JButton(usuarioActual.getNombre());
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 2;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 3;
		gbc_labelNombre.gridy = 3;
		add(labelNombre, gbc_labelNombre);
		ventana=v;
		usuarioActual=u;
		
	}
	
	
}
