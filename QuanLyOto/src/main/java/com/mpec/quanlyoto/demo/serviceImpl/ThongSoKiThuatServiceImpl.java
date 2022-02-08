package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.dto.ThongSoKiThuatDTO;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import com.mpec.quanlyoto.demo.repository.NhomHangRepo;
import com.mpec.quanlyoto.demo.repository.NhomThongSoRepo;
import com.mpec.quanlyoto.demo.repository.ThongSoKiThuatRepo;
import com.mpec.quanlyoto.demo.service.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThongSoKiThuatServiceImpl implements ThongSoKiThuatService {

    @Autowired
    ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    NhomHangRepo nhomHangRepo;

    @Override
    public Page<ThongSoKiThuat> findAll(Pageable pageable) {
        try{
            return thongSoKiThuatRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoKiThuat> findById(Integer id) {
        try{
            return thongSoKiThuatRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<ThongSoKiThuat> findThongSoKiThuatByNhomThongSo(Integer nhomThongSoId, Pageable pageable) {
        try{
            return thongSoKiThuatRepo.findThongSoKiThuatByNhomThongSo(nhomThongSoId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<ThongSoKiThuat> findThongSoKiThuatByNhomHang(Integer nhomHangId, Pageable pageable) {
        try{
        return thongSoKiThuatRepo.findThongSoKiThuatByNhomHang(nhomHangId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<ThongSoKiThuat> search(String text, Pageable pageable) {
        try{
            return thongSoKiThuatRepo.search(text, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoKiThuat> save(ThongSoKiThuatDTO thongSoKiThuat) {
        try{
            ThongSoKiThuat thongSoKiThuat1 = new ThongSoKiThuat();
            thongSoKiThuat1.setNhomThongSo((nhomThongSoRepo.findById(thongSoKiThuat.getNhomThongSoId())).get());
            thongSoKiThuat1.setNhomHang((nhomHangRepo.findById(thongSoKiThuat.getNhomHangId())).get());
            thongSoKiThuat1.setTenThongSo(thongSoKiThuat.getTenThongSo());
            thongSoKiThuat1.setXoa(false);
            return Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuat1));
        }
        catch (Exception e){

            return Optional.empty();
        }
    }

    @Override
    public Optional<ThongSoKiThuat> update(ThongSoKiThuatDTO thongSoKiThuat) {
        try{
            Optional<ThongSoKiThuat> thongSoKiThuatOptional= thongSoKiThuatRepo.findById(thongSoKiThuat.getId());
            if(!thongSoKiThuatOptional.isPresent()){
                return Optional.empty();
            }
            else {
                ThongSoKiThuat thongSoKiThuat1 = thongSoKiThuatOptional.get();

                if(thongSoKiThuat.getTenThongSo() != null){
                    thongSoKiThuat1.setTenThongSo(thongSoKiThuat.getTenThongSo());
                }

                return Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuat1));
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return thongSoKiThuatRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }
}
