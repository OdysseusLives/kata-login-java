package loginkata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class User {
    @NotNull
    public String username;

    @NotNull
    public String password;
}
