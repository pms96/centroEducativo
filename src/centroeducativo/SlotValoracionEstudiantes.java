package centroeducativo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.ValoracionMateriaControlador;

public class SlotValoracionEstudiantes extends JPanel {
	private JSpinner spinner;
	Estudiante estudiante = null;
	Materia materia = null;
	Profesor profesor = null;
	
	public SlotValoracionEstudiantes(Estudiante estudiante, Profesor profesor, Materia materia) {
		this.estudiante = estudiante;
		this.profesor = profesor;
		this.materia = materia;
		GridBagLayout gribBagLayout = new GridBagLayout();
		gribBagLayout.columnWidths = new int [] {0,150};
		setLayout(gribBagLayout);
		
		JLabel jlabelNombre = new JLabel(estudiante.getNombre() + " " + estudiante.getApellido1() + " " + estudiante.getApellido2());
		GridBagConstraints gbcjabelNombre = new GridBagConstraints();
		gbcjabelNombre.insets = new Insets (0,0,0,5);
		gbcjabelNombre.anchor = GridBagConstraints.EAST;
		gbcjabelNombre.gridx = 0;
		gbcjabelNombre.gridy = 0;
		add(jlabelNombre, gbcjabelNombre);
		
		spinner = new JSpinner();
		this.cargarNotasDelEstudiante();
		GridBagConstraints gbcTextFiel = new GridBagConstraints();
		gbcTextFiel.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFiel.gridx = 1;
		gbcTextFiel.gridy = 0;
		add(spinner, gbcTextFiel);
		
		}
	
	
		private void cargarNotasDelEstudiante () {
		Valoracionmateria valoracionActual = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(profesor, materia, estudiante);
		if (valoracionActual != null) {
			this.spinner.setValue(valoracionActual.getValoracion());
			
			}
		
		}
		
		
		private void guardarValoracion () {
			Valoracionmateria valoracionActual = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(profesor, materia, estudiante);
			if (valoracionActual == null) {
				valoracionActual = new Valoracionmateria();
				valoracionActual.setProfesor(this.profesor);
				valoracionActual.setMateria(this.materia);
				valoracionActual.setEstudiante(this.estudiante);
			}
			
			if (this.spinner.getValue()instanceof Float) {
				valoracionActual.setValoracion(((Float)this.spinner.getValue()).floatValue());
			}
		    else if (this.spinner.getValue()instanceof Integer) {
		    	valoracionActual.setValoracion(((Integer) this.spinner.getValue()).floatValue());
			}
			
			if (valoracionActual.getId()== 0) {
				ValoracionMateriaControlador.getInstancia().persist(valoracionActual);	
			}
			else {
				ValoracionMateriaControlador.getInstancia().merge(valoracionActual);
			}
		}

}
