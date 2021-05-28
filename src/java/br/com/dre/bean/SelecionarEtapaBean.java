/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.bean;

import br.com.dre.dao.EtapaDao;
import br.com.dre.domain.Etapa;
import br.com.dre.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author thiag
 */
@ManagedBean
@SessionScoped
public class SelecionarEtapaBean {

   
    private Etapa etapaSelecionada;
    private List<Etapa> listaEtapas;
    

    public Etapa getEtapaSelecionada() {
       
        return etapaSelecionada;
    }

    public void setEtapaSelecionada(Etapa etapaSelecionada) {
        this.etapaSelecionada = etapaSelecionada;
    }

    public List<Etapa> getListaEtapas() {
        return listaEtapas;
    }

    public void setListaEtapas(List<Etapa> listaEtapas) {
        this.listaEtapas = listaEtapas;
    }

    @PostConstruct
    public void carregarEtapas() {
         if (etapaSelecionada == null) {
            etapaSelecionada = new Etapa();
        }
        try {
            EtapaDao etapaDao = new EtapaDao();
            listaEtapas = etapaDao.listar();
            

        } catch (RuntimeException e) {
            FacesUtil.addMsgErro("ERRO ao carregar etapas! " + e.getMessage());
        }

    }

    public void selecionaEtapa() {
        FacesUtil.addMsgInfo("Etapa selecionada, "+etapaSelecionada.getEdicao()+" - "+etapaSelecionada.getDataEdicao());
         
    }
}
