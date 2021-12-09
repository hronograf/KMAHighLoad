package com.common.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class DeleteBookRequestDto implements Serializable {
    private UUID id;
}
