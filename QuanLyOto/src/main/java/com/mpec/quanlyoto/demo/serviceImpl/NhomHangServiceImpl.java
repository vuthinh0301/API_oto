package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.dto.NhomHangDTO;
import com.mpec.quanlyoto.demo.entities.NhomHang;
import com.mpec.quanlyoto.demo.repository.NhomHangRepo;
import com.mpec.quanlyoto.demo.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhomHangServiceImpl implements NhomHangService {
    @Autowired
    NhomHangRepo nhomHangRepo;

    @Override
    public Page<NhomHang> findAll(Pageable pageable) {
        try{
            return nhomHangRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> findById(Integer id) {
        try{
            return nhomHangRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomHang> search(String maNhomHang, String tenNhomHang, Pageable pageable) {
        try {
            return nhomHangRepo.search(maNhomHang, tenNhomHang, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> save(NhomHangDTO nhomHang) {
        try{
            NhomHang nhomHang1 = new NhomHang();
            nhomHang1.setMaNhomHang(nhomHang.getMaNhomHang());
            nhomHang1.setTenNhomHang(nhomHang.getTenNhomHang());
            return Optional.ofNullable(nhomHangRepo.save(nhomHang1));
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<NhomHang> update(NhomHangDTO nhomHang) {
        try {
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(nhomHang.getId());
            if (!nhomHangOptional.isPresent()) {
                return Optional.empty();
            } else {
                NhomHang nhomHang1 = nhomHangOptional.get();

                if(nhomHang.getTenNhomHang() != null){
                    nhomHang1.setTenNhomHang(nhomHang.getTenNhomHang());
                }
                if(nhomHang.getMaNhomHang() != null){
                    nhomHang1.setMaNhomHang(nhomHang.getMaNhomHang());
                }
                return Optional.ofNullable(nhomHangRepo.save(nhomHang1));
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try{
            return nhomHangRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }
}
