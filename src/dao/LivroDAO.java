package dao;

import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection conexao;

    public LivroDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Livro livro){
        String sql = "insert into livros (titulo, data_lancamento, quantidade, preco, editora_id) values (?, ?, ?, ?, ?)";

        try{
            // Preparar a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, livro.getData_lancamento());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setDouble(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());

            //Executar o comando
            stmt.execute();

            //Fechar a conexão
            conexao.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Livro> listarTodos(){
        String sql = "select * from livros";
        List<Livro> livros = new ArrayList<>();

        try {
            // Preparar a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Executar
            ResultSet resutados = stmt.executeQuery();

            // Percorrer os resultados
            while (resutados.next()){
                Livro livro = new Livro();
                livro.setId(resutados.getInt("id"));
                livro.setTitulo(resutados.getString("titulo"));
                livro.setData_lancamento(resutados.getDate("data_lancamento"));
                livro.setQuantidade(resutados.getInt("quantidade"));
                livro.setPreco(resutados.getDouble("preco"));
                livro.setEditora_id(resutados.getInt("editora_id"));

                livros.add(livro);
            }

            // Fechar conexão
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return livros;
    }

    public void alterar(Livro livro){
        String sql = "update livros set titulo = ?, data_lancamento = ?, quantidade = ?, preco = ?, editora_id = ? where id = ?";

        try {
            // Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, livro.getData_lancamento());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setDouble(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());

            // Executa
            stmt.execute();

            // Fecha a conexão
            stmt.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(Livro livro){
        String sql = "delete livros set titulo = ?, data_lancamento = ?, quantidade = ?, preco = ?, editora_id = ? where id = ?";


        try {
            // Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, livro.getData_lancamento());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setDouble(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());

            // Executa
            stmt.execute();

            // Fecha a conexão
            stmt.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Livro listarPorId(int id){
        String sql = "select * from livros where id = ?";

        Livro livro = new Livro();

        try {
            //Prepara a conexão
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executa
            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            //Populando o objeto autor
            livro.setId(resultado.getInt("id"));
            livro.setTitulo(resultado.getString("titulo"));
            livro.setData_lancamento(resultado.getDate("data_lancamento"));
            livro.setQuantidade(resultado.getInt("quantidade"));
            livro.setPreco(resultado.getDouble("preco"));
            livro.setEditora_id(resultado.getInt("editora_id"));

            //Fechar conexão
            conexao.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return livro;
    }

    // Fazer interface com comandos
}
