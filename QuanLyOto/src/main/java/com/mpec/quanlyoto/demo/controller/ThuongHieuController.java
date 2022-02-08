package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import com.mpec.quanlyoto.demo.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thuong-hieu")
public class ThuongHieuController {

    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAllThuongHieu(){
        Pageable pageable = PageRequest.of(0,10);
        Page<ThuongHieu> thuongHieus = thuongHieuService.findAll(pageable);
        return ResponseEntity.ok(thuongHieus);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.findById(id);
        if(thuongHieuOptional.isPresent()){
            return ResponseEntity.ok(thuongHieuOptional.get());
        }
        else {
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThuongHieu th){
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.save(th);
        if(thuongHieuOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ThuongHieu> thuongHieus = thuongHieuService.search(text,pageable);
        return ResponseEntity.ok(thuongHieus);
    }

    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestBody ThuongHieu th){
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.update(th);
        if(thuongHieuOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("fail");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable(name = "id") Integer id){
        return thuongHieuService.delete(id)?ResponseEntity.ok("success"):ResponseEntity.ok("failed");
    }
}
