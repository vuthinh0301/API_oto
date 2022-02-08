package com.mpec.quanlyoto.demo.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "thong_so_ki_thuat")
public class ThongSoKiThuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_thong_so")
    private String tenThongSo;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    @JsonBackReference
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "nhom_thong_so_id")
    @JsonBackReference
    private NhomThongSo nhomThongSo;

    @Column(name = "xoa")
    private Boolean xoa;

//    @OneToMany(mappedBy = "thongSoKiThuat", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ThongSoChiTiet> thongSoChiTiet;
//
//    @OneToMany(mappedBy = "thongSoKiThuat", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<HangHoaThongSo> hangHoaThongSo;

}
