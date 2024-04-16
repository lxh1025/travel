package com.lxh.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Post)表实体类
 *
 * @author makejava
 * @since 2024-04-12 17:28:51
 */
@SuppressWarnings("serial")
public class Post extends Model<Post> {

    @TableId(value="id",type = IdType.AUTO)
    private Integer id;

    private String title;

    private String img;

    private String text;

    private Date time;

    private String username;
    private Integer look;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

}

