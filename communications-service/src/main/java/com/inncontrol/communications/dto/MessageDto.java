package com.inncontrol.communications.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDto {

    @NotBlank
    @Size(max = 10)
    private Long sender;

    @NotBlank
    @Size(max = 10)
    private Long receiver;

    @NotBlank
    @Size(max = 20)
    private String status;

    @NotBlank
    @Size(max = 200)
    private String content;
}
