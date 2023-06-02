package interfaces;

import javax.swing.JPanel;

import clases.Usuario;
import clases.Videojuego;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ElementosListaResultados extends JPanel {
    Ventana ventana;
    Usuario usuarioActual;
    JLabel labelTitulo;
    JLabel labelImagen;
    JLabel labelPlataforma;

    public ElementosListaResultados(Ventana v, Usuario u, Videojuego videojuego) {
        this.ventana = v;
        this.usuarioActual = u;

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        labelTitulo = new JLabel(videojuego.getNombre());
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

        labelImagen = new JLabel(videojuego.getImagen());
        GridBagConstraints gbc_labelImagen = new GridBagConstraints();
        gbc_labelImagen.gridx = 2;
        gbc_labelImagen.gridy = 4;
        add(labelImagen, gbc_labelImagen);

        labelPlataforma = new JLabel(videojuego.getPlataforma().toString());
        GridBagConstraints gbc_labelPlataforma = new GridBagConstraints();
        gbc_labelPlataforma.gridwidth = 2;
        gbc_labelPlataforma.gridx = 2;
        gbc_labelPlataforma.gridy = 5;
        add(labelPlataforma, gbc_labelPlataforma);
        
        
        
        
        
        ventana = v;
        usuarioActual = u;
    }

    public void setVideojuego(Videojuego videojuego, String paginaActual) {
        labelTitulo.setText(videojuego.getNombre());
        labelImagen.setText(videojuego.getImagen().toString());
        labelPlataforma.setText(videojuego.getPlataforma().toString());
        if (paginaActual.equals("PantallaResultado")) {
            // Configuración específica para PantallaResultado
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ventana.cambiarAPantalla(PantallaResultado.class);
                    // Aquí puedes pasar el videojuego como parámetro a la pantalla de detalles
                }
            });
        } else {
            // Otros casos y configuraciones según la página
        }
    }
    
}
