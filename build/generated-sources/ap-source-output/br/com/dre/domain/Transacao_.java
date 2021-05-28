package br.com.dre.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transacao.class)
public abstract class Transacao_ {

	public static volatile SingularAttribute<Transacao, String> transacao;
	public static volatile SingularAttribute<Transacao, Etapa> etapa;
	public static volatile SingularAttribute<Transacao, Double> valor;
	public static volatile SingularAttribute<Transacao, Date> dataTrasacao;
	public static volatile SingularAttribute<Transacao, Usuario> usuario;
	public static volatile SingularAttribute<Transacao, Long> id;

}

