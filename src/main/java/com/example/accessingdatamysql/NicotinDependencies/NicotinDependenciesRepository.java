package com.example.accessingdatamysql.NicotinDependencies;

import org.springframework.data.repository.CrudRepository;

public interface NicotinDependenciesRepository extends CrudRepository<NicotinDependencies, Integer> {
    NicotinDependencies findByUserId(Integer userId);
}
