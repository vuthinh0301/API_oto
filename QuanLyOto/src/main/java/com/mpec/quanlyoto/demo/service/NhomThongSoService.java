package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO;
import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO_update;
import com.mpec.quanlyoto.demo.entities.NhomThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NhomThongSoService {

    Page<NhomThongSo> findAll(Pageable pageable);

    Optional<NhomThongSo> findById(Integer id);

    Page<NhomThongSo> findNhomThongSoByHangHoa(Integer hangHoaId, Pageable pageable);

    Page<NhomThongSo> search(String text, Pageable pageable);

    Optional<NhomThongSo> save(NhomThongSoDTO nhomThongSo);

    Optional<NhomThongSo> update (NhomThongSoDTO nhomThongSo);

    Boolean delete(int id);
}
