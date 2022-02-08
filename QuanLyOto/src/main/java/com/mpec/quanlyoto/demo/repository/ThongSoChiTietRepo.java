package com.mpec.quanlyoto.demo.repository;

import com.mpec.quanlyoto.demo.entities.ThongSoChiTiet;
import com.mpec.quanlyoto.demo.entities.ThongSoKiThuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ThongSoChiTietRepo extends JpaRepository<ThongSoChiTiet, Integer> {

    @Query(value = "from ThongSoChiTiet tsct where tsct.xoa = false")
    Page<ThongSoChiTiet> findAll(Pageable pageable);

    @Query(value = "from ThongSoChiTiet tsct where tsct.id = ?1 and tsct.xoa = false")
    Optional<ThongSoChiTiet> findById(Integer id);

    @Query(value = "from ThongSoChiTiet tsct where tsct.thongSoKiThuat.id = ?1 and tsct.xoa = false")
    Page<ThongSoChiTiet> findThongSoChiTietByThongSoKiThuat(Integer thongSoKiThuatId, Pageable pageable);

    @Query(value = "from ThongSoChiTiet tsct where ?1 is null or " +
            " tsct.ten like concat('%',?1,'%') " +
            " and tsct.xoa = false ")
    Page<ThongSoChiTiet> search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value ="update ThongSoChiTiet tsct set tsct.xoa = true where tsct.id = ?1" )
    Integer delete(Integer id);
}
