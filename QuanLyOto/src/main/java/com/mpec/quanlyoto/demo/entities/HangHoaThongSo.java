package com.mpec.quanlyoto.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "hang_hoa_thong_so")
public class HangHoaThongSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hang_hoa_id")
    @JsonBackReference
    private HangHoa hangHoa;

    @ManyToOne
    @JoinColumn(name = "thong_so_ki_thuat_id")
    @JsonBackReference
    private ThongSoKiThuat thongSoKiThuat;

    @ManyToOne
    @JoinColumn(name = "thong_so_chi_tiet_id")
    @JsonBackReference
    private ThongSoChiTiet thongSoChiTiet;

    @Column(name = "gia_tri")
    private String gia_tri;

    @Column(name = "xoa")
    private Boolean xoa;
}
