package org.camilloop.controller;

import org.camilloop.model.PriceCollector;
import org.camilloop.repository.PriceCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceCollectorController {

    private PriceCollectorRepository priceCollectorRepository;

    @Autowired
    public PriceCollectorController(PriceCollectorRepository priceColletorRepository) {
        this.priceCollectorRepository = priceColletorRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PriceCollector>> allPrices() {
        Iterable<PriceCollector> allPriceData = priceCollectorRepository.findAll();
        return ResponseEntity.ok(allPriceData);
    }


    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceCollector>> getPriceDataByName(@PathVariable String name) {
        List<PriceCollector> priceDataByName = priceCollectorRepository.findByProductName(name);
        return ResponseEntity.ok(priceDataByName);
    }

    @GetMapping(path = "/{name}/min", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceCollector> getPriceDataByNameWithMinPrice(@PathVariable String name) {
        PriceCollector priceData = priceCollectorRepository.findFirstByProductNameOrderByPriceAsc(name);
        return ResponseEntity.ok(priceData);
    }

    @GetMapping(path = "/{name}/max", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceCollector> getPriceDataByNameWithMaxPrice(@PathVariable String name) {
        PriceCollector priceData = priceCollectorRepository.findFirstByProductNameOrderByPriceDesc(name);
        return ResponseEntity.ok(priceData);
    }
}
