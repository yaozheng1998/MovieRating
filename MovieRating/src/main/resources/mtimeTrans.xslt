<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <comments>
            <xsl:for-each select="/comments/mTimeComment">
                <comment>

                    <from>时光网</from>
                    <user>
                        <xsl:value-of select="ca"/>
                    </user>

                    <avatar>
                        <xsl:value-of select="caimg"/>
                    </avatar>

                    <date>
                        <xsl:value-of select="cd"/>
                    </date>

                    <content>
                        <xsl:value-of select="ce"/>
                    </content>

                    <thumb>
                        <xsl:value-of select="commentCount"/>
                    </thumb>

                    <rate>
                        <xsl:value-of select="cr"/>
                    </rate>
                </comment>
            </xsl:for-each>
        </comments>

    </xsl:template>

</xsl:stylesheet>

