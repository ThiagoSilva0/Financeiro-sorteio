/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "despesas_distribuidor")
public class DespesaDistribuidor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(scale = 2, precision = 7,nullable = false)
    private double valor;
    
    @Column(length = 255, nullable = false)
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_despesa")
    private Date data;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "distribuidor_id", referencedColumnName = "id",nullable = false)
    private Distribuidor distribuidor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etapa_id", referencedColumnName = "id",nullable = false)
    private Etapa etapa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.descricao);
        hash = 41 * hash + Objects.hashCode(this.data);
        hash = 41 * hash + Objects.hashCode(this.distribuidor);
        hash = 41 * hash + Objects.hashCode(this.etapa);
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
        final DespesaDistribuidor other = (DespesaDistribuidor) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.distribuidor, other.distribuidor)) {
            return false;
        }
        if (!Objects.equals(this.etapa, other.etapa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DespesaDistribuidor{" + "id=" + id + ", valor=" + valor + ", descricao=" + descricao + ", data=" + data + ", distribuidor=" + distribuidor + ", etapa=" + etapa + '}';
    }
    
    
    
    
    

}
