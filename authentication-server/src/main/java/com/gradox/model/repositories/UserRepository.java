package com.gradox.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gradox.model.vo.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
