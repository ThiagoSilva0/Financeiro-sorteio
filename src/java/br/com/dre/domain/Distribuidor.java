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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author thiag
 */
@Entity
@Table
public class Distribuidor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CPF(message = "O campo CPF é obrigatorio")
    @Column(length = 14, unique = true, nullable = false)
    private String cpf;

    @NotEmpty(message = "O campo telefone é obrigatorio")
    @Column(length = 50, nullable = false)
    private String telefone;

    @NotEmpty(message = "O campo Nome é obrigatorio")
    @Column(length = 55, nullable = false)
    private String nome;

    @Column(length = 55, nullable = false)
    private String area;

    @Column(precision = 7, scale = 2, nullable = false)
    private double comissao;

    @Column(precision = 7, scale = 2,nullable = false)
    private double saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.cpf);
        hash = 11 * hash + Objects.hashCode(this.telefone);
        hash = 11 * hash + Objects.hashCode(this.nome);
        hash = 11 * hash + Objects.hashCode(this.area);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.comissao) ^ (Double.doubleToLongBits(this.comissao) >>> 32));
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.saldo) ^ (Double.doubleToLongBits(this.saldo) >>> 32));
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
        final Distribuidor other = (Distribuidor) obj;
        if (Double.doubleToLongBits(this.comissao) != Double.doubleToLongBits(other.comissao)) {
            return false;
        }
        if (Double.doubleToLongBits(this.saldo) != Double.doubleToLongBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Distribuidor{" + "id=" + id + ", cpf=" + cpf + ", telefone=" + telefone + ", nome=" + nome + ", area=" + area + ", comissao=" + comissao + ", saldo=" + saldo + '}';
    }
    
    
}
