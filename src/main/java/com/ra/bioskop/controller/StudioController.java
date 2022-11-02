package com.ra.bioskop.controller;

import com.ra.bioskop.dto.model.studio.StudioDTO;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.service.StudioService;
import com.ra.bioskop.util.BaseEndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Tag(name = "Studio")
@RestController
@RequestMapping(BaseEndPoint.STUDIO_V1_ENDPOINT)
public class StudioController {

    @Autowired
    private StudioService studioService;

    @Operation(summary = "Mengambil semua data studio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Tidak ditemukan.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @GetMapping
    public ResponseEntity<?> getStudios() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success", studioService.getAllStudio()));
        } catch (BioskopException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Menambahkan data-data studio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @PostMapping("/addAll")
    public ResponseEntity<?> addStudios(@RequestBody List<StudioDTO> studioDTOList) {
        studioService.addStudios(studioDTOList);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(), "success", null));
    }

}
