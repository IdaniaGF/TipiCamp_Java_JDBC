package mx.com.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import mx.com.hotel.controller.ReservationController;
import mx.com.hotel.model.Reservation;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	private JPanel panel; 
	private JTextField txtValor;
	private JDateChooser txtFechaE;
	private JDateChooser txtFechaS;
	private JComboBox<Format> txtFormaPago;
	int xMouse, yMouse;
	private JButton btnSiguiente;
	private ReservationController reservationController;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ReservasView() {
		super("Reserva");
		setStyle();
		setFormFields();
		setFormActions();
		this.reservationController = new ReservationController();
	}

	
	private void setStyle() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/mx/com/hotel/image/logo-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 26, 26));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		panel = new JPanel();
		panel.setBounds(0, 0, 910, 560);
		panel.setBackground(new Color(30, 26, 26));
		panel.setBorder(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(84, 84, 84));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/mx/com/hotel/image/logo-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/mx/com/hotel/image/reservas-img-3.png")));

		final JPanel btnexit = new JPanel();
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
				btnexit.setBackground(new Color(30, 26, 26));
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(30, 26, 26));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		JLabel labelExit = new JLabel("X");
		labelExit.setForeground(new Color(217, 217, 217));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setLayout(null);
		header.setBackground(new Color(30, 26, 26));
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
		
		panel.add(header);

		final JPanel btnAtras = new JPanel();
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(84, 84, 84));
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(93, 152, 48));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(84, 84, 84));
			}
		});
		header.add(btnAtras);

		JLabel labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		labelAtras.setForeground(new Color(217, 217, 217));
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
	}
	
	private void setFormFields() {
		Locale myLocale = new Locale("es-419", "MX");
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 289, 42);
		lblTitulo.setForeground(new Color(93, 152, 48));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(new Color(217, 217, 217));
		lblCheckIn.setBounds(68, 136, 289, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		txtFechaE = new JDateChooser();
		txtFechaE.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/mx/com/hotel/image/icon-reservas.png")));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.getCalendarButton().setBackground(new Color(93, 152, 48));
		txtFechaE.setBounds(68, 161, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.getDateEditor().setEnabled(false);
		txtFechaE.getDateEditor().getUiComponent().setBackground(new Color(30, 26, 26));
		txtFechaE.getDateEditor().getUiComponent().setForeground(new Color(217, 217, 217));
		txtFechaE.setBorder(new LineBorder(new Color(30, 26, 26)));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(93, 152, 48));
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(new Color(93, 152, 48));
		panel.add(separator_1);
			
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(new Color(217, 217, 217));
		lblCheckOut.setBounds(68, 221, 289, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		
		txtFechaS = new JDateChooser();
		txtFechaS.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/mx/com/hotel/image/icon-reservas.png")));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.getCalendarButton().setBackground(new Color(93, 152, 48));
		txtFechaS.setBounds(68, 246, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.getDateEditor().setEnabled(false);
		txtFechaS.getDateEditor().getUiComponent().setBackground(new Color(30, 26, 26));
		txtFechaS.getDateEditor().getUiComponent().setForeground(new Color(217, 217, 217));
		txtFechaS.setBorder(new LineBorder(new Color(30, 26, 26), 0));
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
				if (txtFechaS.getDate() != null && txtFechaE.getDate() != null) {
					long days = ChronoUnit.DAYS.between(txtFechaE.getDate().toInstant(), txtFechaS.getDate().toInstant()); 
					
					txtValor.setText(String.valueOf(Reservation.calcValue(days)));
				} 
			}
		});
		panel.add(txtFechaS);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(93, 152, 48));
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(new Color(93, 152, 48));
		panel.add(separator_1_1);
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(new Color(217, 217, 217));
		lblValor.setBounds(72, 303, 289, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JLabel lblValorSimbolo = new JLabel("$");
		lblValorSimbolo.setBounds(221, 332, 17, 25);
		lblValorSimbolo.setForeground(new Color(217, 217, 217));
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));
		panel.add(lblValorSimbolo);
		
		txtValor = new JTextField();
		txtValor.setBackground(new Color(30, 26, 26));
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(new Color(217, 217, 217));
		txtValor.setBounds(78, 328, 100, 33);
		txtValor.setEnabled(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(93, 152, 48));
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(new Color(93, 152, 48));
		panel.add(separator_1_2);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(new Color(217, 217, 217));
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
			
		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(new Color(30, 26, 26));
		txtFormaPago.setForeground(new Color(217, 217, 217));
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(new Color(93, 152, 48));
		separator_1_3.setBackground(new Color(93, 152, 48));
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		btnSiguiente = new JButton();
		btnSiguiente.setLayout(null);
		btnSiguiente.setBackground(new Color(93, 152, 48));
		btnSiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnSiguiente);
		btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(new Color(217, 217, 217));
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnSiguiente.add(lblSiguiente);
	}
	
	private void setFormActions() {
		btnSiguiente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	}
	
	private void save() {
		if (txtFechaE.getDate() == null && txtFechaS.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Debes llenar todos los campos.");
			return;			
		}
					
		Reservation reservation = new Reservation(
				txtFechaE.getDate(),
				txtFechaS.getDate(), 
				Double.valueOf(txtValor.getText()),
				(String) txtFormaPago.getSelectedItem()
				); 
		try {
			this.reservationController.save(reservation);
			JOptionPane.showMessageDialog(this, "Reservación creada con éxito, ahora registra los datos del huésped");
			changeScreen(reservation);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Ups, algo salió mal, intentalo más tarde");
			e.printStackTrace();
		}

	}
	
	private void changeScreen(Reservation reservation) {
		RegistroHuesped registro = new RegistroHuesped(reservation);
		registro.setVisible(true);
		dispose();
	}
		
	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	
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
