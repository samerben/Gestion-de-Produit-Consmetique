import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Register {

	private JFrame frame;
	private JTextField txt_nom;
	private JTextField txt_pren;
	private JTextField txt_ards;
	private JTextField txt_log;
	private JTextField txt_tel;

	
	 private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
	    
	    

	
	    private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";  
	    private JPasswordField txt_pass;
	    
	  
	
	public void reset() {
		 txt_nom.setText("");
		txt_pren.setText("");
		 txt_ards.setText("");
		 txt_log.setText("");
		 txt_pass.setText("");
		txt_tel.setText("");
		
	}
	
	
	 public static void mysqlConnect(){
	        try{
	            Class.forName(driver);
	            con = DriverManager.getConnection(connection, user, password);
	            System.out.println("Successfully connected to database.");
	            }
	        catch(ClassNotFoundException e){
	            System.err.println("Couldn't load driver.");
	            }
	        catch(SQLException e){
	            System.err.println("Couldn't connect to database.");
	            }
	        }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 584, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 568, 298);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(10, 82, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_1.setBounds(10, 129, 64, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresse :");
		lblNewLabel_2.setBounds(10, 173, 64, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Login :");
		lblNewLabel_3.setBounds(290, 121, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mot de passe :");
		lblNewLabel_4.setBounds(278, 167, 91, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("T\u00E9lephone :");
		lblNewLabel_5.setBounds(290, 79, 79, 14);
		panel.add(lblNewLabel_5);
		
		txt_nom = new JTextField();
		txt_nom.setBounds(99, 79, 129, 20);
		panel.add(txt_nom);
		txt_nom.setColumns(10);
		
		txt_pren = new JTextField();
		txt_pren.setBounds(99, 126, 129, 20);
		panel.add(txt_pren);
		txt_pren.setColumns(10);
		
		txt_ards = new JTextField();
		txt_ards.setBounds(97, 170, 131, 20);
		panel.add(txt_ards);
		txt_ards.setColumns(10);
		
		txt_log = new JTextField();
		txt_log.setBounds(379, 118, 129, 20);
		panel.add(txt_log);
		txt_log.setColumns(10);
		
		txt_tel = new JTextField();
		txt_tel.setBounds(379, 76, 129, 20);
		panel.add(txt_tel);
		txt_tel.setColumns(10);
		
		JButton btn_register = new JButton("R\u00E9gistrer");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//******** insert **********
				if(txt_nom.getText().equals("") || txt_pren.getText().equals("")|| 
						txt_ards.getText().equals("")|| txt_tel.getText().equals("")||
						txt_log.getText().equals("")||txt_pass.getText().equals(""))
				{
		            JOptionPane.showMessageDialog(null, "remplir les champs");

				}
				else {
					try
			    	{
			    		mysqlConnect();
			    	state = con.createStatement();
		            result = state.executeQuery("select tel_client,login_client from client ");
		            while(result.next()){
		 		        String tel = result.getString(1);
		                String login = result.getString(2);


		                if(txt_log.getText().equals(login))
		                {
			                System.out.println("Changer login");
			                JOptionPane.showMessageDialog(null," Changer votre login deja exist !!");
			                
			            }
		                else  if(txt_tel.getText().equals(tel))
		                {
			                System.out.println("Changer login");
			                JOptionPane.showMessageDialog(null," Changer votre numéro deja exist !!");
			                
			                
		                }
		                
		                else {
		                	pstate = con.prepareStatement("insert into client(nom_client,prenom_client,adresse_client,tel_client,login_client,pass_client) VALUES ('"+txt_nom.getText()+"', '"+txt_pren.getText()+"','"+txt_ards.getText()+"','"+txt_tel.getText()+"','"+txt_log.getText()+"','"+txt_pass.getText()+"')");
				             pstate.executeUpdate();

				           
				            System.out.println("Query OK, 1 row insertedted.");
				            JOptionPane.showMessageDialog(null, "Bien ajouter");
				            reset();
				            MysqlConnection.closeConnection();
		                }
		               
		            }}   
			    	catch(SQLException ee){
			            System.err.println("Query error.");
			            }
			        catch(NullPointerException a){
			            System.err.println("Element not found.");
			            }
		     		
				}
			}
		});
		btn_register.setBounds(158, 237, 89, 23);
		panel.add(btn_register);
		
		JButton btn_annuler = new JButton("Connexion");
		btn_annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connxion connxion=new Connxion();
				frame.dispose();
			}
		});
		btn_annuler.setBounds(290, 237, 109, 23);
		panel.add(btn_annuler);
		
		txt_pass = new JPasswordField();
		txt_pass.setBounds(379, 170, 129, 20);
		panel.add(txt_pass);
		
		JLabel lblNewLabel_6 = new JLabel("Cr\u00E9er un compte :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6.setBounds(193, 11, 198, 38);
		panel.add(lblNewLabel_6);
		
		
	}
}
