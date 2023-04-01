package com.example.easywheelz.persistance.impl;

import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public void flush() {

    }

    @Override
    public <S extends ReservationEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ReservationEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ReservationEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public ReservationEntity getById(Long aLong) {
        return null;
    }

    @Override
    public ReservationEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ReservationEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ReservationEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ReservationEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ReservationEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ReservationEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<ReservationEntity> findAll() {
        return null;
    }

    @Override
    public List<ReservationEntity> findAllById(Iterable<Long> longs) {
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
    public void delete(ReservationEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ReservationEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ReservationEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ReservationEntity> findAll(Pageable pageable) {
        return null;
    }
}
