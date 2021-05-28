/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.DespesaDistribuidorDao;
import br.com.dre.dao.DistribuidorDao;
import br.com.dre.dao.VendaEdicaoDao;
import br.com.dre.domain.DespesaDistribuidor;
import br.com.dre.domain.Distribuidor;
import br.com.dre.domain.VendaEdicao;
import br.com.dre.util.FacesUtil;
import java.io.IOException;
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
public class DespesaDistribuidorBean {
    
    private List<DespesaDistribuidor> listaDespesas;
    private DespesaDistribuidor despesaDistribuidor;
    private List<Distribuidor> listaDistribuidor;
    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;
    private String acao;
    private Long id;
    private Distribuidor distribuidor;
    
    public Distribuidor getDistribuidor() {
        if (distribuidor == null) {
            distribuidor = new Distribuidor();
        }
        return distribuidor;
    }
    
    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
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
    
    public SelecionarEtapaBean getSelecionarEtapaBean() {
        return selecionarEtapaBean;
    }
    
    public void setSelecionarEtapaBean(SelecionarEtapaBean selecionarEtapaBean) {
        this.selecionarEtapaBean = selecionarEtapaBean;
    }
    
    public List<DespesaDistribuidor> getListaDespesas() {
        return listaDespesas;
    }
    
    public void setListaDespesas(List<DespesaDistribuidor> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }
    
    public DespesaDistribuidor getDespesaDistribuidor() {
        return despesaDistribuidor;
    }
    
    public void setDespesaDistribuidor(DespesaDistribuidor despesaDistribuidor) {
        this.despesaDistribuidor = despesaDistribuidor;
    }
    
    public List<Distribuidor> getListaDistribuidor() {
        return listaDistribuidor;
    }
    
    public void setListaDistribuidor(List<Distribuidor> listaDistribuidor) {
        this.listaDistribuidor = listaDistribuidor;
    }
    
    public void salvar() {
        
        try {
            int cont = 0;
            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            List<VendaEdicao> listasVenda = vendaEdicaoDao.buscarPorEdicao(selecionarEtapaBean.getEtapaSelecionada().getId());
            
            for (VendaEdicao vendaEdicao : listasVenda) {
                if (vendaEdicao.getDistribuidor().getId() == distribuidor.getId()) {
                    cont = 1;
                }
            }
            if (cont == 1) {
                FacesUtil.addMsgErro("Distribuidor já possui venda na edição, " + selecionarEtapaBean.getEtapaSelecionada().getEdicao() + " - " + selecionarEtapaBean.getEtapaSelecionada().getDataEdicao());
            } else {
                
                if ((despesaDistribuidor.getDescricao().equalsIgnoreCase("SEM DESPESA") && despesaDistribuidor.getValor() == 0)
                        || (!despesaDistribuidor.getDescricao().equalsIgnoreCase("SEM DESPESA") && despesaDistribuidor.getValor() > 0)) {
                    DespesaDistribuidorDao despesaDistribuidorDao = new DespesaDistribuidorDao();
                    despesaDistribuidor.setDistribuidor(distribuidor);
                    despesaDistribuidor.setEtapa(selecionarEtapaBean.getEtapaSelecionada());
                    despesaDistribuidor.setData(new Date());
                    despesaDistribuidorDao.salvar(despesaDistribuidor);
                    
                    FacesUtil.addMsgInfo("Despesa de " + despesaDistribuidor.getDistribuidor().getNome() + ", adicionada com sucesso!");
                    despesaDistribuidor = new DespesaDistribuidor();
                } else {
                    FacesUtil.addMsgErro("SEM DEPESA o valor tem que ser 0, outro tipo tem que ser maior que 0!");
                }
            }
            
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar adicionar despesa, ERRO: " + e.getMessage());
        }
    }
    
    public void editar() {
        try {
            int cont = 0;
            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            List<VendaEdicao> listasVenda = vendaEdicaoDao.buscarPorEdicao(selecionarEtapaBean.getEtapaSelecionada().getId());
            
            for (VendaEdicao vendaEdicao : listasVenda) {
                System.out.println(vendaEdicao.toString());
                if (vendaEdicao.getDistribuidor().getId() == despesaDistribuidor.getDistribuidor().getId()) {
                    cont = 1;
                    System.out.println(vendaEdicao.getId());
                }
            }
            System.out.println("CONT " + cont);
            if (cont == 1) {
                FacesUtil.addMsgErro("Distribuidor já possui venda na edição, " + selecionarEtapaBean.getEtapaSelecionada().getEdicao() + " - " + selecionarEtapaBean.getEtapaSelecionada().getDataEdicao());
            } else {
                DespesaDistribuidorDao despesaDistribuidorDao = new DespesaDistribuidorDao();
                despesaDistribuidorDao.editar(despesaDistribuidor);
                FacesUtil.addMsgInfo("Despesa do distribuidor " + despesaDistribuidor.getDistribuidor().getNome() + ", alterada com sucesso!");
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar alterar despesa, ERRO: " + e.getMessage());
        }
    }
    
    public void excluir() {
        try {
            int cont = 0;
            VendaEdicaoDao vendaEdicaoDao = new VendaEdicaoDao();
            List<VendaEdicao> listasVenda = vendaEdicaoDao.buscarPorEdicao(selecionarEtapaBean.getEtapaSelecionada().getId());
            
            for (VendaEdicao vendaEdicao : listasVenda) {
                if (vendaEdicao.getDistribuidor().getId() == despesaDistribuidor.getDistribuidor().getId()) {
                    cont = 1;
                }
            }
            if (cont == 1) {
                FacesUtil.addMsgErro("Distribuidor já possui venda na edição, " + selecionarEtapaBean.getEtapaSelecionada().getEdicao() + " - " + selecionarEtapaBean.getEtapaSelecionada().getDataEdicao());
            } else {
                DespesaDistribuidorDao despesaDistribuidorDao = new DespesaDistribuidorDao();
                despesaDistribuidorDao.excluir(despesaDistribuidor);
                FacesUtil.addMsgInfo("Despesa do distribuidor " + despesaDistribuidor.getDistribuidor().getNome() + ", excluida com sucesso!");
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar excluir despesa, ERRO: " + e.getMessage());
        }
    }
    
    public void listar() {
        try {
            DespesaDistribuidorDao despesaDistribuidorDao = new DespesaDistribuidorDao();
            listaDespesas = despesaDistribuidorDao.buscarPorEtapa(selecionarEtapaBean.getEtapaSelecionada().getId());
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar listar despesas!");
        }
    }
    
    public void carregarDistribuidor() {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            listaDistribuidor = distribuidorDao.listar();
        } catch (Exception e) {
            FacesUtil.addMsgErro("Erro ao tentar listar distribuidores");
        }
    }
    
    public void carregarDespesas() {
        try {
            if (id == null) {
                despesaDistribuidor = new DespesaDistribuidor();
            } else {
                DespesaDistribuidorDao despesaDistribuidorDao = new DespesaDistribuidorDao();
                despesaDistribuidor = despesaDistribuidorDao.buscarPorCodigo(id);
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar despesas! " + e.getMessage());
        }
    }

    
}
