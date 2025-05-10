/**
 * CorpServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aero.icaro.service.corp.stub;

public interface CorpServicePortType extends java.rmi.Remote {

    /**
     * Verifica si la persona esta registrada en el sistema de acumulación
     * de Puntos
     */
    public aero.icaro.service.corp.stub.IcaroUser corpLogin(java.lang.String cardNumber, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Recibe los datos de estado de cuenta en forma resumida del
     * socio
     */
    public aero.icaro.service.corp.stub.CorpAccountSummary getCorpAccountSummary(java.lang.String cardNumber) throws java.rmi.RemoteException;

    /**
     * Envia el url del documento que contiene el detalle del estado
     * de cuenta del socio
     */
    public aero.icaro.service.corp.stub.IcaroServiceMessage getCorpAccountDetail(java.lang.String cardNumber) throws java.rmi.RemoteException;

    /**
     * Almacena los datos de la venta realizada que correponde a un
     * socio Corp
     */
    public aero.icaro.service.corp.stub.IcaroServiceMessage updateCorpAccount(java.lang.String cardNumber, java.lang.String eTicketNumber, java.lang.String recordLocator, java.math.BigInteger segments, double total) throws java.rmi.RemoteException;
}
