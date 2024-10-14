package model;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}
