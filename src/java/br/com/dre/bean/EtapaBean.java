/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.EtapaDao;
import br.com.dre.dao.UsuarioDao;
import br.com.dre.domain.Etapa;
import br.com.dre.domain.Usuario;
import br.com.dre.util.FacesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiag
 */
@ManagedBean
@ViewScoped
public class EtapaBean {

    private Etapa etapa;
    private List<Etapa> listaEtapa;
    private Long id;
    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;
    private String acao;
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    

    public AutenticacaoBean getAutenticacaoBean() {
        return autenticacaoBean;
    }

    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }

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

    public Etapa getEtapa() {
        if (etapa == null) {
            etapa = new Etapa();
        }
        return etapa;
    }

    public List<Etapa> getListaEtapa() {
        return listaEtapa;
    }

    public void setListaEtapa(List<Etapa> listaEtapa) {
        this.listaEtapa = listaEtapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public void salvar() {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.buscarPorCodigo(autenticacaoBean.getUsuarioLogado().getId());
        try {
            EtapaDao etapaDao = new EtapaDao();

            etapa.setDataCriacao(new Date());
            etapa.setUsuario(usuario);
            etapaDao.salvar(etapa);

            FacesUtil.addMsgInfo("Etapa criada com sucesso!");
            etapa = new Etapa();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao criar etapa! " + e.getMessage());
        }

    }

    public void listar() {
       
        listaEtapa = new ArrayList<>();
        try {
            EtapaDao etapaDao = new EtapaDao();
            listaEtapa = etapaDao.listar();
        } catch (Exception e) {
            FacesUtil.addMsgErro("ERRO: ao tentar listar etapas!  " + e.getMessage());
        }
    }

    public void carregarCadastro() {

        try {
            if (id != null) {
                EtapaDao etapaDao = new EtapaDao();
                etapa = etapaDao.buscarPorCodigo(id);
            } else {
                etapa = new Etapa();
            }
        } catch (Exception e) {
            FacesUtil.addMsgErro("Erro ao tentar obter dados da Etapa!" + e.getMessage());
        }
    }

    public void editar() {
        try {
            EtapaDao etapaDao = new EtapaDao();
            etapaDao.editar(etapa);
            FacesUtil.addMsgInfo("Etapa editada com sucesso!");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao editar etapa! " + e.getMessage());
        }

    }

    public void excluir() {
        try {
            EtapaDao etapaDao = new EtapaDao();
            etapaDao.excluir(etapa);
            FacesUtil.addMsgInfo("Etapa excluida com sucesso!");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao excluir etapa! " + e.getMessage());
        }

    }

    public void novo() {
        etapa = new Etapa();
    }
}
