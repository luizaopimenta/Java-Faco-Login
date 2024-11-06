package controller;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import model.User;
import java.util.ArrayList;

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
                u = new User(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getString("sobre_nome"),
                        result.getString("email")
                );
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

    public List<User>buscar(String nome)throws Exception{
        Connection conn = Conexao.getConexao();
        String sql = "select * from usuario where nome like ?";
        List<User> usuarios = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(sql);
        if(!nome.equals("")){
            ps.setString(1, nome + "%");
        }
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            User u = new User(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sobre_nome"),
                    rs.getString("email")
            );
            usuarios.add(u);
        }
        return usuarios;
    }
    
}
