package org.camilloop.repository;

import org.camilloop.model.Website;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WebsiteRepository extends ElasticsearchRepository<Website, String> {
    Website findByName(String name);
}
