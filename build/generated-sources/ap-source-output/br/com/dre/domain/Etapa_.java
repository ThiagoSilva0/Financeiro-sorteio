package br.com.dre.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Etapa.class)
public abstract class Etapa_ {

	public static volatile SingularAttribute<Etapa, Date> dataEdicao;
	public static volatile SingularAttribute<Etapa, String> situacao;
	public static volatile SingularAttribute<Etapa, Double> valorTitulo;
	public static volatile SingularAttribute<Etapa, Usuario> usuario;
	public static volatile SingularAttribute<Etapa, Long> id;
	public static volatile SingularAttribute<Etapa, Integer> edicao;
	public static volatile SingularAttribute<Etapa, Double> saldoEtapa;
	public static volatile SingularAttribute<Etapa, Date> dataCriacao;

}

