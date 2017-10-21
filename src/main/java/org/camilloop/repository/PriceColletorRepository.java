package org.camilloop.repository;

import org.camilloop.model.PriceCollector;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PriceColletorRepository extends ElasticsearchRepository<PriceCollector, String> {
}
