<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <!--<xsl:variable name="staff" select="document('staff2.xml')"/>-->

    <xsl:template match="/">
        <staffs>
            <xsl:for-each select="/staffs/staff">
                <staff>
                    <xsl:attribute name="order">
                        <xsl:value-of select="@order"/>
                    </xsl:attribute>

                    <xsl:copy-of select="id"/>

                    <info>
                    <xsl:copy-of select="name"/>
                    <xsl:copy-of select="age"/>

                    </info>
                </staff>

            </xsl:for-each>

        </staffs>

    </xsl:template>

</xsl:stylesheet>

