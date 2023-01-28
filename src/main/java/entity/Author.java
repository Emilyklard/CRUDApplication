package main.java.entity;

import lombok.*;

import java.util.Objects;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Data
public class Author {
    private Integer id;
    private String first_name;
    private String last_name;
}
