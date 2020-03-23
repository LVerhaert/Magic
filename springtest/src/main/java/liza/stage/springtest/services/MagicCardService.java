package liza.stage.springtest.services;

import liza.stage.springtest.jsonimport.model.MagicCardJson;
import liza.stage.springtest.repositories.MagicCardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class MagicCardService {
    @Autowired
    private MagicCardRepository magicCardRepository;

    public void save(MagicCardJson magicCard) {

//        magicCardRepository.save(magicCard);
    }

    public List<liza.stage.springtest.domain.MagicCard> findAll() {
        return magicCardRepository.findAll();
    }

    public void save(liza.stage.springtest.domain.MagicCard magicCard) {
        magicCardRepository.save(magicCard);
    }
}
