package com.uz.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Courier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "не оставлять места")
    @Pattern(regexp = "^[0-9][а-яА-ЯёЁa-zA-Z0-9]+$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Номер не может состоять только из букв или специальных символов")
    @Column(nullable = false)
    private String courierName;

    @Email(message = "Email введена неправильно")
    @Column(nullable = false)
    private String mail;

    @Size(min = 3, max = 20, message = "Номер введена неправильно")
    @Column(nullable = false)
    private String telefonogramma;




}
