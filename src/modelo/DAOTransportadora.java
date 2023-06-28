/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kleber0a0m
 */
public class DAOTransportadora {
    public Transportadora localizar(Integer id){
         String sql = "select * from transportadora where codTransportadora=?";
         Transportadora obj = new Transportadora();
         try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodTransportadora(rs.getInt("codTransportadora"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));
                return obj;
            }
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro de SQL Localizar DaoTransportadora"+e.getMessage());
         }
         return null;
     } 
    
    /*********************************/

    public List <Transportadora> getLista(){
       String sql = "select * from transportadora;";
       List<Transportadora> lista = new ArrayList <>();
       try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               Transportadora obj = new Transportadora();
               obj.setCodTransportadora(rs.getInt("codTransportadora"));
               obj.setNome(rs.getString("nome"));
               obj.setEndereco(rs.getString("endereco"));
               obj.setCidade(rs.getString("cidade"));
               obj.setUf(rs.getString("uf"));
               lista.add(obj);
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()"+e.getMessage());
       }
          return lista; 
    }
    
    public boolean salvar(Transportadora obj){
        if(obj.getCodTransportadora()==null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
       
    }
    
    public boolean incluir(Transportadora obj){
        String sql = "INSERT INTO `transportadora` (`nome`, `endereco`, `cidade`, `uf`) VALUES (?,?,?,?);";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getEndereco());  
            pst.setString(3, obj.getCidade());
            pst.setString(4, obj.getUf());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Transportadora cadastrado");
                return true; 
            }else{
                JOptionPane.showMessageDialog(null, "Transportadora não cadastrado");
                return false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro de SQL no incluir do DAOTransportadora"+e.getMessage());
        }
        return false;
    }
    public boolean alterar(Transportadora obj){
       String sql ="UPDATE `transportadora` SET `nome`=?, `endereco`=?, `cidade`=?, `uf`=? WHERE  `codTransportadora`=?;";
       try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setString(1,obj.getNome());
           pst.setString(2,obj.getEndereco());
           pst.setString(3,obj.getCidade());
           pst.setString(4,obj.getUf());
           pst.setInt(5, obj.getCodTransportadora());
           if(pst.executeUpdate()>0){
               JOptionPane.showMessageDialog(null,"Transportadora alterada");
               return true;
           }else{
               JOptionPane.showMessageDialog(null,"Transportadora não alterada");
               return false;
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOTransportadora"+e.getMessage());
       }
       return false;
   }
    
  
        public boolean remover(Transportadora obj){
        String sql = "DELETE FROM `transportadora` WHERE  `codTransportadora`=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodTransportadora());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Transportadora excluido");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Transportadora não excluido");
                return false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro do SQL no excluir do DAOTransportadora"+e.getMessage());
        }
        return false;
    }
}
