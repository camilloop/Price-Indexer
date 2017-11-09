package org.camilloop.jsoup.api;

import org.camilloop.model.Website;

public interface Crawler {

    void executeByWebsite(Website website);
    void executeForAllWebsites();
}
