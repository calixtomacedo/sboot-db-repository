package br.com.cmdev.data.jpa.dto;

public record AddressResponse(
        Long addressId,
        String streat,
        String number,
        String neighborhood,
        String city,
        String state

) {
}
