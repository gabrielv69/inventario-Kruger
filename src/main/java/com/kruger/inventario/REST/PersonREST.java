package com.kruger.inventario.REST;
import com.kruger.inventario.models.Person;
import com.kruger.inventario.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/person/")
public class PersonREST {
    @Autowired
    private PersonService personService;

    @GetMapping
    @Operation(summary = "Obtener lista de empleados")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Lista devuelta con éxito",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Lista no encontrada", content = @Content)})
    private ResponseEntity<List<Person>> getAllEmployers(){
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping
    @Operation(summary = "Guardar información basica de un  nuevo empleado")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Empleado guardado",
            content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "400", description = "Objeto persona inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)})
    private ResponseEntity<Person> saveEmployer( @RequestBody Person person){
        try {
            Person newEmployer = personService.save(person);
            return ResponseEntity.created(new URI("/person/"+newEmployer.getIdPerson())).body(person);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping (value= "delete/{id}")
    @Operation(summary = "Eliminar empleado")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Empleado eliminado con éxito",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Registro de empleado no encontrado", content = @Content)})
    private ResponseEntity<Boolean> deleteEmployer(@PathVariable("id") Long idPersona){
        personService.deleteById(idPersona);
        return ResponseEntity.ok(!personService.findById(idPersona).isPresent());
    }
}
