package dev.loras.EncurtaAE.Links.repositories;

import dev.loras.EncurtaAE.Links.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {

    Link findByShortUrl(String shortUrl);

}
