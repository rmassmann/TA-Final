/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifsul.controle;

import br.edu.ifsul.dao.BandaDAO;
import br.edu.ifsul.modelo.Banda;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Renan
 */
@Named(value="controleBanda")
@ViewScoped
public class ControleBanda implements Serializable{
     @EJB
    private BandaDAO<Banda> dao;
    private Banda objeto;
    private Boolean editando;

    public ControleBanda(){editando = false;}
    
    public String listar(){
        editando=false;
        return "/privado/banda/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Banda();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.getObjectById(id);
            editando = true;
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        }
        
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mensagemErro("Erro ao remover Objeto: "+ 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
            editando = false;
        }catch(Exception e){
              Util.mensagemErro("Erro ao persistir objeto: " + 
                      Util.getMensagemErro(e));
        }
    }

    public BandaDAO<Banda> getDao() {
        return dao;
    }

    public void setDao(BandaDAO<Banda> dao) {
        this.dao = dao;
    }

    public Banda getObjeto() {
        return objeto;
    }

    public void setObjeto(Banda objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
}
