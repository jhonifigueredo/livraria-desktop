package dao;

import model.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conexao;

    public AutorDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Autor autor){
        String sql = "insert into autores (nome, email) values (?, ?)";

        try{
            // Preparar a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());

            //Executar o comando
            stmt.execute();

            //Fechar a conexão
            conexao.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Autor> listarTodos(){
        String sql = "select * from autores";
        List<Autor> autores = new ArrayList<>();

        try {
            // Preparar a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Executar
            ResultSet resutados = stmt.executeQuery();

            // Percorrer os resultados
            while (resutados.next()){
                Autor autor = new Autor();
                autor.setId(resutados.getInt("id"));
                autor.setNome(resutados.getString("nome"));
                autor.setEmail(resutados.getString("email"));

                autores.add(autor);
            }

            // Fechar conexão
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autores;
    }

    public void alterar(Autor autor){
        String sql = "update autores set nome = ?, email = ? where id = ?";

        try {
            // Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());
            stmt.setInt(3, autor.getId());

            // Executa
            stmt.execute();

            // Fecha a conexão
            stmt.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(Autor autor){}

    public Autor listarPorId(int id){
        String sql = "select * from autores where id = ?";

        Autor autor = new Autor();

        try {
            //Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executa
            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            //Populando o objeto autor
            autor.setId(resultado.getInt("id"));
            autor.setNome(resultado.getString("nome"));
            autor.setEmail(resultado.getString("email"));

            //Fechar conexão
            conexao.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return autor;
    }

    // Fazer interface com comandos
}
