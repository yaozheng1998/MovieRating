<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <comments>
            <xsl:for-each select="/comments/doubanComment">
                <comment>
                    <user>
                        <xsl:copy-of select="user/name"/>
                        <xsl:copy-of select="user/avatarUrl"/>
                    </user>
                    <date>
                        <xsl:value-of select="date"/>
                    </date>
                    <content>
                        <xsl:value-of select="doubanContent"/>
                    </content>
                    <rate>
                        <xsl:value-of select="doubanRate"/>
                    </rate>
                </comment>

            </xsl:for-each>

        </comments>

    </xsl:template>

</xsl:stylesheet>

