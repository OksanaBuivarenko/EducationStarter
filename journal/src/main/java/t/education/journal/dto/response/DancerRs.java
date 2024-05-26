package t.education.journal.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DancerRs {

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String phone;

    private String danceGroup;
}
