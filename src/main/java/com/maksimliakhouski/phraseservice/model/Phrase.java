package com.maksimliakhouski.phraseservice.model;

import com.maksimliakhouski.phraseservice.dto.PhraseResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phrase")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phrase_value")
    private String value;

    public PhraseResponse toPhraseResponse() {
        return new PhraseResponse(value);
    }
}
