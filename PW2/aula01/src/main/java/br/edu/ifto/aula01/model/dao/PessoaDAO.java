package br.edu.ifto.aula01.model.dao;

import br.edu.ifto.aula01.model.connection.ConnectionFactory;
import br.edu.ifto.aula01.model.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class PessoaDAO {
    //criar um objeto Connection para receber a conexão
    Connection connection;

    public PessoaDAO(){
        connection = ConnectionFactory.getConnection();
    }

    public List<Pessoa> buscarPessoas() {
        try {
            //comando sql
            String sql = "SELECT * FROM TB_PESSOA LIMIT 50";
            PreparedStatement ps = connection.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Pessoa> pessoas = new ArrayList();
            //laço para buscar todas as pessoas do banco
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                //add pessoa na lista
                pessoas.add(p);
            }
            //retorna a lista de pessoas
            return pessoas;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }//method

    public boolean remove(Long id) {
        try {
            //comando sql
            String sql = "DELETE FROM TB_PESSOA WHERE ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save(Pessoa pessoa) {
        try {
            //comando sql
            String sql = "INSERT INTO TB_PESSOA (nome) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, pessoa.getNome());

            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Pessoa pessoa) {
        try {
            //comando sql
            String sql = "UPDATE TB_PESSOA SET nome=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, pessoa.getNome());
            ps.setLong(2, pessoa.getId());

            if (ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Pessoa buscarPessoaByID(Long id) {
        try {
            //comando sql
            String sql = "SELECT * FROM TB_PESSOA WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}//class
