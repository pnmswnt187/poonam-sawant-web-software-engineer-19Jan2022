package com.api.buyingfrenzy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.buyingfrenzy.entities.Users;
import com.api.buyingfrenzy.vo.UsersVO;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, PagingAndSortingRepository<Users, Long> {

	UsersVO update(Long id, String dishName, String restaurants, UsersVO userVO);
	

}
