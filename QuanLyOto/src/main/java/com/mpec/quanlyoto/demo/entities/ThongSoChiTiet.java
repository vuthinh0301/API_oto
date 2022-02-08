package com.mpec.quanlyoto.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "thong_so_chi_tiet")
public class ThongSoChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten")
    private String ten;

    @ManyToOne
    @JoinColumn(name = "thong_so_ki_thuat_id")
    @JsonBackReference
    private ThongSoKiThuat thongSoKiThuat;

    @Column(name = "gia_tri")
    private String maNhomThongSo;

    @Column(name = "xoa")
    private  Boolean xoa;

//    @OneToMany(mappedBy = "thongSoChiTiet", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<HangHoaThongSo> hangHoaThongSo;

}
