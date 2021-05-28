/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.ContasEdicaoDao;
import br.com.dre.dao.EtapaDao;
import br.com.dre.dao.ExtratoEdicaoDao;
import br.com.dre.domain.ContasEdicao;
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
public class PagarContaBean {

    private ContasEdicao contasEdicao;
    private long id;
    private String acao;
    
    
    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;

    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    public ContasEdicao getContasEdicao() {
        return contasEdicao;
    }

    public void setContasEdicao(ContasEdicao contasEdicao) {
        this.contasEdicao = contasEdicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
    
    

    public void carregarConta() {
        try {
            ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
            contasEdicao = contasEdicaoDao.buscarPorCodigo(id);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar conta a pagar!");
        }

    }

    public void pagar() {
        try {
            ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
            contasEdicao.setValorDifenca(contasEdicao.getValor() - contasEdicao.getValorPago());
            contasEdicao.setSituacao("p");
            contasEdicaoDao.editar(contasEdicao);
            FacesUtil.addMsgInfo("Conta paga com sucesso");
            atualizarExtrato("PAGAMENTOS DE CONTAS - "+contasEdicao.getDescricao(),-contasEdicao.getValorPago());
            atualizaSaldoEtapa(contasEdicao.getValorPago());
            contasEdicao = new ContasEdicao();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar pagar conta! " + e.getMessage());
        }
    }

    private void atualizarExtrato(String descricao, double valor) {
        try {
            ExtratoEdicaoDao extratoEdicaoDao = new ExtratoEdicaoDao();
            ExtratoEdicao extratoEdicao = new ExtratoEdicao();
            extratoEdicao.setDataTransacao(new Date());
            extratoEdicao.setDescricao(descricao);
            extratoEdicao.setEtapa(selecionarEtapaBean.getEtapaSelecionada());
            extratoEdicao.setUsuario(autenticacaoBean.getUsuarioLogado());
            extratoEdicao.setValor(valor);

            extratoEdicao.setTipo("D");

            extratoEdicaoDao.salvar(extratoEdicao);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar atualizar extrato!");
        }
    }
     private void atualizaSaldoEtapa(double valor) {
        try {
            EtapaDao etapaDao = new EtapaDao();
            selecionarEtapaBean.getEtapaSelecionada().setSaldoEtapa(selecionarEtapaBean.getEtapaSelecionada().getSaldoEtapa() - valor);
            etapaDao.editar(selecionarEtapaBean.getEtapaSelecionada());
            FacesUtil.addMsgInfo("Saldo etapa atualizado, " + valor);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao atualizar saldo da etapa! " + e.getMessage());
        }
    }
}
