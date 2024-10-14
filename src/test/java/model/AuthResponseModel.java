package model;

import lombok.Data;

@Data
public class AuthResponseModel {
    String userId,
            username,
            password,
            token,
            expires,
            created_date;
    Boolean isActive;

}
