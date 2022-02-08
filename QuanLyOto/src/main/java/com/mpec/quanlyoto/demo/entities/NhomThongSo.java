package com.mpec.quanlyoto.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "nhom_thong_so")
public class NhomThongSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_nhom_thong_so")
    private String tenNhomThongSo;

    @ManyToOne
    @JoinColumn(name = "hang_hoa_id")
    @JsonBackReference
    private HangHoa hangHoa;

    @Column(name = "ma_nhom_thong_so")
    private String maNhomThongSo;

    @Column(name = "xoa")
    private  Boolean xoa;

//    @OneToMany(mappedBy = "nhomThongSo", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ThongSoKiThuat> thongSoKiThuat;
}
