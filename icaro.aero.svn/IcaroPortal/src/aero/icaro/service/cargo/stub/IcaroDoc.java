/**
 * IcaroDoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.cargo.stub;

public class IcaroDoc  implements java.io.Serializable {
    private java.lang.String estadoDoc;

    private java.lang.String estadoDocDesc;

    private aero.icaro.service.cargo.stub.IcaroPaq[] paquetes;

    private java.math.BigInteger statusCode;

    private java.lang.String response;

    public IcaroDoc() {
    }

    public IcaroDoc(
           java.lang.String estadoDoc,
           java.lang.String estadoDocDesc,
           aero.icaro.service.cargo.stub.IcaroPaq[] paquetes,
           java.math.BigInteger statusCode,
           java.lang.String response) {
           this.estadoDoc = estadoDoc;
           this.estadoDocDesc = estadoDocDesc;
           this.paquetes = paquetes;
           this.statusCode = statusCode;
           this.response = response;
    }


    /**
     * Gets the estadoDoc value for this IcaroDoc.
     * 
     * @return estadoDoc
     */
    public java.lang.String getEstadoDoc() {
        return estadoDoc;
    }


    /**
     * Sets the estadoDoc value for this IcaroDoc.
     * 
     * @param estadoDoc
     */
    public void setEstadoDoc(java.lang.String estadoDoc) {
        this.estadoDoc = estadoDoc;
    }


    /**
     * Gets the estadoDocDesc value for this IcaroDoc.
     * 
     * @return estadoDocDesc
     */
    public java.lang.String getEstadoDocDesc() {
        return estadoDocDesc;
    }


    /**
     * Sets the estadoDocDesc value for this IcaroDoc.
     * 
     * @param estadoDocDesc
     */
    public void setEstadoDocDesc(java.lang.String estadoDocDesc) {
        this.estadoDocDesc = estadoDocDesc;
    }


    /**
     * Gets the paquetes value for this IcaroDoc.
     * 
     * @return paquetes
     */
    public aero.icaro.service.cargo.stub.IcaroPaq[] getPaquetes() {
        return paquetes;
    }


    /**
     * Sets the paquetes value for this IcaroDoc.
     * 
     * @param paquetes
     */
    public void setPaquetes(aero.icaro.service.cargo.stub.IcaroPaq[] paquetes) {
        this.paquetes = paquetes;
    }


    /**
     * Gets the statusCode value for this IcaroDoc.
     * 
     * @return statusCode
     */
    public java.math.BigInteger getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this IcaroDoc.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.math.BigInteger statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the response value for this IcaroDoc.
     * 
     * @return response
     */
    public java.lang.String getResponse() {
        return response;
    }


    /**
     * Sets the response value for this IcaroDoc.
     * 
     * @param response
     */
    public void setResponse(java.lang.String response) {
        this.response = response;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IcaroDoc)) return false;
        IcaroDoc other = (IcaroDoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estadoDoc==null && other.getEstadoDoc()==null) || 
             (this.estadoDoc!=null &&
              this.estadoDoc.equals(other.getEstadoDoc()))) &&
            ((this.estadoDocDesc==null && other.getEstadoDocDesc()==null) || 
             (this.estadoDocDesc!=null &&
              this.estadoDocDesc.equals(other.getEstadoDocDesc()))) &&
            ((this.paquetes==null && other.getPaquetes()==null) || 
             (this.paquetes!=null &&
              java.util.Arrays.equals(this.paquetes, other.getPaquetes()))) &&
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
        if (getEstadoDoc() != null) {
            _hashCode += getEstadoDoc().hashCode();
        }
        if (getEstadoDocDesc() != null) {
            _hashCode += getEstadoDocDesc().hashCode();
        }
        if (getPaquetes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaquetes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaquetes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        new org.apache.axis.description.TypeDesc(IcaroDoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/IcargoService", "IcaroDoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoDocDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoDocDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paquetes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paquetes"));
        elemField.setXmlType(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/IcargoService", "IcaroPaq"));
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
