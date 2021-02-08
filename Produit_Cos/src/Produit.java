import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import org.eclipse.swt.widgets.Combo;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;

public class Produit {

	private JFrame frame;
	private JTextField txt_Name;
	private JTextField txt_Prix;
	private JTextField txt_Marque;
	private JComboBox<String> comb_Categorie;
	JComboBox comb_nom_catg = new JComboBox();


	 private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;

	
	    private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password
	    private JTable table;
	    private JTextField txt_cher;
	    private JTextField txt_quan;
	    
	    
	    
	    //******* actualiser les inputs *******
	    
	    public void Actualiser() {
	    	txt_Name.setText("");
	    	txt_Prix.setText("");
	    	txt_Marque.setText("");
	    	txt_quan.setText("");

	    }
	    
	    
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
						             String prix=result.getString(3);
						             String marque=result.getString(4);
						             String quantite=result.getString(5);
						             String catego=result.getString(6);

				             
				             table.setModel(new DefaultTableModel(null,new String[] { ID,nom,prix,marque,quantite,catego}));
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
		    	
		    	
				String les_prod="select id_prod,nom_prod,prix_prod,marque_prod,quantite,c.nom_catego from produits p,categorie c where (p.ID_C=c.id_catego); ";
			       
			  	 try{
			  		 mysqlConnect();
						
				            state = con.createStatement();
				            result = state.executeQuery(les_prod);
				         
				            while(result.next()){
				             String ID= result.getString(1);
				             String nom=result.getString(2);
				             String prix=result.getString(3);
				             String marque=result.getString(4);
				             String quantite=result.getString(5);
				             String catego=result.getString(6);




					            DefaultTableModel model=(DefaultTableModel)table.getModel();
					            model.addRow(new Object[] { ID,nom,prix,marque,quantite,catego});
			
					            System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+"les catego est: "+result.getString(5));

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
					Produit window = new Produit();
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
	public Produit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBounds(100, 100, 727, 428);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 711, 389);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		JButton Btn_Ajout_Produit = new JButton("Ajouter");
		Btn_Ajout_Produit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//******** insert **********
				if(txt_Name.getText().equals("") || txt_Prix.getText().equals("")|| txt_Marque.getText().equals("")||txt_quan.getText().equals(""))
				{
		            JOptionPane.showMessageDialog(null, "remplir les champs");

				}
				else {
				try{
					mysqlConnect();
					
					  pstate = con.prepareStatement("insert into produits(nom_prod,prix_prod,marque_prod,quantite,ID_C) VALUES ('"+txt_Name.getText()+"','"+txt_Prix.getText()+"','"+txt_Marque.getText()+"','"+txt_quan.getText()+"','"+comb_Categorie.getSelectedItem()+"')");
		             pstate.executeUpdate();

		           
		            System.out.println("Query OK, 1 row insertedted.");
		            JOptionPane.showMessageDialog(null, "Bien ajouter");	

		            Actualiser();
		            Resettab();
		            Affiche();
		          

		    	
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
			public void actionPerformed(ActionEvent e) {
				String No=txt_Name.getText().toString();
				String Pr=txt_Prix.getText().toString();
				String Ma=txt_Marque.getText().toString();
				String qua=txt_quan.getText().toString();


				int row=table.getSelectedRow();
			String	id=table.getModel().getValueAt(row,0).toString();
				String update=table.getModel().getValueAt(row,0).toString();
				String sql="Update produits set nom_prod= '"+No+"', prix_prod='"+Pr+"',marque_prod='"+Ma+"',quantite='"+qua+"' where id_prod='"+id+"'" ;
				
				try{
					mysqlConnect();
					
					
		            
		            pstate = con.prepareStatement(sql);
		            pstate.executeUpdate();
		          Actualiser();
		            Resettab();
		            Affiche();
		            System.out.println("Query OK, 1 row updated.");
		            }
		        catch(SQLException a){
		            System.err.println("Query error."+a.getMessage());
		            }
				
			}
			
			
		});
		Btn_Modifier_Produit.setBounds(255, 181, 116, 23);
		panel.add(Btn_Modifier_Produit);
		
		JButton Btn_Supp_Prdouit = new JButton("Supprimer");
		Btn_Supp_Prdouit.setSelected(true);
		Btn_Supp_Prdouit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//************************ delete *******
				int row=table.getSelectedRow();
				String cell=table.getModel().getValueAt(row,0).toString();
				String sql="delete from produits where id_prod="+cell;
				
				
					
