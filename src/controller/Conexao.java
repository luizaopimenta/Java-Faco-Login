package controller;
import java.sql.DriverManager;
import java.sql.Connection;
public class Conexao {
    
    private static Connection conn;
    
    public static Connection getConexao(){
        try{
            if(conn == null){
              String driver = "com.mysql.cj.jdbc.Driver";
              String url = "jdbc:mysql://facodatabase.mysql.dbaas.com.br/facodatabase";
              String user = "facodatabase";
              String pass = "fac0p4ssW0rd1!";
              
              Class.forName(driver);
              
              conn = DriverManager.getConnection(url, user, pass);
              conn.getAutoCommit();
              
              return conn;
            }else{
                return conn;
            }
        }catch(Exception e){
            throw new RuntimeException(e);  
        }
    }
}
