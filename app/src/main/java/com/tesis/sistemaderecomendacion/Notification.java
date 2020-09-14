package com.tesis.sistemaderecomendacion;

public interface Notification {

    void register(ID id, Listener listener);
    void unregister(ID id, Listener listener);
    <T> void post(ID id, T value);

    interface Listener<T> {
        void onEvent(ID id, T value);
    }

    enum ID {
        GetData,
        GetLogin
    }

}