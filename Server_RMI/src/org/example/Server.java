package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            TimeRequestIml obj = new TimeRequestIml();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", obj);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
