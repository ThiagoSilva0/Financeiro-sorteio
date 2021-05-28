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
@Table(name = "contas_edicao")
public class ContasEdicao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 1550)
    private String descricao;
    
    @Column(nullable = false, length = 50)
    private String situacao;
    
    @Column(precision = 7, scale = 2, nullable = false)
    private double valor;
    
    @Column(precision = 7, scale = 2)
    private double valorPago;
    
    @Column(precision = 7, scale = 2)
    private double valorDifenca;
    
    @Column(length = 15)
    private String formaDePagamento;
    
    @Column(length = 15)
    private String documento;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "etapa_id", referencedColumnName = "id", nullable = false)
    private Etapa etapa;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "plano_de_contas_id", referencedColumnName = "id", nullable = false)
    private PlanoDeContas planoDeContas;
    
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id", nullable = false)
    private Fornecedor fornecedor;

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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public PlanoDeContas getPlanoDeContas() {
        return planoDeContas;
    }

    public void setPlanoDeContas(PlanoDeContas planoDeContas) {
        this.planoDeContas = planoDeContas;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorDifenca() {
        return valorDifenca;
    }

    public void setValorDifenca(double valorDifenca) {
        this.valorDifenca = valorDifenca;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.situacao);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valorPago) ^ (Double.doubleToLongBits(this.valorPago) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valorDifenca) ^ (Double.doubleToLongBits(this.valorDifenca) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.formaDePagamento);
        hash = 97 * hash + Objects.hashCode(this.documento);
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.etapa);
        hash = 97 * hash + Objects.hashCode(this.planoDeContas);
        hash = 97 * hash + Objects.hashCode(this.fornecedor);
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
        final ContasEdicao other = (ContasEdicao) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorPago) != Double.doubleToLongBits(other.valorPago)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorDifenca) != Double.doubleToLongBits(other.valorDifenca)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.situacao, other.situacao)) {
            return false;
        }
        if (!Objects.equals(this.formaDePagamento, other.formaDePagamento)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.etapa, other.etapa)) {
            return false;
        }
        if (!Objects.equals(this.planoDeContas, other.planoDeContas)) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContasEdicao{" + "id=" + id + ", descricao=" + descricao + ", situacao=" + situacao + ", valor=" + valor + ", valorPago=" + valorPago + ", valorDifenca=" + valorDifenca + ", formaDePagamento=" + formaDePagamento + ", documento=" + documento + ", usuario=" + usuario + ", etapa=" + etapa + ", planoDeContas=" + planoDeContas + ", fornecedor=" + fornecedor + '}';
    }

   
    
    
}
