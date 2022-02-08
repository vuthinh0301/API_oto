package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.dto.HangHoaThongSoDTO;
import com.mpec.quanlyoto.demo.entities.HangHoaThongSo;
import com.mpec.quanlyoto.demo.repository.HangHoaRepo;
import com.mpec.quanlyoto.demo.repository.HangHoaThongSoRepo;
import com.mpec.quanlyoto.demo.repository.ThongSoChiTietRepo;
import com.mpec.quanlyoto.demo.repository.ThongSoKiThuatRepo;
import com.mpec.quanlyoto.demo.service.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HangHoaThongSoServiceImpl implements HangHoaThongSoService {

    @Autowired
    HangHoaThongSoRepo hangHoaThongSoRepo;

    @Autowired
    ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    ThongSoChiTietRepo thongSoChiTietRepo;

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Override
    public Page<HangHoaThongSo> findAll(Pageable pageable) {
        try{
            return hangHoaThongSoRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoaThongSo> findById(Integer id) {
        try{
            return hangHoaThongSoRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> findHangHoaThongSoByHangHoa(Integer hangHoaId, Pageable pageable) {
        try{
            return hangHoaThongSoRepo.findHangHoaThongSoByHangHoa(hangHoaId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> findHangHoaThongSoByThongSoKiThuat(Integer thongSoKiThuatId, Pageable pageable) {
        try{
            return hangHoaThongSoRepo.findHangHoaThongSoByThongSoKiThuat(thongSoKiThuatId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> findHangHoaThongSoByThongSoChiTiet(Integer thongSoChiTietId, Pageable pageable) {
        try{
            return hangHoaThongSoRepo.findHangHoaThongSoByThongSoChiTiet(thongSoChiTietId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> search(String text, Pageable pageable) {
        try{
            return hangHoaThongSoRepo.search(text, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoaThongSo> save(HangHoaThongSoDTO hangHoaThongSo) {
        try{
            HangHoaThongSo hangHoaThongSo1 = new HangHoaThongSo();
            hangHoaThongSo1.setThongSoKiThuat((thongSoKiThuatRepo.findById(hangHoaThongSo.getThongSoKiThuatId())).get());
            hangHoaThongSo1.setHangHoa((hangHoaRepo.findById(hangHoaThongSo.getHangHoaId())).get());
            hangHoaThongSo1.setThongSoChiTiet((thongSoChiTietRepo.findById(hangHoaThongSo.getThongSoChiTietId())).get());
            hangHoaThongSo1.setGia_tri(hangHoaThongSo.getGiaTri());
            hangHoaThongSo1.setXoa(false);
            return Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSo1));
        }
        catch (Exception e){

            return Optional.empty();
        }
    }

    @Override
    public Optional<HangHoaThongSo> update(HangHoaThongSoDTO hangHoaThongSo) {
        try{
            Optional<HangHoaThongSo> hangHoaThongSoOptional= hangHoaThongSoRepo.findById(hangHoaThongSo.getId());
            if(!hangHoaThongSoOptional.isPresent()){
                return Optional.empty();
            }
            else {
                HangHoaThongSo hangHoaThongSo1 = hangHoaThongSoOptional.get();

                if(hangHoaThongSo.getGiaTri() != null){
                    hangHoaThongSo1.setGia_tri(hangHoaThongSo.getGiaTri());
                }

                return Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSo1));
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return hangHoaThongSoRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }
}
