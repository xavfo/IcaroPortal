/**
 * IcargoServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.cargo.stub;

public interface IcargoServicePortType extends java.rmi.Remote {

    /**
     * Verifica el estado de un paquete
     */
    public aero.icaro.service.cargo.stub.IcaroDoc icargoEstado(java.lang.String docType, java.lang.String docNumber) throws java.rmi.RemoteException;
}
