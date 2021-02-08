import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Connxion {

	private JFrame frame;
	private JTextField Login_Input;
	private JPasswordField Pass_Input;
	public String ID_Client;
	

	 private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; //'produit' is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password


	    private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
	    
	   public void CreateFile(String x) {
	    	    try {
	    	      FileWriter myWriter = new FileWriter("C:\\Users\\sam\\Desktop\\filename.txt");
	    	      myWriter.write(x);
	    	      myWriter.close();
	    	      System.out.println("Successfully wrote to the file.");
	    	    } catch (IOException e) {
	    	      System.out.println("An error occurred.");
	    	      e.printStackTrace();
	    	    }}
	  
	   
	   
	    
	    
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
					Connxion window = new Connxion();
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
	public Connxion() {
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
		            result = state.executeQuery("select login_client,pass_client,id_client from client ");
		            while(result.next()){
		                String login = result.getString(1);
		                String pass = result.getString(2);
		                String id_client = result.getString(3);


		                if(Login_Input.getText().equals("") || Pass_Input.getText().equals(""))
		                {
				            JOptionPane.showMessageDialog(null, "Enter votre login et mot de passe !");
		                }
		                
		                else if(Login_Input.getText().equals(login)&& Pass_Input.getText().equals(pass))
		                {
			                System.out.println("connection");

			                CreateFile(id_client);
		                	Principale principale=new Principale();
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
		Btn_Conx.setBounds(114, 206, 105, 29);
		panel.add(Btn_Conx);
		
		Pass_Input = new JPasswordField();
		Pass_Input.setBounds(176, 120, 159, 29);
		panel.add(Pass_Input);
		
		JButton btnNewButton = new JButton("Cr\u00E9er un Compte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register register=new Register();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(247, 206, 138, 29);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Connexion Client :");
		lblNewLabel_2.setBounds(202, 28, 105, 35);
		panel.add(lblNewLabel_2);
		
		
		 
	}
}
