package com.common.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Data
public class DeleteBookRequestDto implements Serializable {
    private UUID id;
}
