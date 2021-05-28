package br.com.dre.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasEdicao.class)
public abstract class ContasEdicao_ {

	public static volatile SingularAttribute<ContasEdicao, String> situacao;
	public static volatile SingularAttribute<ContasEdicao, Etapa> etapa;
	public static volatile SingularAttribute<ContasEdicao, Double> valor;
	public static volatile SingularAttribute<ContasEdicao, Double> valorPago;
	public static volatile SingularAttribute<ContasEdicao, String> documento;
	public static volatile SingularAttribute<ContasEdicao, Usuario> usuario;
	public static volatile SingularAttribute<ContasEdicao, PlanoDeContas> planoDeContas;
	public static volatile SingularAttribute<ContasEdicao, Long> id;
	public static volatile SingularAttribute<ContasEdicao, String> formaDePagamento;
	public static volatile SingularAttribute<ContasEdicao, Fornecedor> fornecedor;
	public static volatile SingularAttribute<ContasEdicao, Double> valorDifenca;
	public static volatile SingularAttribute<ContasEdicao, String> descricao;

}

