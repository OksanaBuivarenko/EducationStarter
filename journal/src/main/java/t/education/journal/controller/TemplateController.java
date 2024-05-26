package t.education.journal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t.education.starter.interceptor.StarterRestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/template")
public class TemplateController {

    private final StarterRestTemplate starterRestTemplate;

    @GetMapping("/test")
    public void testRestLog() {
        starterRestTemplate.starterRestTemplate()
                .getForObject("http://jsonplaceholder.typicode.com/posts?_limit=2", String.class);
    }
}
