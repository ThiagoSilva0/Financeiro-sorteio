/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.FornecedorDao;
import br.com.dre.domain.Fornecedor;
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
public class FornecedorBean {

    private Fornecedor fornecedor;
    private List<Fornecedor> listaFornecedor;
    private Long id;
    private String acao;

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void salvar() {

        try {
            FornecedorDao fornecedorDao = new FornecedorDao();
            fornecedorDao.salvar(fornecedor);
            FacesUtil.addMsgInfo("Fornecedor salvo com sucesso!");
            fornecedor = new Fornecedor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar salvar Fornecedor: " + e.getMessage());
        }

    }

    public void listar() {

        try {
            FornecedorDao fornecedorDao = new FornecedorDao();
            listaFornecedor = fornecedorDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar Listar Fornecedor: " + e.getMessage());
        }

    }

    public void editar() {

        try {
            FornecedorDao fornecedorDao = new FornecedorDao();
            fornecedorDao.editar(fornecedor);
            FacesUtil.addMsgInfo("Fornecedor editado com sucesso!");
            fornecedor = new Fornecedor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar editar Fornecedor: " + e.getMessage());
        }

    }

    public void excluir() {

        try {
            FornecedorDao fornecedorDao = new FornecedorDao();
            fornecedorDao.excluir(fornecedor);
            FacesUtil.addMsgInfo("Fornecedor excluido com sucesso!");
            fornecedor = new Fornecedor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar excluir Fornecedor: " + e.getMessage());
        }

    }

    public void carregarCadastro() {

        try {
            if (id != null) {
                FornecedorDao fornecedorDao = new FornecedorDao();
                fornecedor = fornecedorDao.buscarPorCodigo(id);
            } else {
                fornecedor = new Fornecedor();
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar carregar os dados do fornecedor " + e.getMessage());
        }
    }

    public void novo() {
        fornecedor = new Fornecedor();
    }
}
