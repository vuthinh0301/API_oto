package com.mpec.quanlyoto.demo.dto;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class HangHoaThongSoDTO {

    private Integer id;

    private String giaTri;

    private Integer hangHoaId;

    private Integer thongSoKiThuatId;

    private Integer thongSoChiTietId;

}
