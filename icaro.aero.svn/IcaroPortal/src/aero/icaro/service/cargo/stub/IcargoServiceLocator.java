/**
 * IcargoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.cargo.stub;

public class IcargoServiceLocator extends org.apache.axis.client.Service implements aero.icaro.service.cargo.stub.IcargoService {

    public IcargoServiceLocator() {
    }


    public IcargoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IcargoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IcargoServicePort
    private java.lang.String IcargoServicePort_address = "http://webserver.icaro.aero/serviciosweb/IcargoService.php";

    public java.lang.String getIcargoServicePortAddress() {
        return IcargoServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IcargoServicePortWSDDServiceName = "IcargoServicePort";

    public java.lang.String getIcargoServicePortWSDDServiceName() {
        return IcargoServicePortWSDDServiceName;
    }

    public void setIcargoServicePortWSDDServiceName(java.lang.String name) {
        IcargoServicePortWSDDServiceName = name;
    }

    public aero.icaro.service.cargo.stub.IcargoServicePortType getIcargoServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IcargoServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIcargoServicePort(endpoint);
    }

    public aero.icaro.service.cargo.stub.IcargoServicePortType getIcargoServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            aero.icaro.service.cargo.stub.IcargoServiceBindingStub _stub = new aero.icaro.service.cargo.stub.IcargoServiceBindingStub(portAddress, this);
            _stub.setPortName(getIcargoServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIcargoServicePortEndpointAddress(java.lang.String address) {
        IcargoServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (aero.icaro.service.cargo.stub.IcargoServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                aero.icaro.service.cargo.stub.IcargoServiceBindingStub _stub = new aero.icaro.service.cargo.stub.IcargoServiceBindingStub(new java.net.URL(IcargoServicePort_address), this);
                _stub.setPortName(getIcargoServicePortWSDDServiceName());
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
        if ("IcargoServicePort".equals(inputPortName)) {
            return getIcargoServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/IcargoService", "IcargoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/IcargoService", "IcargoServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IcargoServicePort".equals(portName)) {
            setIcargoServicePortEndpointAddress(address);
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
