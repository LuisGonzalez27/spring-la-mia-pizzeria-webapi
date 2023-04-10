package org.learning.pizzeria.service;

import org.learning.pizzeria.exceptions.OffertaNotFoundException;
import org.learning.pizzeria.exceptions.PizzaNotFoundException;
import org.learning.pizzeria.model.Offerta;
import org.learning.pizzeria.repository.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OffertaService {
    @Autowired
    private OffertaRepository offertaRepository;

    public Offerta create(Offerta formOfferta){
        return offertaRepository.save(formOfferta);
    }

    public Offerta getById(Integer id) throws PizzaNotFoundException {
        return offertaRepository.findById(id).orElseThrow(()-> new OffertaNotFoundException(Integer.toString(id)));
    }

    public Offerta update(Integer id, Offerta formOfferta) throws  OffertaNotFoundException{
        Offerta offertaTuUpdate = getById(id);
        offertaTuUpdate.setName(formOfferta.getName());
        offertaTuUpdate.setStartOfferDate(formOfferta.getStartOfferDate());
        offertaTuUpdate.setEndOfferDate(formOfferta.getEndOfferDate());
        return offertaRepository.save(offertaTuUpdate);
    }

    public Integer delete(Integer offertaId) throws OffertaNotFoundException{
        Offerta offertaToDelete = getById(offertaId);
        offertaRepository.delete(offertaToDelete);
        return offertaToDelete.getPizza().getId();
    }
}
