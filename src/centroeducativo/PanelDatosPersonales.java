package centroeducativo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import model.Tipologiasexo;
import model.controladores.TipologiaSexoControlador;
import utils.CacheImagenes;





public class PanelDatosPersonales extends JPanel {
	
	JTextField jtfId = new JTextField(10);
	JTextField jtfNombre = new JTextField(20);
	JTextField jtfApellido1 = new JTextField(20);
	JTextField jtfApellido2 = new JTextField(20);
	JTextField jtfDni = new JTextField(9);
	JTextField jtfDireccion = new JTextField(20);
	JTextField jtfEmail = new JTextField(20);
	JTextField jtfTelefono = new JTextField(9);
	JTextField jtfColor = new JTextField(10);
	
	JButton jbtInsertarImg;
	JButton jbtElegirColor = new JButton("Selectiona el color");
	
	JColorChooser jColorChooser;
	JFileChooser jfileChooser = new JFileChooser();
	JScrollPane jspane = new JScrollPane(new JLabel());
	byte[] imagen;
	
	JComboBox<Tipologiasexo> jcbSexo = new JComboBox<Tipologiasexo>();
	JLabel jlblColorElegido = new JLabel();
	JPanel jpParaColorear = new JPanel();
	Dimension minimaDimensionJTextField = new Dimension(150,20);

	
	public PanelDatosPersonales() {
		
		//JPanel panelGestion = new JPanel();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();


		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Identificador: "), c);

		c.gridx = 1;
		jtfId.setEnabled(false);
		jtfId.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);

		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Nombre: "), c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jtfNombre.setMinimumSize(minimaDimensionJTextField);
		this.add(jtfNombre, c);

		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Primer Apellido: "), c);

