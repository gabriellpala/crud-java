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
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kleber0a0m
 */
public class DAOFuncionario {
    DAOTransportadora daoTransportadora = new DAOTransportadora();
    
    public List <Funcionario> getLista(){
       String sql = "select * from funcionario";
       List<Funcionario> lista = new ArrayList <>();
       try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               Funcionario obj = new Funcionario();
               obj.setCodFuncionario(rs.getInt("codFuncionario"));
               obj.setNome(rs.getString("nome"));
               obj.setCpf(rs.getString("cpf"));
               obj.setRg(rs.getString("rg"));
               obj.setNome(rs.getString("nome"));
               /*COLOCAR NASCOMENTO DEPOIS
               java.sql.Date dt = rs.getDate("nascimento");
               Calendar c = Calendar.getInstance();
               c.setTime(dt);
               obj.setNascimento(c);
               */
               
               obj.setSalario(rs.getDouble("salario"));
               
              
               obj.setTransportadora(daoTransportadora.localizar(rs.getInt("TRANSPORTADORA_codTransportadora"))); 
               lista.add(obj);
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()"+e.getMessage());
       }
          return lista; 
    }
    
    public boolean salvar(Funcionario obj){
        if(obj.getCodFuncionario()==null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
       
    }
    
    public boolean incluir(Funcionario obj){
        String sql = "INSERT INTO `funcionario` (`nome`, `cpf`, `rg`, `salario`, `TRANSPORTADORA_codTransportadora`) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());  
            pst.setString(3, obj.getRg());
  
            pst.setDouble(4, obj.getSalario());
            pst.setInt(5, obj.getTransportadora().getCodTransportadora());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado");
                return true; 
            }else{
                JOptionPane.showMessageDialog(null, "Funcionário não cadastrado");
                return false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro de SQL no incluir do DAOFuncionário"+e.getMessage());
        }
        return false;
    }
    public boolean alterar(Funcionario obj){
       String sql ="UPDATE `funcionario` SET `nome`=?, `cpf`=?, `rg`=?, `salario`=?, `TRANSPORTADORA_codTransportadora`=? WHERE  `codFuncionario`=?;";
       try{
           PreparedStatement pst = Conexao.getPreparedStatement(sql);
           pst.setString(1,obj.getNome());
           pst.setString(2,obj.getCpf());
           pst.setString(3,obj.getRg());

           pst.setDouble(4,obj.getSalario());
           pst.setInt(5, obj.getTransportadora().getCodTransportadora());
           pst.setInt(6, obj.getCodFuncionario());
           if(pst.executeUpdate()>0){
               JOptionPane.showMessageDialog(null,"Funcionário alterado");
               return true;
           }else{
               JOptionPane.showMessageDialog(null,"Funcionário não alterado");
               return false;
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOFuncionário"+e.getMessage());
       }
       return false;
   }
    
  
        public boolean remover(Funcionario obj){
        String sql = "DELETE FROM `funcionario` WHERE  `codFuncionario`=?;";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodFuncionario());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Funcionario excluido");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Funcionário não excluido");
                return false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro do SQL no excluir do DAOFuncionário"+e.getMessage());
        }
        return false;
    }
    
}
