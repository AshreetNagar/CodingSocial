/*
 * A friend request can be:
 * Outgoing Pending
 * Incoming Pending
 * Full Accepted
 */
package com.codingSocial.codingSocial.resource;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FriendsRequest {

    private enum FriendshipStatus{
        OUTGOING, INCOMING, PENDING
    }

    private String userLogin;
    private String friendLogin;
    private FriendshipStatus friendshipStatus;
}
