package dev.loras.EncurtaAE.Links.services;

import dev.loras.EncurtaAE.Links.repositories.LinkRepository;
import dev.loras.EncurtaAE.Links.models.Link;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@Service
public class LinkServiceImpl implements LinkService {

    private LinkRepository repository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.repository = linkRepository;
    }

    @Override
    public Link shortenerUrl(String url) {
        Link link = new Link();
        link.setLongUrl(url);
        link.setShortUrl(generateRandomUrl());
        link.setCreatedAt(LocalDateTime.now());

        return repository.save(link);
    }

    @Override
    public Link getLongerUrl(String shortUrl) {
        try {
            return repository.findByShortUrl(shortUrl);
        } catch (Exception error) {
            throw new RuntimeException("Url don't exists on our system.");
        }
    }

    @Override
    public String generateRandomUrl() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

}
