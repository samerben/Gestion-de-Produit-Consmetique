import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;


import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Categorie {

	private JFrame frame;
	private JTextField txt_Nom_Catego;
	

	 private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; //'produit' is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password


	    private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
	    private JTable table;
	    
	    


	    
	    
	 // **************** reset tableau *****************
	 		   public void Resettab() {
	 			   String emp="select *from employe ; ";
	 		       
	 			  	 try{
	 			  		 mysqlConnect();
	 						
	 				            state = con.createStatement();
	 				            result = state.executeQuery(emp);
	 				         
	 				            while(result.next()){
	 				            	 String ID= result.getString(1);
		 				             String nom=result.getString(2);
		 				       


	 				             
	 				             table.setModel(new DefaultTableModel(null,new String[] { ID,nom}));
	 				             DefaultTableModel model=(DefaultTableModel)table.getModel();


	 				                }
	 				            }
	 				        catch(SQLException e8){
	 				            System.err.println("Query error.");
	 				            }
	 				        catch(NullPointerException ee){
	 				            System.err.println("Element not found.");
	 				            }
	 				
	 			   
	 		   }
	 		   
	 		   //************* methode affiche tableau *****************
	 		    public void Affiche () {
	 		    	
	 		    	String les_prod="select * from categorie ";

	 			  	 try{
	 			  		 mysqlConnect();
	 						
	 				            state = con.createStatement();
	 				            result = state.executeQuery(les_prod);
	 				         
	 				            while(result.next()){
	 				             String ID= result.getString(1);
	 				             String nom=result.getString(2);
	 				       


	 					            DefaultTableModel model_catego=(DefaultTableModel)table.getModel();
	 					            model_catego.addRow(new Object[] { ID,nom});
	 			
	 					            System.out.println(result.getString(1)+" "+result.getString(2));

	 				                }
	 				            }
	 				        catch(SQLException e8){
	 				            System.err.println("Query error.");
	 				            }
	 				        catch(NullPointerException ee){
	 				            System.err.println("Element not found.");
	 				            }
	 				
	 		    }
	    
	  public void actualiser() {
	 		    	txt_Nom_Catego.setText("");
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
					Categorie window = new Categorie();
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
	public Categorie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.setBounds(100, 100, 683, 333);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 711, 389);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton Btn_Ajout_Produit = new JButton("Ajouter");
		Btn_Ajout_Produit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
	           // *****************insert ********
				if(txt_Nom_Catego.getText().equals("")) {
		            JOptionPane.showMessageDialog(null," Saisir une catégorie");

					
				}
				
				else {
					 try{
							mysqlConnect();
							
							
							  pstate = con.prepareStatement("insert into categorie(nom_catego) VALUES ( '"+txt_Nom_Catego.getText()+"')");
				             pstate.executeUpdate();

					            JOptionPane.showMessageDialog(null," bien ajouter");
		                          actualiser();
		                          Resettab();
		                          Affiche();

				            System.out.println("Query OK, 1 row insertedted.");
				            }
				        catch(SQLException e9){
				            System.err.println("Query error.");
				            }
				}
		
			} 
					});
		Btn_Ajout_Produit.setBounds(55, 181, 116, 23);
		panel.add(Btn_Ajout_Produit);
		
		JButton Btn_Modifier_Produit = new JButton("Modifier");
		Btn_Modifier_Produit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom=txt_Nom_Catego.getText().toString();
				int row=table.getSelectedRow();
				String update=table.getModel().getValueAt(row,0).toString();
				String sql="Update categorie set nom_catego= '"+nom+"' where id_catego='"+update+"'" ;
				
				if(txt_Nom_Catego.getText().equals("")) {
		            JOptionPane.showMessageDialog(null," Saisir une catégorie");

					
				}
				
				else {
					try{
						mysqlConnect();
						
						
			            
			            pstate = con.prepareStatement(sql);
			    
			            pstate.executeUpdate();
			          actualiser();
			          Resettab();
			          Affiche();

			            System.out.println("Query OK, 1 row updated.");
			            }
			        catch(SQLException a){
			            System.err.println("Query error."+a.getMessage());
			            }
				}
				
					
				 
			}
		});
		Btn_Modifier_Produit.setBounds(255, 181, 116, 23);
		panel.add(Btn_Modifier_Produit);
		
		JButton Btn_Supp_Prdouit = new JButton("Supprimer");
		Btn_Supp_Prdouit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				//******* delete ******************************************
				
				int row=table.getSelectedRow();
				String cell=table.getModel().getValueAt(row,0).toString();
				String sql="delete from categorie where id_catego="+cell;
				
				
					
				 try{
					 mysqlConnect();
			            pstate = con.prepareStatement(sql);
			            pstate.execute();
			            
			            JOptionPane.showMessageDialog(null, "cette catégorie a été supprimé");
			            actualiser();
			            Resettab();
			            Affiche();

			            System.out.println("Query OK, 1 row deleted.");
			            }
			        catch(SQLException a){
			            System.err.println("Query error.");
			            }
			        
				
			}
		});
		Btn_Supp_Prdouit.setBounds(463, 181, 116, 23);
		panel.add(Btn_Supp_Prdouit);
		
		JLabel lblNewLabel_2 = new JLabel("Nom de cat\u00E9gorie :");
		lblNewLabel_2.setBounds(63, 237, 128, 17);
		panel.add(lblNewLabel_2);
		
		txt_Nom_Catego = new JTextField();
		txt_Nom_Catego.setBounds(201, 235, 86, 20);
		panel.add(txt_Nom_Catego);
		txt_Nom_Catego.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gestion de cat\u00E9gorie :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_6.setBounds(174, 11, 343, 33);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Accueil accueil=new Accueil();	
				frame.dispose();
	
			
			}
		});
		btnNewButton.setBounds(567, 259, 89, 23);
		panel.add(btnNewButton);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row=table.getSelectedRow();
			String	ID=table.getModel().getValueAt(row,0).toString();
			String	nom=table.getModel().getValueAt(row,1).toString();

					txt_Nom_Catego.setText(nom);
			}
		});
		table.setEditingRow(1);
		table.setEditingColumn(1);
		table.setDropMode(DropMode.ON);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setFillsViewportHeight(true);
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		
		
		
		 
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "nom de categorie"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setBounds(123, 55, 429, 94);
		panel.add(table);
		
		// *********** affichage tableau 
		
		Affiche();
		
	}
}
