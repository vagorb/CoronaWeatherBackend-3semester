package ee.taltech.iti02032020.backend.a_theory.question6.art;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Painting {

    private Long id;
    private String author;
    private String name;
    private LocalDate drawnAt;
    private LocalDate boughtAt;
    private BigDecimal price;
}
