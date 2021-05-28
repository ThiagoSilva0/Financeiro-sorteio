/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.DespesaDistribuidorDao;
import br.com.dre.dao.DistribuidorDao;
import br.com.dre.dao.EtapaDao;
import br.com.dre.dao.ExtratoEdicaoDao;

import br.com.dre.dao.VendaEdicaoDao;
import br.com.dre.domain.DespesaDistribuidor;
import br.com.dre.domain.Distribuidor;
import br.com.dre.domain.Etapa;
import br.com.dre.domain.ExtratoEdicao;

import br.com.dre.domain.VendaEdicao;
import br.com.dre.relatorio.RelatorioVendaEdicao;
import br.com.dre.util.FacesUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author thiag
 */
@ManagedBean
@ViewScoped
public class VendaBean {

    private VendaEdicao venda;
    private List<VendaEdicao> listasVenda;

    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;

    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    private Distribuidor distribuidor;

    private Integer distribuidos, vendidos, extravio, devolvidos;

    private Integer totalDistribuidos = 0, totalVendidos = 0, totalExtravio = 0, totalDevolvidos = 0;
    private double totalDespesas = 0, totalValor = 0, totalFaltou = 0, totalRecebido = 0;

    private String acao;
    private Long id;
    private Long idDis;

    private double despesas = 0;
    private double total = 0;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDespesas() {
        return despesas;
    }

    public void setDespesas(double despesas) {
        this.despesas = despesas;
    }

    public double getSomaDespesas() {
        return despesas;
    }

    public void setSomaDespesas(double despesas) {
        this.despesas = despesas;
    }

    public Long getIdDis() {
        return idDis;
    }

    public void setIdDis(Long idDis) {
        this.idDis = idDis;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public AutenticacaoBean getAutenticacaoBean() {
        return autenticacaoBean;
    }

    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }

    public List<VendaEdicao> getListasVenda() {
        return listasVenda;
    }

    public void setListasVenda(List<VendaEdicao> listasVenda) {
        this.listasVenda = listasVenda;
    }

    public SelecionarEtapaBean getSelecionarEtapaBean() {
        return selecionarEtapaBean;
    }