				 try{
					 mysqlConnect();
			            //using PreparedStatement
			            pstate = con.prepareStatement(sql);
			            pstate.execute();
			            
			            JOptionPane.showMessageDialog(null,"good");
			            
			            Actualiser();
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
		
		JLabel lblNewLabel_2 = new JLabel("Nom :");
		lblNewLabel_2.setBounds(25, 236, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Prix :");
		lblNewLabel_3.setBounds(25, 280, 46, 14);
		panel.add(lblNewLabel_3);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(81, 233, 86, 20);
		panel.add(txt_Name);
		txt_Name.setColumns(10);
		
		txt_Prix = new JTextField();
		txt_Prix.setBounds(81, 277, 86, 20);
		panel.add(txt_Prix);
		txt_Prix.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Marque :");
		lblNewLabel_4.setBounds(25, 323, 62, 19);
		panel.add(lblNewLabel_4);
		
		txt_Marque = new JTextField();
		txt_Marque.setBounds(81, 322, 86, 20);
		panel.add(txt_Marque);
		txt_Marque.setColumns(10);
		
		comb_Categorie = new JComboBox<String>();
		comb_Categorie.setBounds(268, 233, 103, 21);
		panel.add(comb_Categorie);
		String cc=(String)comb_Categorie.getSelectedItem();

		//********** select categorie**************
		
		String s="select id_catego from categorie   ";

   	 try{
   		 mysqlConnect();
			
	            state = con.createStatement();
	            result = state.executeQuery(s);
	         
	            while(result.next()){
	             
	        		comb_Categorie.addItem(result.getString(1));
	        		
	            	
	                }
	            }
	        catch(SQLException e){
	            System.err.println("Query error.");
	            }
	        catch(NullPointerException e){
	            System.err.println("Element not found.");
	            }
		
   	
		
		
		
		JLabel lblNewLabel_5 = new JLabel("ID Cat\u00E9gorie :");
		lblNewLabel_5.setBounds(189, 234, 89, 19);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Gestion des produits :");
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
		btnNewButton.setBounds(597, 355, 89, 23);
		panel.add(btnNewButton);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row=table.getSelectedRow();
				String	nom=table.getModel().getValueAt(row,1).toString();
				String	prix=table.getModel().getValueAt(row,2).toString();
				String	marq=table.getModel().getValueAt(row,3).toString();
				String	quant=table.getModel().getValueAt(row,4).toString();
                String catego=table.getModel().getValueAt(row,5).toString();



						txt_Name.setText(nom);
						txt_Prix.setText(prix);
						txt_Marque.setText(marq);
						txt_quan.setText(quant);
						
						

			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom ","prix", "Marque ","quantite","categorie"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class,String.class, String.class,String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(118);
		table.getColumnModel().getColumn(1).setPreferredWidth(111);
		table.setBounds(55, 49, 524, 100);
		panel.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Recherche par categorie :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(381, 280, 306, 69);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txt_cher = new JTextField();
		txt_cher.setBounds(35, 26, 126, 20);
		panel_1.add(txt_cher);
		txt_cher.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Chercher");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//int row=table.getSelectedRow();
			//	String ID_cat=table.getModel().getValueAt(row,0).toString();
				String cher=txt_cher.getText();
				if(cher.equals("")) {
					JOptionPane.showMessageDialog(null, "Champ vide !!");
				}
					
					else
					{
						try{
							String sql_cher="SELECT  id_prod,nom_prod,prix_prod,quantite,marque_prod,c.nom_catego from categorie c, produits p where p.ID_C=c.id_catego and c.nom_catego='"+cher+"'" ;

					  		 mysqlConnect();
								
						            state = con.createStatement();
						            result = state.executeQuery(sql_cher);
						         
						            while(result.next()){
						             String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prix=result.getString(3);
						             String quantite=result.getString(4);
						             String marque=result.getString(5);
						             String catego=result.getString(6);
						             
						             table.setModel(new DefaultTableModel(null,new String[] {ID,nom,prix,quantite,marque,catego}));
						             DefaultTableModel model=(DefaultTableModel)table.getModel();

						             model.addRow(new Object[]{ID,nom,prix,marque,quantite,catego});

							        //  DefaultTableModel model=(DefaultTableModel)table.getModel();
							          // model.addRow(new Object[] { ID,nom,prix,marque,catego});
					
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
		btnNewButton_1.setBounds(198, 25, 102, 23);
		panel_1.add(btnNewButton_1);
		
		comb_nom_catg.setBounds(527, 232, 89, 22);
		panel.add(comb_nom_catg);
		
		JLabel lblNewLabel = new JLabel("Nom de cat\u00E9gorie :");
		lblNewLabel.setBounds(402, 236, 115, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantit\u00E9 :");
		lblNewLabel_1.setBounds(189, 280, 70, 14);
		panel.add(lblNewLabel_1);
		
		txt_quan = new JTextField();
		txt_quan.setBounds(263, 277, 108, 20);
		panel.add(txt_quan);
		txt_quan.setColumns(10);
		String no="select * from categorie where id_catego='"+comb_Categorie.getSelectedItem()+"'";

	   	 try{
	   		 mysqlConnect();
				
		            state = con.createStatement();
		            result = state.executeQuery(no);
		         
		            while(result.next()){
		             
		        		comb_nom_catg.addItem(result.getString(2));
		        		
		            	
		                }
		            }
		        catch(SQLException e){
		            System.err.println("Query error.");
		            }
		        catch(NullPointerException e){
		            System.err.println("Element not found.");
		            }
			
	   	
		
		
    //*****************affichage tableau *****************
	
		Affiche();
	}
}
