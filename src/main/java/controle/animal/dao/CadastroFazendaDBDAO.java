package controle.animal.dao;

import controle.animal.model.CadastroFazenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroFazendaDBDAO implements CadastroFazendaDAO {
    private String sql;
    private static Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private void open() throws SQLException {
        connection = Conexao.getConnection(Conexao.url, Conexao.usuario, Conexao.senha);
    }

    private void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void insere(CadastroFazenda fazenda) throws SQLException {
        open();
        sql = "INSERT INTO CadastroFazenda (nome, cnpj, ramo, telefone, email, cep, rua, nCasa, bairro, cidade, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, fazenda.getNome());
        statement.setString(2, fazenda.getCnpj());
        statement.setString(3, fazenda.getRamo());
        statement.setString(4, fazenda.getTelefone());
        statement.setString(5, fazenda.getEmail());
        statement.setString(6, fazenda.getCep());
        statement.setString(7, fazenda.getRua());
        statement.setInt(8, fazenda.getnCasa());
        statement.setString(9, fazenda.getBairro());
        statement.setString(10, fazenda.getCidade());
        statement.setString(11, fazenda.getEstado());
        statement.executeUpdate();
        close();
    }

    @Override
    public void atualiza(CadastroFazenda fazenda) throws SQLException {
        open();
        sql = "UPDATE CadastroFazenda SET nome=?, ramo=?, telefone=?, email=?, cep=?, rua=?, nCasa=?, bairro=?, cidade=?, estado=? " +
                "WHERE cnpj=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, fazenda.getNome());
        statement.setString(2, fazenda.getRamo());
        statement.setString(3, fazenda.getTelefone());
        statement.setString(4, fazenda.getEmail());
        statement.setString(5, fazenda.getCep());
        statement.setString(6, fazenda.getRua());
        statement.setInt(7, fazenda.getnCasa());
        statement.setString(8, fazenda.getBairro());
        statement.setString(9, fazenda.getCidade());
        statement.setString(10, fazenda.getEstado());
        statement.setString(11, fazenda.getCnpj());
        statement.executeUpdate();
        close();
    }

    @Override
    public void remove(CadastroFazenda fazenda) throws SQLException {
        open();
        sql = "DELETE FROM CadastroFazenda WHERE cnpj=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, fazenda.getCnpj());
        statement.executeUpdate();
        close();
    }

    @Override
    public CadastroFazenda buscaPorCNPJ(String cnpj) throws SQLException {
        open();
        sql = "SELECT * FROM CadastroFazenda WHERE cnpj=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, cnpj);
        result = statement.executeQuery();

        CadastroFazenda fazenda = null;
        if (result.next()) {
            fazenda = new CadastroFazenda();
            fazenda.setNome(result.getString("nome"));
            fazenda.setCnpj(result.getString("cnpj"));
            fazenda.setRamo(result.getString("ramo"));
            fazenda.setTelefone(result.getString("telefone"));
            fazenda.setEmail(result.getString("email"));
            fazenda.setCep(result.getString("cep"));
            fazenda.setRua(result.getString("rua"));
            fazenda.setnCasa(result.getInt("nCasa"));
            fazenda.setBairro(result.getString("bairro"));
            fazenda.setCidade(result.getString("cidade"));
            fazenda.setEstado(result.getString("estado"));
        }
        close();
        return fazenda;
    }

    @Override
    public List<CadastroFazenda> listaTodos() throws SQLException {
        open();
        sql = "SELECT * FROM CadastroFazenda";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();

        List<CadastroFazenda> fazendas = new ArrayList<>();
        while (result.next()) {
            CadastroFazenda fazenda = new CadastroFazenda();
            fazenda.setNome(result.getString("nome"));
            fazenda.setCnpj(result.getString("cnpj"));
            fazenda.setRamo(result.getString("ramo"));
            fazenda.setTelefone(result.getString("telefone"));
            fazenda.setEmail(result.getString("email"));
            fazenda.setCep(result.getString("cep"));
            fazenda.setRua(result.getString("rua"));
            fazenda.setnCasa(result.getInt("nCasa"));
            fazenda.setBairro(result.getString("bairro"));
            fazenda.setCidade(result.getString("cidade"));
            fazenda.setEstado(result.getString("estado"));
            fazendas.add(fazenda);
        }
        close();
        return fazendas;
    }

    public static boolean cnpjExists(String cnpj) throws SQLException {
        String query = "SELECT COUNT(*) FROM fazendas WHERE cnpj = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

}
