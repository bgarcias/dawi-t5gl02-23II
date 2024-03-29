package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validacion();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(78, 30, 205, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(78, 62, 171, 20);
		contentPane.add(txtClave);
		
	}

	
	private JTextField txtClave;
		
	String leerUsuario() {
		if (!txtUsuario.getText().matches("[A-Za-z0-9]+[@][a-z0-9]+[.][a-z]{2,3}")) {
			JOptionPane.showMessageDialog(null, "Usuario debe ser correo");
			return null;
		}
		 return txtUsuario.getText();
	}
	
	void validacion() {
		
		//LEER CAMPOS
			String usuario 	= leerUsuario();
			String clave = txtClave.getText();
			
			if (usuario == null || clave == null ) {
				return;
			}
			
		
		//VALIDAR EN BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager manager = fabrica.createEntityManager();				
		try {
			//select 
			Usuario u = manager.createQuery("select u from Usuario u where u.usr_usua = :xusua and u.cla_usua = :xcla",
					Usuario.class).setParameter("xusua", usuario).setParameter("xcla",clave).getSingleResult();
			
			//MENSAJES DE VALIDACION
			JOptionPane.showMessageDialog(null, "Bienvenido !! " + u.getNom_usua());
			dispose();
			FrmManteProd mantenimiento = new FrmManteProd();
			mantenimiento.setVisible(true);
			
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, "Error:" + e);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuario o clave incorrecta");	
		}
		
	}
}
