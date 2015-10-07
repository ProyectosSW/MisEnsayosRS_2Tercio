/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;

import edu.eci.cosw.persistencia.Sala;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author 2089978
 */
public interface RepositorioSala  extends CrudRepository<Sala, Integer>{
    
}
