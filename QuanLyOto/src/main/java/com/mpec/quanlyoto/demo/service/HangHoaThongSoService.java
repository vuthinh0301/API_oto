package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.dto.HangHoaThongSoDTO;
import com.mpec.quanlyoto.demo.entities.HangHoaThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HangHoaThongSoService {

    Page<HangHoaThongSo> findAll(Pageable pageable);

    Optional<HangHoaThongSo> findById(Integer id);

    Page<HangHoaThongSo> findHangHoaThongSoByHangHoa(Integer hangHoaId, Pageable pageable);

    Page<HangHoaThongSo> findHangHoaThongSoByThongSoKiThuat(Integer thongSoKiThuatId, Pageable pageable);

    Page<HangHoaThongSo> findHangHoaThongSoByThongSoChiTiet(Integer thongSoChiTietId, Pageable pageable);

    Page<HangHoaThongSo> search(String text, Pageable pageable);

    Optional<HangHoaThongSo> save(HangHoaThongSoDTO hangHoaThongSo);

    Optional<HangHoaThongSo> update (HangHoaThongSoDTO hangHoaThongSo);

    Boolean delete(int id);
}
