package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.dto.HangHoaDTO;
import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO;
import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO_update;
import com.mpec.quanlyoto.demo.entities.HangHoa;
import com.mpec.quanlyoto.demo.entities.NhomThongSo;
import com.mpec.quanlyoto.demo.service.HangHoaService;
import com.mpec.quanlyoto.demo.service.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/nhom-thong-so")
public class NhomThongSoController {
    @Autowired
    NhomThongSoService nhomThongSoService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<NhomThongSo> nhomThongSos = nhomThongSoService.findAll(pageable);
        return ResponseEntity.ok(nhomThongSos);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoService.findById(id);
        if(nhomThongSoOptional.isPresent()){
            return ResponseEntity.ok(nhomThongSoOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @GetMapping("/find-nhom-thong-so-by-hang-hoa-id")
    ResponseEntity<?> findHangHoaByNhomHang(@RequestParam(name = "hangHoaId", defaultValue = "1") Integer hangHoaId,
                                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<NhomThongSo> nhomThongSos = nhomThongSoService.findNhomThongSoByHangHoa(hangHoaId,pageable);
        return ResponseEntity.ok(nhomThongSos);
    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<NhomThongSo> nhomThongSos = nhomThongSoService.search(text,pageable);
        return ResponseEntity.ok(nhomThongSos);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NhomThongSoDTO nts){
        Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoService.save(nts);
        if(nhomThongSoOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody NhomThongSoDTO nts){
        Optional<NhomThongSo> nhomThongSo = nhomThongSoService.update(nts);
        if(nhomThongSo.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable(name = "id") Integer id){
        return nhomThongSoService.delete(id)?ResponseEntity.ok("success"):ResponseEntity.ok("failed");
    }
}
