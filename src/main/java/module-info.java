module JShockDetector {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.swing;
    requires java.sql;
    requires com.jfoenix;


    requires ignite.common;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.persistence;
    requires spring.context;
    requires spring.core;
    requires spring.jdbc;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.orm;
    requires spring.beans;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires java.desktop;
    requires static lombok;
    requires transitive org.mapstruct.processor;
    //requires java.transaction;

    requires slf4j.api;
    requires jasperreports;
    requires spring.data.commons;

    opens com.spring.utils;
    opens com.spring;
    opens com.spring.config;
    opens com.spring.bean;
    opens com.spring.repository;
    opens com.spring.service;
    opens com.spring.controller;
    opens com.spring.service.impl;




}