package t.education.journal.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DancerRq {

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String phone;
}
