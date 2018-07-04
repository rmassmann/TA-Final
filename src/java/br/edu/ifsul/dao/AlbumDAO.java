/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Album;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Renan
 */
@Stateful
public class AlbumDAO<TIPO> extends DAOGenerico<Album> implements Serializable {
     public AlbumDAO(){
        super();
        classePersistente = Album.class;
        ordem = "titulo"; // define a ordem padrão do DAO
        maximoObjetos = 3;
    }
     
}
