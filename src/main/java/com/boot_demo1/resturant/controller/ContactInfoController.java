package com.boot_demo1.resturant.controller;

import com.boot_demo1.resturant.dto.ContactInfoDto;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.service.ContactInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/contact-info")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class ContactInfoController {

    private final ContactInfoService contactInfoService;


    @Operation(
            summary = "Save contact Info "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "contact-info saved  successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "contact-info not found",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ContactInfoDto> addContactInfo(@Valid @RequestBody ContactInfoDto contactInfoDto) {
        ContactInfoDto savedContactInfoDto = contactInfoService.saveContactinfo(contactInfoDto);
        return new ResponseEntity<>(savedContactInfoDto, HttpStatus.CREATED);

    }


    @Operation(
            summary = "update contactInfo "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "contact-info updated  successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "contact-info not found",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ContactInfoDto> updateContactInfo(@PathVariable Long id , @Valid @RequestBody ContactInfoDto contactInfoDto) {
        ContactInfoDto updatedContactInfo = contactInfoService.updateContactInfo(contactInfoDto ,id);
        return new ResponseEntity<>(updatedContactInfo, HttpStatus.OK);
    }


    @Operation(
            summary = "Get All ContactInfo "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "contact-info get   successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "contact-info not found",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<ContactInfoDto>> allContactInfo() {
        List<ContactInfoDto> contactInfoDtos = contactInfoService.getAllContactInfo();
        return new ResponseEntity<>(contactInfoDtos, HttpStatus.OK);
    }


    @Operation(
            summary = "Get ContactInfo by ContactInfo ID "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "contact-info get successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "contact-info not found",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ContactInfoDto> allContactInfoById(@PathVariable Long id) {
        ContactInfoDto contactInfoDto = contactInfoService.getContactInfoById(id);
        return new ResponseEntity<>(contactInfoDto, HttpStatus.OK);
    }


    @Operation(
            summary = "delete ContactInfo by ContactInfo ID "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "contact-info deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "contact-info not found",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Map<String , Object>> deleteContactInfoById(@PathVariable Long id) {

        ContactInfoDto contactInfoDto = contactInfoService.getContactInfoById(id);

        Map<String , Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("data", contactInfoDto);
        map.put("DeletedId", id);
        map.put("DeletedDate", new Date());

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

}
