/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.converter;

import br.com.dre.dao.FornecedorDao;
import br.com.dre.domain.Fornecedor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiag
 */
@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Long id = Long.parseLong(string);
            FornecedorDao fornecedorDao = new FornecedorDao();
            Fornecedor fornecedor = fornecedorDao.buscarPorCodigo(id);
            return fornecedor;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            Fornecedor fornecedor = (Fornecedor) o;
            Long id = fornecedor.getId();
            return id.toString();
        } catch (RuntimeException e) {
            return null;
        }
    }

}
