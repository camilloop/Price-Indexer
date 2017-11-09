package org.camilloop.repository;

import org.camilloop.model.PriceCollector;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PriceCollectorRepository extends ElasticsearchRepository<PriceCollector, String> {
    List<PriceCollector> findAllByProductName(String productName);
    List<PriceCollector> findAllByWebsiteName(String productName);
    List<PriceCollector> findByProductName(String productName, Pageable pageable);
    List<PriceCollector> findByProductName(String productName);
    void deleteAllByWebsiteName(String websiteName);

    /**
     * findFist and FindTop don't work with elasticsearch
     */
    default PriceCollector findFirstByProductNameOrderByPriceAsc(String productName) {
        Sort sort = new Sort(Sort.Direction.ASC, "price");
        return this.findByProductName(productName, PageRequest.of(0, 1, sort)).get(0);
    }

    /**
     * findFist and FindTop don't work with elasticsearch
     */
    default PriceCollector findFirstByProductNameOrderByPriceDesc(String productName) {
        Sort sort = new Sort(Sort.Direction.DESC, "price");
        return this.findByProductName(productName, PageRequest.of(0, 1, sort)).get(0);
    }
}
