/**
 * AgencyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.agency.stub;

public class AgencyServiceLocator extends org.apache.axis.client.Service implements aero.icaro.service.agency.stub.AgencyService {

    public AgencyServiceLocator() {
    }


    public AgencyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AgencyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for agencyServicePort
    private java.lang.String agencyServicePort_address = "http://webserver.icaro.aero/serviciosweb/AgencyService.php";

    public java.lang.String getagencyServicePortAddress() {
        return agencyServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String agencyServicePortWSDDServiceName = "agencyServicePort";

    public java.lang.String getagencyServicePortWSDDServiceName() {
        return agencyServicePortWSDDServiceName;
    }

    public void setagencyServicePortWSDDServiceName(java.lang.String name) {
        agencyServicePortWSDDServiceName = name;
    }

    public aero.icaro.service.agency.stub.AgencyServicePortType getagencyServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(agencyServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getagencyServicePort(endpoint);
    }

    public aero.icaro.service.agency.stub.AgencyServicePortType getagencyServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            aero.icaro.service.agency.stub.AgencyServiceBindingStub _stub = new aero.icaro.service.agency.stub.AgencyServiceBindingStub(portAddress, this);
            _stub.setPortName(getagencyServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setagencyServicePortEndpointAddress(java.lang.String address) {
        agencyServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (aero.icaro.service.agency.stub.AgencyServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                aero.icaro.service.agency.stub.AgencyServiceBindingStub _stub = new aero.icaro.service.agency.stub.AgencyServiceBindingStub(new java.net.URL(agencyServicePort_address), this);
                _stub.setPortName(getagencyServicePortWSDDServiceName());
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
        if ("agencyServicePort".equals(inputPortName)) {
            return getagencyServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/AgencyService", "agencyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("webserver.icaro.aero/serviciosweb/AgencyService", "agencyServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("agencyServicePort".equals(portName)) {
            setagencyServicePortEndpointAddress(address);
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
