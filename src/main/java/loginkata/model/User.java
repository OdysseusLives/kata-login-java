package loginkata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @NotNull
    @Size(min=5, max=20)
    @Pattern(regexp = "^\\w*$", message = "must not contain special characters")
    public String username;

    @NotNull
    @Size(min=8, max=20)
    @Pattern.List({
        @Pattern(regexp = "^(?=.*[0-9]).*$", message = "must contain one digit"),
        @Pattern(regexp = "^(?=.*[a-z]).*$", message = "must contain one lowercase letter"),
        @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "must contain one uppercase letter"),
    })
    public String password;
}
