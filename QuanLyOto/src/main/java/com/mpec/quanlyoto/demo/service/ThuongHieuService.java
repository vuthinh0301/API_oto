package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThuongHieuService {

    Page<ThuongHieu>findAll(Pageable pageable);

    Page<ThuongHieu> search(String text, Pageable pageable);

    Optional<ThuongHieu> findById(int id);

    Optional<ThuongHieu> save(ThuongHieu thuongHieu);

    Optional<ThuongHieu> update (ThuongHieu thuongHieu);

    Boolean delete(int id);

}
