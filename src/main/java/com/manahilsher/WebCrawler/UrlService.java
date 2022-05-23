package com.manahilsher.WebCrawler;

import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;

public class UrlService {

    private UrlRepository urlRepository;

    public UrlService() {
        urlRepository = new UrlRepository();
    }

    public FindIterable<Document> findAll() {
        try {
            FindIterable<Document> urls = urlRepository.findAll();
            return urls;
        } catch (Exception e) {
            return null;
        }
    }

    public ObjectId insert(String url) {
        try {
            return urlRepository.insert(url);
        } catch (Exception e) {
            return null;
        }
    }

    public Document find(String url) {
        try {
            return urlRepository.find(url);
        } catch (Exception e) {
            return null;
        }
    }

}
