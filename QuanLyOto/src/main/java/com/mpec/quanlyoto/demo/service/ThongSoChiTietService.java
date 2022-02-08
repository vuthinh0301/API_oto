package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.dto.ThongSoChiTietDTO;
import com.mpec.quanlyoto.demo.entities.ThongSoChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThongSoChiTietService {

    Page<ThongSoChiTiet> findAll(Pageable pageable);

    Optional<ThongSoChiTiet> findById(Integer id);

    Page<ThongSoChiTiet> findThongSoChiTietByThongSoKiThuat(Integer thongSoKiThuatId, Pageable pageable);

    Page<ThongSoChiTiet> search(String text, Pageable pageable);

    Optional<ThongSoChiTiet> save(ThongSoChiTietDTO thongSoChiTiet);

    Optional<ThongSoChiTiet> update (ThongSoChiTietDTO thongSoChiTiet);

    Boolean delete(int id);

}