		c.gridx = 1;
		jtfApellido1.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido1, c);

		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Segundo Apellido: "), c);

		c.gridx = 1;
		jtfApellido2.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido2, c);
		
		List<Tipologiasexo> tipo = TipologiaSexoControlador.getInstancia().findAllTipologiaSexo();
		for (Tipologiasexo t : tipo) {
			jcbSexo.addItem(t);
		}
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Sexo: "), c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		jcbSexo.setMinimumSize(minimaDimensionJTextField);
		this.add(jcbSexo, c);

		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Dni: "), c);

		c.gridx = 1;
		jtfDni.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDni, c);

		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Dirección: "), c);

		c.gridx = 1;
		jtfDireccion.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDireccion, c);

		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Teléfono: "), c);

		c.gridx = 1;
		jtfTelefono.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfTelefono, c);

		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Email: "), c);

		c.gridx = 1;
		jtfEmail.setMinimumSize(minimaDimensionJTextField);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfEmail, c);

		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Color ventana: "), c);

		c.gridy = 4;
		c.gridx = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(jtfColor, c);

		c.gridy = 5;
		c.gridx = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(jbtElegirColor, c);

		jbtElegirColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelColorChooser();

			}

		});
		
		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		jspane.setPreferredSize( new Dimension(250, 250));
		this.add(jspane, c);
		
		jbtInsertarImg = new JButton("Insertar imagen");
		jbtInsertarImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionaFichero(getImagen());
			}
		});
		
		jbtInsertarImg.setPreferredSize( new Dimension(250, 20));
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		this.add(jbtInsertarImg, c);

		


	}   

	/**
	 * 
	 * @return
	 */

	public String getId() {
		return jtfId.getText();
	}

	public void setId(String id) {
		this.jtfId.setText(id);
	}

	public String getNombre() {
		return this.jtfNombre.getText();
	}

	public void setNombre(String nombre) {
		this.jtfNombre.setText(nombre);
	}

	public String getApellido1() {
		return this.jtfApellido1.getText();
	}

	public void setApellido1(String apellido1) {
		this.jtfApellido1.setText(apellido1);
	}

	public String getApellido2() {
		return this.jtfApellido2.getText();
	}

	public void setApellido2(String apellido2) {
		this.jtfApellido2.setText(apellido2);
	}

	public String getDireccion() {
		return this.jtfDireccion.getText();
	}

	public void setDireccion(String direccion) {
		this.jtfDireccion.setText(direccion);
	}

	public String getDni() {
		return this.jtfDni.getText();
	}

	public void setDni(String dni) {
		this.jtfDni.setText(dni);
	}

	public String getEmail() {
		return this.jtfEmail.getText();
	}

	public void setEmail(String email) {
		this.jtfEmail.setText(email);
	}

	public String getTelefono() {
		return this.jtfTelefono.getText();
	}

	public void setTelefono(String telefono) {
		this.jtfTelefono.setText(telefono);
	}

	public Tipologiasexo getTipologiaSexo() {
		return (Tipologiasexo) this.jcbSexo.getSelectedItem();
	}

	public void setTipologiaSexo(Tipologiasexo sexoEscogido) {
		if (sexoEscogido == null && this.jcbSexo.getItemCount() > 0) {
			this.jcbSexo.setSelectedIndex(0);
		} else {
			for (int i = 0; i < this.jcbSexo.getItemCount(); i++) {
				Tipologiasexo sexoEnJCombo = this.jcbSexo.getItemAt(i);
				if (sexoEscogido.getId() == sexoEnJCombo.getId()) {
					this.jcbSexo.setSelectedIndex(i);
				}
			}
		}
	}

	public String getElegirColor() {
		return jtfColor.getText();
	}

	public void setElegirColor(String colorElegido) {
		this.jtfColor.setText(colorElegido);
		try {
			this.setBackground(Color.decode(colorElegido));
		} catch (Exception e) {
			System.out.println("No cambio");
			this.setBackground(Color.WHITE);
		}
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void seleccionarImagen() {
		this.jfileChooser = new JFileChooser();

	}

	public void panelColorChooser() {

		Color color = jColorChooser.showDialog(null, "Selecciona un color", Color.WHITE);

		if (color != null) {
			String strColor = "#" + Integer.toHexString(color.getRGB()).substring(2);
			this.jtfColor.setText(strColor);

			this.setBackground(color);

		}

	}

	protected JScrollPane scrollPane(String imagen) {

		JLabel jlb = new JLabel(CacheImagenes.getCacheImagenes().getIcono(imagen));

		JScrollPane jsp = new JScrollPane(jlb);
		return jsp;

	}


	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
		if (imagen != null && imagen.length > 0) {
			ImageIcon icono = new ImageIcon(this.imagen);
			JLabel jlblIcono = new JLabel(icono);
			jlblIcono.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseDragged(e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseEntered(e);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseExited(e);
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseMoved(e);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseReleased(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					// TODO Auto-generated method stub
					super.mouseWheelMoved(e);
				}

				private void mostrarMenu(MouseEvent e) {
					if (e.isPopupTrigger()) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(new JMenuItem(icono.getIconWidth() + "x" + icono.getIconHeight() + "pixeles"));
						menu.addSeparator();
						JMenuItem miImagenSeleccionada = new JMenuItem("Seleccionar una imagen");
						miImagenSeleccionada.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								//seleccionarImagen();
								seleccionaFichero(getImagen());

							}
						});
						menu.add(miImagenSeleccionada);
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			});
			this.jspane.setViewportView(jlblIcono);
		} else {
			JLabel jlblIcono = new JLabel("No hay imagen");
			this.jspane.setViewportView(jlblIcono);
		}
	}


	private byte[] seleccionaFichero(byte[] imagenActual)  {
		this.jfileChooser = new JFileChooser();
		byte[] imagenSeleccionada = null;

		this.jfileChooser.setCurrentDirectory(new File("C:\\"));
		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Archivos de imagen *.jpg , .png , .jpeg o .gif";
			}

			@Override
			public boolean accept(File f) {
				if (f.isFile() && f.getAbsolutePath().endsWith(".jpg") || f.getAbsolutePath().endsWith(".png")
						|| f.getAbsolutePath().endsWith(".jpeg") || f.getAbsolutePath().endsWith(".gif"));
					
				return true;

			}
		});

		int seleccionUsuario = jfileChooser.showOpenDialog(null);

		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File fichero = this.jfileChooser.getSelectedFile();


			if (fichero.isFile()) {
				try {
					imagenSeleccionada = Files.readAllBytes(fichero.toPath());
					ImageIcon imagenProvisional = new ImageIcon(imagenSeleccionada);
					if (imagenProvisional.getIconWidth() > 800 || imagenProvisional.getIconHeight() > 800) {
						JOptionPane.showMessageDialog(null, "La imagen es demasiado grande");
						return imagenActual;
					}
					setImagen(imagenSeleccionada);
					return imagenSeleccionada;
					

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		imagenSeleccionada = imagenActual;
		
		return imagenActual;
	}


	public void limpiarPantalla() {

		this.jtfNombre.setText("");
		this.jtfNombre.setText("");
		this.jtfApellido1.setText("");
		this.jtfApellido2.setText("");
		this.jtfDni.setText("");
		this.jtfDireccion.setText("");
		this.jtfTelefono.setText("");
		this.jtfEmail.setText("");
		this.jcbSexo.setSelectedIndex(0);
		this.jspane.resetKeyboardActions();
	}

}
