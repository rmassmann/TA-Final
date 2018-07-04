/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifsul.controle;



import br.edu.ifsul.dao.AlbumDAO;
import br.edu.ifsul.dao.BandaDAO;
import br.edu.ifsul.modelo.Album;
import br.edu.ifsul.modelo.Banda;
import br.edu.ifsul.modelo.Utilizador;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Renan
 */
@Named(value = "controleAlbum")
@ViewScoped
public class ControleAlbum implements Serializable{
    @EJB
    private AlbumDAO<Album> dao;
    private Album objeto;
    @Inject
    ControleLogin login;
    private Boolean editando;
     @EJB
    private BandaDAO<Banda> daoBanda;
    
    
     public ControleAlbum(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/album/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Album();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        } 
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            
            if (objeto.getId() == null){
                
                Utilizador u = login.getUtilizadorAutenticado();
                
                objeto.setUtilizador(u);
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public AlbumDAO<Album> getDao() {
        return dao;
    }

    public void setDao(AlbumDAO<Album> dao) {
        this.dao = dao;
    }

    public Album getObjeto() {
        return objeto;
    }

    public void setObjeto(Album objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public BandaDAO<Banda> getDaoBanda() {
        return daoBanda;
    }

    public void setDaoBanda(BandaDAO<Banda> daoBanda) {
        this.daoBanda = daoBanda;
    }

   
}
