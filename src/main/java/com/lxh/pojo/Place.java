package com.lxh.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
public class Place extends Model<Place> {

    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private String name;

    private String province;

    private String image;

    private String tail;

    private Integer hot;
    private float score;

    private int scorenum;

    public int getScorenum() {
        return scorenum;
    }

    public void setScorenum(int scorenum) {
        this.scorenum = scorenum;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}

