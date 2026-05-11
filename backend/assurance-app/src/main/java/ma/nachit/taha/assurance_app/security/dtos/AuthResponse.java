package ma.nachit.taha.assurance_app.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String username;

    private List<String> roles;

    private String accessToken;
}
