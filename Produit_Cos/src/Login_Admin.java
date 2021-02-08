import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.DropMode;
import java.awt.Font;
import java.awt.Frame;
import java.sql.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Dimension;

public class Login_Admin {

	private JFrame frame;
	private JTextField Login_Input;
	private JPasswordField Pass_Input;
	
	

	 private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; //'produit' is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password


	    private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
	    
	    
	    public static void Show() {
       	String s="SELECT * FROM categorie ";

	    	 try{
					MysqlConnection co=new MysqlConnection();
					co.mysqlConnect();
					
		            state = con.createStatement();
		            result = state.executeQuery(s);
		            while(result.next()){
		             
		            	System.out.println(result.getInt(1) + result.getString(2));
		                
		                }
		            }
		        catch(SQLException e){
		            System.err.println("Query error.");
		            }
		        catch(NullPointerException e){
		            System.err.println("Element not found.");
		            }
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
					Login_Admin window = new Login_Admin();
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
	public Login_Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMaximumSize(new Dimension(500, 300));
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		frame.setLocation(new Point(430, 160));
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(114, 72, 52, 29);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(61, 118, 105, 29);
		panel.add(lblNewLabel_1);
		
		Login_Input = new JTextField();
		Login_Input.setToolTipText("");
		Login_Input.setBounds(176, 74, 159, 29);
		panel.add(Login_Input);
		Login_Input.setColumns(10);
		
		JButton Btn_Conx = new JButton("Connexion");
		Btn_Conx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try{
					mysqlConnect();
		            state = con.createStatement();
		            result = state.executeQuery("select login_admin,pass_admin from admin ");
		            while(result.next()){
		                String login = result.getString(1);
		                String pass = result.getString(2);

		                if(Login_Input.getText().equals("") || Pass_Input.getText().equals(""))
		                {
				            JOptionPane.showMessageDialog(null, "Enter votre login et mot de passe !");
		                }
		                
		                else if(Login_Input.getText().equals(login)&& Pass_Input.getText().equals(pass))
		                {
			                System.out.println("connection");

		                	Accueil accueil=new Accueil();
		                	frame.dispose();
		                }
		                else {
				            JOptionPane.showMessageDialog(null, "login ou mot de passe invalide !");

			                System.out.println("login ou mot de passe invalide !!");

		                }
		                
		            }
				}
		        catch(SQLException ee){
		            System.err.println("Query error.");
		            }
		        catch(NullPointerException a){
		            System.err.println("Element not found.");
		            }

							
					
					
					
			}
		});
		Btn_Conx.setBounds(131, 204, 105, 29);
		panel.add(Btn_Conx);
		
		Pass_Input = new JPasswordField();
		Pass_Input.setBounds(176, 120, 159, 29);
		panel.add(Pass_Input);
		
		JButton btnNewButton = new JButton("Fermer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		btnNewButton.setBounds(277, 204, 89, 29);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Connexion Admin :");
		lblNewLabel_2.setBounds(202, 28, 105, 35);
		panel.add(lblNewLabel_2);
		
		
		 
	}
}
