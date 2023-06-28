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
public class DAOLoja_De_Destino {
   
        public Loja_De_Destino localizar(Integer id){
         String sql = "select * from loja_de_destino where codLoja=?";
         Loja_De_Destino obj = new Loja_De_Destino();
         try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setCodLoja(rs.getInt("codLoja"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));
                return obj;
            }
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro de SQL Localizar Dao LOJA DESTINO"+e.getMessage());
         }
         return null;
     } 
    
    
    /********************/
    public List <Loja_De_Destino> getLista(){
       String sql = "SELECT * FROM loja_de_destino;";
       List<Loja_De_Destino> lista = new ArrayList <>();
       try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               Loja_De_Destino obj = new Loja_De_Destino();
               obj.setCodLoja(rs.getInt("codLoja"));
               obj.setNome(rs.getString("nome"));
               obj.setEndereco(rs.getString("endereco"));
               obj.setCidade(rs.getString("cidade"));
               obj.setUf(rs.getString("uf"));
               lista.add(obj);
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro de SQL no getLista() DAOLoja_De_Destino :"+e.getMessage());
       }
        return lista;
    }
    
    public boolean salvar(Loja_De_Destino obj){
        if(obj.getCodLoja()==null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
       
    }
    
    public boolean incluir(Loja_De_Destino obj){
        String sql = "INSERT INTO `transportadora`.`loja_de_destino` (`nome`, `endereco`, `cidade`, `uf`) VALUES (?,?,?,?);";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getEndereco());  
            pst.setString(3, obj.getCidade());
            pst.setString(4, obj.getUf());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Loja_De_Destino cadastrado");
                return true; 
            }else{
                JOptionPane.showMessageDialog(null, "Loja_De_Destino não cadastrado");
                return false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro de SQL no incluir do DAOLoja_De_Destino"+e.getMessage());
        }
        return false;
    }
    public boolean alterar(Loja_De_Destino obj){
       String sql ="UPDATE `loja_de_destino` SET `nome`=?, `endereco`=?, `cidade`=?, `uf`=? WHERE  `codLoja`=?;";
       try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setString(1,obj.getNome());
           pst.setString(2,obj.getEndereco());
           pst.setString(3,obj.getCidade());
           pst.setString(4,obj.getUf());
           pst.setInt(5, obj.getCodLoja());
           if(pst.executeUpdate()>0){
               JOptionPane.showMessageDialog(null,"Loja_De_Destino alterado");
               return true;
           }else{
               JOptionPane.showMessageDialog(null,"Loja_De_Destino não alterado");
               return false;
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOLoja_De_Destino"+e.getMessage());
       }
       return false;
   }
    
  
        public boolean remover(Loja_De_Destino obj){
        String sql = "DELETE FROM `loja_de_destino` WHERE  `codLoja`=?;";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodLoja());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Loja_De_Destino excluido");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Loja_De_Destino não excluido");
                return false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro do SQL no excluir do DAOLoja_De_Destino"+e.getMessage());
        }
        return false;
    }
    
    
}
