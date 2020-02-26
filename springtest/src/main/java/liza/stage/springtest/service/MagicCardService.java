package liza.stage.springtest.service;

import liza.stage.springtest.model.MagicCard;
import liza.stage.springtest.repositories.MagicCardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@AllArgsConstructor
public class MagicCardService {
    private MagicCardRepository magicCardRepository;
    public void save(MagicCard magicCard) {
        Assert.notNull(magicCard);
        magicCardRepository.save(magicCard);
    }
    public List<MagicCard> findAll() {
        return magicCardRepository.findAll();
    }
}
