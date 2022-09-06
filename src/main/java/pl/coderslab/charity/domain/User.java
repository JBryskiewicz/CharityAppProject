package pl.coderslab.charity.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Size(max = 40)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String userName;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

}
