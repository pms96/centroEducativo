package centroeducativo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.sun.xml.xsom.impl.scd.ParseException;

import model.Estudiante;
import model.Profesor;
import model.controladores.EstudianteControlador;
import model.controladores.ProfesorControlador;
import utils.CacheImagenes;

public class PanelGestionEstudiantes extends JPanel {
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	Estudiante actual = null;
	PanelDatosPersonales getPanelDatosPersonales = new PanelDatosPersonales();

	public PanelGestionEstudiantes () {
		this.actual = EstudianteControlador.getInstancia().findFirst();
		this.setLayout(new BorderLayout());
		this.add(getToolBar(),BorderLayout.NORTH);
		this.add(getPanelDatosPersonales,BorderLayout.CENTER);
		cargarDatosActual();
	}
	
	private JToolBar getToolBar() {
		JToolBar toolBar = new JToolBar();
		
		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", LOAD_FIRST);
		toolBar.add(jbtPrimero);
		
		JButton jbtPrevio = new JButton();
		asignarFuncion(jbtPrevio, "previous.png", LOAD_PREV);
		toolBar.add(jbtPrevio);
		
		JButton jbtSiguiente = new JButton();
		asignarFuncion(jbtSiguiente, "next.png", LOAD_NEXT);
		toolBar.add(jbtSiguiente);
		
		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", LOAD_LAST);
		toolBar.add(jbtUltimo);
		
		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", NEW);
		toolBar.add(jbtNuevo);
		
		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", SAVE);
		toolBar.add(jbtGuardar);
		
		JButton jbtEliminar = new JButton();
		asignarFuncion(jbtEliminar, "eliminar.png", REMOVE);
		toolBar.add(jbtEliminar);
		
		
		return toolBar;
			
	}
	private void nuevo () {
		this.getPanelDatosPersonales.limpiarPantalla();
		this.actual = new Estudiante();
		JOptionPane.showMessageDialog(this, "Introduce los nuevos campos");
	}
	
	private void guardar() throws ParseException{
		Estudiante nuevoRegistro = new Estudiante();
		
		nuevoRegistro.setNombre(this.getPanelDatosPersonales.getNombre());
		nuevoRegistro.setApellido1(this.getPanelDatosPersonales.getApellido1());
		nuevoRegistro.setApellido2(this.getPanelDatosPersonales.getApellido2());
		nuevoRegistro.setDni(this.getPanelDatosPersonales.getDni());
		nuevoRegistro.setDireccion(this.getPanelDatosPersonales.getDireccion());
		nuevoRegistro.setTelefono(this.getPanelDatosPersonales.getTelefono());
		nuevoRegistro.setTipologiasexo(this.getPanelDatosPersonales.getTipologiaSexo());
		nuevoRegistro.setColor(this.getPanelDatosPersonales.getElegirColor());
		nuevoRegistro.setImagen(this.getPanelDatosPersonales.getImagen());
		
		if(nuevoRegistro.getId()==0) {
			EstudianteControlador.getInstancia().persist(nuevoRegistro);
		}
		else {
			EstudianteControlador.getInstancia().merge(nuevoRegistro);
		}
		
		JOptionPane.showMessageDialog(this, "Guardado");
		this.actual = nuevoRegistro;
	}
	
	private Estudiante eliminar () {
		String respuestas[] = new String[] {"si", "no"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "Desea eliminar?", "Eliminar Registro",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION, 
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Estudiante nuevoAMostrar = EstudianteControlador.getInstancia().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = EstudianteControlador.getInstancia().findNext(actual);
			}
			EstudianteControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminado");
			
			if (nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			}
			else {
				getPanelDatosPersonales.limpiarPantalla();
			}
		}
		return actual;
	}
	
	
	private void asignarFuncion (JButton jbt, String icono ,final int funcion) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Estudiante obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = EstudianteControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = EstudianteControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = EstudianteControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = EstudianteControlador.getInstancia().findLast();
				if (funcion == NEW)
					nuevo();
				if (funcion == SAVE)
					try {
						guardar();
					}catch (ParseException e) {
						e.printStackTrace();
					}
				if (funcion == REMOVE)
					obtenido = eliminar();
				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
				
			}});
		}

	private void cargarDatosActual () {
		if (this.actual != null) {
			getPanelDatosPersonales.setId(String.valueOf(this.actual.getId()));
			getPanelDatosPersonales.setNombre(this.actual.getNombre());
			getPanelDatosPersonales.setApellido1(this.actual.getApellido1());
			getPanelDatosPersonales.setApellido2(this.actual.getApellido2());
			getPanelDatosPersonales.setDni(this.actual.getDni());
			getPanelDatosPersonales.setDireccion(this.actual.getDireccion());
			getPanelDatosPersonales.setTelefono(this.actual.getTelefono());
			getPanelDatosPersonales.setEmail(this.actual.getEmail());
			getPanelDatosPersonales.setElegirColor(this.actual.getColor());
			getPanelDatosPersonales.setImagen(this.actual.getImagen());


		}
	}

}
