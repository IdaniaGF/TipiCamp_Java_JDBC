package mx.com.hotel;

import java.awt.EventQueue;

import mx.com.hotel.view.MenuPrincipal;

public class ControlReservasMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