    public void setSelecionarEtapaBean(SelecionarEtapaBean selecionarEtapaBean) {
        this.selecionarEtapaBean = selecionarEtapaBean;
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

    public VendaEdicao getVenda() {
        return venda;
    }

    public void setVenda(VendaEdicao venda) {
        this.venda = venda;
    }

    public Integer getTotalDistribuidos() {
        return totalDistribuidos;
    }

    public void setTotalDistribuidos(Integer totalDistribuidos) {
        this.totalDistribuidos = totalDistribuidos;
    }

    public Integer getTotalVendidos() {
        return totalVendidos;
    }

    public void setTotalVendidos(Integer totalVendidos) {
        this.totalVendidos = totalVendidos;
    }

    public Integer getTotalExtravio() {
        return totalExtravio;
    }

    public void setTotalExtravio(Integer totalExtravio) {
        this.totalExtravio = totalExtravio;
    }

    public Integer getTotalDevolvidos() {
        return totalDevolvidos;
    }

    public void setTotalDevolvidos(Integer totalDevolvidos) {
        this.totalDevolvidos = totalDevolvidos;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public double getTotalValor() {
        return totalValor;
    }

    public void setTotalValor(double totalValor) {
        this.totalValor = totalValor;
    }

    public double getTotalFaltou() {
        return totalFaltou;
    }

    public void setTotalFaltou(double totalFaltou) {
        this.totalFaltou = totalFaltou;
    }

    public double getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    public void carregarVenda() {

        try {
            if (id != null) {

                VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
                venda = vendaEdicaoDao.buscarPorCodigo(id);
                distribuidor = venda.getDistribuidor();
                somaDespesas();

            } else {

                venda = new VendaEdicao();
                carregaDistribuidor();
                somaDespesas();

            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar Venda! ");
        }

    }

    public void listar() {

        try {

            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            listasVenda = vendaEdicaoDao.buscarPorEdicao(selecionarEtapaBean.getEtapaSelecionada().getId());

            somarTotais();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar vendas " + e.getMessage());
        }
    }

    public void carregaDistribuidor() {

        try {
            if (idDis != null) {
                DistribuidorDao distribuidorDao = new DistribuidorDao();
                distribuidor = distribuidorDao.buscarPorCodigo(idDis);
            }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/DRE/faces/pages/VendaEdicaoPesquisa.xhtml?faces-redirect=true");
            
            }

        } catch (IOException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar distribuidor " + e.getMessage());
        }

    }

    public void somaDespesas() {

        try {
            DespesaDistribuidorDao despesaDistribuidorDao = new DespesaDistribuidorDao();
            List<DespesaDistribuidor> listaDespesaDistribuidor = despesaDistribuidorDao.buscarDespesas(selecionarEtapaBean.getEtapaSelecionada().getId(), distribuidor.getId());
            for (DespesaDistribuidor despesaDistribuidor : listaDespesaDistribuidor) {
                despesas += despesaDistribuidor.getValor();
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao carregar despesas do distribuidor! ");
        }
    }

    public void somarVendas() {
        if (venda.getExtravio() + venda.getTitulosDevolvidos() + venda.getTitulosVendidos() != venda.getTitulosDistribuidos()) {
            FacesUtil.addMsgErro("O total de titulos distribuidos não bate com os informados! ");
        } else {

            if (venda.getExtravio() == 0) {
                double comissao = ((venda.getTitulosVendidos() * selecionarEtapaBean.getEtapaSelecionada().getValorTitulo()) * (distribuidor.getComissao()) / 100);
                total = (venda.getTitulosVendidos() * selecionarEtapaBean.getEtapaSelecionada().getValorTitulo() - comissao) - despesas;
                venda.setValor(total);
            } else {
                double comissao = ((venda.getTitulosVendidos() * selecionarEtapaBean.getEtapaSelecionada().getValorTitulo()) * (distribuidor.getComissao()) / 100);
                total = (((venda.getTitulosVendidos() * selecionarEtapaBean.getEtapaSelecionada().getValorTitulo())
                        + (venda.getExtravio() * selecionarEtapaBean.getEtapaSelecionada().getValorTitulo())) - comissao) - despesas;
                venda.setValor(total);
            }
            distribuidos = venda.getTitulosDistribuidos();
            devolvidos = venda.getTitulosDevolvidos();
            extravio = venda.getExtravio();
            vendidos = venda.getTitulosVendidos();

        }
    }

    public void salvar() {

        try {

            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            venda.setExtravio(extravio);
            venda.setTitulosDistribuidos(distribuidos);
            venda.setTitulosDevolvidos(devolvidos);
            venda.setTitulosVendidos(vendidos);
            venda.setValor(total);
            venda.setDistribuidor(distribuidor);
            venda.setEtapa(selecionarEtapaBean.getEtapaSelecionada());
            venda.setUsuario(autenticacaoBean.getUsuarioLogado());
            venda.setDespesas(despesas);
            if (venda.getValor() - venda.getRecebido() != 0) {

                double valor = (venda.getRecebido() - venda.getValor());
                venda.setRecibo(valor);
                atualizaSaldoDistribuidor(valor);
            } else {
                venda.setRecibo(0);
            }
            vendaEdicaoDao.salvar(venda);
            FacesUtil.addMsgInfo("Venda de, " + distribuidor.getNome() + " adicionada com sucesso! ");
            atualizaSaldoEtapa(venda.getRecebido());

            atualizarExtrato("Venda do distribuidor " + venda.getDistribuidor().getNome(), venda.getRecebido());

            venda = new VendaEdicao();

        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar incluir venda! " + e.getMessage());
        }
        voltarVendas();
    }

    public void editar() {
        try {

            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            vendaEdicaoDao.editar(venda);

            FacesUtil.addMsgInfo("Venda editada com sucesso! ");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar editar venda! " + e.getMessage());
        }
    }

    public void excluir() {
        try {

            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            vendaEdicaoDao.excluir(venda);
            atualizaSaldoEtapa(-venda.getRecebido());
            atualizarExtrato("Excluir Venda do distribuidor " + venda.getDistribuidor().getNome(), -venda.getRecebido());
            atualizaSaldoDistribuidor(-venda.getRecibo());
            FacesUtil.addMsgInfo("Venda excluida com sucesso! ");
            venda = new VendaEdicao();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar excluir venda! " + e.getMessage());
        }
    }

    private void atualizaSaldoEtapa(double valor) {
        try {
            EtapaDao etapaDao = new EtapaDao();
            selecionarEtapaBean.getEtapaSelecionada().setSaldoEtapa(selecionarEtapaBean.getEtapaSelecionada().getSaldoEtapa() + valor);
            etapaDao.editar(selecionarEtapaBean.getEtapaSelecionada());
            FacesUtil.addMsgInfo("Saldo etapa atualizado, " + valor);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao atualizar saldo da etapa! " + e.getMessage());
        }
    }

    private void atualizaSaldoDistribuidor(double valor) {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            distribuidor.setSaldo(distribuidor.getSaldo() + valor);
            distribuidorDao.editar(distribuidor);
            FacesUtil.addMsgInfo("Saldo do distribuidor atualizado!");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar atualizar saldo do sitribuidor! " + e.getMessage());
        }
    }

    public void verificaVendaDistribuidor() {

        try {
            if (acao.equals("novo")) {
                VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
                listasVenda = vendaEdicaoDao.buscarPorEdicao(selecionarEtapaBean.getEtapaSelecionada().getId());

                for (VendaEdicao vendaEdicao : listasVenda) {
                    if (vendaEdicao.getDistribuidor().getId() == distribuidor.getId()) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/DRE/faces/pages/DespesaDistribuidorCadastro.xhtml?faces-redirect=true");
                        FacesUtil.addMsgErro("Distribuidor " + distribuidor.getNome() + ", já possui venda na edição " + selecionarEtapaBean.getEtapaSelecionada().getEdicao());

                    }
                }
            }

        } catch (IOException ex) {
            FacesUtil.addMsgErro("Erro ao tentar redirecionar " + ex.getMessage());
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
            if (valor > 0) {
                extratoEdicao.setTipo("C");
            } else {
                extratoEdicao.setTipo("D");
            }
            extratoEdicaoDao.salvar(extratoEdicao);
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar atualizar extrato!");
        }

    }

    private void voltarVendas() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/DRE/faces/pages/VendaEdicaoPesquisa.xhtml?faces-redirect=true");

        } catch (IOException ex) {
            FacesUtil.addMsgErro("Erro ao redirecionar para vendas! " + ex.getMessage());
        }
    }

    private void somarTotais() {

        VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
        listasVenda = vendaEdicaoDao.buscarPorEdicao(selecionarEtapaBean.getEtapaSelecionada().getId());
        for (VendaEdicao vendaEdicao : listasVenda) {

            totalDistribuidos += vendaEdicao.getTitulosDistribuidos();
            totalDevolvidos += vendaEdicao.getTitulosDevolvidos();
            totalVendidos += vendaEdicao.getTitulosVendidos();
            totalExtravio += vendaEdicao.getExtravio();
            totalDespesas += vendaEdicao.getDespesas();
            totalValor += vendaEdicao.getValor();
            totalFaltou += vendaEdicao.getRecibo();
            totalRecebido += vendaEdicao.getRecebido();
        }

    }
    public void imprimir(){
        RelatorioVendaEdicao relatorioVendaEdicao = new RelatorioVendaEdicao();
        List<Etapa> vendaEdicaos = new ArrayList<>();
        vendaEdicaos.add(selecionarEtapaBean.getEtapaSelecionada());
        relatorioVendaEdicao.getRelatorio(listasVenda);
    }

}
