package mx.com.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import mx.com.hotel.controller.GuestController;
import mx.com.hotel.model.Guest;
import mx.com.hotel.model.Reservation;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JButton btnGuardar; 
	private JButton btnAgregarOtro; 
	private Reservation reservation; 
	private GuestController guestController; 
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	/*//Por ahora no necesito estas líneas ya que esta página se ejecutará solo después de reservas.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param reservationId 
	 */
	public RegistroHuesped(Reservation reservation) {
		this.reservation = reservation; 
		setStyle();
		setFormFields();
		setFormActions();
		guestController = new GuestController();
	}
	
	private void setStyle() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color (30,26,26));
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setBounds(100, 100, 910, 634);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/mx/com/hotel/image/logo-50px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
						
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setBackground(new Color(84,84,84));
		header.setLayout(null);
		header.setOpaque(false);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		
		
		final JPanel btnAtras = new JPanel();
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.setBackground(new Color(30,26,26));
		btnAtras.setLayout(null);
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(30,26,26));
			}
		});
		
		JLabel labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(new Color(127,127,127));
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		
		final JPanel btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(84,84,84));
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(new Color(84,84,84));
			}
		});
		
		JLabel labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setForeground(new Color(217, 217, 217));
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(84, 84, 84));
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/mx/com/hotel/image/registro.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/mx/com/hotel/image/logo-100px.png")));
		
		btnAtras.add(labelAtras);
		header.add(btnAtras);
		panel.add(imagenFondo);
		panel.add(logo);
		btnexit.add(labelExit);
		contentPane.add(btnexit);
		contentPane.add(header);
		contentPane.add(panel);
	}
	
	private void setFormFields() {
		
		JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
		JLabel lblNombre = new JLabel("NOMBRE");
		JLabel lblApellido = new JLabel("APELLIDO");
		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		JLabel lblTelefono = new JLabel("TELÉFONO");
		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		
		lblTitulo.setBounds(606, 55, 234, 42);
		lblNombre.setBounds(562, 119, 253, 14);
		lblApellido.setBounds(560, 189, 255, 14);
		lblFechaN.setBounds(560, 256, 255, 14);
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblTelefono.setBounds(562, 406, 253, 14);
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		
		lblTitulo.setForeground(new Color(93, 152, 48));
		lblNombre.setForeground(new Color(217, 217, 217));
		lblApellido.setForeground(new Color(217, 217, 217));
		lblFechaN.setForeground(new Color(217, 217, 217));
		lblNacionalidad.setForeground(new Color(217, 217, 217));
		lblTelefono.setForeground(new Color(217, 217, 217));
		lblNumeroReserva.setForeground(new Color(217, 217, 217));
		
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));

		contentPane.add(lblTitulo);
		contentPane.add(lblNombre);
		contentPane.add(lblApellido);
		contentPane.add(lblFechaN);
		contentPane.add(lblNacionalidad);
		contentPane.add(lblTelefono);	
		contentPane.add(lblNumeroReserva);
		
		txtNombre = new JTextField();
		txtApellido = new JTextField();
		txtFechaN = new JDateChooser();
		txtNacionalidad = new JComboBox();
		txtTelefono = new JTextField();
		txtNreserva = new JTextField();

		txtNombre.setBounds(560, 135, 285, 33);
		txtApellido.setBounds(560, 204, 285, 33);
		txtFechaN.setBounds(560, 278, 285, 36);
		txtNacionalidad.setBounds(560, 350, 289, 36);
		txtTelefono.setBounds(560, 424, 285, 33);
		txtNreserva.setBounds(560, 495, 285, 33);

		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFechaN.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));

		txtNombre.setForeground(new Color (217, 217, 217));
		txtApellido.setForeground(new Color (217, 217, 217));
		txtFechaN.getDateEditor().getUiComponent().setForeground(new Color (217, 217, 217));
		txtNacionalidad.setForeground(new Color (217, 217, 217));
		txtTelefono.setForeground(new Color(217, 217, 217));
		txtNreserva.setForeground(new Color (217, 217, 217));

		txtNombre.setBackground(new Color (30,26,26));
		txtApellido.setBackground(new Color (30,26,26));
		txtFechaN.getDateEditor().setEnabled(false);
		txtFechaN.getDateEditor().getUiComponent().setBackground(new Color (30,26,26));
		txtNacionalidad.setBackground(new Color (30,26,26));
		txtTelefono.setBackground(new Color (30,26,26));
		txtNreserva.setBackground(new Color (30,26,26));
		
		txtNombre.setColumns(10);
		txtApellido.setColumns(10);
		txtTelefono.setColumns(10);
		txtNreserva.setColumns(10);

		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		txtNreserva.setText(String.valueOf(reservation.getId()));
		
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"afgano-afgana", "alemán-", "alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"}));

		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/mx/com/hotel/image/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(new Color(93, 152, 48));
		txtFechaN.setDateFormatString("yyyy-MM-dd");

		contentPane.add(txtNombre);
		contentPane.add(txtApellido);
		contentPane.add(txtFechaN);
		contentPane.add(txtNacionalidad);
		contentPane.add(txtTelefono);
		contentPane.add(txtNreserva);
		
		JSeparator separator_1_2 = new JSeparator();
		JSeparator separator_1_2_1 = new JSeparator();
		JSeparator separator_1_2_2 = new JSeparator();
		JSeparator separator_1_2_3 = new JSeparator();
		JSeparator separator_1_2_4 = new JSeparator();
		JSeparator separator_1_2_5 = new JSeparator();

		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_5.setBounds(560, 529, 289, 2);

		separator_1_2.setForeground(new Color(93, 152, 48));
		separator_1_2_1.setForeground(new Color(93, 152, 48));
		separator_1_2_2.setForeground(new Color(93, 152, 48));
		separator_1_2_3.setForeground(new Color(93, 152, 48));
		separator_1_2_4.setForeground(new Color(93, 152, 48));
		separator_1_2_5.setForeground(new Color(93, 152, 48));

		separator_1_2.setBackground(new Color(93, 152, 48));
		separator_1_2_1.setBackground(new Color(93, 152, 48));
		separator_1_2_2.setBackground(new Color(93, 152, 48));
		separator_1_2_3.setBackground(new Color(93, 152, 48));
		separator_1_2_4.setBackground(new Color(93, 152, 48));
		separator_1_2_5.setBackground(new Color(93, 152, 48));

		contentPane.add(separator_1_2);
		contentPane.add(separator_1_2_1);
		contentPane.add(separator_1_2_2);
		contentPane.add(separator_1_2_3);
		contentPane.add(separator_1_2_4);
		contentPane.add(separator_1_2_5);
		
		btnGuardar = new JButton();
		btnGuardar.setBounds(723, 560, 122, 35);
		btnGuardar.setBackground(new Color(93, 152, 48));
		btnGuardar.setLayout(null);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setBounds(0, 0, 122, 35);
		labelGuardar.setForeground(new Color(217, 217, 217));
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnGuardar.add(labelGuardar);	
		contentPane.add(btnGuardar);
		
		btnAgregarOtro = new JButton();
		btnAgregarOtro.setBounds(560, 560, 150, 35);
		btnAgregarOtro.setBackground(new Color(93, 152, 48));
		btnAgregarOtro.setLayout(null);
		btnAgregarOtro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelAgregarOtro = new JLabel("AGREGAR OTRO");
		labelAgregarOtro.setHorizontalAlignment(SwingConstants.CENTER);
		labelAgregarOtro.setBounds(0, 0, 150, 35);
		labelAgregarOtro.setForeground(new Color(217, 217, 217));
		labelAgregarOtro.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnAgregarOtro.add(labelAgregarOtro);	
		contentPane.add(btnAgregarOtro);
	}
	
	private void setFormActions() {
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
			
		});
		
		btnAgregarOtro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addGuest();
			}
		});
		
	}
	
	protected void addGuest() {
		if (txtNombre.getText().isBlank() || txtApellido.getText().isBlank() || txtTelefono.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Los campos Nombre, Apellido y Telefono son requeridos.");
            return; 
		}
		
		Long phone; 
		try {
			phone = Long.parseLong(txtTelefono.getText());
			if(txtTelefono.getText().length() != 10) {
	            JOptionPane.showMessageDialog(this, "El número telefónico debe contener 10 digitos.");
	            return; 
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El campo teléfono debe ser numérico de 10 digitos sin espacios");
            return;
		}
		
		Guest guest = new Guest(
			txtNombre.getText(), 
			txtApellido.getText(), 
			txtFechaN.getDate(),
			(String) txtNacionalidad.getSelectedItem(),
			phone,
			reservation.getId()
		);
		reservation.addGuest(guest);
        JOptionPane.showMessageDialog(this, String.format("%d huéspedes añadidos", reservation.getGuests().size()));
        cleanForm();
	}

	protected void cleanForm() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtFechaN.setDate(new Date(System.currentTimeMillis()));
		txtNacionalidad.setSelectedIndex(0);
		txtTelefono.setText("");
	}


	private void save() {
		if (!txtNombre.getText().isBlank() || !txtApellido.getText().isBlank() || !txtTelefono.getText().isBlank()) {
            addGuest();
            return;
		}
		try {
			this.guestController.save(reservation.getGuests());
			changeScreen();
		} catch(Exception e) {
			e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Ups, algo salió mal, intenta ingresar nuevamente los huéspedes!");
	        cleanForm();
	        reservation.cleanGuests();
		}
        
	}
	
	private void changeScreen() {
		Exito exito = new Exito();
		exito.setVisible(true);
		dispose();
	}
	
	
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
       int x = evt.getXOnScreen();
       int y = evt.getYOnScreen();
       this.setLocation(x - xMouse, y - yMouse);
    }
											
}
