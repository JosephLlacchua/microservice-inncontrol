package com.inncontrol.accommodation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Sharon Antuanet Ivet Barrial Marin - u202114900
 * @version 1.0
 */

@Setter
@Getter
public class RoomDTO {

    @NotBlank
    @Size(min = 1, max = 50)
    private int roomNumber;

    @NotBlank
    @Size(min = 1, max = 50)
    private String roomType;

    @NotBlank
    @Size(min = 1, max = 50)
    private String roomStatus;
}
