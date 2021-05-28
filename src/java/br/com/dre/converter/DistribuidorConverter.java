/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.converter;

import br.com.dre.dao.DistribuidorDao;
import br.com.dre.domain.Distribuidor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiag
 */
@FacesConverter("distribuidorConverter")
public class DistribuidorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Long id = Long.parseLong(string);
            DistribuidorDao distribuidorDao = new DistribuidorDao();
            Distribuidor distribuidor = distribuidorDao.buscarPorCodigo(id);
            return distribuidor;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            Distribuidor distribuidor = (Distribuidor) o;
            Long id = distribuidor.getId();
            return id.toString();
        } catch (RuntimeException e) {
            return null;
        }
    }

}
