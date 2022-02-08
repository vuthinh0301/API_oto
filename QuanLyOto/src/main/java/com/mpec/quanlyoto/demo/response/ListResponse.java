package com.mpec.quanlyoto.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> extends MyResponse{

    private List<T> datas;
}
