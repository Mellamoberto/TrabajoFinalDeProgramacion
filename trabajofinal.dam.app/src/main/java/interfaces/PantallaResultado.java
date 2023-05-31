package interfaces;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class PantallaResultado extends JPanel {
	private Ventana ventana;
	
	public PantallaResultado(Ventana v) {
		this.ventana=v;
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel labelResultados = new JLabel("RESULTADOS");
		add(labelResultados, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
	}
	

}
