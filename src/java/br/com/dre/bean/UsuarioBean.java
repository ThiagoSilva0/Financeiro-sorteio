/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.UsuarioDao;
import br.com.dre.domain.Usuario;
import br.com.dre.util.FacesUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiag
 */
@ManagedBean
@ViewScoped
public class UsuarioBean {

    private Usuario usuario;
    private String confirmaSenha;
    private String acao;
    private Long id;
    private List<Usuario> listaUsuarios;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void carregarCadastro() {
        try {
            if (id != null) {
                UsuarioDao usuarioDao = new UsuarioDao();
                usuario = usuarioDao.buscarPorCodigo(id);
            } else {
                usuario = new Usuario();
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar usuário! " + e.getMessage());
        }
    }

    public void salvar() {
        try {
            if (usuario.getSenha().equals(confirmaSenha)) {
                UsuarioDao usuarioDao = new UsuarioDao();
                usuarioDao.salvar(usuario);
                FacesUtil.addMsgInfo("Usuário " + usuario.getNome() + " cadastrado com sucesso");

                usuario = new Usuario();
            } else {
                FacesUtil.addMsgErro("Senha e confirma senha não conferem");
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar cadastrar usuário " + e.getMessage());
        }

    }

    public void editar() {
        try {
            if (usuario.getSenha().equals(confirmaSenha)) {
                UsuarioDao usuarioDao = new UsuarioDao();
                usuarioDao.editar(usuario);
                FacesUtil.addMsgInfo("Usuário " + usuario.getNome() + " editado com sucesso");

                usuario = new Usuario();
            } else {
                FacesUtil.addMsgErro("Senha e confirma senha não conferem");
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar editar usuário " + e.getMessage());
        }

    }

    public void excluir() {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.excluir(usuario);
            FacesUtil.addMsgInfo("Usuário " + usuario.getNome() + " excluido com sucesso");

            usuario = new Usuario();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar excluir usuário " + e.getMessage());
        }
    }

    public void listar() {

        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            listaUsuarios = usuarioDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar listar usuários " + e.getMessage());
        }
    }

    public void novo() {
        usuario = new Usuario();
    }

}
