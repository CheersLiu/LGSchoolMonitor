package com.lancoo.lgschoolmonitor.bean;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 13:05.
 */
@Root(name = "ArrayOfString", strict = false)
@NamespaceList({@Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"), @Namespace
        (reference = "http://LGCPWS_Basic.org/")})
public class SysConfigInfoXmlBean {
    @ElementList(name = "string", inline = true, type = String.class)
    List<String> configInfos;

    public SysConfigInfoXmlBean() {
    }

    public SysConfigInfoXmlBean(List<String> configInfos) {
        this.configInfos = configInfos;
    }

    public List<String> getConfigInfos() {
        return configInfos;
    }

    public void setConfigInfos(List<String> configInfos) {
        this.configInfos = configInfos;
    }
}
