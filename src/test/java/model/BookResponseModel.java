package model;

import lombok.Data;

@Data
public class BookResponseModel {
    String isbn,
            title,
            subTitle,
            author,
            publish_date,
            publisher,
            description,
            website;
    Integer pages;

}
