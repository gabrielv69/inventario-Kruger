package com.kruger.inventario.REST;

import com.kruger.inventario.models.Person;
import com.kruger.inventario.models.Record;
import com.kruger.inventario.models.User;
import com.kruger.inventario.service.RecordService;
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
@RequestMapping("/record/")
public class RecordREST {
    @Autowired
    private RecordService recordService;

    @GetMapping
    @Operation(summary = "Obtener lista de record")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Lista devuelta con éxito",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Lista no encontrada", content = @Content)})
    private ResponseEntity<List<Record>> getAllRecord(){
        return ResponseEntity.ok(recordService.findAll());
    }

    @DeleteMapping(value= "delete/{id}")
    @Operation(summary = "Eliminar record")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Record eliminado con éxito",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Registro de record no encontrado", content = @Content)})
    private ResponseEntity<Boolean> deleteRecord(@PathVariable("id") Long idRecord){
        recordService.deleteById(idRecord);
        return ResponseEntity.ok(!recordService.findById(idRecord).isPresent());
    }

    @PostMapping
    @Operation(summary = "Guardar información adicional de un  nuevo empleado")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Record guardado",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "400", description = "Objeto record inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Record no encontrado", content = @Content)})
    private ResponseEntity<Record> saveRecord( @RequestBody Record record){
        try {
            Record newRecord = recordService.save(record);
            return ResponseEntity.created(new URI("/record/"+newRecord.getIdRecord())).body(record);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
