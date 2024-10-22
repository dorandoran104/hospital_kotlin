package com.ggg.kotlin.hospital_java.util

import org.springframework.stereotype.Component
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

@Component
class XmlUtil {

    /**
     * xml 값 가져오기
     */
    fun getTagValue(tag: String, eElement: Element): String? {
        val nodeList: NodeList
        try {
            nodeList = eElement.getElementsByTagName(tag).item(0).childNodes
        } catch (e: Exception) {
            return null
        }
        val nValue = nodeList.item(0) as Node
        return nValue.nodeValue
    }
}