package com.bstbackend.bstbackend.dtos;

import lombok.Data;

@Data
public class MetaDTO {
    private MetaOpenGraphDTO openGraph = new MetaOpenGraphDTO();

    private MetaOtherDTO otherDTO = new MetaOtherDTO();
}
