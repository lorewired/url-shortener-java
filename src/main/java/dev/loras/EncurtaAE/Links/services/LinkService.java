package dev.loras.EncurtaAE.Links.services;

import dev.loras.EncurtaAE.Links.models.Link;
import dev.loras.EncurtaAE.Links.repositories.LinkRepository;
import org.springframework.stereotype.Service;

public interface LinkService {

    String generateRandomUrl();
    Link shortenerUrl(String url);
    Link getLongerUrl(String url);

}
