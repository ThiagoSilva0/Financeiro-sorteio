/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.domain;

import java.io.Serializable;
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

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "venda_edicao")
public class VendaEdicao implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, name = "titulos_distribuidos")
    private Integer titulosDistribuidos; 
    
    @Column(nullable = false, name = "titulos_vendidos")
    private Integer titulosVendidos; 
    
    @Column(nullable = false, name = "titulos_devolvidos")
    private Integer titulosDevolvidos; 
    
    @Column(nullable = false, name = "titulos_extraviados")
    private Integer extravio; 
    
    @Column(scale = 2, precision = 7)
    private double valor;
    
    @Column(scale = 2, precision = 7)
    private double despesas;
    
    @Column(scale = 2, precision = 7)
    private double recebido;
    
    @Column(scale = 2, precision = 7)
    private double recibo;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "etapa_id", referencedColumnName = "id", nullable = false)
    private Etapa etapa;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "distribuidor_id", referencedColumnName = "id", nullable = false)
    private Distribuidor distribuidor;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRecibo() {
        return recibo;
    }

    public void setRecibo(double recibo) {
        this.recibo = recibo;
    }
    
    

    public Integer getTitulosDistribuidos() {
        return titulosDistribuidos;
    }

    public void setTitulosDistribuidos(Integer titulosDistribuidos) {
        this.titulosDistribuidos = titulosDistribuidos;
    }

    public Integer getTitulosVendidos() {
        return titulosVendidos;
    }

    public void setTitulosVendidos(Integer titulosVendidos) {
        this.titulosVendidos = titulosVendidos;
    }

    public Integer getTitulosDevolvidos() {
        return titulosDevolvidos;
    }

    public void setTitulosDevolvidos(Integer titulosDevolvidos) {
        this.titulosDevolvidos = titulosDevolvidos;
    }

    public Integer getExtravio() {
        return extravio;
    }

    public void setExtravio(Integer extravio) {
        this.extravio = extravio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getRecebido() {
        return recebido;
    }

    public void setRecebido(double recebido) {
        this.recebido = recebido;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getDespesas() {
        return despesas;
    }

    public void setDespesas(double despesas) {
        this.despesas = despesas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.titulosDistribuidos);
        hash = 83 * hash + Objects.hashCode(this.titulosVendidos);
        hash = 83 * hash + Objects.hashCode(this.titulosDevolvidos);
        hash = 83 * hash + Objects.hashCode(this.extravio);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.despesas) ^ (Double.doubleToLongBits(this.despesas) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.recebido) ^ (Double.doubleToLongBits(this.recebido) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.etapa);
        hash = 83 * hash + Objects.hashCode(this.distribuidor);
        hash = 83 * hash + Objects.hashCode(this.usuario);
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
        final VendaEdicao other = (VendaEdicao) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.despesas) != Double.doubleToLongBits(other.despesas)) {
            return false;
        }
        if (Double.doubleToLongBits(this.recebido) != Double.doubleToLongBits(other.recebido)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.titulosDistribuidos, other.titulosDistribuidos)) {
            return false;
        }
        if (!Objects.equals(this.titulosVendidos, other.titulosVendidos)) {
            return false;
        }
        if (!Objects.equals(this.titulosDevolvidos, other.titulosDevolvidos)) {
            return false;
        }
        if (!Objects.equals(this.extravio, other.extravio)) {
            return false;
        }
        if (!Objects.equals(this.etapa, other.etapa)) {
            return false;
        }
        if (!Objects.equals(this.distribuidor, other.distribuidor)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VendaEdicao{" + "id=" + id + ", titulosDistribuidos=" + titulosDistribuidos + ", titulosVendidos=" + titulosVendidos + ", titulosDevolvidos=" + titulosDevolvidos + ", extravio=" + extravio + ", valor=" + valor + ", despesas=" + despesas + ", recebido=" + recebido + ", etapa=" + etapa + ", distribuidor=" + distribuidor + ", usuario=" + usuario + '}';
    }

    
    
    
    
    
}
