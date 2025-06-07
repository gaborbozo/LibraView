package hu.bozgab.cinematic.domain;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "USER_CINEMATIC")
@IdClass(UserCinematicId.class)
@EqualsAndHashCode(exclude = { "user", "cinematic", "userCinematicGroup" })
@ToString(exclude = { "user", "cinematic", "userCinematicGroup" })
public class UserCinematic {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "CINEMATIC_ID")
    private Long cinematicId;

    @Column(name = "INDEX")
    private Double index;

    @NotNull
    @Column(name = "ADDED_AT", updatable = false)
    private Timestamp addedAt;

    @Column(name = "WATCHED_AT")
    private Timestamp watched;

    @Column(name = "COMMENT")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CINEMATIC_ID")
    @MapsId
    private Cinematic cinematic;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "CINEMATIC_GROUP_ID")
    private UserCinematicGroup userCinematicGroup;

    @PrePersist
    protected void onPrePersist() {
        this.addedAt = new Timestamp(System.currentTimeMillis());
    }

}
