import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.List;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Client extends MysqlConnection {

	private JFrame frame;
	private JTable table;
	private JTextField txt_cher;
	private JButton btnNewButton;
	private JLabel lblNewLabel;

	
	private static Connection con = null;
    private static Statement state = null;
    private static ResultSet result;
    private static PreparedStatement pstate;


    private static String driver = "com.mysql.jdbc.Driver";
    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
    private static String user = "root";                  //'root' is username
    private static String password = "";        //'pass' is password
    
    
    

    
public void ResetTab() {
    	
    	String sql_cher="SELECT * from client" ;
		
		
		try{
	  		 mysqlConnect();
				
		            state = con.createStatement();
		            result = state.executeQuery(sql_cher);
		         
		            while(result.next()){
		             String ID= result.getString(1);
		             String nom=result.getString(2);
		             String prenom=result.getString(3);
		             String adrs=result.getString(4);
		             String tel=result.getString(5);
		             
		             table.setModel(new DefaultTableModel(null,new String[] { ID,nom,prenom,adrs,tel}));



			       System.out.println(result.getString(3)+result.getString(5));

		                }
		            }
		        catch(SQLException e8){
		            System.err.println("Query error.");
		            }
		        catch(NullPointerException ee){
		            System.err.println("Element not found.");
		            
		        }
	}
    
    // ************** affichage tableau 
    
    public void Affiche() {
    	
    	String sql_cher="SELECT * from client" ;
		
		
		try{
	  		 mysqlConnect();
				
		            state = con.createStatement();
		            result = state.executeQuery(sql_cher);
		         
		            while(result.next()){
		             String ID= result.getString(1);
		             String nom=result.getString(2);
		             String prenom=result.getString(3);
		             String adrs=result.getString(4);
		             String tel=result.getString(5);
		             
		             DefaultTableModel model=(DefaultTableModel)table.getModel();

			          model.addRow(new Object[] { ID,nom,prenom,adrs,tel});


			       System.out.println(result.getString(3)+result.getString(5));

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
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		MysqlConnection mysqlConnection=new MysqlConnection();
		
		
		
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.setBounds(100, 100, 666, 349);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 650, 310);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID","Nom","Prénom","Adresse","Télephone"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class,String.class,String.class,String.class,String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		table.setBounds(31, 55, 569, 144);
		panel.add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Chercher  :");
		lblNewLabel_1.setBounds(290, 230, 68, 19);
		panel.add(lblNewLabel_1);
		
		txt_cher = new JTextField();
		txt_cher.setBounds(361, 229, 156, 20);
		panel.add(txt_cher);
		txt_cher.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Consulter les Clients :");
		lblNewLabel_6.setBounds(174, 11, 343, 33);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel_6);
		
		btnNewButton = new JButton("Retour ");
		btnNewButton.setBounds(529, 277, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Accueil accueil=new Accueil();
			frame.dispose();

			
				
			}
		});
		panel.add(btnNewButton);
		
		lblNewLabel = new JLabel("Type de recheche :");
		lblNewLabel.setBounds(31, 232, 126, 14);
		panel.add(lblNewLabel);
		
		JComboBox combo_cher = new JComboBox();
		combo_cher.addItem("ID");
		combo_cher.addItem("Nom");
		combo_cher.addItem("Télphone");


		
		combo_cher.setBounds(174, 228, 83, 22);
		panel.add(combo_cher);
		
		JButton btnNewButton_1 = new JButton("Chercher");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cher=txt_cher.getText();

				if(cher.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Champ vide!! ");
				}
				else if(combo_cher.getSelectedIndex()==0 && cher!="")
				{
					String sql_cher="SELECT * from client where id_client='"+cher+"'" ;
					
					
					
						try{
					  		 mysqlConnect();
								
						            state = con.createStatement();
						            result = state.executeQuery(sql_cher);
						         
						            while(result.next()){
						             String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prenom=result.getString(3);
						             String adrs=result.getString(4);
						             String tel=result.getString(5);
						             

						            ResetTab();
						             DefaultTableModel model=(DefaultTableModel)table.getModel();

							          model.addRow(new Object[] { ID,nom,prenom,adrs,tel});
						                }
						            }
						        catch(SQLException e8){
						            System.err.println("Query error.");
						            }
						        catch(NullPointerException ee){
						            System.err.println("Element not found.");
						            }
						
					
					
					
				}
				else if(combo_cher.getSelectedIndex()==1) {
					String sql_cher="SELECT * from client where nom_client='"+cher+"'" ;
				
						try{
					  		 mysqlConnect();
								
						            state = con.createStatement();
						            result = state.executeQuery(sql_cher);
						         
						            while(result.next()){
						             String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prenom=result.getString(3);
						             String adrs=result.getString(4);
						             String tel=result.getString(5);
						             


							       System.out.println(result.getString(3)+result.getString(5));

						                }
						            }
						        catch(SQLException e8){
						            System.err.println("Query error.");
						            }
						        catch(NullPointerException ee){
						            System.err.println("Element not found.");
						            }
						
					
					
					
				}
				else if(combo_cher.getSelectedIndex()==2) {
					
					String sql_cher="SELECT * from client where tel_client='"+cher+"'" ;
					
					
						try{
					  		 mysqlConnect();
								
						            state = con.createStatement();
						            result = state.executeQuery(sql_cher);
						         
						            while(result.next()){
						             String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prenom=result.getString(3);
						             String adrs=result.getString(4);
						             String tel=result.getString(5);
						             


							       System.out.println(result.getString(3)+result.getString(5));

						                }
						            }
						        catch(SQLException e8){
						            System.err.println("Query error.");
						            }
						        catch(NullPointerException ee){
						            System.err.println("Element not found.");
						            
						
					}
					
					
				}
								 
								
				
				
			}
		});
		btnNewButton_1.setBounds(399, 277, 89, 23);
		panel.add(btnNewButton_1);
		
		
		// ******* affichage ********
		Affiche();
		
	}
}
