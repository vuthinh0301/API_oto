package com.mpec.quanlyoto.demo.service;

import com.mpec.quanlyoto.demo.dto.HangHoaDTO;
import com.mpec.quanlyoto.demo.entities.HangHoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HangHoaService {

    Page<HangHoa> findAll(Pageable pageable);

    Optional<HangHoa> findById(Integer id);

    Page<HangHoa> findHangHoaByNhomHang(Integer nhomHangId, Pageable pageable);

    Page<HangHoa> search(String text, Pageable pageable);

   Optional<HangHoa> save(HangHoa hangHoa);

    Optional<HangHoa> update (HangHoaDTO hangHoa);

    Boolean delete(int id);

}
