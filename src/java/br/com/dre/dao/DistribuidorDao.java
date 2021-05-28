/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.Distribuidor;
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
public class DistribuidorDao {

    public void salvar(Distribuidor distribuidor) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(distribuidor);
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

    public void editar(Distribuidor distribuidor) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(distribuidor);
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

    public void excluir(Distribuidor distribuidor) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(distribuidor);
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

    public List<Distribuidor> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Distribuidor> distribuidores = null;

        try {
            Query consulta = session.createQuery("From Distribuidor");
            distribuidores = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return distribuidores;
    }

    public Distribuidor buscarPorCodigo(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Distribuidor distribuidor = null;

        try {
            Query consulta = session.createQuery("Select distribuidor FROM Distribuidor distribuidor WHERE distribuidor.id like :id");
            consulta.setLong("id", id);
            distribuidor = (Distribuidor) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return distribuidor;
    }

}
