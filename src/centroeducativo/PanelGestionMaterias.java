package centroeducativo;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.Curso;
import model.Materia;
import model.controladores.CursoControlador;
import model.controladores.MateriaControlador;
import utils.CacheImagenes;

public class PanelGestionMaterias extends JPanel{
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	
	
	private JTextField jtfId = new JTextField(5);
	private JTextField jtfNombre = new JTextField(20);
	private JTextField jtfAcronimo = new JTextField(20);
	private JComboBox<Curso> jcbCurso = new JComboBox<Curso>();
	private Dimension minimaDimensionJTextField  = new Dimension (150,20);

	
	Materia actual = null;

	public PanelGestionMaterias () {
		super();
		this.setLayout(new BorderLayout());
		this.add(getToolBar(),BorderLayout.NORTH);
		this.add(getPanelGestion(),BorderLayout.CENTER);
		actual = MateriaControlador.getInstancia().findFirst();
		cargarDatosActual();
	}
	
	
	
	private JPanel getPanelGestion() {
		JPanel panelGestion = new JPanel();
		panelGestion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Identificador: "),c);
		
		c.gridx = 1;
		jtfId.setEnabled(false);
		jtfId.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfId, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Nombre: "),c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jtfNombre.setMinimumSize(minimaDimensionJTextField);
		panelGestion.add(jtfNombre, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Acrónimo: "),c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jtfAcronimo.setMinimumSize(minimaDimensionJTextField);
		panelGestion.add(jtfAcronimo, c);
		
		List<Curso> cursos = CursoControlador.getInstancia().findAllCursos();
		for (Curso cur : cursos) {
			jcbCurso.addItem(cur);
		}
		
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Curso: "),c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jcbCurso.setMinimumSize(minimaDimensionJTextField);
		panelGestion.add(jcbCurso, c);
		
		return panelGestion;
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
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Introduce los nuevos campos");
	}
	
	
	private void limpiarPantalla( ) {
		this.jtfId.setText("");
		this.jtfNombre.setText("");
		this.jtfAcronimo.setText("");
		this.jcbCurso.setSelectedIndex(0);


	}

	private void guardar() {
		Materia nuevoRegistro = new Materia();
		
		if(this.jtfId.getText().trim().equals(""))nuevoRegistro.setId(0);
		else
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));
		
		nuevoRegistro.setNombre(this.jtfNombre.getText());
		nuevoRegistro.setAcronimo(this.jtfAcronimo.getText());
		nuevoRegistro.setCurso((Curso)this.jcbCurso.getSelectedItem());

		
		if(nuevoRegistro.getId()==0) {
			CursoControlador.getInstancia().persist(nuevoRegistro);
		}
		else {
			CursoControlador.getInstancia().merge(nuevoRegistro);
		}
		
		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado");
		
		this.actual = nuevoRegistro;
	}
	
	private Materia eliminar () {
		String respuestas[] = new String[] {"si", "no"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "Desea eliminar?", "Eliminar Registro",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION, 
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Materia nuevoAMostrar = MateriaControlador.getInstancia().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = MateriaControlador.getInstancia().findNext(actual);
			}
			CursoControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminado");
			
			if (nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			}
			else {
				limpiarPantalla();
			}
		}
		return actual;
	}
	
	
	private void asignarFuncion (JButton jbt, String icono ,final int funcion) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Materia obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = MateriaControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = MateriaControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = MateriaControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = MateriaControlador.getInstancia().findLast();
				if (funcion == NEW)
					nuevo();
				if (funcion == SAVE)
					guardar();
				if (funcion == REMOVE)
					obtenido = eliminar();
				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
				
			}});
		}

	/**
	 * 
	 */
	private void cargarDatosActual () {
		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfNombre.setText(this.actual.getNombre());
			this.jtfAcronimo.setText(this.actual.getAcronimo());
			this.jcbCurso.setSelectedItem(this.actual.getCurso());

		}
	}

}
