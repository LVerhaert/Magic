package liza.stage.magic.services;

import liza.stage.magic.domain.MagicCard;
import liza.stage.magic.repositories.MagicCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MagicCardService {
    private final MagicCardRepository magicCardRepository;

    public void save(MagicCard magicCard) {
        magicCardRepository.save(magicCard);
    }

    public List<MagicCard> findAll() {
        return magicCardRepository.findAll();
    }
}
