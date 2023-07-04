package com.maksimliakhouski.phraseservice.service;

import com.maksimliakhouski.phraseservice.dto.PhraseRequest;
import com.maksimliakhouski.phraseservice.exception.NotFoundException;
import com.maksimliakhouski.phraseservice.model.Phrase;
import com.maksimliakhouski.phraseservice.repository.PhraseRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PhraseService {

    private final PhraseRepository phraseRepository;
    private final Random random = new Random();

    public PhraseService(PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    public Phrase getPhrase(Long id) {
        return phraseRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Phrase", id));
    }

    public Phrase getRandomPhrase() {
        long count = phraseRepository.count();
        if (count == 0L) return null;
        long id = 1 + random.nextLong(count);
        return getPhrase(id);
    }

    public void createPhrase(PhraseRequest request) {
        Phrase phrase = Phrase
                .builder()
                .value(request.value())
                .build();
        phraseRepository.save(phrase);
    }

    public void updatePhrase(Long id, PhraseRequest request) {
        Phrase phrase = getPhrase(id);
        phrase.setValue(request.value());
        phraseRepository.save(phrase);
    }

    public void removePhrase(Long id) {
        Phrase phrase = getPhrase(id);
        phraseRepository.delete(phrase);
    }

}
