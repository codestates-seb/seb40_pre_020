package com.SEB_Pre_020.demo.tag.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PostTag> postTags;
}
