package br.com.dre.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DespesaDistribuidor.class)
public abstract class DespesaDistribuidor_ {

	public static volatile SingularAttribute<DespesaDistribuidor, Date> data;
	public static volatile SingularAttribute<DespesaDistribuidor, Etapa> etapa;
	public static volatile SingularAttribute<DespesaDistribuidor, Double> valor;
	public static volatile SingularAttribute<DespesaDistribuidor, Long> id;
	public static volatile SingularAttribute<DespesaDistribuidor, Distribuidor> distribuidor;
	public static volatile SingularAttribute<DespesaDistribuidor, String> descricao;

}

