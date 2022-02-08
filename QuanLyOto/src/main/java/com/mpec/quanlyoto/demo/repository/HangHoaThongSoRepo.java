package com.mpec.quanlyoto.demo.repository;

import com.mpec.quanlyoto.demo.entities.HangHoaThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HangHoaThongSoRepo extends JpaRepository<HangHoaThongSo,Integer> {

    @Query(value = "from HangHoaThongSo hhts where hhts.xoa = false")
    Page<HangHoaThongSo> findAll(Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where hhts.id = ?1 and hhts.xoa = false")
    Optional<HangHoaThongSo> findById(Integer id);

    @Query(value = "from HangHoaThongSo hhts where hhts.hangHoa.id = ?1 and hhts.xoa = false")
    Page<HangHoaThongSo> findHangHoaThongSoByHangHoa(Integer hangHoaId, Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where hhts.thongSoKiThuat.id = ?1 and hhts.xoa = false")
    Page<HangHoaThongSo> findHangHoaThongSoByThongSoKiThuat(Integer thongSoKiThuatId, Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where hhts.thongSoChiTiet.id = ?1 and hhts.xoa = false")
    Page<HangHoaThongSo> findHangHoaThongSoByThongSoChiTiet(Integer thongSoChiTietId, Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where ?1 is null or " +
            " hhts.gia_tri like concat('%',?1,'%') " +
            " and hhts.xoa = false ")
    Page<HangHoaThongSo> search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value ="update HangHoaThongSo hhts set hhts.xoa = true where hhts.id = ?1" )
    Integer delete(Integer id);
}
