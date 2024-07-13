package dev.loras.EncurtaAE.Links.controllers;

import dev.loras.EncurtaAE.Links.models.Link;
import dev.loras.EncurtaAE.Links.models.LinkResponse;
import dev.loras.EncurtaAE.Links.services.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class LinkController {

    private final LinkService service;

    public LinkController(LinkService linkService) {
        this.service = linkService;
    }

    @PostMapping("/encurta-ae")
    public ResponseEntity<LinkResponse> gerenateUrl(
            @RequestBody Map<String, String> request
            ) {
        String longUrl = request.get("original_url");
        Link link = this.service.shortenerUrl(longUrl);

        String userRedirectUrl = "localhost:3000/r/" + link.getShortUrl();

        LinkResponse response = new LinkResponse(
                link.getId(),
                link.getLongUrl(),
                userRedirectUrl,
                link.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/r/{shortUrl}")
    public void redirectUser(
            @PathVariable("shortUrl") String shortUrl, HttpServletResponse response
            ) throws IOException {

        Link link = this.service.getLongerUrl(shortUrl);
        if (link != null) {
            response.sendRedirect(link.getLongUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

}
