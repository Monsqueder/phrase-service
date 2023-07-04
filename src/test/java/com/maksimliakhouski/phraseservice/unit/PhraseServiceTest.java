package com.maksimliakhouski.phraseservice.unit;

import com.maksimliakhouski.phraseservice.dto.PhraseRequest;
import com.maksimliakhouski.phraseservice.exception.NotFoundException;
import com.maksimliakhouski.phraseservice.model.Phrase;
import com.maksimliakhouski.phraseservice.repository.PhraseRepository;
import com.maksimliakhouski.phraseservice.service.PhraseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PhraseServiceTest {

    @Mock
    private PhraseRepository phraseRepository;

    @InjectMocks
    private PhraseService phraseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPhrase() {
        // Arrange
        Long phraseId = 1L;
        Phrase phrase = new Phrase(phraseId, "Test phrase");
        when(phraseRepository.findById(phraseId)).thenReturn(Optional.of(phrase));

        // Act
        Phrase result = phraseService.getPhrase(phraseId);

        // Assert
        assertEquals(phrase, result);
        verify(phraseRepository, times(1)).findById(phraseId);
    }

    @Test
    void testGetPhrase_NotFound() {
        // Arrange
        Long phraseId = 1L;
        when(phraseRepository.findById(phraseId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> phraseService.getPhrase(phraseId));
        verify(phraseRepository, times(1)).findById(phraseId);
    }

    @Test
    void testGetRandomPhrase() {
        // Arrange
        long phraseCount = 5;
        when(phraseRepository.count()).thenReturn(phraseCount);
        when(phraseRepository.findById(anyLong())).thenReturn(Optional.of(new Phrase()));

        // Act
        Phrase result = phraseService.getRandomPhrase();

        // Assert
        assertNotNull(result);
        verify(phraseRepository, times(1)).count();
        verify(phraseRepository, times(1)).findById(anyLong());
    }

    @Test
    void testCreatePhrase() {
        // Arrange
        PhraseRequest request = new PhraseRequest("New phrase");

        // Act
        phraseService.createPhrase(request);

        // Assert
        verify(phraseRepository, times(1)).save(any(Phrase.class));
    }

    @Test
    void testUpdatePhrase() {
        // Arrange
        Long phraseId = 1L;
        PhraseRequest request = new PhraseRequest("Updated phrase");
        Phrase existingPhrase = new Phrase(phraseId, "Old phrase");
        when(phraseRepository.findById(phraseId)).thenReturn(Optional.of(existingPhrase));

        // Act
        phraseService.updatePhrase(phraseId, request);

        // Assert
        assertEquals(request.value(), existingPhrase.getValue());
        verify(phraseRepository, times(1)).findById(phraseId);
        verify(phraseRepository, times(1)).save(existingPhrase);
    }

    @Test
    void testRemovePhrase() {
        // Arrange
        Long phraseId = 1L;
        Phrase existingPhrase = new Phrase(phraseId, "Test phrase");
        when(phraseRepository.findById(phraseId)).thenReturn(Optional.of(existingPhrase));

        // Act
        phraseService.removePhrase(phraseId);

        // Assert
        verify(phraseRepository, times(1)).findById(phraseId);
        verify(phraseRepository, times(1)).delete(existingPhrase);
    }
}
