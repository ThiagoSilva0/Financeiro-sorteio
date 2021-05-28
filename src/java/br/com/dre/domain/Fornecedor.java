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

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotEmpty(message = "O Campo CNPJ é obrigatório")
    @Column(length = 20, nullable = false)
    private String cnpj;
    
    @NotEmpty(message = "O Campo RAZÃO SOCIAL é obrigatório")
    @Column(length = 255, nullable = false,name = "razao_social")
    private String razaoSocial;
    
    @NotEmpty(message = "O Campo TIPO DE SERVIÇO é obrigatório")
    @Column(length = 255, nullable = false,name = "tipo_servico")
    private String tipoServico;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.cnpj);
        hash = 89 * hash + Objects.hashCode(this.razaoSocial);
        hash = 89 * hash + Objects.hashCode(this.tipoServico);
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
        final Fornecedor other = (Fornecedor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.razaoSocial, other.razaoSocial)) {
            return false;
        }
        if (!Objects.equals(this.tipoServico, other.tipoServico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fornecedor{" + "id=" + id + ", cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", tipoServico=" + tipoServico + '}';
    }
    
    
    
    
}
