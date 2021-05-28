/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.DistribuidorDao;
import br.com.dre.dao.EtapaDao;
import br.com.dre.dao.ExtratoEdicaoDao;
import br.com.dre.domain.Distribuidor;
import br.com.dre.domain.Etapa;
import br.com.dre.domain.ExtratoEdicao;
import br.com.dre.util.FacesUtil;
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
public class ReceberContaDistribuidorBean {

    private List<Distribuidor> listaDistribuidores;

    private Distribuidor distribuidor;
    private double valorPagamento;

    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;

    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    public double getValor() {
        return valorPagamento;
    }

    public void setValor(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Distribuidor getDistribuidor() {
        if (distribuidor == null) {
            distribuidor = new Distribuidor();
        }
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public List<Distribuidor> getListaDistribuidores() {
        return listaDistribuidores;
    }

    public void setListaDistribuidores(List<Distribuidor> listaDistribuidores) {
        this.listaDistribuidores = listaDistribuidores;
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

    public void listarDistribuidor() {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            listaDistribuidores = distribuidorDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar listar distribuidor! " + e.getMessage());
        }
    }

    public void receber() {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            distribuidor.setSaldo(distribuidor.getSaldo()+valorPagamento);
            atualizarSaldoEtapa(valorPagamento);
            atualizaExtrato("Conta recebida de "+distribuidor.getNome()+", promissória", valorPagamento);
            distribuidorDao.editar(distribuidor);
            FacesUtil.addMsgInfo("Conta recebida com sucesso!");
            distribuidor = new Distribuidor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar receber conta! "+e.getMessage());
        }
    }

    private void atualizarSaldoEtapa(double valor) {
        try {
            EtapaDao etapaDao = new EtapaDao();
            selecionarEtapaBean.getEtapaSelecionada().setSaldoEtapa(selecionarEtapaBean.getEtapaSelecionada().getSaldoEtapa()+valor);
            etapaDao.editar(selecionarEtapaBean.getEtapaSelecionada());
            FacesUtil.addMsgInfo("Saldo etapa atualizado com sucesso! R$ "+valor);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar atualizar saldo da etapa! "+e.getMessage());
        }
    }

    private void atualizaExtrato(String descricao, double valor) {
        try {
            ExtratoEdicaoDao extratoEdicaoDao = new ExtratoEdicaoDao();
            ExtratoEdicao extratoEdicao = new ExtratoEdicao();
            extratoEdicao.setDataTransacao(new Date());
            extratoEdicao.setDescricao(descricao);
            extratoEdicao.setEtapa(selecionarEtapaBean.getEtapaSelecionada());
            extratoEdicao.setTipo("C");
            extratoEdicao.setUsuario(autenticacaoBean.getUsuarioLogado());
            extratoEdicao.setValor(valor);
            extratoEdicaoDao.salvar(extratoEdicao);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar atualizar extrato! "+e.getMessage());
        }
    }
}
