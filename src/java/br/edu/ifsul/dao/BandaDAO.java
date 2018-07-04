/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Banda;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Renan
 */
@Stateful
public class BandaDAO<TIPO>extends DAOGenerico<Banda>implements Serializable {
    public BandaDAO(){
         super();
        classePersistente = Banda.class;
        ordem = "nome";
    }
}
