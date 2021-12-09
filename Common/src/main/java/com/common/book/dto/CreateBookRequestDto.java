package com.common.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class CreateBookRequestDto implements Serializable, IBookDto {
    @NotBlank
    @Pattern(regexp = "^(?!^\\s.*)(?!.*\\s$)[A-Za-z0-9 ]{1,30}$")
    private String title;
    @NotBlank
    @Pattern(regexp = "^(?!^\\s.*)(?!.*\\s$)[A-Za-z ]{1,20}$")
    private String author;
}
