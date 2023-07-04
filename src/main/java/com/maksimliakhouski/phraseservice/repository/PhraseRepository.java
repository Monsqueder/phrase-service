package com.maksimliakhouski.phraseservice.repository;

import com.maksimliakhouski.phraseservice.model.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long> {
}
