package com.SEB_Pre_020.demo.tag.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tagId")
    private int id;

    @Column(name = "tagName", nullable = false, updatable = true, unique = true)
    private String tagName;
}
