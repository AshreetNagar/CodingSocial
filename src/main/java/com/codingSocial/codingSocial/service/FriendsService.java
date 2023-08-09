package com.codingSocial.codingSocial.service;

import com.codingSocial.codingSocial.repository.FriendsRepository;
import com.codingSocial.codingSocial.resource.FriendsRequest;
import com.codingSocial.codingSocial.model.FriendsModel;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class FriendsService {
    private final FriendsRepository FriendsRepository; 

    public List<FriendsModel> getFriends(Integer userId){
        List<FriendsModel> friendsListRepository = FriendsRepository.findByUserId(userId);
        return friendsListRepository;
    }

    public FriendsModel addFriend(Integer userId, Integer friendUserId){
        Optional<FriendsModel> optionalExistingRequest = FriendsRepository.findByUserIdAndFriendUserId(userId,friendUserId);
        if (optionalExistingRequest.isPresent()){
            FriendsModel existingRequest = optionalExistingRequest.get();
            if (existingRequest.getFriendshipStatus() == FriendsModel.FriendshipStatus.OUTGOING){
                return null;
            } 
        }

        Optional<FriendsModel> optionalIncomingRequest = FriendsRepository.findByUserIdAndFriendUserId(friendUserId,userId);
        // If friend did not already send a request, make an outgoing request
        if (optionalIncomingRequest.isEmpty()){
            FriendsModel newFriend = new FriendsModel();
            newFriend.setUserId(userId);
            newFriend.setFriendUserId(friendUserId);
            newFriend.setFriendshipStatus(FriendsModel.FriendshipStatus.OUTGOING);
            FriendsModel outgoingRequest = new FriendsModel();
            outgoingRequest.setUserId(friendUserId);
            outgoingRequest.setFriendUserId(userId);
            outgoingRequest.setFriendshipStatus(FriendsModel.FriendshipStatus.INCOMING);
            FriendsRepository.save(outgoingRequest);
            return FriendsRepository.save(newFriend);
        }
        // If friend already sent a request, accept it
        else{
            FriendsModel incomingRequest = optionalIncomingRequest.get();
            incomingRequest.setFriendshipStatus(FriendsModel.FriendshipStatus.ACCEPTED);
            Optional<FriendsModel> optionalmyRequest = FriendsRepository.findByUserIdAndFriendUserId(userId,friendUserId);
            if(optionalmyRequest.isEmpty()){
                return null;                 //Bad data
            }
            FriendsModel matchingRequest = optionalmyRequest.get();
            matchingRequest.setFriendshipStatus(FriendsModel.FriendshipStatus.ACCEPTED);
            FriendsRepository.save(incomingRequest);
            return FriendsRepository.save(matchingRequest);
        }
    }

    public FriendsModel deleteFriend(Integer userId, Integer friendUserId){
        Optional<FriendsModel> friendEntry = FriendsRepository.findByUserIdAndFriendUserId(userId,friendUserId);
        // If friend entry doesn't exist, return null
        if (friendEntry.isEmpty()){
            return null;
        }
        // If exists, delete it
        else{
            FriendsModel removedFriend = friendEntry.get();
            FriendsRepository.delete(removedFriend);
            Optional<FriendsModel> optionalMatchingFriendEntry = FriendsRepository.findByUserIdAndFriendUserId(friendUserId,userId);
            if(optionalMatchingFriendEntry.isPresent()){
                FriendsModel matchingFriendEntry = optionalMatchingFriendEntry.get();
                FriendsRepository.delete(matchingFriendEntry);
            }
            return removedFriend;
        }
    }

}
