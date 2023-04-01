package com.example.easywheelz.persistance.impl;

import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;

@Repository
public class CarRepositoryImpl implements CarRepository {


    @Override
    public void flush() {

    }

    @Override
    public <S extends CarEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CarEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CarEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CarEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public CarEntity getById(Long aLong) {
        return null;
    }

    @Override
    public CarEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CarEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CarEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CarEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CarEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CarEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CarEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CarEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends CarEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CarEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CarEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<CarEntity> findAll() {
        return null;
    }

    @Override
    public List<CarEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CarEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CarEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CarEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CarEntity> findAll(Pageable pageable) {
        return null;
    }
}
