package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.dto.ThongSoKiThuatDTO;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThongSoKiThuatService {

    Page<ThongSoKiThuat> findAll(Pageable pageable);

    Optional<ThongSoKiThuat> findById(Integer id);

    Page<ThongSoKiThuat> findThongSoKiThuatByNhomThongSo(Integer nhomThongSoId, Pageable pageable);

    Page<ThongSoKiThuat> findThongSoKiThuatByNhomHang(Integer nhomHangId, Pageable pageable);

    Page<ThongSoKiThuat> search(String text, Pageable pageable);

    Optional<ThongSoKiThuat> save(ThongSoKiThuatDTO thongSoKiThuat);

    Optional<ThongSoKiThuat> update (ThongSoKiThuatDTO thongSoKiThuat);

    Boolean delete(int id);
}
