<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <comments>
            <xsl:for-each select="/comments/maoYanComment">
                <comment>

                    <from>猫眼电影</from>
                    <user>
                        <xsl:value-of select="username"/>
                    </user>

                    <avatar>
                        <xsl:value-of select="avatar"/>
                    </avatar>

                    <date>
                        <xsl:value-of select="commentTime"/>
                    </date>

                    <content>
                        <xsl:value-of select="commentText"/>
                    </content>

                    <thumb>
                        <xsl:value-of select="commentUp"/>
                    </thumb>

                    <rate>
                        <xsl:value-of select="userRate"/>
                    </rate>
                </comment>
            </xsl:for-each>
        </comments>

    </xsl:template>

</xsl:stylesheet>

