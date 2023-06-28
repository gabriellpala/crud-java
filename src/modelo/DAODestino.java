package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * author kleber0a0m
 */
public class DAODestino {
    DAOTransportadora daoTransportadora = new DAOTransportadora();
    DAOLoja_De_Destino daoLoja_De_Destino = new DAOLoja_De_Destino();
    
    public List<Destino> getLista() {
    String sql = "SELECT * FROM destino";
    List<Destino> lista = new ArrayList<>();
    try {
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Destino obj = new Destino();
            obj.setCodDestino(rs.getInt("codDestino"));
            obj.setFrete(rs.getDouble("frete"));
            obj.setValorEncomenda(rs.getDouble("valorEncomenda"));
            obj.setTransportadora(daoTransportadora.localizar(rs.getInt("TRANSPORTADORA_codTransportadora")));
            obj.setLoja_de_destino(daoLoja_De_Destino.localizar(rs.getInt("LOJA_DE_DESTINO_codLoja")));
            
            // Verificar se a coluna "dataDestino" não é nula
            java.sql.Date dt = rs.getDate("dataDestino");
            if (dt != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                obj.setDataDestino(c);
            }
            
            lista.add(obj);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro de SQL no getLista(): " + e.getMessage());
    }
    return lista;
}



    public boolean salvar(Destino obj) {
        if (obj.getCodDestino() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }
    }

   public boolean incluir(Destino obj) {
    // Verificar se obj.getTransportadora() e obj.getLoja_de_destino() não são nulos
    if (obj.getTransportadora() == null || obj.getLoja_de_destino() == null) {
        JOptionPane.showMessageDialog(null, "Transportadora ou Loja de Destino inválida");
        return false;
    }
    
    String sql = "INSERT INTO destino (frete, valorEncomenda, TRANSPORTADORA_codTransportadora, LOJA_DE_DESTINO_codLoja, dataDestino) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        pst.setDouble(1, obj.getFrete());
        pst.setDouble(2, obj.getValorEncomenda());
        pst.setInt(3, obj.getTransportadora().getCodTransportadora());
        pst.setInt(4, obj.getLoja_de_destino().getCodLoja());
        pst.setDate(5, new java.sql.Date(obj.getDataDestino().getTimeInMillis()));
        if (pst.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Destino cadastrado");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Destino não cadastrado");
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAODestino: " + e.getMessage());
    }
    return false;
}

    public boolean alterar(Destino obj) {
        String sql = "UPDATE destino SET frete=?, valorEncomenda=?, TRANSPORTADORA_codTransportadora=?, LOJA_DE_DESTINO_codLoja=?, dataDestino=? WHERE codDestino=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setDouble(1, obj.getFrete());
            pst.setDouble(2, obj.getValorEncomenda());
            pst.setInt(3, obj.getTransportadora().getCodTransportadora());
            pst.setInt(4, obj.getLoja_de_destino().getCodLoja());
            pst.setDate(5, new java.sql.Date(obj.getDataDestino().getTimeInMillis()));
            pst.setInt(6, obj.getCodDestino());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Destino alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Destino não alterado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAODestino: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(Destino obj) {
        String sql = "DELETE FROM destino WHERE codDestino=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodDestino());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Destino excluído");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Destino não excluído");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAODestino: " + e.getMessage());
        }
        return false;
    }
}