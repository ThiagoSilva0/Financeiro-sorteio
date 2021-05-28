/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.Fornecedor;
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
public class FornecedorDao {

    public void salvar(Fornecedor fornecedor) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(fornecedor);
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

    public void editar(Fornecedor fornecedor) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(fornecedor);
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

    public void excluir(Fornecedor fornecedor) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(fornecedor);
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

    public List<Fornecedor> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Fornecedor> fornecedors = null;

        try {
            Query consulta = session.createQuery("From Fornecedor");
            fornecedors = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return fornecedors;
    }

    public Fornecedor buscarPorCodigo(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Fornecedor fornecedor = null;
        try {
            Query consulta = session.createQuery("SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.id LIKE :id");
            consulta.setLong("id", id);
            fornecedor = (Fornecedor) consulta.uniqueResult();

        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return fornecedor;
    }

}
