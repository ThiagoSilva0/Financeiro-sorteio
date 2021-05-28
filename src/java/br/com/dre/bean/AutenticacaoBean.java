/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.UsuarioDao;
import br.com.dre.domain.Usuario;
import br.com.dre.util.FacesUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author thiag
 */
@ManagedBean
@SessionScoped
public class AutenticacaoBean {

    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado() {
        if (usuarioLogado == null) {
            usuarioLogado = new Usuario();
        }
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @PostConstruct
    public void autenticar() {

        try {

            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioLogado = usuarioDao.autenticar(usuarioLogado.getLogin(), usuarioLogado.getSenha());

            if (usuarioLogado == null) {

                FacesUtil.addMsgErro("Login ou senha inválidos!");
            } else {
                FacesUtil.addMsgInfo("Usuario autenticado com suacesso!");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/DRE/faces/pages/Home.xhtml?faces-redirect=true");
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Login ou senha inválidos! ");
        } catch (IOException ex) {
            FacesUtil.addMsgErro("Erro ao tentar redirecionar! ");
        }
    }
    
    public void sair(){
        usuarioLogado = new Usuario();
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml?faces-redirect=true");
        } catch (IOException e) {
             FacesUtil.addMsgErro("Erro ao tentar redirecionar " + e.getMessage());
        }
    
    }
    public void redirecionarSemLogar(){
    
        try {
            if(usuarioLogado.getId() == null){
             FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml?faces-redirect=true");
            }
        } catch (IOException e) {
            FacesUtil.addMsgErro("Erro ao tentar redirecionar " + e.getMessage());
        }
    }
}
