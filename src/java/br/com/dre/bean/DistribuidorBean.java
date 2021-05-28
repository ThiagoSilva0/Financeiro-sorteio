/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.DistribuidorDao;
import br.com.dre.domain.Distribuidor;
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
public class DistribuidorBean {
    
    private Distribuidor distribuidor;
    private List<Distribuidor> listaDistribuidor;
    private Long codigo;
    private String acao;
    
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public String getAcao() {
        return acao;
    }
    
    public void setAcao(String acao) {
        this.acao = acao;
    }
    
    public Distribuidor getDistribuidor() {
        
        return distribuidor;
    }
    
    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }
    
    public List<Distribuidor> getListaDistribuidor() {
        return listaDistribuidor;
    }
    
    public void setListaDistribuidor(List<Distribuidor> listaDistribuidor) {
        this.listaDistribuidor = listaDistribuidor;
    }
    
    public void salvar() {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            distribuidorDao.salvar(distribuidor);
            FacesUtil.addMsgInfo("Distribuidor salvo com sucesso!");
            distribuidor = new Distribuidor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO: ao tentar salvar distribuidor!  ");
        }
        
    }
    
    public void listar() {
        
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            listaDistribuidor = distribuidorDao.listar();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO: ao tentar listar distribuidor!  " + e.getMessage());
        }
    }
    
    public void carregarCadastro() {
        
        try {
            if (codigo != null) {
                
                DistribuidorDao distribuidorDao = new DistribuidorDao();
                distribuidor = new DistribuidorDao().buscarPorCodigo(codigo);
            } else {
                distribuidor = new Distribuidor();
            }
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("Erro ao tentar obter dados do Distribuidor!" + e.getMessage());
        }
        
    }
    
    public void novo() {
        distribuidor = new Distribuidor();
    }
    
    public void editar() {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            distribuidorDao.editar(distribuidor);
            FacesUtil.addMsgInfo("Distribuidor alterado com sucesso!");
            distribuidor = new Distribuidor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar alterar distribuidor " + e.getMessage());
        }
        
    }
    
    public void excluir() {
        try {
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            distribuidorDao.excluir(distribuidor);
            FacesUtil.addMsgInfo("Distribuidor excluido com sucesso!");
            distribuidor = new Distribuidor();
        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao tentar ecluir distribuidor " + e.getMessage());
        }
        
    }
}
