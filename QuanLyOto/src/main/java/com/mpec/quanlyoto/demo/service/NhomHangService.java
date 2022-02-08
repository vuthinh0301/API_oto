package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.dto.NhomHangDTO;
import com.mpec.quanlyoto.demo.entities.NhomHang;
import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NhomHangService {

    Page<NhomHang> findAll(Pageable pageable);

    Optional<NhomHang> findById(Integer id);

    Page<NhomHang> search(String maNhomHang, String tenNhomHang, Pageable pageable);

    Optional<NhomHang> save(NhomHangDTO nhomHang);

    Optional<NhomHang> update (NhomHangDTO nhomHang);

    Boolean delete(Integer id);

}
