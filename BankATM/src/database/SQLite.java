package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SQLite {
	
	private static Connection conn = null;
	
	//create db and initiazlie table schemas
	public static void init(){
//		SQLite.createNewDatabase("Bank.db");
		
		SQLite.createNewTable("Customers", new String[]{"id","Name","Username","Password"}, 
										   new String[]{"INTEGER","TEXT","TEXT","TEXT"}, 
										   new String[]{"PRIMARY KEY","none","NOT NULL","NOT NULL"},
										   new String[]{});
		SQLite.createNewTable("Accounts", new String[]{"id", "Type","Amount","Customer_id","StartingAmount","DateCreated"}, 
										  new String[]{"integer","text","real","integer","real","text"}, 
										  new String[]{"primary key","not null","none","not null","none","not null"},
										  new String[]{"FOREIGN KEY (Customer_id) REFERENCES Customers(id)"});
		SQLite.createNewTable("Loans", new String[]{"id","Amount","Customer_id","Collateral","Interest","ApplyDate","ApproveDate"}, 
									   new String[]{"integer","real","integer","text","real","text","text"},
									   new String[]{"primary key","none","not null","none","none","none","none"},
									   new String[]{"FOREIGN KEY (Customer_id) REFERENCES Customers(id)"});
		SQLite.createNewTable("Transactions", new String[]{"id", "Customer_id","Account_id","Amount","Date"},
											  new String[]{"integer","integer","integer","real","text"},
											  new String[]{"primary key","not null","not null","not null","none"},
											  new String[]{"FOREIGN KEY (Customer_id) REFERENCES Customers(id),\n"+
											  			   "FOREIGN KEY (Account_id) REFERENCES Accounts(id)"});
		SQLite.createNewTable("HeldStocks", new String[]{"id","PriceBoughtAt","StockMarket_id","Account_id","DateBought","Amount"}, 
										    new String[]{"integer","real","integer","integer","text","integer"},
										    new String[]{"primary key","not null","not null","not null","none","not null"},
										    new String[]{"FOREIGN KEY (StockMarket_id) REFERENCES StockMarket(id),\n"+
										    			 "FOREIGN KEY (Account_id) REFERENCES Accounts(id)"});
		SQLite.createNewTable("StockMarket", new String[]{"id","Name","Price","Amount"}, 
											 new String[]{"integer","text","real","integer"},
											 new String[]{"primary key","not null","not null","not null"},
											 new String[]{});
	}
	
	private static Connection connect(String dbName) {
        // SQLite connection string
		String url = "jdbc:sqlite:./src/database/" + dbName;
        
        try {
//        	if(conn==null){
//        		System.out.println("not connected");
        		conn = DriverManager.getConnection(url);
//        		System.out.println("connected");
//        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public static void createNewDatabase(String dbName) {

        

        try (
        	Connection conn = connect(dbName)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	public static void createNewTable(String tableName, String[] columnNames, String[] columnTypes, String[] columnConstraints , String[] foreignKeys) {
        //make sure all three string[] are of same length
		
        // SQL statement for creating a new table
		String columns="";
		try{
			for(int i=0;i<columnNames.length;i++){
				//handle comma issue in last line
				if(i!=columnNames.length-1){
					//check if there is constraint for this column, "none" indicates no constraints
					if(columnConstraints[i].toLowerCase().equals("none")){
						columns+=" "+columnNames[i]+" "+columnTypes[i].toUpperCase()+",\n";
					}
					else{
						columns+=" "+columnNames[i]+" "+columnTypes[i].toUpperCase()+" "+columnConstraints[i].toUpperCase()+",\n";
					}
				}
				else{
					
					if(columnConstraints[i].toLowerCase().equals("none")){
						if(foreignKeys.length<=0){
							columns+=" "+columnNames[i]+" "+columnTypes[i].toUpperCase()+"\n";
						}
						else{
							columns+=" "+columnNames[i]+" "+columnTypes[i].toUpperCase()+",\n";
						}
						
					}else{
						if(foreignKeys.length<=0){
							columns+=" "+columnNames[i]+" "+columnTypes[i].toUpperCase()+" "+columnConstraints[i].toUpperCase()+"\n";
						}
						else{
							columns+=" "+columnNames[i]+" "+columnTypes[i].toUpperCase()+" "+columnConstraints[i].toUpperCase()+",\n";
						}
					}
				}
			}
			
		}catch(Exception e){
			System.out.println("all input arrays need to have the same length ");
		}
		
		String foreign=" ";
		for(String s:foreignKeys){
			foreign+=s+",\n";
		}
		//remove extra comma in last line
		if(foreignKeys.length>0){
			foreign = foreign.substring(0,foreign.length()-2)+"\n";
			foreign+="	ON UPDATE CASCADE\n	ON DELETE CASCADE\n";
		}
		
        String sql = "CREATE TABLE IF NOT EXISTS "+tableName +" (\n"
                + columns
                +foreign
                + ");";
        
        System.out.println(sql);
        try (
        	Connection conn = connect("Bank.db");
            Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
//        Connection conn=null;
//        Statement stmt=null;
//        try {
//        	conn = connect("Bank.db");
//          stmt = conn.createStatement();
//            // create a new table
//            stmt.execute(sql);
//                
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
        
    }
	
	// id refers to the unique id for the record in the table
	public static void delete(String tableName, int id) {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";

        try (Connection conn = connect("Bank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	// example insert statement: "INSERT INTO test(name,capacity) VALUES(?,?)"
	//columns:	[col1, col2, col3]
	//values:	ex:["value","2","3.3"]
	//types:	ex:[text,real,integer]
	//no validation for foreign key
	public static int insert(String tableName, String[] columns, String[] values, String[] types) {
		
        String columnString="";
        String valueString="";
      
        try{
        	for(int i=0; i<columns.length;i++){
            	if(i==0){
            		columnString+="("+columns[i];
            		valueString+=" VALUES(?";
            	}
            	else if(i==columns.length-1){
            		columnString+=","+columns[i]+")";
            		valueString+=",?)";
            	}
            	else{
            		columnString+=","+columns[i];
            		valueString+=",?";
            	}
            	
            }
        	if(columns.length<2){
        		columnString+=")";
        		valueString+=")";
        	}
        }catch(Exception e){
        	System.out.println("all input arrays need to have the same length");
        }

        String sql= "INSERT INTO "+tableName+columnString+valueString;
        System.out.println(sql);

        // set the corresponding param
        try (
    		Connection conn = connect("Bank.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	for(int i=0; i<values.length;i++){
        		if(types[i].toLowerCase().equals("text")){
        			pstmt.setString(i+1, values[i]);
        		
        		}
        		else if(types[i].toLowerCase().equals("real")){
        			pstmt.setDouble(i+1, Double.valueOf(values[i])); 
        		}
        		else if(types[i].toLowerCase().equals("integer")){
        			pstmt.setInt(i+1, Integer.parseInt(values[i]));
        		}
        	}     
            pstmt.executeUpdate();
            ResultSet rs=null;
            rs = pstmt.getGeneratedKeys();
            //return the newly generated primary key
            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
	
    public static ArrayList<ArrayList<String>> query(String query, String[] columns, String[] columnTypes){
//        String sql = "SELECT id, name, capacity FROM test";
        
        try (Connection conn = connect("Bank.db");
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)){
            
        	String res="";	//for display
        	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();	//result to be result
            // loop through the result set
            while (rs.next()) {
            	//store values by row and col
                ArrayList<String> row = new ArrayList<String>();
                for(int i=0; i<columns.length;i++){
            		if(columnTypes[i].toLowerCase().equals("text")){
            			res+=rs.getString(columns[i]) + "\t";
            			row.add(rs.getString(columns[i]));
            		
            		}
            		else if(columnTypes[i].toLowerCase().equals("real")){
            			res+=rs.getDouble(columns[i]) + "\t"; 
            			row.add(Double.toString(rs.getDouble(columns[i])));
            		}
            		else if(columnTypes[i].toLowerCase().equals("integer")){
            			res+=rs.getInt(columns[i])+"\t";
            			row.add(Integer.toString(rs.getInt(columns[i])));
            		}
            	}     
                res+="\n";
                result.add(row);
            }
            System.out.println(res);
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
// simple update query in this format
//    UPDATE table
//    SET column_1 = new_value_1,
//        column_2 = new_value_2
//    WHERE
//        search_condition
    
//where:	ex: Col1 = Val1 AND Col2 = Val2 AND Col3 = Val3
    public static void update(String tableName, String where, String[] columns, String[] values, String[] types) {
        
        String columnString="";
        
        
        try{
        	for(int i=0; i<columns.length;i++){
            	if(i==columns.length-1){
            		columnString+=columns[i]+" = ? ";
            	}
            	else{
            		columnString+=columns[i]+" = ?, ";
            	}
            }

        }catch(Exception e){
        	System.out.println("all input arrays need to have the same length");
        }
        
        String sql="UPDATE "+tableName+" SET "+columnString+"WHERE "+where;

        try (Connection conn = connect("Bank.db");
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
        	for(int i=0; i<values.length;i++){
        		if(types[i].toLowerCase().equals("text")){
        			pstmt.setString(i+1, values[i]);
        		
        		}
        		else if(types[i].toLowerCase().equals("real")){
        			pstmt.setDouble(i+1, Double.valueOf(values[i])); 
        		}
        		else if(types[i].toLowerCase().equals("integer")){
        			pstmt.setInt(i+1, Integer.parseInt(values[i]));
        		}
        	}     
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
