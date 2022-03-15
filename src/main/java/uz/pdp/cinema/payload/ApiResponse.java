package uz.pdp.cinema.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Asilbek Fayzullayev 15.03.2022 9:22
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {

    private String massage;

    private boolean isSuccess;

    private Object data;

    public ApiResponse(String message, boolean status) {
        this.massage = message;
        this.isSuccess = status;
    }
}
