/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author kleber0a0m
 */
public class Destino {
    private Integer codDestino;
    private Double frete,valorEncomenda;
    private Transportadora transportadora;
    private Loja_De_Destino loja_de_destino;
    private Calendar dataDestino;

    public Calendar getDataDestino() {
        return dataDestino;
    }

    public void setDataDestino(Calendar dataDestino) {
        this.dataDestino = dataDestino;
    }

    public Destino() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.codDestino);
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
        final Destino other = (Destino) obj;
        if (!Objects.equals(this.codDestino, other.codDestino)) {
            return false;
        }
        return true;
    }

    public Integer getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(Integer codDestino) {
        this.codDestino = codDestino;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Double getValorEncomenda() {
        return valorEncomenda;
    }

    public void setValorEncomenda(Double valorEncomenda) {
        this.valorEncomenda = valorEncomenda;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Loja_De_Destino getLoja_de_destino() {
        return loja_de_destino;
    }

    public void setLoja_de_destino(Loja_De_Destino loja_de_destino) {
        this.loja_de_destino = loja_de_destino;
    }

    /**
     *
     * @return
     */
    @Transient
    public String getdataDestinoFormatado(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataDestino.getTime());
    }
    
    
    
}
