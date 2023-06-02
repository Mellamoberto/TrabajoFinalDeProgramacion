package interfaces;

import javax.swing.JPanel;

import clases.Usuario;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ElementosListaResultados extends JPanel {
	Ventana ventana;
	
	Usuario usuarioActual;
	
	public ElementosListaResultados(Ventana v, Usuario u) {
	    this.ventana = v;
	    this.usuarioActual = u;

	    GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
	    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    setLayout(gridBagLayout);


	    JLabel labelTitulo = new JLabel(usuarioActual.getEmail());
	    GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
	    gbc_labelTitulo.gridx = 2;
	    gbc_labelTitulo.gridy = 2;
	    add(labelTitulo, gbc_labelTitulo);
	    
	    

	    JLabel separador = new JLabel();
	    GridBagConstraints gbc_separador = new GridBagConstraints();
	    gbc_separador.gridx = 2;
	    gbc_separador.gridy = 3;
	    gbc_separador.insets = new Insets(30, 0, 0, 0); // Ajusta el margen del espacio adicional
	    gbc_separador.weighty = 1.0; // Permite que el separador ocupe espacio vertical
	    add(separador, gbc_separador);
	    
	    
	    

	    JLabel labelImagen = new JLabel(usuarioActual.getPass());
	    GridBagConstraints gbc_labelImagen = new GridBagConstraints();
	    gbc_labelImagen.gridx = 2;
	    gbc_labelImagen.gridy = 4;
	    add(labelImagen, gbc_labelImagen);
	    
	    
	    

	    JLabel labelPlataforma = new JLabel(usuarioActual.getNombre());
	    GridBagConstraints gbc_labelPlataforma = new GridBagConstraints();
	    gbc_labelPlataforma.gridwidth = 2;
	    gbc_labelPlataforma.gridx = 2;
	    gbc_labelPlataforma.gridy = 5;
	    add(labelPlataforma, gbc_labelPlataforma);

	    ventana = v;
	    usuarioActual = u;
	}
}
