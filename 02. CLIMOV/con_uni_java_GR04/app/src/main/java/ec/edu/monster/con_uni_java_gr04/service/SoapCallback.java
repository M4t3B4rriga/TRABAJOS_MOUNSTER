package ec.edu.monster.con_uni_java_gr04.service;

public interface SoapCallback<T> {
    void onSuccess(T result);
    void onError(String error);
}