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
@Table(name = "hang_hoa")
public class HangHoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ma_giam_gia")
    private String maGiamGia;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "phan_tram_giam_gia")
    private Float phanTramGiamGia;

    @Column(name = "ten_hang_hoa")
    private String tenHangHoa;

    @Column(name = "tich_diem")
    private Integer tichDiem;

    @Column(name = "url_hinh_anh_1")
    private String urlHinhAnh1;

    @Column(name = "url_hinh_anh_2")
    private String urlHinhAnh2;

    @Column(name = "url_hinh_anh_3")
    private String urlHinhAnh3;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    @JsonBackReference
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    @Column(name = "ma_vach")
    private String maVach;

    @Column(name = "xoa")
    private Boolean xoa;

//    @OneToMany(mappedBy = "hangHoa", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<NhomThongSo> nhomThongSo;

}
