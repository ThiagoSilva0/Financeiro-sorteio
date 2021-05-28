/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.dao;

import br.com.dre.domain.Usuario;
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
public class UsuarioDao {

    public void salvar(Usuario usuario) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(usuario);
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

    public void editar(Usuario usuario) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(usuario);
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

    public void excluir(Usuario usuario) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(usuario);
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

    public List<Usuario> listar() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> usuarios = null;

        try {
            Query consulta = session.createQuery("From Usuario");
            usuarios = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                throw e;
            }
        }

        return usuarios;
    }

    public Usuario autenticar(String login, String senha) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;
        try {
            Query consulta = (Query) session.createQuery("Select usuario FROM Usuario usuario WHERE usuario.login like :login and usuario.senha like :senha");
            consulta.setString("login", login);
            consulta.setString("senha", senha);
            usuario = (Usuario) consulta.uniqueResult();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return usuario;
    }

    public Usuario buscarPorCodigo(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;
        try {
            Query consulta = session.createQuery("SELECT usuario FROM Usuario usuario where usuario.id like :id");
            consulta.setLong("id", id);
            usuario = (Usuario) consulta.uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return usuario;
    }
}
