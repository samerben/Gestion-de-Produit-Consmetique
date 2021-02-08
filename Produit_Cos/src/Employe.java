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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

public class Employe {
	private JFrame frame;
	private JTable table;
	private JTextField txt_Nom;
	private JTextField txt_Prenom;
	private JTextField txt_adrs;
	private JTextField txt_tlf;
	private JTextField txt_garde;
	private   JComboBox cmb_rech = new JComboBox();

	 private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;

	
	    private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; // produit is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password
	    private JTextField txt_rech;
	    
	    
   //******* actualiser les inputs *******
	    
	    public void Actualiser() {
	    	txt_Nom.setText("");
	    	txt_Prenom.setText("");
	    	txt_garde.setText("");
	    	txt_adrs.setText("");
	    	txt_tlf.setText("");


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
	    
	    
	    
	   public void Resettab() {
		   String emp="select *from employe ; ";
	       
		  	 try{
		  		 mysqlConnect();
					
			            state = con.createStatement();
			            result = state.executeQuery(emp);
			         
			            while(result.next()){
			             String ID= result.getString(1);
			             String nom=result.getString(2);
			             String prenom=result.getString(3);
			             String adrs=result.getString(4);
			             String garde=result.getString(5);
			             String tel=result.getString(6);
			             
			             table.setModel(new DefaultTableModel(null,new String[] {ID,nom,prenom,adrs,garde,tel}));


			                }
			            }
			        catch(SQLException e8){
			            System.err.println("Query error.");
			            }
			        catch(NullPointerException ee){
			            System.err.println("Element not found.");
			            }
			
		   
	   }
	    public void Affiche () {
	    	
	    	
	    	
	    	String emp="select *from employe ; ";
		       
		  	 try{
		  		 mysqlConnect();
					
			            state = con.createStatement();
			            result = state.executeQuery(emp);
			         
			            while(result.next()){
			             String ID= result.getString(1);
			             String nom=result.getString(2);
			             String prenom=result.getString(3);
			             String adrs=result.getString(4);
			             String garde=result.getString(5);
			             String tel=result.getString(6);
			             
			             

				            DefaultTableModel model_emp=(DefaultTableModel)table.getModel();
				            model_emp.addRow(new Object[] { ID,nom,prenom,adrs,garde,tel});
		
				            System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(5));

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
					Employe window = new Employe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	Employe employe=new Employe();
	}

	/**
	 * Create the application.
	 */
	public Employe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMaximumSize(new Dimension(500, 500));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 767, 428);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 751, 389);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row=table.getSelectedRow();
				String	ID_emp=table.getModel().getValueAt(row,0).toString();
				String	nom_emp=table.getModel().getValueAt(row,1).toString();
				 String prenom_emp=table.getModel().getValueAt(row,2).toString();
				 String garde_emp=table.getModel().getValueAt(row,4).toString();
				 String adrs_emp=table.getModel().getValueAt(row,3).toString();
				 String tel_emp=table.getModel().getValueAt(row,5).toString();
				
				txt_Nom.setText(nom_emp);
				txt_Prenom.setText(prenom_emp);
				txt_garde.setText(garde_emp);
				txt_adrs.setText(adrs_emp);
				txt_tlf.setText(tel_emp);



						
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID","Nom","Prénom","Adresse","Garde","Télephone"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class,String.class,String.class,String.class,String.class,String.class,
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(61, 55, 569, 99);
		panel.add(table);
		
