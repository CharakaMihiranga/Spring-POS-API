package lk.ijse.gdse.springboot.springposapi.response.impl;

import lk.ijse.gdse.springboot.springposapi.response.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemErrorResponse implements ItemResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
