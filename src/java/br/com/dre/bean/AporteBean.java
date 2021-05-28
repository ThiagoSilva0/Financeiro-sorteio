/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.EtapaDao;
import br.com.dre.dao.ExtratoEdicaoDao;
import br.com.dre.domain.ExtratoEdicao;
import br.com.dre.util.FacesUtil;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiag
 */
@ManagedBean
@ViewScoped
public class AporteBean {
    
    private String acao;
    
    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;
    
    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;
    
    private String nomeSocio;
    
    private double valorRecebido;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
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
    
    public String getNomeSocio() {
        return nomeSocio;
    }
    
    public void setNomeSocio(String nomeSocio) {
        this.nomeSocio = nomeSocio;
    }
    
    public double getValorRecebido() {
        return valorRecebido;
    }
    
    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    
    public void receberAporte() {
        try {
            EtapaDao etapaDao = new EtapaDao();
            selecionarEtapaBean.getEtapaSelecionada().setSaldoEtapa(selecionarEtapaBean.getEtapaSelecionada().getSaldoEtapa() + valorRecebido);
            etapaDao.editar(selecionarEtapaBean.getEtapaSelecionada());
            atualizaExtrato("Aporte recebido de " + nomeSocio, valorRecebido);
            FacesUtil.addMsgInfo("Aporte recebido com sucesso! ");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar receber aporte! " + e.getMessage());
        }
    }
    
    public void pagarAporte() {
        try {
            EtapaDao etapaDao = new EtapaDao();
            selecionarEtapaBean.getEtapaSelecionada().setSaldoEtapa(selecionarEtapaBean.getEtapaSelecionada().getSaldoEtapa() - valorRecebido);
            etapaDao.editar(selecionarEtapaBean.getEtapaSelecionada());
            atualizaExtrato("Aporte pago para " + nomeSocio, -valorRecebido);
            FacesUtil.addMsgInfo("Aporte pago com sucesso! ");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar pagar aporte! " + e.getMessage());
        }
    }
    
    private void atualizaExtrato(String descricao, double valor) {
        try {
            ExtratoEdicaoDao extratoEdicaoDao = new ExtratoEdicaoDao();
            ExtratoEdicao extratoEdicao = new ExtratoEdicao();
            extratoEdicao.setDataTransacao(new Date());
            extratoEdicao.setDescricao(descricao);
            extratoEdicao.setEtapa(selecionarEtapaBean.getEtapaSelecionada());
            extratoEdicao.setUsuario(autenticacaoBean.getUsuarioLogado());
            extratoEdicao.setValor(valor);
            
            if (valor > 0) {
                extratoEdicao.setTipo("C");
            } else {
                extratoEdicao.setTipo("D");
            }
            extratoEdicaoDao.salvar(extratoEdicao);
            FacesUtil.addMsgInfo("Extrato atualizado com sucesso!");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar atualizar extrato! "+e.getMessage());
        }
    }
}
