package br.com.cmdev.data.jpa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressRequest(

        @NotNull @Size(min = 3, max = 100)
        String streat,

        @NotNull @Size(min = 1, max = 10)
        String number,

        @NotNull @Size(min = 3, max = 100)
        String neighborhood,

        @NotNull @Size(min = 3, max = 100)
        String city,

        @NotNull @Size(min = 3, max = 100)
        String state

) {
}
