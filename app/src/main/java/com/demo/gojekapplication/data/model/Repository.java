package com.demo.gojekapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repository implements Parcelable
{

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("forks")
    @Expose
    private Integer forks;
    @SerializedName("currentPeriodStars")
    @Expose
    private Integer currentPeriodStars;
    @SerializedName("builtBy")
    @Expose
    private List<BuiltBy> builtBy = null;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("languageColor")
    @Expose
    private String languageColor;
    public final static Creator<Repository> CREATOR = new Creator<Repository>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return (new Repository[size]);
        }

    }
            ;

    protected Repository(Parcel in) {
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.stars = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.forks = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentPeriodStars = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.builtBy, (BuiltBy.class.getClassLoader()));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.languageColor = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Repository() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(Integer currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    public List<BuiltBy> getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(List<BuiltBy> builtBy) {
        this.builtBy = builtBy;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(author);
        dest.writeValue(name);
        dest.writeValue(avatar);
        dest.writeValue(url);
        dest.writeValue(description);
        dest.writeValue(stars);
        dest.writeValue(forks);
        dest.writeValue(currentPeriodStars);
        dest.writeList(builtBy);
        dest.writeValue(language);
        dest.writeValue(languageColor);
    }

    public int describeContents() {
        return 0;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected = false;

}
