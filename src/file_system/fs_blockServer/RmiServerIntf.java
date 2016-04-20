package file_system.fs_blockServer;

import file_system.*;
import sun.security.rsa.RSAPublicKeyImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public interface RmiServerIntf extends Remote {
    ArrayList<Object> get(String id, int RID) throws RemoteException, InterruptedException;
    ArrayList<Object> put_k(PublicKeyBlock data, RSAPublicKeyImpl public_key, int wts) throws RemoteException, NoSuchAlgorithmException, IntegrityViolationException, DifferentTimestampException, InterruptedException;
    ArrayList<Object> put_h(byte[] data, int wts) throws RemoteException, NoSuchAlgorithmException;
    ArrayList<Object> readPublicKeys(int RID) throws RemoteException;
    ArrayList<Object> storePubKey(RSAPublicKeyImpl p, int wts) throws RemoteException, NoSuchAlgorithmException;
    Object serverRequest(String functionName, ArrayList<Object> args) throws RemoteException, InterruptedException, NoSuchAlgorithmException, DifferentTimestampException, IntegrityViolationException ;

}
