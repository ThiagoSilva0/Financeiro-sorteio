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
@Table(name = "extrato_edicao")
public class ExtratoEdicao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 500,nullable = false)
    private String descricao;

    @Column(length = 1,nullable = false)
    private String tipo;
    
    @Column(precision = 7,scale = 2,nullable = false)
    private double valor;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataTransacao;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id",nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "etapa_id", referencedColumnName = "id",nullable = false)
    private Etapa etapa;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.descricao);
        hash = 11 * hash + Objects.hashCode(this.tipo);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 11 * hash + Objects.hashCode(this.dataTransacao);
        hash = 11 * hash + Objects.hashCode(this.usuario);
        hash = 11 * hash + Objects.hashCode(this.etapa);
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
        final ExtratoEdicao other = (ExtratoEdicao) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataTransacao, other.dataTransacao)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.etapa, other.etapa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExtratoEdicao{" + "id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + ", valor=" + valor + ", dataTransacao=" + dataTransacao + ", usuario=" + usuario + ", etapa=" + etapa + '}';
    }

   
    
    
}
