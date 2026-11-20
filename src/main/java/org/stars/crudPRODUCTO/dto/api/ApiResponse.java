package org.stars.crudPRODUCTO.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String mensaje;
    private T data;
    private LocalDateTime fechaRegistro;

    public static <T> ApiResponse<T> exito(T data, String mensaje) {
        return new ApiResponse<>(mensaje, data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(String mensaje) {
        return new ApiResponse<>(mensaje, null, LocalDateTime.now());
    }

    @JsonProperty("exito")
    public boolean esExito() {
        return this.data != null;
    }
}
