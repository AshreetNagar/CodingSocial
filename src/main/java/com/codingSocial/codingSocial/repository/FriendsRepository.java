package com.codingSocial.codingSocial.repository;

import com.codingSocial.codingSocial.model.FriendsModel;
import com.codingSocial.codingSocial.model.UsersModel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface FriendsRepository extends JpaRepository <FriendsModel, Integer>{

    Optional<FriendsModel> findByUserIdAndFriendUserId(Integer userId, Integer friendUserId);
    List<FriendsModel> findByUserId(Integer userId);

}
