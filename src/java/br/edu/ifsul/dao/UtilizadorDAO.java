/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Utilizador;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Renan
 */
@Stateful
public class UtilizadorDAO <TIPO> extends DAOGenerico<Utilizador> implements Serializable {
    public UtilizadorDAO(){
        super();
        classePersistente = Utilizador.class;
        ordem = "nome"; // define a ordem padrão do DAO
        maximoObjetos = 3;
    }
    
     public Utilizador getObjectById(Object id) throws Exception {
        Utilizador obj = em.find(Utilizador.class, id);
        /**
         * A linha obj.getPermissoes().size(); é necessária para inicializar a coleção
         * para quando ela for exibida na tela não gerar um erro de 
         * lazyInicializationException
         */
        obj.getAutorizacoes().size(); 
        return obj;
    }
    
    public Utilizador localizaPorNomeUtilizador(String utilizador) {
          Utilizador obj = (Utilizador) super.getEm().createQuery("from Utilizador where upper(utilizador) = :utilizador").
          setParameter("utilizador", utilizador.toUpperCase()).getSingleResult();
          obj.getAutorizacoes().size();
         return obj;


    }
}

