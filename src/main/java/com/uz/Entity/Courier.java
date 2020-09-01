package com.uz.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Courier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "не оставлять места")
    @Column(nullable = false)
    private String courierName;

    @Email(message = "Email введена неправильно")
    @Column(nullable = false)
    private String mail;

    @Size(min = 3, max = 20, message = "Номер введена неправильно")
    @Column(nullable = false)
    private String telefonogramma;

//    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Document> documents;

}
