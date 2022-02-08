package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO;
import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO_update;
import com.mpec.quanlyoto.demo.dto.ThongSoKiThuatDTO;
import com.mpec.quanlyoto.demo.entities.NhomThongSo;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import com.mpec.quanlyoto.demo.service.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thong-so-ki-thuat")
public class ThongSoKiThuatController {
    @Autowired
    ThongSoKiThuatService thongSoKiThuatService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.findAll(pageable);
        return ResponseEntity.ok(thongSoKiThuats);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatService.findById(id);
        if(thongSoKiThuatOptional.isPresent()){
            return ResponseEntity.ok(thongSoKiThuatOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @GetMapping("/find-thong-so-ki-thuat-by-nhom-thong-so-id")
    ResponseEntity<?> findThongSoKiThuatByNhomThongSo(@RequestParam(name = "nhomThongSoId", defaultValue = "1") Integer nhomThongSoId,
                                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.findThongSoKiThuatByNhomThongSo(nhomThongSoId,pageable);
        return ResponseEntity.ok(thongSoKiThuats);
    }

    @GetMapping("/find-thong-so-ki-thuat-by-nhom-hang-id")
    ResponseEntity<?> findThongSoKiThuatByNhomHang(@RequestParam(name = "nhomHangId", defaultValue = "1") Integer nhomHangId,
                                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.findThongSoKiThuatByNhomHang(nhomHangId,pageable);
        return ResponseEntity.ok(thongSoKiThuats);
    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.search(text,pageable);
        return ResponseEntity.ok(thongSoKiThuats);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThongSoKiThuatDTO tskt){
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatService.save(tskt);
        if(thongSoKiThuatOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody ThongSoKiThuatDTO tskt){
        Optional<ThongSoKiThuat> thongSoKiThuat = thongSoKiThuatService.update(tskt);
        if(thongSoKiThuat.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable(name = "id") Integer id){
        return thongSoKiThuatService.delete(id)?ResponseEntity.ok("success"):ResponseEntity.ok("failed");
    }
}
