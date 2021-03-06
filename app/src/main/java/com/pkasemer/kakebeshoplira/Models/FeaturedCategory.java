
package com.pkasemer.kakebeshoplira.Models;


import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class FeaturedCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order_level")
    @Expose
    private Integer orderLevel;
    @SerializedName("commision_rate")
    @Expose
    private Integer commisionRate;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("featured")
    @Expose
    private Integer featured;
    @SerializedName("top")
    @Expose
    private Integer top;
    @SerializedName("digital")
    @Expose
    private Integer digital;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("meta_description")
    @Expose
    private Object metaDescription;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("featuredCategoriesProduct")
    @Expose
    private List<FeaturedCategoriesProduct> featuredCategoriesProduct = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    public Integer getCommisionRate() {
        return commisionRate;
    }

    public void setCommisionRate(Integer commisionRate) {
        this.commisionRate = commisionRate;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getFeatured() {
        return featured;
    }

    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getDigital() {
        return digital;
    }

    public void setDigital(Integer digital) {
        this.digital = digital;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Object getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<FeaturedCategoriesProduct> getFeaturedCategoriesProduct() {
        return featuredCategoriesProduct;
    }

    public void setFeaturedCategoriesProduct(List<FeaturedCategoriesProduct> featuredCategoriesProduct) {
        this.featuredCategoriesProduct = featuredCategoriesProduct;
    }

}
