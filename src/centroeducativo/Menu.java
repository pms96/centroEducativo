package centroeducativo;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.KeyStroke;

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	public Menu () {
		// Men� Archivo de la aplicaci�n
        JMenu menuDirectorio = new JMenu("Men�");
        menuDirectorio.add(crearNuevoMenuJItem("Gesti�n Cursos", new PanelGestionCursosAcademicos(), KeyStroke.getKeyStroke(KeyEvent.VK_1, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        menuDirectorio.add(crearNuevoMenuJItem("Gestion Mat�rias", new PanelGestionMaterias(), KeyStroke.getKeyStroke(KeyEvent.VK_2, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        menuDirectorio.add(crearNuevoMenuJItem("Gesti�n Estudiantes",  new PanelGestionEstudiantes(), KeyStroke.getKeyStroke(KeyEvent.VK_3, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        menuDirectorio.add(crearNuevoMenuJItem("Gesti�n Profesores", new PanelGestionProfesores(), KeyStroke.getKeyStroke(KeyEvent.VK_4, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        menuDirectorio.add(crearNuevoMenuJItem("Gesti�n Valoraci�n", new PanelValoracionEstudiante(), KeyStroke.getKeyStroke(KeyEvent.VK_5, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        menuDirectorio.add(crearNuevoMenuJItem("Gesti�n M�xima", new PanelValoracion(), KeyStroke.getKeyStroke(KeyEvent.VK_6, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));

        menuDirectorio.addSeparator();
        //menuDirectorio.add(crearNuevoMenuJItem("Salir", KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        
        this.add(menuDirectorio);
        
	}
	

	
	/**
	 * Men� Item para salir de la aplicaci�n
	 * @return
	 */
	private JMenuItem crearNuevoMenuJItem (String titulo, JPanel panel, KeyStroke atajoTeclado) {
        JMenuItem item = new JMenuItem(titulo);
        item.setAccelerator(atajoTeclado);
        item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				dialogo.setResizable(true);
				dialogo.setTitle(titulo);
				dialogo.setContentPane(panel);
				dialogo.pack();
				dialogo.setModal(true);
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
				dialogo.setVisible(true);
				 System.out.println("Han hecho clic en: " + titulo);
			}
		});
        
        return item;
	}
	
}