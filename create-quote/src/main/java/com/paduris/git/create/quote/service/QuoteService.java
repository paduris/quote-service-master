package com.paduris.git.create.quote.service;


import com.paduris.git.create.quote.exception.QuoteNotFoundException;
import com.paduris.git.create.quote.model.Quote;
import com.paduris.git.create.quote.repository.QuoteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * @author paduris
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuoteService {

    @NonNull
    private QuoteRepository quoteRepository;

    public Quote save(Quote quote) {
        return quoteRepository.saveAndFlush(quote);
    }

    public Page<Quote> findAll(Pageable pageable) {
        return quoteRepository.findAll(pageable);
    }

    public ResponseEntity<?> deleteQuote(@PathVariable Long quoteId) {
        return quoteRepository.findById(quoteId).map(quote -> {
            quoteRepository.delete(quote);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new QuoteNotFoundException("Quote Id" + quoteId));
    }

    public Optional<Quote> findById(Long quoteId) {
        return quoteRepository.findById(quoteId);
    }

    public void delete(Quote quote) {
        quoteRepository.delete(quote);
    }


}
