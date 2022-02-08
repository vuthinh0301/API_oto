package com.mpec.quanlyoto.demo.controller;

import com.mpec.quanlyoto.demo.dto.HangHoaThongSoDTO;
import com.mpec.quanlyoto.demo.dto.ThongSoKiThuatDTO;
import com.mpec.quanlyoto.demo.entities.HangHoaThongSo;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import com.mpec.quanlyoto.demo.service.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/hang-hoa-thong-so")
public class HangHoaThongSoController {
    @Autowired
    HangHoaThongSoService hangHoaThongSoService;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findAll(pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoService.findById(id);
        if(hangHoaThongSoOptional.isPresent()){
            return ResponseEntity.ok(hangHoaThongSoOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @GetMapping("/find-hang-hoa-thong-so-by-thong-so-ki-thuat-id")
    ResponseEntity<?> findHangHoaThongSoByThongSoKiThuat (@RequestParam(name = "thongSoKiThuatId", defaultValue = "1") Integer thongSoKiThuatId,
                                                      @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findHangHoaThongSoByThongSoKiThuat(thongSoKiThuatId,pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find-hang-hoa-thong-so-by-thong-so-chi-tiet-id")
    ResponseEntity<?> findHangHoaThongSoByThongSoChiTiet(@RequestParam(name = "thongSoChiTietId", defaultValue = "1") Integer thongSoChiTietId,
                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findHangHoaThongSoByThongSoChiTiet(thongSoChiTietId,pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find-hang-hoa-thong-so-by-hang-hoa-id")
    ResponseEntity<?> findHangHoaThongSoByHangHoa(@RequestParam(name = "hangHoaId", defaultValue = "1") Integer hangHoaId,
                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findHangHoaThongSoByHangHoa(hangHoaId,pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }


    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.search(text,pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HangHoaThongSoDTO hhts){
        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoService.save(hhts);
        if(hangHoaThongSoOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody HangHoaThongSoDTO hhts){
        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoService.update(hhts);
        if(hangHoaThongSoOptional.isPresent()){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable(name = "id") Integer id){
        return hangHoaThongSoService.delete(id)?ResponseEntity.ok("success"):ResponseEntity.ok("failed");
    }
}
