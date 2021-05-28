/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.converter;

import br.com.dre.dao.EtapaDao;
import br.com.dre.domain.Etapa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiag
 */
@FacesConverter("etapaConverter")
public class EtapaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       
        try {
            Long id = Long.parseLong(string);
            EtapaDao etapaDao = new EtapaDao();
            Etapa etapa = etapaDao.buscarPorCodigo(id);
            return etapa;
        } catch (RuntimeException e) {
            return null;
       }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        
        try {
            Etapa etapa = (Etapa) o;
            Long id = etapa.getId();
            return id.toString();
        } catch (RuntimeException e) {
            return null;
        }
    }

}
