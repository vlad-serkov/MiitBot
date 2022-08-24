package io.proj3ct.miitbot.test;

public interface PartialBotMethodFacade<T> {
    T getInstance();
    String getName();

    String getPath();
}
