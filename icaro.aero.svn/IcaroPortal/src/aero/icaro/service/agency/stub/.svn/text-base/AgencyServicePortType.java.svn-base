/**
 * AgencyServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.agency.stub;

public interface AgencyServicePortType extends java.rmi.Remote {

    /**
     * Verifica si la persona esta registrada en el sistema de acumulación
     * de Puntos
     */
    public aero.icaro.service.agency.stub.IcaroUser agencyLogin(java.lang.String cardNumber, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Recibe los datos de estado de cuenta en forma resumida del
     * socio
     */
    public aero.icaro.service.agency.stub.AgencyAccountSummary getagencyAccountSummary(java.lang.String cardNumber) throws java.rmi.RemoteException;

    /**
     * Envia el url del documento que contiene el detalle del estado
     * de cuenta del socio
     */
    public aero.icaro.service.agency.stub.IcaroServiceMessage getagencyAccountDetail(java.lang.String cardNumber) throws java.rmi.RemoteException;

    /**
     * Almacena los datos de la venta realizada que correponde a un
     * socio agency
     */
    public aero.icaro.service.agency.stub.IcaroServiceMessage updateagencyAccount(java.lang.String cardNumber, java.lang.String eTicketNumber, java.lang.String recordLocator, java.math.BigInteger segments, double total) throws java.rmi.RemoteException;
}
