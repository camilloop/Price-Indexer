package org.camilloop.controller;

import org.camilloop.model.Website;
import org.camilloop.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/websites")
public class WebsiteController {

    private WebsiteRepository websiteRepository;

    @Autowired
    public WebsiteController(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Website>> allWebsites() {
        Iterable<Website> allWebsites = websiteRepository.findAll();
        return ResponseEntity.ok(allWebsites);
    }

    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Website> getWebsiteByName(@PathVariable String name) {
        Website website = websiteRepository.findByName(name);
        return ResponseEntity.ok(website);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveWebsite(@RequestBody Website website) {
        Website save = websiteRepository.save(website);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(save.getName())
                .toUri();
        return ResponseEntity.created(location).body(save);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteWebsiteByName(@PathVariable String name) {
        websiteRepository.deleteById(name);
        return ResponseEntity.noContent().build();
    }
}
