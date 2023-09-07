package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AdArticleConfig {
    private String selectAllAdSQL;
    private String insertAdSQL;
    private String getMethod;
    private String setMethod;
    private List<PropertyMap> adMappings;

    @JsonProperty("selectAllAdSQL")
    public String getSelectAllAdSQL() { return selectAllAdSQL; }
    @JsonProperty("selectAllAdSQL")
    public void setSelectAllAdSQL(String value) { this.selectAllAdSQL = value; }

    @JsonProperty("insertAdSQL")
    public String getInsertAdSQL() { return insertAdSQL; }
    @JsonProperty("insertAdSQL")
    public void setInsertAdSQL(String value) { this.insertAdSQL = value; }

    @JsonProperty("getMethod")
    public String getGetMethod() { return getMethod; }
    @JsonProperty("getMethod")
    public void setGetMethod(String value) { this.getMethod = value; }

    @JsonProperty("setMethod")
    public String getSetMethod() { return setMethod; }
    @JsonProperty("setMethod")
    public void setSetMethod(String value) { this.setMethod = value; }

    @JsonProperty("adMappings")
    public List<PropertyMap> getAdMappings() { return adMappings; }
    @JsonProperty("adMappings")
    public void setAdMappings(List<PropertyMap> value) { this.adMappings = value; }
}
