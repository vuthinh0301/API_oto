package com.mpec.quanlyoto.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.soap.Name;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "nhom_hang")
public class NhomHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma_nhom_hang")
    private String maNhomHang;

    @Column(name = "ten_nhom_hang")
    private String tenNhomHang;

    @Column(name = "xoa")
    private Boolean xoa;

//    @OneToMany(mappedBy = "nhomHang", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<HangHoa> hangHoa;
//
//    @OneToMany(mappedBy = "nhomHang", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ThongSoKiThuat> thongSoKiThuat;
}
