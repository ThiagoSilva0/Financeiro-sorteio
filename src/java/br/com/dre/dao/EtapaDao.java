/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.Etapa;
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
public class EtapaDao {

    public void salvar(Etapa etapa) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(etapa);
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

    public void editar(Etapa etapa) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(etapa);
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

    public void excluir(Etapa etapa) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(etapa);
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

    public List<Etapa> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Etapa> etapas = null;

        try {
            Query consulta = session.createQuery("From Etapa");
            etapas = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return etapas;
    }

    public Etapa buscarPorCodigo(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Etapa etapa = new Etapa();
        try {
            Query consulta = session.createQuery("Select etapa From Etapa etapa WHERE etapa.id like :id");
            consulta.setLong("id", id);
            etapa = (Etapa) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return etapa;
    }
    
     public List<Etapa> buscarPorUsuario(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Etapa> listaEtapa;
        try {
            Query consulta = session.createQuery("Select etapa From Etapa etapa WHERE etapa.usuario.id like :id");
            consulta.setLong("id", id);
            listaEtapa = consulta.list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return listaEtapa;
    }

}
