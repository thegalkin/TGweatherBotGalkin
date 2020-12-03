
package com.company;

import java.util.HashMap;
import java.util.List;
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
    "message_id",
    "from",
    "chat",
    "date",
    "text",
    "entities"
})
public class Message {

    @JsonProperty("message_id")
    public Integer messageId;
    @JsonProperty("from")
    public From from;
    @JsonProperty("chat")
    public Chat chat;
    @JsonProperty("date")
    public Integer date;
    @JsonProperty("text")
    public String text;
    @JsonProperty("entities")
    public List<Entity> entities = null;
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
        return new ToStringBuilder(this).append("messageId", messageId).append("from", from).append("chat", chat).append("date", date).append("text", text).append("entities", entities).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(date).append(entities).append(chat).append(messageId).append(from).append(text).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Message) == false) {
            return false;
        }
        Message rhs = ((Message) other);
        return new EqualsBuilder().append(date, rhs.date).append(entities, rhs.entities).append(chat, rhs.chat).append(messageId, rhs.messageId).append(from, rhs.from).append(text, rhs.text).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
