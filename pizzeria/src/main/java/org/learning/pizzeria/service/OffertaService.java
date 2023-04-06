package org.learning.pizzeria.service;

import org.learning.pizzeria.model.Offerta;
import org.learning.pizzeria.repository.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {
    @Autowired
    private OffertaRepository offertaRepository;

    public Offerta create(Offerta formOfferta){
        return offertaRepository.save(formOfferta);
    }
}
