/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.ExtratoEdicaoDao;
import br.com.dre.domain.ExtratoEdicao;
import br.com.dre.util.FacesUtil;
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
public class ExtratoEdicaoBean {
    
    
    private List<ExtratoEdicao> listaExtrato;
    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;

    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    public List<ExtratoEdicao> getListaExtrato() {
        return listaExtrato;
    }

    public void setListaExtrato(List<ExtratoEdicao> listaExtrato) {
        this.listaExtrato = listaExtrato;
    }

    public SelecionarEtapaBean getSelecionarEtapaBean() {
        return selecionarEtapaBean;
    }

    public void setSelecionarEtapaBean(SelecionarEtapaBean selecionarEtapaBean) {
        this.selecionarEtapaBean = selecionarEtapaBean;
    }

    public AutenticacaoBean getAutenticacaoBean() {
        return autenticacaoBean;
    }

    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }
    
    
    
    public void listar(){
        try {
            ExtratoEdicaoDao extratoEdicaoDao = new ExtratoEdicaoDao();
            listaExtrato = extratoEdicaoDao.buscarPorEtapa(selecionarEtapaBean.getEtapaSelecionada().getId());
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar listar extrato! "+e.getMessage());
        }
    }
}
