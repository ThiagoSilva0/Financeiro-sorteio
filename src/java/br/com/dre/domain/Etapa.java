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
@Table(name = "etapa")
public class Etapa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private Integer edicao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_edicao")
    private Date dataEdicao;
    
    @Column(nullable = false,length = 5)
    private String situacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Date dataCriacao;
    
    @Column(nullable = false, name = "valor_titulo")
    private double valorTitulo;
    
    @Column( name = "saldo_etapa")
    private double saldoEtapa;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public Date getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Date dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(double valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    public double getSaldoEtapa() {
        return saldoEtapa;
    }

    public void setSaldoEtapa(double saldoEtapa) {
        this.saldoEtapa = saldoEtapa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.edicao);
        hash = 67 * hash + Objects.hashCode(this.dataEdicao);
        hash = 67 * hash + Objects.hashCode(this.situacao);
        hash = 67 * hash + Objects.hashCode(this.dataCriacao);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valorTitulo) ^ (Double.doubleToLongBits(this.valorTitulo) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.saldoEtapa) ^ (Double.doubleToLongBits(this.saldoEtapa) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.usuario);
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
        final Etapa other = (Etapa) obj;
        if (Double.doubleToLongBits(this.valorTitulo) != Double.doubleToLongBits(other.valorTitulo)) {
            return false;
        }
        if (Double.doubleToLongBits(this.saldoEtapa) != Double.doubleToLongBits(other.saldoEtapa)) {
            return false;
        }
        if (!Objects.equals(this.situacao, other.situacao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.edicao, other.edicao)) {
            return false;
        }
        if (!Objects.equals(this.dataEdicao, other.dataEdicao)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etapa{" + "id=" + id + ", edicao=" + edicao + ", dataEdicao=" + dataEdicao + ", situacao=" + situacao + ", dataCriacao=" + dataCriacao + ", valorTitulo=" + valorTitulo + ", saldoEtapa=" + saldoEtapa + ", usuario=" + usuario + '}';
    }

    
    
    
    
    
   
    
}
