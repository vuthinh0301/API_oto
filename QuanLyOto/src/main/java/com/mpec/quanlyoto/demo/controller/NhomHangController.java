package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.dto.NhomHangDTO;
import com.mpec.quanlyoto.demo.entities.NhomHang;
import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import com.mpec.quanlyoto.demo.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/nhom-hang")
public class NhomHangController {

    @Autowired
    NhomHangService nhomHangService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(){
        Pageable pageable = PageRequest.of(0,10);
        Page<NhomHang> nhomHangs = nhomHangService.findAll(pageable);
        return ResponseEntity.ok(nhomHangs);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<NhomHang> nhomHangOptional = nhomHangService.findById(id);
        if(nhomHangOptional.isPresent()){
            return ResponseEntity.ok(nhomHangOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NhomHangDTO nh){
        Optional<NhomHang> nhomHangOptional = nhomHangService.save(nh);
        if(nhomHangOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("fail");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody NhomHangDTO nh){
        Optional<NhomHang> nhomHang = nhomHangService.update(nh);
        if(nhomHang.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "maNhomHang", required = false, defaultValue = "") String maNhomHang,
                             @RequestParam (name = "tenNhomHang", required = false, defaultValue = "") String tenNhomHang,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<NhomHang> nhomHangs = nhomHangService.search(maNhomHang, tenNhomHang, pageable);
        return ResponseEntity.ok(nhomHangs);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable(name = "id") Integer id){
        return nhomHangService.delete(id)?ResponseEntity.ok("success"):ResponseEntity.ok("failed");
    }
}
