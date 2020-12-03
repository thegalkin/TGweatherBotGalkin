
package com.company;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "is_bot",
    "first_name",
    "username",
    "language_code"
})
public class From {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("is_bot")
    public Boolean isBot;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("username")
    public String username;
    @JsonProperty("language_code")
    public String languageCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("isBot", isBot).append("firstName", firstName).append("username", username).append("languageCode", languageCode).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isBot).append(firstName).append(id).append(additionalProperties).append(languageCode).append(username).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof From) == false) {
            return false;
        }
        From rhs = ((From) other);
        return new EqualsBuilder().append(isBot, rhs.isBot).append(firstName, rhs.firstName).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(languageCode, rhs.languageCode).append(username, rhs.username).isEquals();
    }

}
