package controller;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
}
