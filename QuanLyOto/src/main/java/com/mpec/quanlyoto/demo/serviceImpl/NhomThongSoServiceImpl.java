package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO;
import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO_update;
import com.mpec.quanlyoto.demo.entities.HangHoa;
import com.mpec.quanlyoto.demo.entities.NhomThongSo;
import com.mpec.quanlyoto.demo.repository.HangHoaRepo;
import com.mpec.quanlyoto.demo.repository.NhomThongSoRepo;
import com.mpec.quanlyoto.demo.service.HangHoaService;
import com.mpec.quanlyoto.demo.service.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.Optional;

@Service
public class NhomThongSoServiceImpl implements NhomThongSoService {

    @Autowired
    NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    HangHoaService hangHoaService;

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Override
    public Page<NhomThongSo> findAll(Pageable pageable) {
        try{
            return nhomThongSoRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomThongSo> findById(Integer id) {
        try{
            return nhomThongSoRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomThongSo> findNhomThongSoByHangHoa(Integer hangHoaId, Pageable pageable) {
        try{
            return nhomThongSoRepo.findNhomThongSoByHangHoa(hangHoaId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<NhomThongSo> search(String text, Pageable pageable) {
        try{
            return nhomThongSoRepo.search(text, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomThongSo> save(NhomThongSoDTO nhomThongSo) {
        try{
            NhomThongSo nhomThongSo1 = new NhomThongSo();
            nhomThongSo1.setHangHoa((hangHoaService.findById(nhomThongSo.getHangHoaId())).get());
            nhomThongSo1.setTenNhomThongSo(nhomThongSo.getTenNhomThongSo());
            nhomThongSo1.setMaNhomThongSo(nhomThongSo.getMaNhomThongSo());
            nhomThongSo1.setXoa(false);

            return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSo1));
        }
        catch (Exception e){

            return Optional.empty();
        }
    }


    @Override
    public Optional<NhomThongSo> update(NhomThongSoDTO nhomThongSo) {
        try{
            Optional<NhomThongSo> nhomThongSoOptional= nhomThongSoRepo.findById(nhomThongSo.getId());
            if(!nhomThongSoOptional.isPresent()){
                return Optional.empty();
            }
            else {
                NhomThongSo nhomThongSo1 = nhomThongSoOptional.get();

                if(nhomThongSo.getHangHoaId() != null){
                    Optional<HangHoa> hangHoa = hangHoaRepo.findById(nhomThongSo.getHangHoaId());
                    nhomThongSo1.setHangHoa(hangHoa.get());
                }
                if(nhomThongSo.getTenNhomThongSo() != null){
                    nhomThongSo1.setTenNhomThongSo(nhomThongSo.getTenNhomThongSo());
                }
                if(nhomThongSo.getMaNhomThongSo() != null){
                    nhomThongSo1.setMaNhomThongSo(nhomThongSo.getMaNhomThongSo());
                }
                return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSo1));
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return nhomThongSoRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }
}