		JButton Btn_Ajout_emp = new JButton("Ajouter");
		Btn_Ajout_emp.setBounds(58, 181, 116, 23);
		Btn_Ajout_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_Nom.getText().equals("") || txt_Prenom.getText().equals("")|| txt_garde.getText().equals("") || txt_tlf.getText().equals("")|| txt_adrs.getText().equals(""))
				{
		            JOptionPane.showMessageDialog(null, "remplir les champs");

				}
				else {
				try{
					mysqlConnect();
					
					  pstate = con.prepareStatement("insert into employe(nom_emp,prenom_emp,adresse_emp,garde,num_tel) VALUES ('"+txt_Nom.getText()+"', '"+txt_Prenom.getText()+"','"+txt_adrs.getText()+"','"+txt_garde.getText()+"','"+txt_tlf.getText()+"')");
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
				}}
			
			
		});
		panel.add(Btn_Ajout_emp);
		
		JButton Btn_Modifier_emp = new JButton("Modifier");
		Btn_Modifier_emp.setBounds(241, 181, 116, 23);
		Btn_Modifier_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String	nom=txt_Nom.getText().toString();
				 String	pre=txt_Prenom.getText().toString();
				String gar=txt_garde.getText().toString();
				 String tel=txt_tlf.getText().toString();
				 String adrs=txt_adrs.getText().toString();
				
				int row=table.getSelectedRow();
				String update=table.getModel().getValueAt(row,0).toString();
				String sql="Update employe set nom_emp= '"+nom+"',prenom_emp='"+pre+"',adresse_emp='"+adrs+"',garde='"+gar+"',num_tel='"+tel+"' where id_emp='"+update+"'" ;
				
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
		panel.add(Btn_Modifier_emp);
		
		JButton Btn_Supp_emp = new JButton("Supprimer");
		Btn_Supp_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//************************ delete *******
				int row=table.getSelectedRow();
				String cell=table.getModel().getValueAt(row,0).toString();
				String sql="delete from employe where id_emp="+cell;

				
					
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
		Btn_Supp_emp.setBounds(471, 181, 116, 23);
		panel.add(Btn_Supp_emp);
		
		JLabel lblNewLabel_2 = new JLabel("Nom :");
		lblNewLabel_2.setBounds(10, 239, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_3.setBounds(10, 287, 59, 14);
		panel.add(lblNewLabel_3);
		
		txt_Nom = new JTextField();
		txt_Nom.setBounds(79, 236, 103, 20);
		panel.add(txt_Nom);
		txt_Nom.setColumns(10);
		
		txt_Prenom = new JTextField();
		txt_Prenom.setBounds(79, 284, 103, 20);
		panel.add(txt_Prenom);
		txt_Prenom.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Adresse :");
		lblNewLabel_4.setBounds(204, 282, 77, 19);
		panel.add(lblNewLabel_4);
		
		txt_adrs = new JTextField();
		txt_adrs.setBounds(291, 281, 115, 20);
		panel.add(txt_adrs);
		txt_adrs.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gestion des employes :");
		lblNewLabel_6.setBounds(166, 11, 362, 33);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("T\u00E9lephone :");
		lblNewLabel_7.setBounds(204, 239, 77, 14);
		panel.add(lblNewLabel_7);
		
		txt_tlf = new JTextField();
		txt_tlf.setBounds(291, 236, 115, 20);
		panel.add(txt_tlf);
		txt_tlf.setColumns(10);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.setBounds(597, 355, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Accueil accueil=new Accueil();		
				frame.dispose();
	
			
			}
		});
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Garde :");
		lblNewLabel.setBounds(10, 343, 46, 14);
		panel.add(lblNewLabel);
		
		txt_garde = new JTextField();
		txt_garde.setBounds(79, 340, 103, 20);
		panel.add(txt_garde);
		txt_garde.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Recherche :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(435, 226, 306, 118);
		panel.add(panel_1);
		
		JButton button = new JButton("Chercher");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cher=txt_rech.getText();
				
				
				if(cher.equals("")) {
					JOptionPane.showMessageDialog(null, "Champ vide !!");
				}		
				
				else if(cmb_rech.getSelectedIndex()==0 && cher!="")
				{
					String sql_cher="SELECT * from employe where id_emp='"+cher+"'" ;

					 try{
				  		 mysqlConnect();
							
					            state = con.createStatement();
					            result = state.executeQuery(sql_cher);
					         
					            while(result.next()){
					                String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prenom=result.getString(3);
						             String adrs=result.getString(4);
						             String garde=result.getString(5);
						             String tel=result.getString(6);
						             
					             table.setModel(new DefaultTableModel(null,new String[] {ID,nom,prenom,adrs,garde,tel}));
					             DefaultTableModel model=(DefaultTableModel)table.getModel();

					             model.addRow(new Object[]{ID,nom,prenom,adrs,garde,tel});

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
				
				else if(cmb_rech.getSelectedIndex()==1) {
					String sql_cher="SELECT * from employe where nom_emp='"+cher+"'" ;
				
						try{
					  		 mysqlConnect();
								
						            state = con.createStatement();
						            result = state.executeQuery(sql_cher);
						         
						            while(result.next()){
						             String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prenom=result.getString(3);
						             String adrs=result.getString(4);
						             String garde=result.getString(5);
						             String tel=result.getString(6);
						             

						             table.setModel(new DefaultTableModel(null,new String[] {ID,nom,prenom,adrs,garde,tel}));
						             DefaultTableModel model=(DefaultTableModel)table.getModel();

						             model.addRow(new Object[]{ID,nom,prenom,adrs,garde,tel});
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
							
                     else if(cmb_rech.getSelectedIndex()==2) {
					
					String sql_cher="SELECT * from employe where garde='"+cher+"'" ;
					
					
						try{
					  		 mysqlConnect();
								
						            state = con.createStatement();
						            result = state.executeQuery(sql_cher);
						         
						            while(result.next()){
						             String ID= result.getString(1);
						             String nom=result.getString(2);
						             String prenom=result.getString(3);
						             String adrs=result.getString(4);
						             String garde=result.getString(5);
						             String tel=result.getString(6);
						             

						             table.setModel(new DefaultTableModel(null,new String[] {ID,nom,prenom,adrs,garde,tel}));
						             DefaultTableModel model=(DefaultTableModel)table.getModel();

						             model.addRow(new Object[]{ID,nom,prenom,adrs,garde,tel});
							       System.out.println(result.getString(1));

						                }
						            }
						        catch(SQLException e8){
						            System.err.println("Query error.");
						            }
						        catch(NullPointerException ee){
						            System.err.println("Element not found.");
						            
						
					}}
								
								
			}
		});
		button.setBounds(194, 64, 102, 23);
		panel_1.add(button);
		
		JLabel label = new JLabel("Type de recheche :");
		label.setBounds(31, 35, 126, 14);
		panel_1.add(label);
         
         txt_rech = new JTextField();
         txt_rech.setBounds(41, 65, 116, 20);
         panel_1.add(txt_rech);
         txt_rech.setColumns(10);
         
         cmb_rech.setBounds(194, 31, 59, 22);
         panel_1.add(cmb_rech);
         cmb_rech.addItem("ID");
         cmb_rech.addItem("Nom");
         cmb_rech.addItem("Grade");



		

		
	    //*****************affichage tableau *****************
			
			
     	Affiche();

			
	
		
		
	}
}
