/**
 * SkiesAccountSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.skies.stub;

public class SkiesAccountSummary  implements java.io.Serializable {
    private java.lang.String reportDate;

    private java.lang.String memberSince;

    private aero.icaro.service.skies.stub.MilleDetail[] millesDetail;

    private long availableMiles;

    private java.math.BigInteger statusCode;

    private java.lang.String response;

    public SkiesAccountSummary() {
    }

    public SkiesAccountSummary(
           java.lang.String reportDate,
           java.lang.String memberSince,
           aero.icaro.service.skies.stub.MilleDetail[] millesDetail,
           long availableMiles,
           java.math.BigInteger statusCode,
           java.lang.String response) {
           this.reportDate = reportDate;
           this.memberSince = memberSince;
           this.millesDetail = millesDetail;
           this.availableMiles = availableMiles;
           this.statusCode = statusCode;
           this.response = response;
    }


    /**
     * Gets the reportDate value for this SkiesAccountSummary.
     * 
     * @return reportDate
     */
    public java.lang.String getReportDate() {
        return reportDate;
    }


    /**
     * Sets the reportDate value for this SkiesAccountSummary.
     * 
     * @param reportDate
     */
    public void setReportDate(java.lang.String reportDate) {
        this.reportDate = reportDate;
    }


    /**
     * Gets the memberSince value for this SkiesAccountSummary.
     * 
     * @return memberSince
     */
    public java.lang.String getMemberSince() {
        return memberSince;
    }


    /**
     * Sets the memberSince value for this SkiesAccountSummary.
     * 
     * @param memberSince
     */
    public void setMemberSince(java.lang.String memberSince) {
        this.memberSince = memberSince;
    }


    /**
     * Gets the millesDetail value for this SkiesAccountSummary.
     * 
     * @return millesDetail
     */
    public aero.icaro.service.skies.stub.MilleDetail[] getMillesDetail() {
        return millesDetail;
    }


    /**
     * Sets the millesDetail value for this SkiesAccountSummary.
     * 
     * @param millesDetail
     */
    public void setMillesDetail(aero.icaro.service.skies.stub.MilleDetail[] millesDetail) {
        this.millesDetail = millesDetail;
    }


    /**
     * Gets the availableMiles value for this SkiesAccountSummary.
     * 
     * @return availableMiles
     */
    public long getAvailableMiles() {
        return availableMiles;
    }


    /**
     * Sets the availableMiles value for this SkiesAccountSummary.
     * 
     * @param availableMiles
     */
    public void setAvailableMiles(long availableMiles) {
        this.availableMiles = availableMiles;
    }


    /**
     * Gets the statusCode value for this SkiesAccountSummary.
     * 
     * @return statusCode
     */
    public java.math.BigInteger getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this SkiesAccountSummary.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.math.BigInteger statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the response value for this SkiesAccountSummary.
     * 
     * @return response
     */
    public java.lang.String getResponse() {
        return response;
    }


    /**
     * Sets the response value for this SkiesAccountSummary.
     * 
     * @param response
     */
    public void setResponse(java.lang.String response) {
        this.response = response;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SkiesAccountSummary)) return false;
        SkiesAccountSummary other = (SkiesAccountSummary) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reportDate==null && other.getReportDate()==null) || 
             (this.reportDate!=null &&
              this.reportDate.equals(other.getReportDate()))) &&
            ((this.memberSince==null && other.getMemberSince()==null) || 
             (this.memberSince!=null &&
              this.memberSince.equals(other.getMemberSince()))) &&
            ((this.millesDetail==null && other.getMillesDetail()==null) || 
             (this.millesDetail!=null &&
              java.util.Arrays.equals(this.millesDetail, other.getMillesDetail()))) &&
            this.availableMiles == other.getAvailableMiles() &&
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              this.response.equals(other.getResponse())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getReportDate() != null) {
            _hashCode += getReportDate().hashCode();
        }
        if (getMemberSince() != null) {
            _hashCode += getMemberSince().hashCode();
        }
        if (getMillesDetail() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMillesDetail());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMillesDetail(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getAvailableMiles()).hashCode();
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getResponse() != null) {
            _hashCode += getResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SkiesAccountSummary.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/SkiesService", "SkiesAccountSummary"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberSince");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberSince"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("millesDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "millesDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/SkiesService", "MilleDetail"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableMiles");
        elemField.setXmlName(new javax.xml.namespace.QName("", "availableMiles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
