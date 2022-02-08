package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.dto.HangHoaDTO;
import com.mpec.quanlyoto.demo.dto.NhomHangDTO;
import com.mpec.quanlyoto.demo.entities.HangHoa;
import com.mpec.quanlyoto.demo.entities.NhomHang;
import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import com.mpec.quanlyoto.demo.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hang-hoa")
public class HangHoaController {

    @Autowired
    HangHoaService hangHoaService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<HangHoa> hangHoas = hangHoaService.findAll(pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<HangHoa> hangHoaOptional = hangHoaService.findById(id);
        if(hangHoaOptional.isPresent()){
            return ResponseEntity.ok(hangHoaOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @GetMapping("/find-hang-hoa-by-nhom-hang-id")
    ResponseEntity<?> findHangHoaByNhomHang(@RequestParam (name = "nhomHangId", defaultValue = "1") Integer nhomHangId,
                                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HangHoa> hangHoas = hangHoaService.findHangHoaByNhomHang(nhomHangId,pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HangHoa> hangHoas = hangHoaService.search(text,pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HangHoa hh){
        Optional<HangHoa> hangHoaOptional = hangHoaService.save(hh);
        if(hangHoaOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody HangHoaDTO hh){
        Optional<HangHoa> hangHoa = hangHoaService.update(hh);
        if(hangHoa.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }
}
