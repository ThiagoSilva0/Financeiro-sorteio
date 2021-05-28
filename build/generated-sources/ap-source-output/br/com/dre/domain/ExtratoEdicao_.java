package br.com.dre.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExtratoEdicao.class)
public abstract class ExtratoEdicao_ {

	public static volatile SingularAttribute<ExtratoEdicao, String> tipo;
	public static volatile SingularAttribute<ExtratoEdicao, Etapa> etapa;
	public static volatile SingularAttribute<ExtratoEdicao, Double> valor;
	public static volatile SingularAttribute<ExtratoEdicao, Usuario> usuario;
	public static volatile SingularAttribute<ExtratoEdicao, Long> id;
	public static volatile SingularAttribute<ExtratoEdicao, String> descricao;
	public static volatile SingularAttribute<ExtratoEdicao, Date> dataTransacao;

}

