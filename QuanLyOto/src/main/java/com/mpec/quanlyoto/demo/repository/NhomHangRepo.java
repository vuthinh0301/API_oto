package com.mpec.quanlyoto.demo.repository;

import com.mpec.quanlyoto.demo.dto.NhomHangDTO;
import com.mpec.quanlyoto.demo.entities.NhomHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NhomHangRepo extends JpaRepository<NhomHang,Integer> {

    @Query(value = "from NhomHang nh where nh.xoa = false")
    Page<NhomHang> findAll(Pageable pageable);

    @Query(value = "from NhomHang nh where nh.id = ?1 and nh.xoa = false")
    Optional<NhomHang> findById(Integer id);

    @Query(value = "from NhomHang nh where " +
            " ?1 is null or nh.maNhomHang like concat('%',?1,'%') " +
            " and ?2 is null or nh.tenNhomHang like concat('%',?2,'%') " +
            " and nh.xoa = false ")
    Page<NhomHang> search(String maNhomHang, String tenNhomhang, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value ="update NhomHang nh set nh.xoa = true where nh.id = ?1" )
    Integer delete(Integer id);

//    @Modifying
//    @Transactional
//    @Query(value ="update NhomHang nh set nh.maNhomHang = ?2, nh.tenNhomHang = ?3 where nh.id = ?1" )
//    Optional<NhomHang> update (Integer id, String maNhomHang, String tenNhomHang);
}
