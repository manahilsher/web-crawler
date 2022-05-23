package com.manahilsher.WebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    private static final int MAX_URLs = 100;

    private Queue<String> urlQueue;
    private List<String> visitedURLs;

    public WebCrawler() {
        urlQueue = new LinkedList<>();
        visitedURLs = new ArrayList<>();
    }

    public void crawl(String rootURL) {
        urlQueue.add(rootURL);
        visitedURLs.add(rootURL);

        while (!urlQueue.isEmpty()) {
            String url = urlQueue.remove();

            scrape(url);

            if (urlQueue.size() == MAX_URLs)
                break;
        }
    }

    private void scrape(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements foundLinks = document.select("a[href]");

            for (Element foundLink : foundLinks) {
                String foundURL = foundLink.attr("abs:href");

                if (!visitedURLs.contains(foundURL)) {
                    visitedURLs.add(foundURL);
                    System.out.println("Website found with URL " + foundURL);
                    urlQueue.add(foundURL);

                    if (urlQueue.size() == MAX_URLs)
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("For '" + url + "': " + e.getMessage());
        }
    }
}
