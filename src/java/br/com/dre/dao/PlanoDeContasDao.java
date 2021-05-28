/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.PlanoDeContas;
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
public class PlanoDeContasDao {

    public void salvar(PlanoDeContas planoDeContas) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(planoDeContas);
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

    public void editar(PlanoDeContas planoDeContas) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(planoDeContas);
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

    public void excluir(PlanoDeContas planoDeContas) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(planoDeContas);
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

    public List<PlanoDeContas> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PlanoDeContas> planoDeContases = null;

        try {
            Query consulta = session.createQuery("From PlanoDeContas");
            planoDeContases = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return planoDeContases;
    }

    public PlanoDeContas buscarPorcodigo(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        PlanoDeContas planoDeContas = null;
        try {
            Query consulta = session.createQuery("SELECT planoDeContas FROM PlanoDeContas planoDeContas WHERE planoDeContas.id LIKE :id");
            consulta.setLong("id", id);
            planoDeContas = (PlanoDeContas) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return planoDeContas;
    }

}
