package ru.filenko18.dto;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated("jsonschema2pojo")
@Getter
@Setter
public class Data {

    @JsonProperty("id")
    public String id;
    @JsonProperty("deletehash")
    public String deletehash;
    @JsonProperty("account_id")
    public Integer accountId;
    @JsonProperty("account_url")
    public String accountUrl;
    @JsonProperty("ad_type")
    public Object adType;
    @JsonProperty("ad_url")
    public Object adUrl;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public Object description;
    @JsonProperty("name")
    public String name;
    @JsonProperty("type")
    public String type;
    @JsonProperty("width")
    public Integer width;
    @JsonProperty("height")
    public Integer height;
    @JsonProperty("size")
    public Integer size;
    @JsonProperty("views")
    public Integer views;
    @JsonProperty("section")
    public Object section;
    @JsonProperty("vote")
    public Object vote;
    @JsonProperty("bandwidth")
    public Integer bandwidth;
    @JsonProperty("animated")
    public Boolean animated;
    @JsonProperty("favorite")
    public Boolean favorite;
    @JsonProperty("in_gallery")
    public Boolean inGallery;
    @JsonProperty("in_most_viral")
    public Boolean inMostViral;
    @JsonProperty("has_sound")
    public Boolean hasSound;
    @JsonProperty("is_ad")
    public Boolean isAd;
    @JsonProperty("nsfw")
    public Object nsfw;
    @JsonProperty("link")
    public String link;
    @JsonProperty("tags")
    public List<Object> tags = new ArrayList<Object>();
    @JsonProperty("datetime")
    public Integer datetime;
    @JsonProperty("mp4")
    public String mp4;
    @JsonProperty("hls")
    public String hls;

}
