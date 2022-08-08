package com.kruger.inventario.REST;
import com.kruger.inventario.models.Person;
import com.kruger.inventario.models.User;
import com.kruger.inventario.service.UserService;
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
@RequestMapping("/user/")
public class UserREST {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Obtener lista de usuarios")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Lista devuelta con éxito",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Lista no encontrada", content = @Content)})
    private ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping(value= "delete/{id}")
    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Usuario eliminado con éxito",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Registro de usuario no encontrado", content = @Content)})
    private ResponseEntity<Boolean> deleteEmployer(@PathVariable("id") Long idUser){
        userService.deleteById(idUser);
        return ResponseEntity.ok(!userService.findById(idUser).isPresent());
    }

    @PostMapping
    @Operation(summary = "Creacion de usuario y password")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Datos usuario guardado",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "400", description = "Objeto usuario inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)})
  private ResponseEntity<User> saveUser(Person person){
        User usuario= new User();
        char name= person.getNames().charAt(0);//Tomamos letra del primer nombre
        String surname = person.getSurnames().substring(1, person.getNames().indexOf("\\s+"));//Tomamos primer apellido
        usuario.setUsername(name+surname);
        usuario.setPassword("123456"); //Cuando se crea el usuario la password será default pa todos
        usuario.setRol(false); //Todos al crear son rol empleado
        usuario.setIdUser(person.getIdPerson());
      try {
          User newUser= userService.save(usuario);
          return ResponseEntity.created(new URI("/user/"+newUser.getIdUser())).body(usuario);
      }catch (Exception e){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
  }

}
