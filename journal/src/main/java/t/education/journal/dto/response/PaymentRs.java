package t.education.journal.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PaymentRs {

    private String dancerFirstName;

    private String dancerLastName;

    private LocalDate from;

    private LocalDate to;

    private Integer sum;
}
