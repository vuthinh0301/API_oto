package com.mpec.quanlyoto.demo.serviceImpl;

import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import com.mpec.quanlyoto.demo.repository.ThuongHieuRepo;
import com.mpec.quanlyoto.demo.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    ThuongHieuRepo thuongHieuRepo;

    @Override
    public Page<ThuongHieu> findAll(Pageable pageable) {
        try{
            return thuongHieuRepo.findAll(pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Page<ThuongHieu> search(String text, Pageable pageable) {
        try{
            return thuongHieuRepo.search(text,pageable);
        }
        catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> findById(int id) {
        try{
            return thuongHieuRepo.findById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> save(ThuongHieu thuongHieu) {
        try{
            return Optional.ofNullable(thuongHieuRepo.save(thuongHieu));
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> update(ThuongHieu thuongHieu) {
        try{
            Optional<ThuongHieu> thuongHieuOptional = findById(thuongHieu.getId());
            if(!thuongHieuOptional.isPresent()){
                return Optional.empty();
            }
            else{
                return Optional.ofNullable(thuongHieuRepo.save(thuongHieu));
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return thuongHieuRepo.delete(id) >= 0;
        }
        catch (Exception e){
            return false;
        }
    }



}
