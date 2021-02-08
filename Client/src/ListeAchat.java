import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ListeAchat {

	private JFrame frame;
	private JTable table;
	private JLabel txt_total = new JLabel("");
	

	   private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
   
	
	    private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";
	    
	    
	    public  String  ReadFile() {
            String d = null;
	    	try {
	    		
	    	      File myObj = new File("C:\\Users\\sam\\Desktop\\filename.txt");
	    	      Scanner myReader = new Scanner(myObj);  
	    	      while (myReader.hasNextLine()) {
	    	        String data = myReader.nextLine();

	    	        d=data;
	    	      }
	    	      myReader.close();
	    	    } catch (FileNotFoundException e) {
	    	      System.out.println("An error occurred.");
	    	      e.printStackTrace();
	    	    }
			return d; 
	    }
	    
	    
	    public void Resettab() {
			String les_prod="select c.id_cmd,c.id_client,c.quantite,c.date_cmd,p.nom_prod,p.prix_prod,cl.nom_client from commande c, produits p,client cl where (p.id_prod=c.id_prod)AND (c.id_client=cl.id_client); ";
		       
			  	    
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

				             
				             table.setModel(new DefaultTableModel(null,new String[] { ID,quantite,Date,nom,prix,client}));


				                }
				            }
				        catch(SQLException e8){
				            System.err.println("Query error.");
				            }
				        catch(NullPointerException ee){
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
	    //************* methode affiche tableau *****************
	    public void MaListe () {
	    	
	    	String les_prod="select c.id_cmd,c.quantite,c.date_cmd,p.nom_prod,p.prix_prod,cl.nom_client from commande c, produits p,client cl where (p.id_prod=c.id_prod)AND (c.id_client=cl.id_client)AND('"+ReadFile()+"'=cl.id_client) ; ";
		       
	  	    
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
	    
	    public void CalculPrix() {
	    	
	    	String les_prod="select c.id_client,sum(p.prix_prod*c.quantite) AS total from commande c, produits p where (p.id_prod=c.id_prod)AND (c.id_client='"+ReadFile()+"');";
		       
		  	 try{
		  		 mysqlConnect();
					
			            state = con.createStatement();
			            result = state.executeQuery(les_prod);
			         
			            while(result.next()){
			             String total= result.getString(2);
			            txt_total.setText(total+"DT");
			            

		
				            System.out.println(result.getString(1));

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
					ListeAchat window = new ListeAchat();
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
	public ListeAchat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 672, 309);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 656, 269);
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
		table.setBounds(23, 66, 607, 104);
		panel.add(table);
		
		JLabel lblNewLabel = new JLabel("Liste des achats :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(177, 29, 193, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prix Total :");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(284, 181, 86, 26);
		panel.add(lblNewLabel_1);
		txt_total.setForeground(Color.BLUE);
		txt_total.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		txt_total.setBounds(380, 184, 96, 23);
		panel.add(txt_total);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Principale principale=new Principale();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(458, 235, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row=table.getSelectedRow();
				String cell=table.getModel().getValueAt(row,0).toString();
				String sql="delete from commande where id_cmd="+cell;

				
					
				 try{
					 mysqlConnect();
			            //using PreparedStatement
			            pstate = con.prepareStatement(sql);
			            pstate.execute();
			      
			         
			            JOptionPane.showMessageDialog(null,"Votre commande a été supprimer ");
			            Resettab();
			            MaListe();
			            CalculPrix();

			            System.out.println("Query OK, 1 row deleted.");
			            }
			        catch(SQLException a){
			            System.err.println("Query error.");
			            }
			}
		});
		btnNewButton_1.setBounds(33, 184, 102, 23);
		panel.add(btnNewButton_1);
	MaListe();
	CalculPrix();
	
	}
}
