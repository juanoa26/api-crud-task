package com.taskcrud.Api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskcrud.Api.Model.Task;
import com.taskcrud.Api.Repository.TaskRepository;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskRepository tareaRepository;

    @GetMapping
    public List<Task> listarTarea() {
        return tareaRepository.findAll();
    }

    @PostMapping
    public Task crearTarea(@RequestBody Task tarea){
        return tareaRepository.save(tarea);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> obtenerTarea(@PathVariable Long id){
        return tareaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> actualizarTarea(@PathVariable Long id, @RequestBody Task tareaActualizada){
        return tareaRepository.findById(id)
        .map(tarea -> {
            tarea.setTitulo(tareaActualizada.getTitulo());
            tarea.setCompletado(tareaActualizada.isCompletado());
            return ResponseEntity.ok(tareaRepository.save(tarea));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
