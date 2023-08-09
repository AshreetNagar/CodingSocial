import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import axios from 'axios';

function Friends() {

    const submitGetList = async (event) => {
        // Prevent the browser from reloading the page
        event.preventDefault();
        // Read the form data
        const formData = new FormData(event.target);
        // Submit Req
        try {
          const response = await axios.get(
            `http://localhost:8080/friends` , { params:{userId:formData.get("username")} }
          );
          console.info("==RESPONSE DATA START==")
          console.info(response)
          console.info("==RESPONSE DATA END==")
        } catch (error) {
          console.error(error);
        }
      };

    const submitPutFriend = async (event) => {
        // Prevent the browser from reloading the page
        event.preventDefault();
        // Read the form data
        const formData = new FormData(event.target);
        const addFriendData = {userId:formData.get("username"), friendUserId:formData.get("friend-username")}
        // Submit Req
        try {
          const response = await axios.post(
            `http://localhost:8080/friends` , 
            addFriendData
          );
          console.info("==RESPONSE DATA START==")
          console.info(response)
          console.info("==RESPONSE DATA END==")
        } catch (error) {
          console.error(error);
        }
    };
    const submitDelFriend = async (event) => {
        // Prevent the browser from reloading the page
        event.preventDefault();
        // Read the form data
        const formData = new FormData(event.target);
        const addFriendData = {userId:formData.get("username"), friendUserId:formData.get("friend-username")}
        // Submit Req
        try {
          const response = await axios.delete(
            `http://localhost:8080/friends` , 
            // addFriendData
            { params:{userId:formData.get("username"), friendUserId:formData.get("friend-username")} }
          );
          console.info("==RESPONSE DATA START==")
          console.info(response)
          console.info("==RESPONSE DATA END==")
        } catch (error) {
          console.error(error);
        }
    };

    return (
        <div className="friends-page">
            <h1>Get Friends List for User:</h1>
            <div className="list_friends-form">
                <form method="post" onSubmit={submitGetList}>
                    <label>
                        User to get friends list for user with ID: <input name="username"/>
                    </label>
                    <button type="submit">Submit</button>
                </form>
            </div>

            <h1>Add Friend to User:</h1>
            <div className="list_friends-form">
                <form method="post" onSubmit={submitPutFriend}>
                    <label>
                        User id to edit to friends list for: <input name="username"/>
                    </label>
                    <label>
                        Friend id to add to friends list: <input name="friend-username"/>
                    </label>
                    <button type="submit">Submit</button>
                </form>
            </div>

            <h1>Remove Friend from User:</h1>
            <div className="list_friends-form">
                <form method="post" onSubmit={submitDelFriend}>
                    <label>
                        User id to edit friends list for: <input name="username"/>
                    </label>
                    <label>
                        friend id to remove from friends list: <input name="friend-username"/>
                    </label>
                    <button type="submit">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default Friends;
