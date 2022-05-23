package com.manahilsher.WebCrawler;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        new WebCrawler().crawl("https://en.wikipedia.org/wiki/World_Wide_Web");
    }
}
