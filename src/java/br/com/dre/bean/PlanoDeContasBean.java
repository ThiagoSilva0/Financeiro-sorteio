/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.PlanoDeContasDao;
import br.com.dre.domain.PlanoDeContas;
import br.com.dre.relatorio.RelatorioPlanoDeContas;
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
public class PlanoDeContasBean {

    private PlanoDeContas planoDeContas;
    private List<PlanoDeContas> listaPlanoDeContas;
    private Long id;
    private String acao;

    public List<PlanoDeContas> getListaPlanoDeContas() {
        return listaPlanoDeContas;
    }

    public void setListaPlanoDeContas(List<PlanoDeContas> listaPlanoDeContas) {
        this.listaPlanoDeContas = listaPlanoDeContas;
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

    public PlanoDeContas getPlanoDeContas() {
        return planoDeContas;
    }

    public void setPlanoDeContas(PlanoDeContas planoDeContas) {
        this.planoDeContas = planoDeContas;
    }

    public void carregarCadastro() {

        try {
            if (id != null) {
                PlanoDeContasDao planoDeContasDao = new PlanoDeContasDao();
                planoDeContas = planoDeContasDao.buscarPorcodigo(id);
            } else {
                planoDeContas = new PlanoDeContas();
            }
        } catch (Exception e) {
            FacesUtil.addMsgErro("ERRO ao tentar acrregar plano de contas! " + e.getMessage());
        }

    }

    public void salvar() {

        try {
            PlanoDeContasDao contasDao = new PlanoDeContasDao();
            contasDao.salvar(planoDeContas);
            FacesUtil.addMsgInfo("Plano de contas adcionado com sucesso!");
            planoDeContas = new PlanoDeContas();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar adcionar Plano de contas: " + e.getMessage());
        }
    }

    public void editar() {

        try {
            PlanoDeContasDao contasDao = new PlanoDeContasDao();
            contasDao.editar(planoDeContas);
            FacesUtil.addMsgInfo("Plano de contas editado com sucesso!");
            planoDeContas = new PlanoDeContas();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar editar Plano de contas: " + e.getMessage());
        }
    }

    public void excluir() {

        try {
            PlanoDeContasDao contasDao = new PlanoDeContasDao();
            contasDao.excluir(planoDeContas);
            FacesUtil.addMsgInfo("Plano de contas excluido com sucesso!");
            planoDeContas = new PlanoDeContas();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar excluir Plano de contas: " + e.getMessage());
        }
    }

    public void listar() {
        try {
            PlanoDeContasDao planoDeContasDao = new PlanoDeContasDao();
            listaPlanoDeContas = planoDeContasDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar listar planos de contas");
        }
    }

    public void novo() {
        planoDeContas = new PlanoDeContas();
    }
    
    public void gerarRelatorio(){
        
        RelatorioPlanoDeContas relatorioPlanoDeContas = new RelatorioPlanoDeContas();
        relatorioPlanoDeContas.getRelatorio(listaPlanoDeContas);
    } 
}
