package com.cs160.joleary.catnip;

/**
 * Created by dradding on 3/3/16.
 */
public class Person {

    String name;
    String email;
    String website;
    String tweet;
    String party;
    String committee;
    String bill;
    int photoID;

    Person(String name, String email, String website, String tweet, String party, String committee, String bill, int photoId) {
        this.name = name;
        this.email = email;
        this.website = website;
        this.tweet = tweet;
        this.party = party;
        this.committee = committee;
        this.bill = bill;
        this.photoID = photoId;
    }
}