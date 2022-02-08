package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.dto.ThongSoChiTietDTO;
import com.mpec.quanlyoto.demo.dto.ThongSoKiThuatDTO;
import com.mpec.quanlyoto.demo.entities.ThongSoChiTiet;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import com.mpec.quanlyoto.demo.service.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thong-so-chi-tiet")
public class ThongSoChiTietController {

    @Autowired
    ThongSoChiTietService thongSoChiTietService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<ThongSoChiTiet> thongSoChiTiets = thongSoChiTietService.findAll(pageable);
        return ResponseEntity.ok(thongSoChiTiets);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietService.findById(id);
        if(thongSoChiTietOptional.isPresent()){
            return ResponseEntity.ok(thongSoChiTietOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @GetMapping("/find-thong-so-chi-tiet-by-thong-so-ki-thuat-id")
    ResponseEntity<?> findThongSoChiTietByThongSoKiThuat(@RequestParam(name = "thongSoKiThuatID", defaultValue = "1") Integer thongSoKiThuatID,
                                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ThongSoChiTiet> thongSoChiTiets = thongSoChiTietService.findThongSoChiTietByThongSoKiThuat(thongSoKiThuatID,pageable);
        return ResponseEntity.ok(thongSoChiTiets);
    }


    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ThongSoChiTiet> thongSoChiTiets = thongSoChiTietService.search(text,pageable);
        return ResponseEntity.ok(thongSoChiTiets);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThongSoChiTietDTO tsct){
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietService.save(tsct);
        if(thongSoChiTietOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody ThongSoChiTietDTO tsct){
        Optional<ThongSoChiTiet> thongSoChiTiet = thongSoChiTietService.update(tsct);
        if(thongSoChiTiet.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable(name = "id") Integer id){
        return thongSoChiTietService.delete(id)?ResponseEntity.ok("success"):ResponseEntity.ok("failed");
    }
}

