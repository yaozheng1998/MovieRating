<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <comments>
            <xsl:for-each select="/comments/doubanComment">
                <comment>

                    <id>
                        <xsl:value-of select="id"/>
                    </id>

                    <from>豆瓣电影</from>

                    <user>
                        <xsl:value-of select="uid"/>
                    </user>

                    <avatar>
                        <xsl:value-of select="avatar"/>
                    </avatar>

                    <date>
                        <xsl:value-of select="create_at"/>
                    </date>

                    <content>
                        <xsl:value-of select="content"/>
                    </content>

                    <thumb>0</thumb>

                    <rate>
                        <xsl:value-of select="rating"/>
                    </rate>
                </comment>
            </xsl:for-each>
        </comments>

    </xsl:template>

</xsl:stylesheet>

