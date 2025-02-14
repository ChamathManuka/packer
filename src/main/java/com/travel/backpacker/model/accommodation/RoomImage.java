package com.travel.backpacker.model.accommodation;

import javax.persistence.*;

@Entity
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomImageId;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @Column(nullable = false)
    private String imageUrl;

    // ... Getters and setters ...

    public Long getRoomImageId() {
        return roomImageId;
    }

    public void setRoomImageId(Long roomImageId) {
        this.roomImageId = roomImageId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}