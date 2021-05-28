/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.ExtratoEdicao;
import br.com.dre.util.HibernateUtil;
import com.fasterxml.classmate.TypeBindings;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thiag
 */
public class ExtratoEdicaoDao {

    public void salvar(ExtratoEdicao extratoEdicao) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(extratoEdicao);
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

    public void editar(ExtratoEdicao extratoEdicao) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(extratoEdicao);
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

    public void excluir(ExtratoEdicao extratoEdicao) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(extratoEdicao);
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

    public List<ExtratoEdicao> listar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ExtratoEdicao> listaExtrato = null;
        try {
            Query consulta = session.createQuery("FROM ExtratoEdicao");
            listaExtrato = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {
                throw e;
            }
        }
        return listaExtrato;

    }

    public List<ExtratoEdicao> buscarPorEtapa(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ExtratoEdicao> listaExtrato = null;

        try {
            Query consulta = session.createQuery("SELECT extratoEdicao FROM ExtratoEdicao extratoEdicao WHERE extratoEdicao.etapa.id LIKE :id");
            consulta.setLong("id", id);
            listaExtrato = consulta.list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return listaExtrato;
    }

    public ExtratoEdicao buscarPorCodigo(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ExtratoEdicao extratoEdicao = null;
        try {
            Query consulta = session.createQuery("SELECT extratoEdicao FROM ExtratoEdicao extratoEdicao WHERE extratoEdicao.id LIKE :id");
            consulta.setLong("id", id);
            extratoEdicao = (ExtratoEdicao) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return extratoEdicao;
    }
}
