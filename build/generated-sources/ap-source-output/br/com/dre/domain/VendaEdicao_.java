package br.com.dre.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VendaEdicao.class)
public abstract class VendaEdicao_ {

	public static volatile SingularAttribute<VendaEdicao, Double> recibo;
	public static volatile SingularAttribute<VendaEdicao, Integer> extravio;
	public static volatile SingularAttribute<VendaEdicao, Integer> titulosDevolvidos;
	public static volatile SingularAttribute<VendaEdicao, Etapa> etapa;
	public static volatile SingularAttribute<VendaEdicao, Integer> titulosVendidos;
	public static volatile SingularAttribute<VendaEdicao, Double> valor;
	public static volatile SingularAttribute<VendaEdicao, Usuario> usuario;
	public static volatile SingularAttribute<VendaEdicao, Long> id;
	public static volatile SingularAttribute<VendaEdicao, Integer> titulosDistribuidos;
	public static volatile SingularAttribute<VendaEdicao, Double> despesas;
	public static volatile SingularAttribute<VendaEdicao, Distribuidor> distribuidor;
	public static volatile SingularAttribute<VendaEdicao, Double> recebido;

}

