/**
 * SkiesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.skies.stub;

public class SkiesServiceLocator extends org.apache.axis.client.Service implements aero.icaro.service.skies.stub.SkiesService {

    public SkiesServiceLocator() {
    }


    public SkiesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SkiesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SkiesServicePort
    private java.lang.String SkiesServicePort_address = "http://webserver.icaro.aero/serviciosweb/SkiesService.php";

    public java.lang.String getSkiesServicePortAddress() {
        return SkiesServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SkiesServicePortWSDDServiceName = "SkiesServicePort";

    public java.lang.String getSkiesServicePortWSDDServiceName() {
        return SkiesServicePortWSDDServiceName;
    }

    public void setSkiesServicePortWSDDServiceName(java.lang.String name) {
        SkiesServicePortWSDDServiceName = name;
    }

    public aero.icaro.service.skies.stub.SkiesServicePortType getSkiesServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SkiesServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSkiesServicePort(endpoint);
    }

    public aero.icaro.service.skies.stub.SkiesServicePortType getSkiesServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            aero.icaro.service.skies.stub.SkiesServiceBindingStub _stub = new aero.icaro.service.skies.stub.SkiesServiceBindingStub(portAddress, this);
            _stub.setPortName(getSkiesServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSkiesServicePortEndpointAddress(java.lang.String address) {
        SkiesServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (aero.icaro.service.skies.stub.SkiesServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                aero.icaro.service.skies.stub.SkiesServiceBindingStub _stub = new aero.icaro.service.skies.stub.SkiesServiceBindingStub(new java.net.URL(SkiesServicePort_address), this);
                _stub.setPortName(getSkiesServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SkiesServicePort".equals(inputPortName)) {
            return getSkiesServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/SkiesService", "SkiesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/SkiesService", "SkiesServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SkiesServicePort".equals(portName)) {
            setSkiesServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
