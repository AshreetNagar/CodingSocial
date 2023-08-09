/*
 * Model which represents the "friend" relationship between two users
 *  
 */
package com.codingSocial.codingSocial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "friends_table")
public class FriendsModel {

    public enum FriendshipStatus{
        OUTGOING, INCOMING, ACCEPTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer userId;
    Integer friendUserId;
    private FriendshipStatus friendshipStatus;
}
