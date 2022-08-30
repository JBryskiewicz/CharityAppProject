package pl.coderslab.charity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer quantity;
    private String street;
    private String city;
    private String zipcode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    private Institution institution;
}
