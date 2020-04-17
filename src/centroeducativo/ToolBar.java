package centroeducativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import utils.CacheImagenes;

public class ToolBar extends JToolBar{

	public ToolBar () {
        //this.add(creaSalir("", "exit.png", "Salir"));
        this.addSeparator();
        this.add(creaBoton("Curso", "exit.png", "Curso", new PanelGestionCursosAcademicos() ));
        this.add(creaBoton("Materias", "exit.png", "Materias",  new PanelGestionMaterias()));
        this.add(creaBoton("Estudiante", "exit.png", "Estudiante", new PanelGestionEstudiantes()));
        this.add(creaBoton("Profesores", "exit.png", "Profesores", new PanelGestionProfesores()));
        this.add(creaBoton("Valoracion", "exit.png", "Valoracion", new PanelValoracionEstudiante()));
        this.add(creaBoton("Valoracion Maxiva", "exit.png", "Valoracion Maxiva", new PanelValoracion()));

	}
	
	private JButton creaBoton( String titulo, String icono, String toolTip, JPanel pane) {
        JButton jbt = new JButton();
        
        jbt.setText(titulo);
        jbt.setToolTipText(toolTip);
        
        jbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Has hecho clic en el botón: \"" + toolTip + "\"");
            	JDialog dialogo = new JDialog();
            	dialogo.setResizable(true);
            	dialogo.setTitle(titulo);
            	dialogo.setContentPane(pane);
            	dialogo.pack();
            	dialogo.setModal(true);
            	dialogo.setVisible(true);
            }
        });
        
        try {
        	jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));  
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
        return jbt;
	}
}
