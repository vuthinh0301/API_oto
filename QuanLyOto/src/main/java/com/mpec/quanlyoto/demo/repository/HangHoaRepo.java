package com.mpec.quanlyoto.demo.repository;

import com.mpec.quanlyoto.demo.entities.HangHoa;
import com.mpec.quanlyoto.demo.entities.NhomHang;
import com.mpec.quanlyoto.demo.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HangHoaRepo extends JpaRepository<HangHoa,Integer> {

    @Query(value = "from HangHoa hh where hh.xoa = false")
    Page<HangHoa> findAll(Pageable pageable);

    @Query(value = "from HangHoa hh where hh.id = ?1 and hh.xoa = false")
    Optional<HangHoa> findById(Integer id);

    @Query(value = "from HangHoa hh where hh.nhomHang.id = ?1 and hh.xoa = false")
    Page<HangHoa> findHangHoaByNhomHang(Integer nhomHangId,Pageable pageable);

    @Query(value = "from HangHoa hh where ?1 is null or " +
            " hh.tenHangHoa like concat('%',?1,'%') " +
            " and hh.xoa = false ")
    Page<HangHoa> search(String text, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value ="update HangHoa hh set hh.xoa = true where hh.id = ?1" )
    Integer delete(Integer id);
}
