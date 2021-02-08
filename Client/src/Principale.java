import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Principale {

	private JFrame frame;
	private JTable table;
	private JTextField txt_quan;
	private String id_client;

	
	

	   private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
      
	
	    private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password
	    private String Id_Prod;
	    
	    
	  
	    public void Deconnexion() {
	    	
	    	try  
	    	{         
	    	File f= new File("C:\\Users\\sam\\Desktop\\filename.txt");           //file to be delete  
	    	if(f.delete())                      //returns Boolean value  
	    	{  
	    	System.out.println(f.getName() + " deleted");   //getting and printing the file name  
	    	}  
	    	else  
	    	{  
	    	System.out.println("failed");  
	    	}  
	    	}  
	    	catch(Exception e)  
	    	{  
	    	e.printStackTrace();  
	    	}  
	    	    
	    	    Connxion conx =new Connxion();
	    	    frame.dispose();
	    }
	    
//**************** reset tableau *****************
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
		    
		  
	    
	    // methode commande **************
		  
		  public void Commander()
		  {
			  String qua=txt_quan.getText().toString();

				int row=table.getSelectedRow();
			String	id=table.getModel().getValueAt(row,0).toString();
				String update=table.getModel().getValueAt(row,0).toString();
				String sql="Update produits set quantite=quantite-'"+qua+"' where id_prod='"+id+"'" ;
				
				try{
					mysqlConnect();
					
					
						 pstate = con.prepareStatement(sql);
				            pstate.executeUpdate();
				            Resettab();
				            Affiche();
				            System.out.println("Query OK, 1 row updated.");
					
					
		           
		            }
		        catch(SQLException a){
		            System.err.println("Query error."+a.getMessage());
		            }
		  }
		  
		  
	    //verification la quantite ********************
		  public void Verif_Q() {
			  
				
				String les_prod="select quantite from produits p,categorie c where (p.ID_C=c.id_catego); ";
			       
			  	 try{
			  		 mysqlConnect();
			  		
				            state = con.createStatement();
				            result = state.executeQuery(les_prod);
				         
				            int row=table.getSelectedRow();
							String	id=table.getModel().getValueAt(0,0).toString();
							String	id_=table.getModel().getValueAt(0,0).toString();

				            while(result.next()){
				            
				              
				             int quantite=Integer.parseInt(result.getString(1));
				             if(txt_quan.getText().equals("")||txt_quan.getText().equals(" ")) {
					            	JOptionPane.showMessageDialog(null,"Vous deviez sélectionner une ligne !");

								}
				             else if(quantite<Integer.parseInt(txt_quan.getText())){
				            	 JOptionPane.showMessageDialog(null, "Vérifier votre quantité !");
				             }
				             else {
				            	 Commander();
				            	 Operation();
				             }


				                }
				            }
				        catch(SQLException e8){
				            System.err.println("Query error.");
				            }
				        catch(NullPointerException ee){
				            System.err.println("Element not found.");
				            }
		  }
		  
		  // methode operation (timestamp,id_client,id_prod,prix) ***********
		  public void Operation() {
			  
			try{
				mysqlConnect();
				
				String c=ReadFile();
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                     int a=3;
					int b=22;
					  pstate = con.prepareStatement("insert into commande (id_client,id_prod,date_cmd,quantite) VALUES ('"+c+"','"+Id_Prod+"','"+timestamp+"','"+txt_quan.getText()+"')");
		             pstate.executeUpdate();

			            System.out.println(ReadFile()+"samer");

		            System.out.println("Query OK, 1 row insertedted.");
		            JOptionPane.showMessageDialog(null, "Votre commande a été enregistrer ");	

		          

		    	
		            }
		        catch(SQLException e9){
		            System.err.println("Query error.");
		            }
			
				}
		  
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
					Principale window = new Principale();
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
	public Principale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 621, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 605, 241);
		frame.getContentPane().add(panel);
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				int row=table.getSelectedRow();
				String	ID=table.getModel().getValueAt(row,0).toString();
				String	nom=table.getModel().getValueAt(row,1).toString();
				String	prix=table.getModel().getValueAt(row,2).toString();
				String	marq=table.getModel().getValueAt(row,3).toString();
				String	quant=table.getModel().getValueAt(row,4).toString();
                String catego=table.getModel().getValueAt(row,5).toString();
                
                txt_quan.setText(quant);
                Id_Prod=ID;

			}
		});
		panel.setLayout(null);
		table.setBounds(55, 49, 524, 146);
		panel.add(table);
		
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
			
			JButton btnNewButton = new JButton("Commander");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Verif_Q();
					
				}
			});
			btnNewButton.setBounds(340, 169, 120, 23);
			panel.add(btnNewButton);
			
			JLabel lblNewLabel = new JLabel("Quantit\u00E9 :");
			lblNewLabel.setBounds(65, 173, 61, 14);
			panel.add(lblNewLabel);
			
			txt_quan = new JTextField();
			txt_quan.setBounds(168, 170, 86, 20);
			panel.add(txt_quan);
			txt_quan.setColumns(10);
			
			JButton btnNewButton_1 = new JButton("Ma Liste");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ListeAchat listeAchat=new ListeAchat();
				    frame.dispose(); 
					
				}
			});
			btnNewButton_1.setBounds(340, 207, 121, 23);
			panel.add(btnNewButton_1);
			
			JLabel lblNewLabel_1 = new JLabel("Pour commander vous devriez s\u00E9lectionner une ligne :");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_1.setBounds(142, 11, 352, 27);
			panel.add(lblNewLabel_1);
			
			JButton btnNewButton_2 = new JButton("D\u00E9connexion");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Deconnexion();
				}
			});
			btnNewButton_2.setBounds(471, 207, 124, 23);
			panel.add(btnNewButton_2);
			
			
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Recherche par categorie :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(381, 280, 306, 69);
			panel_1.setLayout(null);
			
			Affiche();
		
	}
}
