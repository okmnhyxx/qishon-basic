package com.qishon.common.config.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Created by jackie on 17-5-9.
 */
@ConfigurationProperties(prefix = "qishon.rest.docs.service")
public class ApiDocumentProperties {

    private String name = "";
    private String apis = "";
    private String desc = "";
    private String version = "";
    private Contact contact = new Contact();

    public ApiDocumentProperties() {
    }

    public ApiDocumentProperties(String name, String apis, String desc, String version, Contact contact) {
        this.name = name;
        this.apis = apis;
        this.desc = desc;
        this.version = version;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApis() {
        return apis;
    }

    public void setApis(String apis) {
        this.apis = apis;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    protected class Contact {

        @NestedConfigurationProperty
        private String name;

        @NestedConfigurationProperty
        private String url;

        @NestedConfigurationProperty
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
