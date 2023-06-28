/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author kleber0a0m
 */
public class Loja_De_Destino {
    private Integer codLoja;
    private String nome,endereco,cidade,uf;

    public Loja_De_Destino() {
    }

    public Integer getCodLoja() {
        return codLoja;
    }

    public void setCodLoja(Integer codLoja) {
        this.codLoja = codLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codLoja);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Loja_De_Destino other = (Loja_De_Destino) obj;
        if (!Objects.equals(this.codLoja, other.codLoja)) {
            return false;
        }
        return true;
    }
    
    @Override
public String toString(){
        return nome;
    }
    
}
