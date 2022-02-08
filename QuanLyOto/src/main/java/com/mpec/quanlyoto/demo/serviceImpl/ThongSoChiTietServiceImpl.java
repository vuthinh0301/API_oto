package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.dto.ThongSoChiTietDTO;
import com.mpec.quanlyoto.demo.entities.ThongSoChiTiet;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import com.mpec.quanlyoto.demo.repository.ThongSoChiTietRepo;
import com.mpec.quanlyoto.demo.repository.ThongSoKiThuatRepo;
import com.mpec.quanlyoto.demo.service.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThongSoChiTietServiceImpl implements ThongSoChiTietService {
    @Autowired
    ThongSoChiTietRepo thongSoChiTietRepo;

    @Autowired
    ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Override
    public Page<ThongSoChiTiet> findAll(Pageable pageable) {
        try{
            return thongSoChiTietRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoChiTiet> findById(Integer id) {
        try{
            return thongSoChiTietRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<ThongSoChiTiet> findThongSoChiTietByThongSoKiThuat(Integer thongSoKiThuatId, Pageable pageable) {
        try{
            return thongSoChiTietRepo.findThongSoChiTietByThongSoKiThuat(thongSoKiThuatId, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<ThongSoChiTiet> search(String text, Pageable pageable) {
        try{
            return thongSoChiTietRepo.search(text, pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoChiTiet> save(ThongSoChiTietDTO thongSoChiTiet) {
        try{
            ThongSoChiTiet thongSoChiTiet1 = new ThongSoChiTiet();
            thongSoChiTiet1.setThongSoKiThuat((thongSoKiThuatRepo.findById(thongSoChiTiet.getThongSoKiThuatId())).get());
            thongSoChiTiet1.setTen(thongSoChiTiet.getTen());
            thongSoChiTiet1.setXoa(false);
            return Optional.ofNullable(thongSoChiTietRepo.save(thongSoChiTiet1));
        }
        catch (Exception e){

            return Optional.empty();
        }
    }

    @Override
    public Optional<ThongSoChiTiet> update(ThongSoChiTietDTO thongSoChiTiet) {
        try{
            Optional<ThongSoChiTiet> thongSoChiTietOptional= thongSoChiTietRepo.findById(thongSoChiTiet.getId());
            if(!thongSoChiTietOptional.isPresent()){
                return Optional.empty();
            }
            else {
                ThongSoChiTiet thongSoChiTiet1 = thongSoChiTietOptional.get();

                if(thongSoChiTiet.getTen() != null){
                    thongSoChiTiet1.setTen(thongSoChiTiet.getTen());
                }

                return Optional.ofNullable(thongSoChiTietRepo.save(thongSoChiTiet1));
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return thongSoChiTietRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }
}
