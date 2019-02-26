package pl.mps.quotes.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue
    private Long id;

    private String author;

    private String content;

    private LocalDateTime created;

    private LocalDateTime modified;

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }
}
