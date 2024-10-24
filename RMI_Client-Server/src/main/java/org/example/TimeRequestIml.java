package org.example;


import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.time.LocalTime;

public class TimeRequestIml extends UnicastRemoteObject implements TimeRequest {
    protected TimeRequestIml() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return LocalTime.now() + "";
    }
}
