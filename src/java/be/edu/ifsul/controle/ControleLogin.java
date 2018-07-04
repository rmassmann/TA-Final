/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifsul.controle;

import br.edu.ifsul.dao.UtilizadorDAO;
import br.edu.ifsul.modelo.Utilizador;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Renan
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    
    private Utilizador utilizadorAutenticado;
    @EJB
    private UtilizadorDAO<Utilizador>dao;
    private String utilizador;
    private String password;

    public ControleLogin(){
        
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.utilizador, this.password);
            if(request.getUserPrincipal() != null){
                utilizadorAutenticado = dao.localizaPorNomeUtilizador(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login efetuado com sucesso!");
                utilizador = "";
                password = "";
            }
            return "/index";
        }catch(Exception e){
            Util.mensagemErro("Utilizador ou password inválidos!" +
                    Util.getMensagemErro(e));
            return "/login";
        }
    }
    
    public String efetuarLogout() {
        try {
            utilizadorAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) FacesContext.
            getCurrentInstance().getExternalContext().getRequest();
            request.logout();
        } catch (Exception e) {
            Util.mensagemErro("Erro: " + Util.getMensagemErro(e));
        }
         return "/index";
    }

    public Utilizador getUtilizadorAutenticado() {
        return utilizadorAutenticado;
    }

    public void setUtilizadorAutenticado(Utilizador utilizadorAutenticado) {
        this.utilizadorAutenticado = utilizadorAutenticado;
    }

    public UtilizadorDAO<Utilizador> getDao() {
        return dao;
    }

    public void setDao(UtilizadorDAO<Utilizador> dao) {
        this.dao = dao;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   

  

   
    
    
}
