package com.berzenin.university.service;

public interface ModelConverter<M, V> {

    V toView(M model);
}
