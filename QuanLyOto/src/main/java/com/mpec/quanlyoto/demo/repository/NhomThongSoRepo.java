package com.mpec.quanlyoto.demo.repository;

import com.mpec.quanlyoto.demo.dto.NhomThongSoDTO;
import com.mpec.quanlyoto.demo.entities.HangHoa;
import com.mpec.quanlyoto.demo.entities.NhomThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NhomThongSoRepo extends JpaRepository<NhomThongSo,Integer> {

    @Query(value = "from NhomThongSo nts where nts.xoa = false")
    Page<NhomThongSo> findAll(Pageable pageable);

    @Query(value = "from NhomThongSo nts where nts.id = ?1 and nts.xoa = false")
    Optional<NhomThongSo> findById(Integer id);

    @Query(value = "from NhomThongSo nts where nts.hangHoa.id = ?1 and nts.xoa = false")
    Page<NhomThongSo> findNhomThongSoByHangHoa(Integer hangHoaId, Pageable pageable);

    @Query(value = "from NhomThongSo nts where ?1 is null or " +
            " nts.tenNhomThongSo like concat('%',?1,'%') " +
            " and nts.xoa = false ")
    Page<NhomThongSo> search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value ="update NhomThongSo nts set nts.xoa = true where nts.id = ?1" )
    Integer delete(Integer id);
}





