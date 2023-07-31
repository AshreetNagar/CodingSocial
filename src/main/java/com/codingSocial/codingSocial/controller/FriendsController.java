package com.codingSocial.codingSocial.controller;

import com.codingSocial.codingSocial.model.FriendsModel;
import com.codingSocial.codingSocial.service.FriendsService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


@RestController
@CrossOrigin
@AllArgsConstructor
public class FriendsController {

    private final FriendsService friendsService;

    @GetMapping("/friends")
    public ResponseEntity<?> getFriends(@RequestParam Integer userId){
        List<FriendsModel> friendsList = friendsService.getFriends(userId);
        return ResponseEntity.ok().body(friendsList);
    }

    @PostMapping("/friends")
    public ResponseEntity<?> addFriend(@RequestBody FriendsModel FriendsModel){
        System.out.println("userid: "+FriendsModel.getUserId()+" friendid:"+FriendsModel.getFriendUserId());
        FriendsModel friendsModel = friendsService.addFriend(FriendsModel.getUserId(),FriendsModel.getFriendUserId());
        if (friendsModel == null){
            return ResponseEntity.badRequest().body("Friend entry already exists");
        }
        return ResponseEntity.ok().body(friendsModel);
    }

    @DeleteMapping("/friends")
    public ResponseEntity<?> deleteFriend(@RequestParam Integer userId, @RequestParam Integer friendUserId){
        System.out.println("userid: "+userId+" friendid:"+friendUserId);
        FriendsModel friendsModel = friendsService.deleteFriend(userId,friendUserId);
        if (friendsModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(friendsModel);
    }


    
}
