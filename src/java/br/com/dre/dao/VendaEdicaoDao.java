/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.VendaEdicao;
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
public class VendaEdicaoDao {

    public void salvar(VendaEdicao vendaEdicao) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(vendaEdicao);
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

    public void editar(VendaEdicao vendaEdicao) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(vendaEdicao);
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

    public void excluir(VendaEdicao vendaEdicao) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(vendaEdicao);
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

    public List<VendaEdicao> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<VendaEdicao> vendaEdicaos = null;

        try {
            Query consulta = session.createQuery("From VendaEdicao");
            vendaEdicaos = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return vendaEdicaos;
    }

    public VendaEdicao buscarPorCodigo(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        VendaEdicao vendaEdicao = null;
        try {
            Query consulta = session.createQuery("SELECT vendaEdicao FROM VendaEdicao vendaEdicao WHERE vendaEdicao.id LIKE :id");
            consulta.setLong("id", id);
            vendaEdicao = (VendaEdicao) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return vendaEdicao;

    }

    public List<VendaEdicao> buscarPorEdicao(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<VendaEdicao> listaPoredicao;
        try {
            Query consulta = session.createQuery("SELECT vendaEdicao FROM VendaEdicao vendaEdicao WHERE vendaEdicao.etapa.id LIKE :id");
            consulta.setLong("id", id);
            listaPoredicao = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return listaPoredicao;
    }

}
