package controller;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import model.User;
public class UserDAO {
    public int inserir(User user)throws Exception{
        String sql = "insert into usuario(nome, sobre_nome, email, senha) values (?, ?, ?, ?)";
        PreparedStatement ps = null;
        Connection conn = Conexao.getConexao();
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getNome());
        ps.setString(2, user.getSobre_nome());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getSenha());
        int retorno = ps.executeUpdate();
        ps.close();
        return retorno;
    }
    
    
    public User BuscarUsuarioID(int id)throws Exception{
        String sql = "select * from usuario where id = ?";
        Connection conn = Conexao.getConexao();
        User u = null;
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                u = new User();
                u.setId(result.getInt("id"));
                u.setNome(result.getString("nome"));
                u.setSobre_nome(result.getString("sobre_nome"));
                u.setEmail(result.getString("email"));
                u.setSenha(result.getString("senha")); 
                return u;
            }
            return u;
        }catch(Exception e){
            throw new Exception("Erro:" + e.getMessage());
        }
    }
    
    public boolean autenticacao(String email, String senha)throws Exception{
        String sql = "select * from usuario where email = ? and senha = ?";
        Connection conn = Conexao.getConexao();
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet result = ps.executeQuery();
            return result.next();
        }catch(Exception e){
            throw new Exception("Errro: " + e.getMessage());
        }
    }

    public List<User> buscar(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
