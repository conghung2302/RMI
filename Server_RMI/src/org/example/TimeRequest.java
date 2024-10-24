package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TimeRequest extends Remote{
    String sayHello() throws RemoteException;
}
