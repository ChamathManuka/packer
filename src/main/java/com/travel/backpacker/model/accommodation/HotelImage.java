package com.travel.backpacker.model.accommodation;

import javax.persistence.*;

@Entity
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelImageId;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @Column(nullable = false)
    private String imageUrl;

    // ... Getters and setters ...

    public Long getHotelImageId() {
        return hotelImageId;
    }

    public void setHotelImageId(Long hotelImageId) {
        this.hotelImageId = hotelImageId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}