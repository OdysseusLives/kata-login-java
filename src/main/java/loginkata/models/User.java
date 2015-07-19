package loginkata.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    @Size(min=5, max=20)
    public String username;

    @NotNull
    @Size(min=8, max=20)
    public String password;
}
