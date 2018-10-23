package br.com.pablocouto.discool.model.party;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Party {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("lacationName")
    private String lacationName;

    @SerializedName("addressDescription")
    private String addressDescription;

    @SerializedName("linkMaps")
    private String linkMaps;

    @SerializedName("ticketsLink")
    private String ticketsLink;

    @SerializedName("ticketsDescription")
    private String ticketsDescription;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("idHost")
    private String idHost;

    private boolean like = false;

    public Party(String title, String description, String lacationName, String addressDescription, String linkMaps, String ticketsLink, String ticketsDescription, String thumb, String date, String time, String idHost) {
        this.title = title;
        this.description = description;
        this.lacationName = lacationName;
        this.addressDescription = addressDescription;
        this.linkMaps = linkMaps;
        this.ticketsLink = ticketsLink;
        this.ticketsDescription = ticketsDescription;
        this.thumb = thumb;
        this.date = date;
        this.time = time;
        this.idHost = idHost;
    }

    public Party() {
        this(null, null,null,null,null,null,null,null,null,null,null,null);
    }

    public Party(String id, String title, String description, String lacationName, String addressDescription, String linkMaps, String ticketsLink, String ticketsDescription, String thumb, String date, String time, String idHost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lacationName = lacationName;
        this.addressDescription = addressDescription;
        this.linkMaps = linkMaps;
        this.ticketsLink = ticketsLink;
        this.ticketsDescription = ticketsDescription;
        this.thumb = thumb;
        this.date = date;
        this.time = time;
        this.idHost = idHost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLacationName() {
        return lacationName;
    }

    public void setLacationName(String lacationName) {
        this.lacationName = lacationName;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public String getLinkMaps() {
        return linkMaps;
    }

    public void setLinkMaps(String linkMaps) {
        this.linkMaps = linkMaps;
    }

    public String getTicketsLink() {
        return ticketsLink;
    }

    public void setTicketsLink(String ticketsLink) {
        this.ticketsLink = ticketsLink;
    }

    public String getTicketsDescription() {
        return ticketsDescription;
    }

    public void setTicketsDescription(String ticketsDescription) {
        this.ticketsDescription = ticketsDescription;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdHost() {
        return idHost;
    }

    public void setIdHost(String idHost) {
        this.idHost = idHost;
    }

    public void setLike(){
        this.like = true;
    }

    public void setUnlike(){
        this.like = false;
    }

    public boolean isLiked() {
        return  this.like;
    }
}
