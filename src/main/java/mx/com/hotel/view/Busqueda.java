package mx.com.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import mx.com.hotel.controller.GuestController;
import mx.com.hotel.controller.ReservationController;
import mx.com.hotel.model.Guest;
import mx.com.hotel.model.Reservation;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private ReservationController reservationController; 
	private GuestController guestController; 
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {	
		reservationController = new ReservationController(); 
		guestController = new GuestController();
		setMainPanel();
		setContentTable();
		setFormFields();
		setFormActions();
	}
	
	private void setMainPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/mx/com/hotel/image/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84,84,84));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(217, 217, 217));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/mx/com/hotel/image/logo-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
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
		header.setLayout(null);
		header.setBackground(new Color (30,26,26));
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		final JPanel btnAtras = new JPanel();
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color (30,26,26));
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
				btnAtras.setBackground(new Color(12, 138, 199));
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color (30,26,26));
			}
		});

		header.add(btnAtras);
		
		JLabel labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(new Color(217, 217, 217));
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		final JPanel btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color (30,26,26));
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(new Color (30,26,26));
			}
		});

		header.add(btnexit);
		
		JLabel labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(new Color(217, 217, 217));
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
	}
	
	private void setContentTable() {
		UIManager.put("TabbedPane.selected", new Color(30,26,26));
		final JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(93, 152, 48));
		panel.setForeground(new Color(217, 217, 217));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setBackground(new Color (30,26,26));
		tbReservas.setForeground(new Color(217, 217, 217));
		
		JScrollPane scrollPane = new JScrollPane(tbReservas);
		scrollPane.getViewport().setBackground(new Color (30,26,26));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/mx/com/hotel/image/reservado.png")), scrollPane, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("ID");
		modelo.addColumn("CHECK-IN");
		modelo.addColumn("CHECK-OUT");
		modelo.addColumn("VALOR");
		modelo.addColumn("PAGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO");
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setBackground(new Color (30,26,26));
		tbHuespedes.setForeground(new Color(217, 217, 217));
		JScrollPane scrollPaneH = new JScrollPane(tbHuespedes);
		scrollPaneH.getViewport().setBackground(new Color (30,26,26));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/mx/com/hotel/image/pessoas.png")), scrollPaneH, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("ID");
		modeloH.addColumn("NOMBRE");
		modeloH.addColumn("APELLIDO");
		modeloH.addColumn("NACIMIENTO");
		modeloH.addColumn("NACIONALIDAD");
		modeloH.addColumn("TELEFONO");
		modeloH.addColumn("ID RESERVACIÓN");
	}
	
	private void setFormFields() {
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar.setForeground(new Color(217, 217, 217));
		txtBuscar.setBackground(new Color(84,84,84));
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(93, 152, 48));
		separator_1_2.setBackground(new Color(93, 152, 48));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		btnBuscar = new JButton();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnBuscar.setLayout(null);
		btnBuscar.setBackground(new Color(93, 152, 48));
		btnBuscar.setBounds(748, 125, 122, 35);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnBuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(new Color(217, 217, 217));
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnEditar = new JButton();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(93, 152, 48));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(new Color(217, 217, 217));
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		btnEliminar = new JButton();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(93, 152, 48));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(new Color(217, 217, 217));
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	private void setFormActions() {
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanTable(); 
				search();
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				cleanTable(); 
				search();
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
				cleanTable();
				search();
			}
		});
	}
	
	private void search() {
		if (txtBuscar.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Id de la reservación, nombre, apellido, o el número telefónico del huésped");
			return;
		}
		try {
			List<Reservation> reservations = reservationController.search(txtBuscar.getText());	
			List<Guest> guests = guestController.search(txtBuscar.getText());
			displayResultsTables(reservations, guests);
		} catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ups, algo salió mal, vuelve a intentarlo");
			e.printStackTrace();
		}		
	}
	
	private void update() {
		if(!guestSelected() && !reservationSelected()) {
			JOptionPane.showMessageDialog(this, "Selecciona algun huésped o reservación");
			return; 
		}
		
		if (guestSelected()) {
			try {
				Guest guest = new Guest(
						Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString()), 
						modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1).toString(), 
						modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString(), 
						new SimpleDateFormat("yyyy-MM-dd").parse(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString()),
						modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4).toString(), 
						Long.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString()),
						Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString())
						);
				this.guestController.update(guest);
	            JOptionPane.showMessageDialog(this, String.format("El huésped %d fue modificado con éxito!",guest.getId())); 
			} catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Ups, algo salió mal, vuelve a intentarlo");
				e.printStackTrace();
			}
		}
		
		if(reservationSelected()) {
			try {
				Reservation reservation = new Reservation (
						Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString()),
						new SimpleDateFormat("yyyy-MM-dd").parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString()),
						new SimpleDateFormat("yyyy-MM-dd").parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString()),
						Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString()),		
						modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString().toString()
						);
				this.reservationController.update(reservation);
	            JOptionPane.showMessageDialog(this, String.format("La reservación %d fue modificada con éxito!",reservation.getId())); 
				
			} catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Ups, algo salió mal, vuelve a intentarlo");
				e.printStackTrace();
			}
		}
	}
	
	private void delete() {
		if(!guestSelected() && !reservationSelected()) {
			JOptionPane.showMessageDialog(this, "Selecciona algun huésped o reservación");
			return; 
		}
		
		if (guestSelected()) {
			try {
				var id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				this.guestController.delete(id);
	            JOptionPane.showMessageDialog(this, String.format("El huésped %d fue eliminada con éxito!",id)); 
			} catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Ups, algo salió mal, vuelve a intentarlo");
				e.printStackTrace();
			}
		}
		
		if(reservationSelected()) {
			try {
				var id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
				this.reservationController.delete(id);
	            JOptionPane.showMessageDialog(this, String.format("La reservación %d fue eliminada con éxito!",id)); 
			} catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Ups, algo salió mal, vuelve a intentarlo");
				e.printStackTrace();
			}
		}
	}
	
	private void cleanTable() {
		modelo.getDataVector().clear();
		modeloH.getDataVector().clear();
		tbReservas.updateUI();
		tbHuespedes.updateUI();
	}
	
	private boolean guestSelected() {
		return tbHuespedes.getSelectedColumnCount() > 0; 
	}
	
	private boolean reservationSelected() {
		return tbReservas.getSelectedRowCount() > 0;
	}
	
	private void displayResultsTables(List<Reservation> reservations, List<Guest> guests) {
        JOptionPane.showMessageDialog(this, String.format("Resultados: \n %d Reservaciones \n %d Huéspedes", reservations.size(), guests.size())); 

        if (!reservations.isEmpty()) {
        	reservations.forEach(reservation -> {
				reservation.getGuests().forEach(guest ->modelo.addRow(new Object[] {
						reservation.getId(),
						new SimpleDateFormat("yyyy-MM-dd").format(reservation.getDateIn()),
						new SimpleDateFormat("yyyy-MM-dd").format(reservation.getDateOut()),
						reservation.getValue(),
						reservation.getPaymentMethod(),
						guest.getFirstName(), 
						guest.getLastName()
					}));	        
			});
        }
			
        if (!guests.isEmpty()) {
        	guests.forEach(guest -> modeloH.addRow(new Object[] {
					guest.getId(),
					guest.getFirstName(),
					guest.getLastName(),
					new SimpleDateFormat("yyyy-MM-dd").format(guest.getBirth()),
					guest.getNationality(),
					guest.getPhoneNumber(),
					guest.getIdReservation()
			}));
        }
			

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
