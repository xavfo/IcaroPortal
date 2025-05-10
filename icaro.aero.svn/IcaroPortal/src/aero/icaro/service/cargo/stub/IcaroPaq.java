/**
 * IcaroPaq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.cargo.stub;

public class IcaroPaq  implements java.io.Serializable {
    private java.lang.String idPaq;

    private java.lang.String estadoPaq;

    private java.lang.String estadoPaqDesc;

    public IcaroPaq() {
    }

    public IcaroPaq(
           java.lang.String idPaq,
           java.lang.String estadoPaq,
           java.lang.String estadoPaqDesc) {
           this.idPaq = idPaq;
           this.estadoPaq = estadoPaq;
           this.estadoPaqDesc = estadoPaqDesc;
    }


    /**
     * Gets the idPaq value for this IcaroPaq.
     * 
     * @return idPaq
     */
    public java.lang.String getIdPaq() {
        return idPaq;
    }


    /**
     * Sets the idPaq value for this IcaroPaq.
     * 
     * @param idPaq
     */
    public void setIdPaq(java.lang.String idPaq) {
        this.idPaq = idPaq;
    }


    /**
     * Gets the estadoPaq value for this IcaroPaq.
     * 
     * @return estadoPaq
     */
    public java.lang.String getEstadoPaq() {
        return estadoPaq;
    }


    /**
     * Sets the estadoPaq value for this IcaroPaq.
     * 
     * @param estadoPaq
     */
    public void setEstadoPaq(java.lang.String estadoPaq) {
        this.estadoPaq = estadoPaq;
    }


    /**
     * Gets the estadoPaqDesc value for this IcaroPaq.
     * 
     * @return estadoPaqDesc
     */
    public java.lang.String getEstadoPaqDesc() {
        return estadoPaqDesc;
    }


    /**
     * Sets the estadoPaqDesc value for this IcaroPaq.
     * 
     * @param estadoPaqDesc
     */
    public void setEstadoPaqDesc(java.lang.String estadoPaqDesc) {
        this.estadoPaqDesc = estadoPaqDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IcaroPaq)) return false;
        IcaroPaq other = (IcaroPaq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idPaq==null && other.getIdPaq()==null) || 
             (this.idPaq!=null &&
              this.idPaq.equals(other.getIdPaq()))) &&
            ((this.estadoPaq==null && other.getEstadoPaq()==null) || 
             (this.estadoPaq!=null &&
              this.estadoPaq.equals(other.getEstadoPaq()))) &&
            ((this.estadoPaqDesc==null && other.getEstadoPaqDesc()==null) || 
             (this.estadoPaqDesc!=null &&
              this.estadoPaqDesc.equals(other.getEstadoPaqDesc())));
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
        if (getIdPaq() != null) {
            _hashCode += getIdPaq().hashCode();
        }
        if (getEstadoPaq() != null) {
            _hashCode += getEstadoPaq().hashCode();
        }
        if (getEstadoPaqDesc() != null) {
            _hashCode += getEstadoPaqDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IcaroPaq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/IcargoService", "IcaroPaq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPaq");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPaq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoPaq");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoPaq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoPaqDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoPaqDesc"));
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
