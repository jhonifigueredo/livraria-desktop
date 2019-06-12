package dao;

import model.Editora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

    private Connection conexao;

    public EditoraDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Editora editora){
        String sql = "insert into editoras (nome, site) values (?, ?)";

        try{
            // Preparar a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());

            //Executar o comando
            stmt.execute();

            //Fechar a conexão
            conexao.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Editora> listarTodos(){
        String sql = "select * from editoras";
        List<Editora> editoras = new ArrayList<>();

        try {
            // Preparar a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Executar
            ResultSet resutados = stmt.executeQuery();

            // Percorrer os resultados
            while (resutados.next()){
                Editora editora = new Editora();
                editora.setId(resutados.getInt("id"));
                editora.setNome(resutados.getString("nome"));
                editora.setSite(resutados.getString("site"));

                editoras.add(editora);
            }

            // Fechar conexão
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return editoras;
    }

    public void alterar(Editora editora){
        String sql = "update editoras set nome = ?, site = ? where id = ?";

        try {
            // Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setInt(3, editora.getId());

            // Executa
            stmt.execute();

            // Fecha a conexão
            stmt.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(Editora editora){
        String sql = "delete editoras set nome = ?, site = ? where id = ?";

        try {
            // Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setInt(3, editora.getId());

            // Executa
            stmt.execute();

            // Fecha a conexão
            stmt.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Editora listarPorId(int id){
        String sql = "select * from editoras where id = ?";

        Editora editora = new Editora();

        try {
            //Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executa
            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            //Populando o objeto
            editora.setId(resultado.getInt("id"));
            editora.setNome(resultado.getString("nome"));
            editora.setSite(resultado.getString("site"));

            //Fechar conexão
            conexao.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return editora;
    }

    // Fazer interface com comandos
}
