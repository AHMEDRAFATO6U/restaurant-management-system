package com.boot_demo1.resturant.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefDTO {

    private Long id;

    @NotEmpty(message = "not_empty.name")
    @Size(min = 3, max = 50, message = "size.name")
    private String name;

    @NotEmpty(message = "not_empty.spec")
    private String spec;

    @NotEmpty(message = "not_empty.logoPath")
    private String logoPath;

    @Pattern(regexp = "^(https?://).*", message = "invalid_url.facebookLink")
    private String facebookLink;

    @Pattern(regexp = "^(https?://).*", message = "invalid_url.twitterLink")
    private String twitterLink;

    @Pattern(regexp = "^(https?://).*", message = "invalid_url.instagramLink")
    private String instagramLink;
}
