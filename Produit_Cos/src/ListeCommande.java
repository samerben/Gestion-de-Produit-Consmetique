import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListeCommande {

	private JFrame frame;
	private JTable table;

	
	
	
	
	
	

	   private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;

	
	    private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";
	    private JLabel lblNewLabel;
	    private JButton btnNewButton;
	    
	    
	   
	    
	   
	    
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
	    //************* methode affiche tableau *****************
	    public void Affichage () {
	    	
	    	String les_prod="select c.id_cmd,c.quantite,c.date_cmd,p.nom_prod,p.prix_prod,cl.nom_client from commande c, produits p,client cl where (p.id_prod=c.id_prod)AND (c.id_client=cl.id_client) ; ";
		       
	  	    
		  	 try{
		  		 mysqlConnect();
					
			            state = con.createStatement();
			            result = state.executeQuery(les_prod);
			         
			            while(result.next()){
			             String ID= result.getString(1);
			             String quantite=result.getString(2);
			             String Date=result.getString(3);
			             String nom=result.getString(4);
			             String prix=result.getString(5);
			             String client=result.getString(6);




				            DefaultTableModel model=(DefaultTableModel)table.getModel();
				            model.addRow(new Object[] { ID,quantite,Date,nom,prix,client});
		
				            System.out.println("le nom de client est:" +client);

			                }
			            }
			        catch(SQLException e8){
			            System.err.println("Query error.");
			            }
			        catch(NullPointerException ee){
			            System.err.println("Element not found.");
			            }
			
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeCommande window = new ListeCommande();
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
	public ListeCommande() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 674, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 658, 332);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID_Commande", "Quantite", "Date", "Nom de produit", "Prix", "client"
			}
		) {
			Class[] columnTypes = new Class[] {
				int.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(93);
		table.getColumnModel().getColumn(3).setPreferredWidth(104);
		table.setBounds(23, 66, 607, 200);
		panel.add(table);
		
		lblNewLabel = new JLabel("La liste des commandes :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(195, 11, 274, 41);
		panel.add(lblNewLabel);
		
		btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil accueil=new Accueil();
				frame.dispose();
				
			}
		});
		btnNewButton.setBounds(546, 298, 89, 23);
		panel.add(btnNewButton);
		Affichage();
	}
}
