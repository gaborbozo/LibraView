package hu.bozgab.libraview.cineregistry.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("GENRE")
public class Genre {

    @Id
    @Column("ID")
    private Long id;

    @Column("REFERENCE_ID")
    private Long referenceId;

    @Column("NAME")
    private String name;

}