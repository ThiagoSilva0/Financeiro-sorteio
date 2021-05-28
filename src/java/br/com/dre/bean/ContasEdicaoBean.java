/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.ContasEdicaoDao;
import br.com.dre.dao.FornecedorDao;
import br.com.dre.dao.PlanoDeContasDao;
import br.com.dre.domain.ContasEdicao;
import br.com.dre.domain.Fornecedor;
import br.com.dre.domain.PlanoDeContas;
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
public class ContasEdicaoBean {

    @ManagedProperty(value = "#{selecionarEtapaBean}")
    private SelecionarEtapaBean selecionarEtapaBean;

    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    private List<PlanoDeContas> listaPlanoDeContas;
    private List<ContasEdicao> listaContas;
    private List<Fornecedor> listaFornecedores;

    private ContasEdicao contasEdicao;
    private long id;
    private String acao;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public List<PlanoDeContas> getListaPlanoDeContas() {
        return listaPlanoDeContas;
    }

    public void setListaPlanoDeContas(List<PlanoDeContas> listaPlanoDeContas) {
        this.listaPlanoDeContas = listaPlanoDeContas;
    }

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

    public List<ContasEdicao> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<ContasEdicao> listaContas) {
        this.listaContas = listaContas;
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

    public void listarPlanoDeContas() {
        try {
            PlanoDeContasDao planoDeContasDao = new PlanoDeContasDao();
            listaPlanoDeContas = planoDeContasDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar plano de contas! " + e.getMessage());
        }
    }

    public void listarFornecedores() {
        try {
            FornecedorDao fornecedorDao = new FornecedorDao();
            listaFornecedores = fornecedorDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar Fornecedores! " + e.getMessage());
        }

    }

    public void carregarConta() {
        try {
            if (id == 0) {
                contasEdicao = new ContasEdicao();
            } else {
                ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
                contasEdicao = contasEdicaoDao.buscarPorCodigo(id);
            }
            listarFornecedores();
            listarPlanoDeContas();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar conta! " + e.getMessage());
        }
    }

    public void listar() {
        try {
            ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
            listaContas = contasEdicaoDao.buscarPorEtapa(selecionarEtapaBean.getEtapaSelecionada().getId());
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar contas! " + e.getMessage());
        }
    }

    public void salvar() {
        try {
            contasEdicao.setEtapa(selecionarEtapaBean.getEtapaSelecionada());
            contasEdicao.setUsuario(autenticacaoBean.getUsuarioLogado());
            contasEdicao.setSituacao("a");
            
            ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
            contasEdicaoDao.salvar(contasEdicao);
            FacesUtil.addMsgInfo("Conta adicionada com sucesso!");
            contasEdicao = new ContasEdicao();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar adicionar conta! " + e.getMessage());
        }
    }

    public void novo() {
        contasEdicao = new ContasEdicao();
    }

    public void excluir() {
        try {
            ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
            contasEdicaoDao.excluir(contasEdicao);
            FacesUtil.addMsgInfo("Conta excluida com sucesso!");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar excluir conta! " + e.getMessage());
        }
    }

    public void editar() {
        try {
            ContasEdicaoDao contasEdicaoDao = new ContasEdicaoDao();
            contasEdicaoDao.editar(contasEdicao);
            FacesUtil.addMsgInfo("Conta editada com sucesso!");
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar editar conta! " + e.getMessage());
        }
    }
}
