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
@Table
public class Transacao implements Serializable {
    
    @Id
    private Long id;
    
    @Column(nullable = false, length = 5, name = "tipo_trasacao")
    private String transacao;
    
    @Column(precision = 7, scale = 2,nullable = false)
    private double valor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etapa_id", referencedColumnName = "id", nullable = false)
    private Etapa etapa;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_transacao")
    private Date dataTrasacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataTrasacao() {
        return dataTrasacao;
    }

    public void setDataTrasacao(Date dataTrasacao) {
        this.dataTrasacao = dataTrasacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.transacao);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.etapa);
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.dataTrasacao);
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
        final Transacao other = (Transacao) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.transacao, other.transacao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.etapa, other.etapa)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.dataTrasacao, other.dataTrasacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transacao{" + "id=" + id + ", transacao=" + transacao + ", valor=" + valor + ", etapa=" + etapa + ", usuario=" + usuario + ", dataTrasacao=" + dataTrasacao + '}';
    }
    
    
    
    
}
