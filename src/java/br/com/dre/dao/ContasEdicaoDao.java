/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;


import br.com.dre.domain.ContasEdicao;
import br.com.dre.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thiag
 */
public class ContasEdicaoDao {
    
      public void salvar(ContasEdicao contasEdicao) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(contasEdicao);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void editar(ContasEdicao contasEdicao)  {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(contasEdicao);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void excluir(ContasEdicao contasEdicao) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(contasEdicao);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<ContasEdicao> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ContasEdicao> contasEdicaos = null;

        try {
            Query consulta = session.createQuery("From ContaEdicao");
            contasEdicaos = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return contasEdicaos;
    }
    
    public ContasEdicao buscarPorCodigo(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        ContasEdicao contasEdicao = null;
        try {
            Query consulta = session.createQuery("SELECT contasEdicao FROM ContasEdicao contasEdicao WHERE contasEdicao.id like :id");
            consulta.setLong("id", id);
            contasEdicao = (ContasEdicao) consulta.uniqueResult();
            
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return contasEdicao;
    }
    
    public List<ContasEdicao> buscarPorEtapa(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ContasEdicao> listaPorEdicao = null;
        try {
            Query consulta = session.createQuery("SELECT contasEdicao FROM ContasEdicao contasEdicao WHERE contasEdicao.etapa.id like :id");
            consulta.setLong("id", id);
            listaPorEdicao = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return listaPorEdicao;
    }
    
    
}
