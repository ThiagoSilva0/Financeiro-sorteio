/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.DespesaDistribuidor;
import br.com.dre.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thiag
 */
public class DespesaDistribuidorDao {

    public void salvar(DespesaDistribuidor despesaDistribuidor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(despesaDistribuidor);
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

    public void editar(DespesaDistribuidor despesaDistribuidor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(despesaDistribuidor);
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

    public void excluir(DespesaDistribuidor despesaDistribuidor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(despesaDistribuidor);
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

    public List<DespesaDistribuidor> listaDespesas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DespesaDistribuidor> listaDespesas;
        try {
            Query consulta = session.createQuery("FROM DespesaDistribuidor");
            listaDespesas = consulta.list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return listaDespesas;

    }

    public DespesaDistribuidor buscarPorCodigo(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DespesaDistribuidor despesaDistribuidor = null;
        try {
            Query consulta = session.createQuery("SELECT despesaDistribuidor FROM DespesaDistribuidor despesaDistribuidor WHERE despesaDistribuidor.id LIKE :id");
            consulta.setLong("id", id);
            despesaDistribuidor = (DespesaDistribuidor) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return despesaDistribuidor;
    }

    public List<DespesaDistribuidor> buscarPorEtapa(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DespesaDistribuidor> listaDespesas = null;
        try {
            Query consulta = session.createQuery("SELECT despesaDistribuidor FROM DespesaDistribuidor despesaDistribuidor WHERE despesaDistribuidor.etapa.id LIKE :id");
            consulta.setLong("id", id);
            listaDespesas = consulta.list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return listaDespesas;
    }

    public List<DespesaDistribuidor> buscarDespesas(Long idE, Long idD) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DespesaDistribuidor> listaDespesas = null;
        List<DespesaDistribuidor> listaDespesasFiltrada = new ArrayList<>();
        try {
            Query consulta = session.createQuery("SELECT despesaDistribuidor FROM DespesaDistribuidor despesaDistribuidor WHERE despesaDistribuidor.etapa.id LIKE :id");
            consulta.setLong("id", idE);
            listaDespesas = consulta.list();
            for (DespesaDistribuidor despesaDistribuidor : listaDespesas) {
                if (despesaDistribuidor.getDistribuidor().getId() == (idD)) {
                    listaDespesasFiltrada.add(despesaDistribuidor);
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }
        return listaDespesasFiltrada;
    }

}
