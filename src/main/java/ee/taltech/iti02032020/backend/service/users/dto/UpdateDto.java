package ee.taltech.iti02032020.backend.service.users.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UpdateDto {
    private String oldusername;
    private String username;
    private String hometown;
}
