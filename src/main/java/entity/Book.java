package main.java.entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Book {
    private Long id;
    private String name;
    private Short year;
    private Short pages;
    private Integer author_id;


}
