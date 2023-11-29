package com.seneau.communs.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public abstract class GenericController<T extends GenericEntity<T>, D, R> {

    private final GenericService<T, D, R> service;
    protected Class<T> TClass;
    protected Class<D> DClass;

    public GenericController(GenericRepository<T> repository, Class<T> TClass, Class<D> DClass) {
        this.service = new GenericService<T, D, R>(repository, TClass, DClass) {};
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(service.getPage(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
