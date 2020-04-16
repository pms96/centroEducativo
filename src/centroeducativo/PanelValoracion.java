package centroeducativo;
//holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaweeeeeeeeeeeeeeeeeeeeeeeeeeeeee
import java.awt.BorderLayout;      
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Curso;
import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.CursoControlador;
import model.controladores.EstudianteControlador;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;
import model.controladores.ValoracionMateriaControlador;
import utils.CacheImagenes;

public class PanelValoracion extends JPanel {
	
	public static int SAVE = 1;
	private Dimension minimaDimensionJTextField  = new Dimension (150,20) ;
	
	static final int MIN = 0;
	static final int MAX = 10;
	static final int INIT = 0;
	
	private JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	private JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	
	JButton jbtRefrescarAlumno = new JButton("Refrescar");
	JButton jbtAniadirUno = new JButton(" > ");
	JButton jbtAniadirTodos = new JButton(" >> ");
	JButton jbtQuitarUno = new JButton(" < ");
	JButton jbtQuitarTodos = new JButton(" << ");
	JLabel jlNota = new JLabel();
	JSlider jsNota = new JSlider (MIN, MAX, INIT);
	
	Materia materia = null;
	Profesor profesor = null;

	DefaultListModel<Estudiante> lmDisponibles = new DefaultListModel<Estudiante>();
	JList<Estudiante> jListDisponibles = new JList<Estudiante>(lmDisponibles);
	JScrollPane jScrollDisponibles = new JScrollPane(jListDisponibles);
	
	DefaultListModel<Estudiante> lmSeleccionados = new DefaultListModel<Estudiante>();
	JList<Estudiante> jListSeleccionados = new JList<Estudiante>(lmSeleccionados);
	JScrollPane jScrollSeleccionados = new JScrollPane(jListSeleccionados);
	
	
	public PanelValoracion () {
		super();
		this.setLayout(new BorderLayout());
		this.add(getPanelGestion(),BorderLayout.CENTER);
		
	
		
	}
	
	private JPanel getPanelGestion() {
		
		JPanel panelGestion = new JPanel();
		panelGestion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		List<Materia> materia = MateriaControlador.getInstancia().findAllMaterias();
		for (Materia mat : materia) {
			jcbMateria.addItem(mat);
		}
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Materia: "),c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jcbMateria.setMinimumSize(minimaDimensionJTextField);
		panelGestion.add(jcbMateria, c);
		
		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesores();
		for (Profesor prof : profesores) {
			jcbProfesor.addItem(prof);
		}
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Profesor: "),c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jcbProfesor.setMinimumSize(minimaDimensionJTextField);
		panelGestion.add(jcbProfesor, c);
		
		c.gridy = 3;
		c.gridx = 0;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add( new JLabel ("nota: "), c);
		
		c.gridy= 3;
		c.gridx = 0;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(jlNota, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 3;
		c.gridx = 1;
		c.weighty = 0.75;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jsNota, c);
		
		
		jsNota.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
			 jlNota.setText(String.valueOf(jsNota.getValue()));
			}	
		});
		
		
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Fecha: "), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(getDatePersonalizado(), c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(jbtRefrescarAlumno, c);		
		
		jbtRefrescarAlumno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				List<Estudiante> todosEstudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
				for (Estudiante e : todosEstudiantes) {
					lmDisponibles.addElement(e);
				}


			}
		});
			
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		panelGestion.add(getPanelAlumnos(),c);
		

		c.gridx = 1;
		c.gridy = 15;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		JButton jbtGuardar  = new JButton("Guardar");
		panelGestion.add(jbtGuardar, c);
		

		
		return panelGestion;
		
	}
	
	
	
	public JPanel getPanelAlumnos() {
		JPanel panelAlumnos = new JPanel();
		panelAlumnos.setLayout(new GridBagLayout());
		GridBagConstraints a = new GridBagConstraints();
		//panelAlumnos.setBorder(new LineBorder(Color.BLACK));
		
		a.gridx = 0;
		a.gridy = 0;
		a.anchor = GridBagConstraints.CENTER;
		panelAlumnos.add(new JLabel(" Lista alumnos "), a);
		
		a.gridx = 2;
		a.gridy = 0;
		a.anchor = GridBagConstraints.CENTER;
		panelAlumnos.add(new JLabel(" Lista alumnos a evaluar "), a);
		
		a.gridx = 0;
		a.gridy = 1;
		a.gridwidth = 1;
		a.anchor = GridBagConstraints.NORTHEAST;
		jScrollDisponibles.setPreferredSize(new Dimension(250,250));
		panelAlumnos.add(jScrollDisponibles, a);

		
		a.gridx = 1;
		a.gridy = 1;
		a.gridwidth = 1;
		a.anchor = GridBagConstraints.CENTER;
		panelAlumnos.add(getjpBotones(),a);
		
		
		a.gridy = 1;
		a.gridx = 2;
		a.gridwidth = 1;
		a.anchor = GridBagConstraints.NORTHWEST;
		jScrollSeleccionados.setPreferredSize(new Dimension(250,250));
		panelAlumnos.add(jScrollSeleccionados,a);
		
		return panelAlumnos;
		
	}
	
	
	public JPanel getjpBotones() {
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridBagLayout());
		GridBagConstraints b = new GridBagConstraints();
		//panelBotones.setBorder(new LineBorder(Color.BLACK));
		b.gridx = 0;
		b.gridy = 0;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtAniadirTodos, b);
		
		b.gridx = 0;
		b.gridy = 1;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtAniadirUno, b);
		
		jbtAniadirUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int indicesSeleccionados[] = jListDisponibles.getSelectedIndices();
				for (int i = indicesSeleccionados.length - 1; i > -1; i--) {
					Estudiante est = lmDisponibles.elementAt(indicesSeleccionados[i]);
					lmSeleccionados.addElement(est);
					lmDisponibles.removeElement(est);
				}
			}
		});
		
		b.gridx = 0;
		b.gridy = 2;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtQuitarUno, b);
		
		b.gridx = 0;
		b.gridy = 3;
		b.anchor = GridBagConstraints.CENTER;
		panelBotones.add(jbtQuitarTodos, b);
		
		return panelBotones;
	}
	

	private JFormattedTextField getDatePersonalizado() {
		JFormattedTextField jftf = new JFormattedTextField(new JFormattedTextField.AbstractFormatter() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					return sdf.format(((Date) value));
				}
				return "";
			}

			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					return sdf.parse(text);
				} catch (Exception e) {
					return null;
				}
			}
		});
		jftf.setColumns(20);
		jftf.setValue(new Date());
		jftf.setMinimumSize(minimaDimensionJTextField);
		return jftf;
	}
	


	private class EstudianteJSpinner extends JSpinner {
		
		Estudiante estudiante = null;
		
		public EstudianteJSpinner (Estudiante estudiante) {
			super();
			this.estudiante = estudiante;
		}
		
		
	}
	
//	private JSlider jsNota (int vertical, int min, int max, int init ) {
//		JSlider js = new JSlider(vertical, min, max, init);
//		js.setMajorTickSpacing(10);
//		js.setMinorTickSpacing(0);
//		js.setPaintTicks(true);
//		js.setPaintLabels(true);
//		return js;
//	}

}
