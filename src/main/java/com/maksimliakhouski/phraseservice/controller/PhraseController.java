package com.maksimliakhouski.phraseservice.controller;

import com.maksimliakhouski.phraseservice.dto.PhraseRequest;
import com.maksimliakhouski.phraseservice.dto.PhraseResponse;
import com.maksimliakhouski.phraseservice.model.Phrase;
import com.maksimliakhouski.phraseservice.service.PhraseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("phrase")
public class PhraseController {

    private final PhraseService phraseService;

    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/")
    public PhraseResponse getRandomPhrase() {
        Phrase phrase = phraseService.getRandomPhrase();
        return phrase != null ? phrase.toPhraseResponse() : null;
    }

    @GetMapping("/{id}")
    public PhraseResponse getPhrase(@PathVariable Long id) {
        return phraseService
                .getPhrase(id)
                .toPhraseResponse();
    }

    @PostMapping
    public void createPhrase(@RequestBody PhraseRequest request) {
        phraseService.createPhrase(request);
    }

    @PutMapping("/{id}")
    public void updatePhrase(@PathVariable Long id,
                          @RequestBody PhraseRequest request) {
        phraseService.updatePhrase(id, request);
    }

    @DeleteMapping("/{id}")
    public void removePhrase(@PathVariable Long id) {
        phraseService.removePhrase(id);
    }

}
