package com.maksimliakhouski.phraseservice.integration;

import com.maksimliakhouski.phraseservice.exception.NotFoundException;
import com.maksimliakhouski.phraseservice.model.Phrase;
import com.maksimliakhouski.phraseservice.repository.PhraseRepository;
import com.maksimliakhouski.phraseservice.service.PhraseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PhraseServiceIntegrationTest {

    @Autowired
    private PhraseRepository phraseRepository;

    private PhraseService phraseService;

    @BeforeEach
    void setUp() {
        phraseService = new PhraseService(phraseRepository);
    }

    @Test
    void testGetPhrase() {
        // Arrange
        Phrase phrase = new Phrase();
        phrase.setValue("Test phrase");
        phraseRepository.save(phrase);

        // Act
        Phrase result = phraseService.getPhrase(phrase.getId());

        // Assert
        assertEquals(phrase.getValue(), result.getValue());
    }

    @Test
    void testGetPhrase_NotFound() {
        // Arrange
        Long phraseId = 1L;

        // Act & Assert
        assertThrows(NotFoundException.class, () -> phraseService.getPhrase(phraseId));
    }

    @Test
    void testGetRandomPhrase() {
        // Arrange
        Phrase phrase1 = new Phrase();
        phrase1.setValue("Phrase 1");
        phraseRepository.save(phrase1);

        Phrase phrase2 = new Phrase();
        phrase2.setValue("Phrase 2");
        phraseRepository.save(phrase2);

        // Act
        Phrase randomPhrase = phraseService.getRandomPhrase();

        // Assert
        assertNotNull(randomPhrase);
    }

}
