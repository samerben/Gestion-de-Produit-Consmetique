import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.Font;

public class Accueil {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		

	}

	/**
	 * Create the application.
	 */
	public Accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMaximumSize(new Dimension(430, 160));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBounds(100, 100, 497, 257);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(50, 400));
		panel.setMaximumSize(new Dimension(50, 400));
		panel.setBounds(0, 0, 481, 218);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton Btn_Produit = new JButton("Produit");
		Btn_Produit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produit produit=new Produit();
				frame.dispose();
			}
		});
		Btn_Produit.setBounds(33, 81, 131, 35);
		panel.add(Btn_Produit);
		
		JButton Btn_client = new JButton("Client");
		Btn_client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Client client=new Client();
				frame.dispose();
			}
		});
		Btn_client.setBounds(184, 81, 131, 35);
		panel.add(Btn_client);
		
		JButton Btn_employe = new JButton("Employe");
		Btn_employe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Employe employe=new Employe();
				frame.dispose();

			}
			
		});
		Btn_employe.setBounds(337, 81, 131, 35);
		panel.add(Btn_employe);
		
		JLabel lblNewLabel = new JLabel("Principale");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setBounds(152, 11, 199, 47);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			
			}
		});
		btnNewButton.setBounds(337, 142, 131, 35);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cat\u00E9gorie ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categorie categorie=new Categorie();
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(33, 142, 131, 35);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Les commandes");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeCommande liste=new ListeCommande();
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(184, 142, 131, 35);
		panel.add(btnNewButton_2);
	}
}
