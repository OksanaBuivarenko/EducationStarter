package t.education.journal.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import t.education.journal.dto.request.DancerRq;
import t.education.journal.dto.response.DancerRs;
import t.education.journal.dto.response.PaymentRs;
import t.education.journal.service.DancerService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dancer")
public class DancerController {

    private final DancerService dancerService;

    @GetMapping("/")
    public List<DancerRs> getDancers() {
        return dancerService.getDancerList();
    }

    @SneakyThrows
    @GetMapping("/{dancerId}")
    public DancerRs getDancersById(@PathVariable Long dancerId) {
        return dancerService.getDancerRsById(dancerId);
    }

    @PostMapping("/")
    public DancerRs addDancers(@RequestBody DancerRq dancerRq) {
        return dancerService.addDancer(dancerRq);
    }

    @PostMapping("/lesson/{dancerId}/{lessonId}")
    public DancerRs addLessonToDancer(@PathVariable Long dancerId, @PathVariable Long lessonId) {
        return dancerService.addLessonToDancer(dancerId, lessonId);
    }

    @GetMapping("/payment/{dancerId}")
    public PaymentRs getDancersById(@PathVariable Long dancerId, @RequestParam("from") String from,
                                    @RequestParam("to") String to) {
        return dancerService.getPaymentByDancerId(dancerId, from, to);
    }
}
