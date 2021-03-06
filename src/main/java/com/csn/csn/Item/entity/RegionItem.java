package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class RegionItem extends Item {

    private String category;
    private String description;
    private String telephone;
    private String address; // 임베디트 타임 Address 추가할 것인지 고민 필요
    private String roadAddress; // 임베디트 타임 Address 추가할 것인지 고민 필요
    private Integer mapx;
    private Integer mapy;
}
