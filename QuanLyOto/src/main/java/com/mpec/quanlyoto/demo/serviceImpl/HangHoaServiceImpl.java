package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.dto.HangHoaDTO;
import com.mpec.quanlyoto.demo.entities.HangHoa;
import com.mpec.quanlyoto.demo.repository.HangHoaRepo;
import com.mpec.quanlyoto.demo.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class HangHoaServiceImpl implements HangHoaService {

    @Autowired
    HangHoaRepo hangHoaRepo;
    @Override
    public Page<HangHoa> findAll(Pageable pageable) {
        try{
            return hangHoaRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoa> findById(Integer id) {
        try{
            return hangHoaRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<HangHoa> findHangHoaByNhomHang(Integer nhomHangId, Pageable pageable) {
        try{
            return hangHoaRepo.findHangHoaByNhomHang(nhomHangId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }

    }

    @Override
    public Page<HangHoa> search(String text, Pageable pageable) {
        try{
            return hangHoaRepo.search(text, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return hangHoaRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Optional<HangHoa> save(HangHoa hangHoa) {
        try{
            return Optional.ofNullable(hangHoaRepo.save(hangHoa));
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<HangHoa> update(HangHoaDTO hangHoa) {
        try {
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoa.getId());
            if (!hangHoaOptional.isPresent()) {
                return Optional.empty();
            } else {
                HangHoa hangHoa1 = hangHoaOptional.get();

                if(hangHoa.getMaGiamGia() != null){
                    hangHoa1.setMaGiamGia(hangHoa.getMaGiamGia());
                }
                if(hangHoa.getMoTa() != null){
                    hangHoa1.setMoTa(hangHoa.getMoTa());
                }
                if(hangHoa.getPhanTramGiamGia() != null){
                    hangHoa1.setPhanTramGiamGia(hangHoa.getPhanTramGiamGia());
                }
                if(hangHoa.getUrlHinhAnh1() != null){
                    hangHoa1.setUrlHinhAnh1(hangHoa.getUrlHinhAnh1());
                }
                return Optional.ofNullable(hangHoaRepo.save(hangHoa1));

            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
}
