package com.email.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestObject {
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    @NotNull(message = "subject can not null")
    private String subject;
    private String message;
}
