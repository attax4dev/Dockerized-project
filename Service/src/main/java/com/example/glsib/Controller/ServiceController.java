package com.example.glsib.Controller;

import com.example.glsib.Entite.Comment;
import com.example.glsib.Entite.Service;

import com.example.glsib.Service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/api/service")
@RestController
public class ServiceController {

    @Autowired
    ServiceService service;



    @GetMapping("/list-Service")
   // @PreAuthorize("hasRole('ADMIN')")
    public List<Service> ListService() {return service.gettAllService();}

    @DeleteMapping("/delete-Service/{idService}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteService(@PathVariable("idLevel") Long idService) {
        service.deleteService(idService);
    }

    /*  @ApiResponse(Value = {
            @ApiResponse(responseCode = "200", description ="Found the comm",
                    content ={ @Content(mediaType="application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400°", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "comm not found",
                    content =@Content) })

 */
    @PostMapping("/add-Service")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add service")
        public Service addService(@RequestBody @Validated Service s1) {
        return service.addService(s1);
    }


}
