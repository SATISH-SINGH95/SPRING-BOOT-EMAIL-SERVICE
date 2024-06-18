package com.email.model;


import com.email.annotation.EmailAnnotation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestObject{


    @NotNull(message = "to should not be null")
    @Size(min = 1, max = 150, message = "to should have between 1 and 150 recipients")
    @EmailAnnotation
    private String[] to;

    @NotNull(message = "subject should not be null")
    @NotBlank(message = "subject should not be blank")
    @Size(min = 3, max = 126, message = "subject character should be between 3 to 126")
    private String subject;

    @NotNull(message = "message should not be null")
    @NotBlank(message = "message should not be blank")
    private String message;



}
