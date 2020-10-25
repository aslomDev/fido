package com.uz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uz.Entity.Enum.KorrespondendEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "не оставлять места")
    @Size(min = 3, max = 50, message = "название принимается от 3 до 50")
    @Pattern(regexp = "[a-zA-z.@#$%^&*?().,:;'!]+", flags = Pattern.Flag.CASE_INSENSITIVE, message = "номер не может быть введен")
    @Column(nullable = false)
    private String documentName;


    private Date regDate;


    @NotEmpty(message = "не оставлять места")
    @Size(min = 3, max = 50, message = "название принимается от 3 до 50")
    @Pattern(regexp = "[a-zA-z.@#$%^&*?().,:;'!]+", flags = Pattern.Flag.CASE_INSENSITIVE, message = "номер не может быть введен")
    private String isxDocument;

    @FutureOrPresent(message = "не может быть раны даты регистрации документа")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate isxDocument_date;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korres_id")
    private Korres korres;



//    @ManyToMany
//    @JoinTable(name ="korrespondend", joinColumns = {@JoinColumn(name = "document_id")},
//            inverseJoinColumns = {@JoinColumn(name = "korrespondend_id")})
//    private List<KorrespondendRepository> korrespondends;

    @NotEmpty(message = "не оставлять места")
    @Size(min = 3, max = 100, message = "название принимается от 3 до 100")
    @Pattern(regexp = "[a-zA-z.@#$%^&*?().,:;'!]+", flags = Pattern.Flag.CASE_INSENSITIVE, message = "номер не может быть введен")
    @Column(nullable = false)
    private String topic;


    @NotEmpty(message = "не оставлять места")
    @Size(min = 3, max = 150, message = "название принимается от 3 до 1000")
    @Pattern(regexp = "[a-zA-z.%a.%s]+", flags = Pattern.Flag.CASE_INSENSITIVE, message = "номер не может быть введен")
    @Column(nullable = false)
    private String description;

    @FutureOrPresent(message = "срок исполнения не может быть раны даты регистрации документа")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate srok_Date_doc;

    @OneToOne(fetch = FetchType.LAZY)
        private FileUpload fileUpload;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private DocumentAttachment documentAttachment;
//
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private DocumentAttachmentContent documentAttachmentContent;

//    @Column()
//    private String downloadUri;
}
