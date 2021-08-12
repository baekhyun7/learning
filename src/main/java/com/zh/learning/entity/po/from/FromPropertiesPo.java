package com.zh.learning.entity.po.from;

import lombok.Data;

import java.util.*;

/**
 * @author zh
 * @date 2021-07-20 13:53
 */
@Data
public class FromPropertiesPo extends LongBaseModel {

    private Long parentId;
    private Long fromId;
    private String name;
    private String placeholder;
    private Long controlId;
    private String controlName;
    private Boolean isContainer;
    private String options;
    private Boolean required;
    private Boolean auth;
    private Integer sort;

    private List<FromPropertiesPo> content;


    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        Set<Integer> list2 = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(i);
        }
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }
}
