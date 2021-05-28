/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.converter;

import br.com.dre.dao.PlanoDeContasDao;
import br.com.dre.domain.PlanoDeContas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiag
 */
@FacesConverter("planoDeContasConverter")
public class PlanoDeContasConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Long id = Long.parseLong(string);
            PlanoDeContasDao planoDeContasDao = new PlanoDeContasDao();
            PlanoDeContas planoDeContas = planoDeContasDao.buscarPorcodigo(id);
            return planoDeContas;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            PlanoDeContas planoDeContas = (PlanoDeContas) o;
            Long id = planoDeContas.getId();
            return id.toString();
        } catch (RuntimeException e) {
            return null;
        }
    }
    
}
